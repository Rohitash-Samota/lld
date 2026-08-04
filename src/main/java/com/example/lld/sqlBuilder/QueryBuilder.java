package com.example.lld.sqlBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import com.example.lld.sqlBuilder.enums.JoinType;
import com.example.lld.sqlBuilder.enums.SortDirection;

public class QueryBuilder implements SqlQueryBuilder {
    private final List<String> columns = new ArrayList<>();
    private final List<String> conditions = new ArrayList<>();
    private final List<Object> parameters = new ArrayList<>();
    private final List<JoinDTO> joins = new ArrayList<>();
    private String table;
    private String orderBy;
    private Integer limit;

    @Override
    public SqlQueryBuilder select(String... columns) {
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    @Override
    public SqlQueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    @Override
    public SqlQueryBuilder where(String column, String op, Object value) {
        validateColumn(column);
        validateOperator(op);
        conditions.add(column + " " + op + " ?");
        parameters.add(value);
        return this;
    }

    @Override
    public SqlQueryBuilder join(String table, JoinType joinType, String colA, String colB) {
        if (joinType == null) {
            throw new IllegalArgumentException("Join type is required");
        }

        validateColumn(colA);
        validateColumn(colB);
        joins.add(new JoinDTO(joinType, table, colA, colB));
        return this;
    }

    @Override
    public SqlQueryBuilder orderBy(String column, SortDirection direction) {
        validateColumn(column);
        this.orderBy = column + " " + direction.name();
        return this;
    }

    @Override
    public SqlQueryBuilder limit(Integer limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit must be greater than zero\"");
        }
        this.limit = limit;
        return this;
    }

    @Override
    public SqlQuery build() {
        validate();
        StringBuilder query = new StringBuilder("SELECT ");
        query.append(columns.isEmpty() ? "*" : String.join(", ", columns));
        query.append(" From ").append(this.table);

        if (!joins.isEmpty()) {
            joins.forEach(join -> query
                    .append(" ")
                    .append(join.getJoinType().getValue())
                    .append(" ")
                    .append(join.getTable())
                    .append(" ON ")
                    .append(join.getColA())
                    .append(" = ")
                    .append(join.getColB()));
        }

        if (!conditions.isEmpty()) {
            StringJoiner conditionJoiner = new StringJoiner(" AND ");
            conditions.forEach(conditionJoiner::add);
            query.append(" WHERE ").append(conditionJoiner);
        }

        if (orderBy != null) {
            query.append(" ORDER BY ").append(orderBy);
        }

        if (limit != null) {
            query.append(" LIMIT ?");
            parameters.add(limit);
        }

        SqlQuery sqlQuery = new SqlQuery(query.toString(), parameters);
        reset();

        return sqlQuery;

    }

    @Override
    public void reset() {
        columns.clear();
        conditions.clear();
        parameters.clear();
        joins.clear();
        table = null;
        orderBy = null;
        limit = null;
    }

    private void validate() {
        if (table == null || table.isBlank()) {
            throw new IllegalStateException(
                    "Table name is required");
        }
    }

    private void validateOperator(String operator) {
        List<String> allowedOperators = List.of(
                "=",
                "!=",
                ">",
                ">=",
                "<",
                "<=",
                "LIKE");

        if (!allowedOperators.contains(operator.toUpperCase())) {
            throw new IllegalArgumentException(
                    "Unsupported operator: " + operator);
        }
    }

    private void validateIdentifier(String identifier) {
        if (identifier == null ||
                !identifier.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {

            throw new IllegalArgumentException(
                    "Invalid SQL identifier: " + identifier);
        }
    }

    private void validateColumn(String column) {
        if (column == null ||
                !column.matches(
                        "[a-zA-Z_][a-zA-Z0-9_]*(\\.[a-zA-Z_][a-zA-Z0-9_]*)?")) {
            throw new IllegalArgumentException(
                    "Invalid column: " + column);
        }
    }
}
