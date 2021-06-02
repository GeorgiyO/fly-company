package org.example.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;

@SuppressWarnings("all")
public class DefaultRepository<T> {

    protected final JdbcTemplate jdbc;
    protected final RowMapper<T> mapper;
    protected final Function<T, Object[]> toArgs;

    final String table;
    final String sqlGet;
    final String sqlAdd;
    final String sqlAll;
    final String sqlRemove;

    protected T one(String sql, Object... args) {
        var res = jdbc.query(sql, mapper, args);
        return res.size() == 0 ? null : res.get(0);
    }

    public DefaultRepository(JdbcTemplate jdbc, String table, RowMapper<T> mapper, Function<T, Object[]> toArgs, int addArgsCount) {
        this.jdbc = jdbc;
        this.table = table;
        this.mapper = mapper;
        this.toArgs = toArgs;

        var addArgs = "?";
        for (int i = 1; i < addArgsCount; i++) {
            addArgs += ",?";
        }

        sqlAll = format("call %s_all()", table);
        sqlGet = format("call %s_get(?)", table);
        sqlAdd = format("call %s_add(%s)", table, addArgs);
        sqlRemove = format("call %s_remove(?)", table);
    }

    public T get(int id) {
        var res = one(sqlGet, id);
        if (res == null) throw new NotFoundException(table + " with id " + id + " not found");
        return res;
    }

    public List<T> all() {
        return jdbc.query(sqlAll, mapper);
    }

    public T add(T entity) {
        return one(sqlAdd, toArgs.apply(entity));
    }

    public void remove(int id) {
        jdbc.update(sqlRemove, id);
    }

}
