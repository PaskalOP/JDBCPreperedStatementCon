package org.example.jdbcWork.DtoForQuery;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClientTableDto {
    private long ID;
    private  String name;
    private long project_count;

    public ClientTableDto(long id, String name, long project_count) {
        this.ID = id;
        this.name=name;
        this.project_count = project_count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ID + " ");
        sb.append(name + " ");
        sb.append(project_count + " ");
        return sb.toString();

    }
}
