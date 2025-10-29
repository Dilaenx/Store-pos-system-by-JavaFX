package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudService <T,id> extends SuperService{
    boolean add(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    T searchById(id id) throws SQLException;

    boolean deleteById(id text) throws SQLException;

    boolean updateById(T t) throws SQLException;
     List<String> getAllIds() throws SQLException;
}
