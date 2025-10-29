package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CartTM;
import model.Customer;
import model.Item;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ItemService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class OderFormController implements Initializable {

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private ComboBox<String> cmbCustomerID;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTotal;

    @FXML
    private TableColumn colUnitPrice;

    @FXML
    private Label lblCM;

    @FXML
    private Label lblCM1;

    @FXML
    private Label lblCM11;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblCustomerPhoneNumber;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblUnityPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<CartTM> tblOder;

    @FXML
    private JFXTextField txtQtyOnHand;

    CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
    ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.ITEM);
    List<CartTM> cartTMSList = new ArrayList<>();
    Double netTotal= 0.0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        loadCustomerIds();
        loadItemIds();

        cmbCustomerID.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if(t1!=null)setTextCustomerLbl((String) t1);
        });
        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {
            if(t1!=null)setItemCustomerLbl((String) t1);
        });

    }
    private void setTextCustomerLbl(String id){
        Customer customer;
        try {
             customer = customerService.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblCustomerName.setText(customer.getName());
        lblCustomerPhoneNumber.setText(customer.getPhoneNumber()+"");
    }
    private void setItemCustomerLbl(String id){
        Item item = new Item();

        try {
            item = itemService.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblItemName.setText(item.getName());
        lblStock.setText(item.getQty()+"");
        lblUnityPrice.setText(item.getUnitPrice()+"");
    }
    @FXML
    void btnAddOrderOnAction(ActionEvent event) {

    }
    private void loadTable(){
        netTotal=0.0;
        for(CartTM item :cartTMSList){
            netTotal+= item.getTotal();
        }
        tblOder.setItems(FXCollections.observableArrayList(cartTMSList));
        System.out.println(netTotal);
        lblNetTotal.setText(netTotal+"");
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        CartTM cartTM = new CartTM(
                (String) cmbItemId.getValue(),
                lblItemName.getText(),
                Integer.parseInt(lblStock.getText()),
                Double.parseDouble(lblUnityPrice.getText()),
                Double.parseDouble(txtQtyOnHand.getText()),
                (Double.parseDouble(lblUnityPrice.getText())*(Double.parseDouble(txtQtyOnHand.getText())))
        );
        System.out.println(cartTM);
        cartTMSList.add(cartTM);
        loadTable();
        clear();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clear();
    }

    @FXML
    void btnCustomerManagement(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnItemManagementForm(ActionEvent event) {

    }

    @FXML
    void btnOderDetailsOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrdrOnAction(ActionEvent event) {

    }
    private void loadCustomerIds(){

        try {
           cmbCustomerID.setItems(FXCollections.observableArrayList(customerService.getAllIds()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    private void loadItemIds(){

        try {
            cmbItemId.setItems(FXCollections.observableArrayList(itemService.getAllIds()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void clear(){
        cmbItemId.getSelectionModel().clearSelection();
        cmbCustomerID.getSelectionModel().clearSelection();
        lblCustomerName.setText("Customer");
        lblCustomerPhoneNumber.setText("Phone Number");
        lblItemName.setText("Item");
        lblStock.setText("Stock");
        lblUnityPrice.setText("Unity Price");
        txtQtyOnHand.clear();
    }



}
