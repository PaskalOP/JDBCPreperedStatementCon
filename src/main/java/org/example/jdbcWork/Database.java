package org.example.jdbcWork;

import org.example.jdbcWork.servise.PropertyReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Database {
    private static final Database INSTANCE = new Database();
    private static Connection connection;
//Конструктор із закритим модифікатором доступу, щоб екземпляр
// створювався лише в середині класу. В конструкторі встновлюємо звʼязок з БД
    private Database(){
        String url = PropertyReader.urlForPostgres();
        String userName = PropertyReader.userForPostgres();
        String password = PropertyReader.passwordForPostgres();

        try {
            connection = DriverManager.getConnection(url,userName,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //метод для виклику конструктора і отримання екземпляру класу
    public static Database getInstance(){
        return INSTANCE;
    }

    // метод для отримання звязку з БД у зовнішній код
    public static Connection getConnection(){
        return connection;
    }
//Метод, який реалізує sql запит, зчитуючи його з sql файлу
    public void execute(String fileName){
        try (Statement statement = connection.createStatement()){
            String query = new String(Files.readAllBytes(Paths.get(fileName)));
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
//Метод, який повертає кількість змінених рядкув у БД
    public int executeUpdate (String query){
        try (Statement statement = connection.createStatement()){
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object> executeResult (String query, List<String> columnsName){
        List<Object>  result = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                for (String column:  columnsName) {
                    //resultSet.getObject(column);
                    result.add(resultSet.getObject(column));

                }
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Database db = Database.getInstance();
        List<String> userCol = new ArrayList();
        userCol.add("id");
        userCol.add("name");
        userCol.add("birthday");
        String query = PropertyReader.queryReader("/Users/mac/IdeaProjects/JDBCHomeWork/src/main/java/org/example/jdbcWork/sql/minSelary.sql");

       List<Object> selected =  db.executeResult(query,userCol );
        for (Object o:selected) {
            System.out.println(o);
        }

    }


}
