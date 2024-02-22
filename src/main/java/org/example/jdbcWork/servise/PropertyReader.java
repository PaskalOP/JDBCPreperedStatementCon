package org.example.jdbcWork.servise;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertyReader {
    public static String urlForPostgres(){
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("dataForConnection.properties")){

            Properties properties = new Properties();
            if(input==null){
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            properties.load(input);
            return new StringBuilder("jdbc:postgresql://")
                    .append(properties.getProperty("postgres.db.host"))
                    .append(":")
                    .append(properties.getProperty("postgres.db.port"))
                    .append("/")
                    .append(properties.getProperty("postgres.db.database"))
                    .append("?currentSchema=public")
                    .toString();


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public static String userForPostgres(){
        try(InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("dataForConnection.properties")) {

            Properties properties = new Properties();
            if(input==null){
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            properties.load(input);
            return properties.getProperty("postgres.db.username");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String passwordForPostgres(){
        try(InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("dataForConnection.properties")) {

            Properties properties = new Properties();
            if(input==null){
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }
            properties.load(input);
            return properties.getProperty("postgres.db.password");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public  static String queryReader(String absolutPathFile){
        try {
            String query =  new String(Files.readAllBytes(Paths.get(absolutPathFile)));
            return query;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
