package repository;

import repository.custom.impl.CustomerRepositoryImpl;
import util.RepositoryType;

public class RepositoryFactory {
    private static RepositoryFactory instance;
    private RepositoryFactory(){}

    public static RepositoryFactory getInstance(){
        return instance==null?instance=new RepositoryFactory():instance;
    }

    public <T extends SuperRepository>T getRepository(RepositoryType type){
        switch (type){
            case CUSTOMER:return (T) new CustomerRepositoryImpl();

        }
        return null;
    }
}
