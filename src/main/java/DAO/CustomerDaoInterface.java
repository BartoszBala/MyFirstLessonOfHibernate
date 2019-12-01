package DAO;

import java.io.Serializable;
import java.util.List;

public interface CustomerDaoInterface<T, A, Id extends Serializable> {
    public void persist(T entity, A entityAddress);    // create
    public void update(T entity, A entityAddress);
    public T findById(Id id);
    public void delete(T entity);
    public List<T> findAll();
    public void deleteAll();


}
