package service.custom.impl;

import model.Customer;
import repository.RepositoryFactory;
import repository.custom.CustomerRepository;
import service.custom.CustomerService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    CustomerRepository repository= RepositoryFactory.getInstance().getRepository(RepositoryType.CUSTOMER);
    @Override
    public boolean addCustomer(Customer customer) throws SQLException {
        return repository.save(customer);

    }



    @Override
    public List<Customer> getAll() throws SQLException {
        return repository.getAll();

    }

    @Override
    public Customer searchCustomerById(String id) throws SQLException {
        return repository.searchById(id);
    }

    @Override
    public boolean deleteByCustomerId(String text) throws SQLException {
        return repository.deleteByCustomerId(text);
    }

}
