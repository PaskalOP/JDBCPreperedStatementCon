package org.example.jdbcWork.servise;

import org.example.jdbcWork.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

public class InsertQuery {
    Connection connection;
    PaternsForPreperedStatement paterns;
    public InsertQuery(){
        this.connection = Database.getConnection();
        paterns = new PaternsForPreperedStatement(connection);
    }

    public int insertIntoClient(String name){
        try {
            paterns.getInsertIntoClient().setString(1,name);
            int res1 = paterns.getInsertIntoClient().executeUpdate();
            int res2 = paterns.getUpdateProjectCount().executeUpdate();
            return res1+res2;
        } catch (SQLException e) {
            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
        }
        return -1;
    }

    public int insertIntoProject(long clientId, LocalDate startDate, LocalDate finishDate){
        try {
            paterns.getInsertIntoProject().setLong(1, clientId);
            paterns.getInsertIntoProject().setDate(2,java.sql.Date.valueOf(startDate));
            paterns.getInsertIntoProject().setDate(3, java.sql.Date.valueOf(finishDate));
            int res1 = paterns.getInsertIntoProject().executeUpdate();
            int res2 = paterns.getUpdateProjectCount().executeUpdate();
            int res3  = paterns.getUpdateCountMonth().executeUpdate();
            return res1+res2+res3;
        } catch (SQLException e) {
            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
        }
        return -1;
    }

    public  int insertIntoWorker(String name, LocalDate birthday, String level, int selary){
        try {
            paterns.getInsertIntoWorker().setString(1, name);
            paterns.getInsertIntoWorker().setDate(2,java.sql.Date.valueOf(birthday));
            paterns.getInsertIntoWorker().setObject(3, level, Types.OTHER);
            paterns.getInsertIntoWorker().setInt(4, selary);
            return paterns.getInsertIntoWorker().executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
        }
        return -1;
    }
    public int insertIntoProjectWorker(long projectID, long workerId ){
        try {
            paterns.getInsertIntoProjectWorcer().setLong(1, projectID);
            paterns.getInsertIntoProjectWorcer().setLong(2,workerId);

            return paterns.getInsertIntoProjectWorcer().executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail simple insert statement. Reason: " + e.getMessage());
        }
        return -1;
    }

    public static void main(String[] args) {
        InsertQuery insertQuery = new InsertQuery();
        insertQuery.insertIntoClient("Велерий Яковлевич");
        insertQuery.insertIntoProject(37,LocalDate.of(2024,02,28), LocalDate.of(2026,03,01));
        insertQuery.insertIntoWorker("Санта",LocalDate.of(2000,03,28),"Middle",3000);
        insertQuery.insertIntoProjectWorker(5,21);

    }
}
