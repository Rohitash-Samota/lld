package com.example.lld.sqlBuilder;

import com.example.lld.sqlBuilder.enums.JoinType;
import com.example.lld.sqlBuilder.enums.SortDirection;

public interface SqlQueryBuilder {
    SqlQueryBuilder select(String... columns);

    SqlQueryBuilder from(String table);

    SqlQueryBuilder join(String table, JoinType joinType, String colA, String colB);

    SqlQueryBuilder where(String col, String op, Object value);

    SqlQueryBuilder orderBy(String col, SortDirection direction);

    SqlQueryBuilder limit(Integer limit);

    SqlQuery build();

    void reset();
}