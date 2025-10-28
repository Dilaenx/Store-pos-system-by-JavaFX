package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudService <T,id> extends SuperService{
    boolean addCustomer(T t) throws SQLException;
    List<T> getAll() throws SQLException;
    T searchCustomerById(id id) throws SQLException;

    boolean deleteByCustomerId(id text) throws SQLException;

    boolean updateCustomerById(T t) throws SQLException;
}
