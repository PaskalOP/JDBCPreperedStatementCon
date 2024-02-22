package org.example.jdbcWork.DtoForQuery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgeTypeDto {
    private String type;
    private String name;
    private LocalDate birthday;
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public AgeTypeDto(String type, String name, LocalDate birthday) {
       this.type = type;
       this.name = name;
       this.birthday =  java.sql.Date.valueOf(birthday).toLocalDate();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
            sb.append(type + " ");
            sb.append(name + " ");
            sb.append(birthday.format(formater) + " ");
        return sb.toString();
    }
}