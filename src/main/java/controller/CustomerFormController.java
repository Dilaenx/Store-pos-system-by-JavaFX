package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import service.ServiceFactory;
import service.custom.CustomerService;
import util.ServiceType;

import java.sql.SQLException;
import java.util.List;

import static util.ServiceType.CUSTOMER;

public class CustomerFormController {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnClear1;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableView<Customer> tblCustomerList;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPhoneNumber;

    @FXML
    private Label lblCM;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPhoneNumber;


    CustomerService customerService= ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {
        Customer customer = new Customer(
            txtId.getText(),
            txtName.getText(),
            Integer.parseInt(txtPhoneNumber.getText()),
            txtAddress.getText()
        );


        if(customerService.addCustomer(customer)){
            new Alert(Alert.AlertType.CONFIRMATION,"Customer Added!").show();
        }

        loadTable();
        Clear();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        Clear();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            if(customerService.deleteByCustomerId(txtId.getText())){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadTable();
        Clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            customerService.updateCustomerById(
                    new Customer(
                            txtId.getText(),
                            txtName.getText(),
                            Integer.parseInt(txtPhoneNumber.getText()),
                            txtAddress.getText()
                    )
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadTable();
    }
    @FXML
    void btnSearchOnAction(ActionEvent actionEvent) {
        Customer customer = null;
        try {
            customer = customerService.searchCustomerById(txtId.getText());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtName.setText(customer.getName());
        txtAddress.setText(customer.getAddress());
        txtPhoneNumber.setText(customer.getPhoneNumber().toString());
        loadTable();

    }

    private void loadTable(){
        List<Customer>getAll;
        try {
            getAll = customerService.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblCustomerList.setItems(FXCollections.observableArrayList(getAll));


    }
    private void Clear(){
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtPhoneNumber.setText("");
    }

}
