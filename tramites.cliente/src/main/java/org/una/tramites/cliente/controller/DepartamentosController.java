/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.tramites.cliente.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.DepartamentoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class DepartamentosController implements Initializable {

    @FXML
    private TableView<DepartamentoDTO> tablaDepartamentos;
    @FXML
    private Button botonCerrar;
    @FXML
    private TextField txtBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private ComboBox<String> cbxTipoBusqueda;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    DepartamentoDTO registroClick = new DepartamentoDTO();
    DepartamentoService depService = new DepartamentoService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnBuscar.setDisable(true);
        txtBuscar.setDisable(true);
        ArrayList<String> opciones = new ArrayList<String>();
        opciones.add("Por identificador unico");
        opciones.add("Por nombre");
        opciones.add("Por estado");
        cbxTipoBusqueda.getItems().clear();
        cbxTipoBusqueda.getItems().addAll(opciones);
        cargarDepartamentosTodos();
        
                // TODO
    }    

    public void cargarDepartamentosTodos(){
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getAll();
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);

    }
    
    public void cargarDepartamentosEstado(String esta){
        boolean estado=true;
        if(esta.equals("activo")||esta.equals("Activo")){
            estado = true;
        }else{
            if(esta.equals("inactivo")||esta.equals("Inactivo")){
                estado = false;
            }
        }
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getDepartamentoByEstado(estado);
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);
    }
    
    public void cargarDepartamentosNombre(String nombre){
        
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getDepartamentoByNombre(nombre);
        departamentos=(ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        llenarTabla(departamentos);
    }
    
    public void cargarDepartamentosId(Long id){
        ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();
        Respuesta res = depService.getById(id);
        DepartamentoDTO depa = (DepartamentoDTO) res.getResultado("Departamento");
        departamentos.add(depa);
        llenarTabla(departamentos);
    }
    
    public void llenarTabla(ArrayList<DepartamentoDTO> departamentos){
        tablaDepartamentos.getColumns().clear();
        if(departamentos!=null){
            ObservableList items = FXCollections.observableArrayList(departamentos);   


            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colNombre = new TableColumn("Nombre");
            colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
            TableColumn<DepartamentoDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(per -> {
                String estadoString;
                if(per.getValue().getEstado())
                    estadoString = "Activo";
                else
                    estadoString = "Inactivo";
                return new ReadOnlyStringWrapper(estadoString);
            });

            tablaDepartamentos.getColumns().addAll(colId);
            tablaDepartamentos.getColumns().addAll(colNombre);
            tablaDepartamentos.getColumns().addAll(colEstado);
            addButtonToTable();
            tablaDepartamentos.setItems(items);
        }
    }
    public void  ver(DepartamentoDTO dep) throws IOException{
        if(dep!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadDepartamentos", "Ver");
            AppContext.getInstance().set("DepartamentoVer", dep);

            Parent root = FXMLLoader.load(App.class.getResource("departamentosDetalleInformacion" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Departamento", "Debes seleccionar un departamento");
        }
    }
    private void addButtonToTable() {
        TableColumn<DepartamentoDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<DepartamentoDTO, Void>, TableCell<DepartamentoDTO, Void>> cellFactory = new Callback<TableColumn<DepartamentoDTO, Void>, TableCell<DepartamentoDTO, Void>>() {
            @Override
            public TableCell<DepartamentoDTO, Void> call(final TableColumn<DepartamentoDTO, Void> param) {
                final TableCell<DepartamentoDTO, Void> cell = new TableCell<DepartamentoDTO, Void>() {

                    private final Button btn = new Button("Modificar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            DepartamentoDTO data = getTableView().getItems().get(getIndex());
                            editarDepartamento(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            DepartamentoDTO data = getTableView().getItems().get(getIndex());
                            ver(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    HBox pane = new HBox(btn, btn2);

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                            
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tablaDepartamentos.getColumns().add(colBtn);

    }
    
    
    @FXML
    private void actBuscar(ActionEvent event) {
        if(!txtBuscar.getText().isEmpty()){
            if(tipoBusqueda.equals("id")){
                cargarDepartamentosId(Long.valueOf(txtBuscar.getText()));
            }else{
                if(tipoBusqueda.equals("nombre")){
                    cargarDepartamentosNombre(txtBuscar.getText());
                }else{
                    if(tipoBusqueda.equals("estado")){
                        cargarDepartamentosEstado(txtBuscar.getText());
                    }
                }
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Busqueda", "Ingresa el valor a buscar");
        }
    }

    @FXML
    private void actBorrar(ActionEvent event) {
        txtBuscar.setText("");
        cargarDepartamentosTodos();
        
    }

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadDepartamentos", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("departamentosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }

    
    public void editarDepartamento(DepartamentoDTO depa) throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadDepartamentos", "Editar");
        AppContext.getInstance().set("DepartamentoEditar", depa);
        
        Parent root = FXMLLoader.load(App.class.getResource("departamentosDetalleInformacion" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    
    private String tipoBusqueda;
    @FXML
    private void actTipoBusqueda(ActionEvent event) {
        txtBuscar.setDisable(false);
        btnBuscar.setDisable(false);
        if(cbxTipoBusqueda.getValue().equals("Por identificador unico")){
            tipoBusqueda="id";
        }else{
            if(cbxTipoBusqueda.getValue().equals("Por nombre")){
                tipoBusqueda="nombre";
            }else if(cbxTipoBusqueda.getValue().equals("Por estado")){
                tipoBusqueda="estado";
            }
        }
    }

    @FXML
    private void actCerrar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        Contenedor.getChildren().clear();
    }


}
