package service;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudService <T,String> extends SuperService{
    boolean addCustomer(T customer) throws SQLException;
    List<T> getAll() throws SQLException;
    T searchCustomerById(String id) throws SQLException;

    boolean deleteByCustomerId(String text) throws SQLException;
}
