package org.example.jdbcWork.DtoForQuery;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ProjectTableDto {
    private long id;
    private  long CLIENT_ID;
    private LocalDate start_date;
    private LocalDate finish_Date;
    private long MONTH_COUNT;
    private DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy/MM/dd");


    public ProjectTableDto(long id, long CLIENT_ID, LocalDate start_date, LocalDate finish_Date, long MONTH_COUNT) {
        this.id = id;
        this.CLIENT_ID = CLIENT_ID;
        this.start_date = start_date;
        this.finish_Date = finish_Date;
        this.MONTH_COUNT = MONTH_COUNT;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + " ");
        sb.append(CLIENT_ID + " ");
        sb.append(start_date.format(formater) + " ");
        sb.append(finish_Date.format(formater) + " ");
        sb.append(MONTH_COUNT + " ");
        return sb.toString();

    }
}
