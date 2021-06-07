package org.example.repository;

import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.StringJoiner;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.lang.String.format;

@SuppressWarnings("all")
@Log4j2
public class DefaultRepository<T> {

    protected String table;
    protected JdbcTemplate jdbc;
    protected RowMapper<T> mapper;
    protected Function<T, Object[]> toAddArgs;
    protected BiFunction<Integer, T, Object[]> toUpdateArgs;

    private String sqlAll;
    private String sqlGet;
    private String sqlAdd;
    private String sqlUpdate;
    private String sqlRemove;

    protected T one(String sql, Object... args) {
        var res = jdbc.query(sql, mapper, args);
        return res.size() == 0 ? null : res.get(0);
    }

    public DefaultRepository(JdbcTemplate jdbc, String table) {
        this.jdbc = jdbc;
        this.table = table;

        sqlAll = format("call %s_all()", table);
        sqlGet = format("call %s_get(?)", table);
        sqlRemove = format("call %s_delete(?)", table);
    }

    protected void setMapper(RowMapper<T> mapper) {
        this.mapper = mapper;
    }

    protected void setAddProps(Function<T, Object[]> toAddArgs, int argsCount) {
        var addArgs = new StringJoiner(",");
        for (int i = 0; i < argsCount; i++) {
            addArgs.add("?");
        }
        sqlAdd = format("call %s_add(%s)", table, addArgs.toString());
        this.toAddArgs = toAddArgs;
    }

    protected void setUpdateProps(BiFunction<Integer, T, Object[]> toUpdateArgs, int argsCount) {
        var updateArgs = new StringJoiner(",");
        for (int i = 0; i < argsCount; i++) {
            updateArgs.add("?");
        }
        sqlUpdate = format("call %s_update(%s)", table, updateArgs);
        this.toUpdateArgs = toUpdateArgs;
    }

    public List<T> all() {
        return jdbc.query(sqlAll, mapper);
    }

    public T get(int id) {
        var res = one(sqlGet, id);
        if (res == null) throw new NotFoundException(table + " with id " + id + " not found");
        return res;
    }

    public T add(T entity) {
        log.info(table + " add " + entity);
        return one(sqlAdd, toAddArgs.apply(entity));
    }

    public T update(int id, T entity) {
        log.info(table + " update " + id + "; " + entity);
        return one(sqlUpdate, toUpdateArgs.apply(id, entity));
    }

    public void remove(int id) {
        log.info(table + " remove " + id);
        jdbc.update(sqlRemove, id);
    }

}
