package org.example.repository;

import javax.transaction.Transactional;
import java.util.List;

public interface DefaultRepository<T> {
    T get(int id);
    List<T> getAll();
    void delete(int id);
    T add(T instance);
    T update(T instance, int id);
}
