package org.example.jdbcWork.servise;

import org.example.jdbcWork.Database;
import org.example.jdbcWork.DtoForQuery.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SelectQuery {
    PaternsForPreperedStatement paterns;


    public SelectQuery() {
       Connection connection = Database.getConnection();
       this.paterns = new PaternsForPreperedStatement(connection);
    }
    public List<WorkerTableDto> findMinSelary(){
        List<WorkerTableDto> selectResult = new ArrayList<>();
        try {
            ResultSet resultSet = paterns.getSelectMinSelary().executeQuery();
            while (resultSet.next()){
                WorkerTableDto worker = new WorkerTableDto(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        LocalDate.parse(resultSet.getString("birthday")),
                        resultSet.getObject("level").toString(),
                        resultSet.getInt("selary"));
                selectResult.add(worker);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectResult ;
    }

    public List<ProjectTableDto> findMaxMonth(){
        List<ProjectTableDto> selectResult = new ArrayList<>();
        try {
            ResultSet resultSet = paterns.getSelectMaxMounthCount().executeQuery();
            while (resultSet.next()){
                ProjectTableDto projects = new ProjectTableDto(resultSet.getLong("id"),
                        resultSet.getLong("CLIENT_ID"),
                        LocalDate.parse(resultSet.getString("start_date")),
                        LocalDate.parse(resultSet.getString("finish_Date")),
                        resultSet.getLong("MONTH_COUNT"));
                selectResult.add(projects );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectResult ;
    }

    public List<ClientTableDto> selectMaxProject(){
        List<ClientTableDto> selectResult = new ArrayList<>();
        try {
            ResultSet resultSet = paterns.getSelectMaxProjectCount().executeQuery();
            while (resultSet.next()){
                ClientTableDto client = new ClientTableDto(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("project_count"));
                selectResult.add(client );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectResult ;
    }

    public List<PriseTableDto> prisesOfProjects(){
        List<PriseTableDto> selectResult = new ArrayList<>();
        try {
            ResultSet resultSet = paterns.getSelectPriseOfProject().executeQuery();
            while (resultSet.next()){
                PriseTableDto prise = new PriseTableDto(resultSet.getLong("project_id"),
                        resultSet.getBigDecimal("price"));
                selectResult.add(prise);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectResult ;
    }

    public  List<AgeTypeDto> selectByAgeType(){
        List<AgeTypeDto> selectResult = new ArrayList<>();
        try {
            ResultSet resultSet = paterns.getSelectYoungestOldest().executeQuery();
            while (resultSet.next()){
                AgeTypeDto ageType = new AgeTypeDto(resultSet.getString("type"),
                        resultSet.getString("name"),
                        LocalDate.parse(resultSet.getString("birthday")));
                selectResult.add(ageType);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectResult ;
    }

    public static void main(String[] args) {
        SelectQuery sq = new SelectQuery();
        System.out.println(sq.findMinSelary());

        System.out.println(sq.findMaxMonth());
        System.out.println(sq.selectMaxProject());
        System.out.println(sq.prisesOfProjects());
        System.out.println(sq.selectByAgeType());
    }

}
