package org.example.jdbcWork.servise;

import lombok.Data;
import org.example.jdbcWork.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaternsForPreperedStatement {
    private Connection connection;
    private final static String INSERT_INTO_CLIENT = "insert into client(name) values(?)";
    private final static String INSERT_INTO_CLIENT_PROJECT_COUNT = "update client set project_count = (\n" +
            "\tselect count(*) from project where project.CLIENT_ID = client.ID);";

    private final static String INSERT_INTO_PROJECT = "insert into project(CLIENT_ID, START_DATE,finish_date) values(?,?,?)";
    private final static String ADD_COUNT_MONTH = "update project\n" +
            "set MONTH_COUNT = extract (month from age(FINISH_DATE,START_DATE))+ extract(year from age(FINISH_DATE,START_DATE))*12;\n";
    private final static String INSERT_INTO_WORKER= "insert into worker(name,birthday,level,selary) values(?,?,?,?)";
    private final static String INSERT_INTO_PROJECT_WORKER= "insert into project_worker(PROJECT_ID,WORKER_ID) values(?,?)";
    private final static String FIND_MAX_MONTH_COUNT= "select * from project p\n" +
            "where p.month_count  = (select max (p.month_count) from project p )";
    private final static String FIND_MAX_PROJECT_COUNT= "select * from client c\n" +
            "where c.project_count = (select max(c.project_count) from client c )";
    private final static String FIND_MIN_SELARY= "select * from worker w\n" +
            "where w.selary  = (select min (w.selary) from worker w)";
    private final static String FIND_PRISE_OF_PROJECT= "SELECT\n" +
            "    project.id AS project_id,\n" +
            "    SUM(worker.selary * project.MONTH_COUNT) AS price\n" +
            "FROM\n" +
            "    project, project_worker, worker\n" +
            "WHERE\n" +
            "    project.id = project_worker.project_id\n" +
            "    AND project_worker.worker_id = worker.id\n" +
            "GROUP BY\n" +
            "    project.id\n" +
            "ORDER BY\n" +
            "    price DESC";
    private final static String FIND_YOUNGEST_OLDEST= "SELECT\n" +
            "    CASE\n" +
            "        WHEN birthday = youngest_birthday THEN 'YOUNGEST'\n" +
            "        WHEN birthday = oldest_birthday THEN 'OLDEST'\n" +
            "    END AS TYPE,\n" +
            "    name,\n" +
            "    birthday\n" +
            "FROM\n" +
            "    worker,\n" +
            "    (\n" +
            "        SELECT\n" +
            "            MIN(birthday) as youngest_birthday,\n" +
            "            MAX(birthday) as oldest_birthday\n" +
            "        FROM\n" +
            "            worker\n" +
            "    ) AS type_age\n" +
            "WHERE\n" +
            "    birthday = youngest_birthday OR birthday = oldest_birthday";

    private PreparedStatement insertIntoClient;
    private PreparedStatement updateProjectCount;
    private PreparedStatement updateCountMonth;
    private PreparedStatement insertIntoWorker;
    private PreparedStatement insertIntoProject;
    private PreparedStatement insertIntoProjectWorcer;
    private PreparedStatement selectMaxMounthCount;
    private PreparedStatement selectMaxProjectCount;
    private PreparedStatement selectMinSelary;
    private PreparedStatement selectPriseOfProject;
    private PreparedStatement selectYoungestOldest;


    public PreparedStatement getInsertIntoClient() {
        return insertIntoClient;
    }

    public PreparedStatement getUpdateProjectCount() {
        return updateProjectCount;
    }

    public PreparedStatement getInsertIntoWorker() {
        return insertIntoWorker;
    }

    public PreparedStatement getInsertIntoProject() {
        return insertIntoProject;
    }

    public PreparedStatement getInsertIntoProjectWorcer() {
        return insertIntoProjectWorcer;
    }

    public PreparedStatement getSelectMaxMounthCount() {
        return selectMaxMounthCount;
    }

    public PreparedStatement getSelectMaxProjectCount() {
        return selectMaxProjectCount;
    }

    public PreparedStatement getSelectMinSelary() {
        return selectMinSelary;
    }

    public PreparedStatement getSelectPriseOfProject() {
        return selectPriseOfProject;
    }

    public PreparedStatement getSelectYoungestOldest() {
        return selectYoungestOldest;
    }

    public PreparedStatement getUpdateCountMonth() {
        return updateCountMonth;
    }

    public PaternsForPreperedStatement(Connection connection){
        this.connection = connection;
        try {
            this.insertIntoClient= connection.prepareStatement(INSERT_INTO_CLIENT);
            this.updateProjectCount = connection.prepareStatement(INSERT_INTO_CLIENT_PROJECT_COUNT);
            this.updateCountMonth = connection.prepareStatement(ADD_COUNT_MONTH);
            this.insertIntoWorker= connection.prepareStatement(INSERT_INTO_WORKER);
            this.insertIntoProject= connection.prepareStatement(INSERT_INTO_PROJECT);
            this.insertIntoProjectWorcer= connection.prepareStatement(INSERT_INTO_PROJECT_WORKER);
            this.selectMaxMounthCount= connection.prepareStatement(FIND_MAX_MONTH_COUNT);
            this.selectMaxProjectCount= connection.prepareStatement(FIND_MAX_PROJECT_COUNT);
            this.selectMinSelary= connection.prepareStatement(FIND_MIN_SELARY);
            this.selectPriseOfProject= connection.prepareStatement(FIND_PRISE_OF_PROJECT);
            this.selectYoungestOldest= connection.prepareStatement(FIND_YOUNGEST_OLDEST);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    public int insertIntoClient(String name){
//        try {
//            insertIntoClient.setString(1,name);
//            int res1 = insertIntoClient.executeUpdate();
//            int res2 = updateProjectCount.executeUpdate();
//            return res1+res2;
//        } catch (SQLException e) {
//            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
//        }
//        return -1;
//
//    }

//    public static void main(String[] args) {
//        PaternsForPreperedStatement paterns = new PaternsForPreperedStatement(Database.getConnection());
//        paterns.insertIntoClient("Давід Пінхосович");
//        paterns.insertIntoClient("Ірина Костянтинівна");
//        paterns.insertIntoClient("Вячеслав Ігнатєвич");
//    }


}
