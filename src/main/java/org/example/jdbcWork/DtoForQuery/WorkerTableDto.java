package org.example.jdbcWork.DtoForQuery;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
public class WorkerTableDto {
    private long id;
    private String name;
    private LocalDate birthday;
    private String level;
    private int selary;
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    public WorkerTableDto(long id, String name, LocalDate birthday, String level, int selary) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.level = level;
        this.selary = selary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + " ");
        sb.append(name + " ");
        sb.append(birthday.format(formater) + " ");
        sb.append(level + " ");
        sb.append(selary+ " ");
        return sb.toString();

    }

}
