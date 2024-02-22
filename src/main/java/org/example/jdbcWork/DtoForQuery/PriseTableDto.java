package org.example.jdbcWork.DtoForQuery;

import java.math.BigDecimal;
import java.util.List;

public class PriseTableDto {
    private long project_id;
    private BigDecimal price;

    public PriseTableDto (long project_id, BigDecimal price) {
        this.project_id = project_id;
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(project_id + " ");
        sb.append(price + " ");
        return sb.toString();
    }
}
