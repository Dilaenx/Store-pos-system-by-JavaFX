package util;

import db.DBConnection;
import model.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CrudUtil {

    CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    public static <T> T execute(String sql,Object... args) throws SQLException {
        PreparedStatement psTM = DBConnection.getInstance().getConnection().prepareStatement(sql);

        for(int i=0;i<args.length;i++){
            psTM.setObject((i+1),args[i]);
            System.out.println(args[i]);
        }

        if(sql.startsWith("SELECT")||sql.startsWith("select")){
            return (T) psTM.executeQuery();
        }

        return (T) (Boolean) (psTM.executeUpdate()>0);
    }

}
