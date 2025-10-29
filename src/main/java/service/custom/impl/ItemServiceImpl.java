package service.custom.impl;

import model.Item;
import repository.RepositoryFactory;
import repository.custom.ItemRepository;
import service.custom.ItemService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    ItemRepository repository = RepositoryFactory.getInstance().getRepository(RepositoryType.ITEM);

    @Override
    public boolean add(Item item) throws SQLException {
       return repository.save(item);
    }

    @Override
    public List<Item> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public Item searchById(String id) throws SQLException {
        return repository.searchById(id);
    }

    @Override
    public boolean deleteById(String id) throws SQLException {
        return repository.deleteById(id);
    }

    @Override
    public boolean updateById(Item item) throws SQLException {
        return repository.updateById(item);
    }

    @Override
    public List<String> getAllIds() throws SQLException {
        return repository.getAllIds();
    }
}
