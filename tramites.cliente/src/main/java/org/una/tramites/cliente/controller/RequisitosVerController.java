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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.RequisitoDTO;
import org.una.tramites.cliente.service.RequisitoService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class RequisitosVerController implements Initializable {

    @FXML
    private TableView<RequisitoDTO> tbRequisitos;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    RequisitoService reqService = new RequisitoService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarRequisitos();
    }    

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        AppContext.getInstance().set("ModalidadParametros", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("requisitosAgregar" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    public void cargarRequisitos(){
        ArrayList<RequisitoDTO> requisitos = new ArrayList<RequisitoDTO>();
        //Respuesta res = reqService.getByVariacion();
        //requisitos=(ArrayList<RequisitoDTO>) res.getResultado("Requisitos");
        llenarTabla(requisitos);
    }
    public void llenarTabla(ArrayList<RequisitoDTO> requisitos){
        tbRequisitos.getColumns().clear();
        if(requisitos!=null){
            ObservableList items = FXCollections.observableArrayList(requisitos);   


            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
            TableColumn<RequisitoDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(per -> {
                String estadoString;
                if(per.getValue().isEstado())
                    estadoString = "Activo";
                else
                    estadoString = "Inactivo";
                return new ReadOnlyStringWrapper(estadoString);
            });
            
            TableColumn colVariacion = new TableColumn("Variacion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("variacion")); //EEVISAR SI EL NOMBRE ESTA BIEN
            
            tbRequisitos.getColumns().addAll(colId);
            tbRequisitos.getColumns().addAll(colDescripcion);
            tbRequisitos.getColumns().addAll(colEstado);
            tbRequisitos.getColumns().addAll(colVariacion);
            
            addButtonToTable();
            tbRequisitos.setItems(items);
        }
    }
    private void addButtonToTable() {
        TableColumn<RequisitoDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<RequisitoDTO, Void>, TableCell<RequisitoDTO, Void>> cellFactory = new Callback<TableColumn<RequisitoDTO, Void>, TableCell<RequisitoDTO, Void>>() {
            @Override
            public TableCell<RequisitoDTO, Void> call(final TableColumn<RequisitoDTO, Void> param) {
                final TableCell<RequisitoDTO, Void> cell = new TableCell<RequisitoDTO, Void>() {

                    private final Button btn = new Button("Modificar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            RequisitoDTO data = getTableView().getItems().get(getIndex());
                            editarRequisito(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            RequisitoDTO data = getTableView().getItems().get(getIndex());
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

        tbRequisitos.getColumns().add(colBtn);

    }
    public void  ver(RequisitoDTO dep) throws IOException{
        if(dep!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");

            AppContext.getInstance().set("ModalidadRequisitos", "Ver");
            AppContext.getInstance().set("RequisitoVer", dep);

            Parent root = FXMLLoader.load(App.class.getResource("requisitosAgregar" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Requisito", "Debes seleccionar un requisito");
        }
    }
    public void editarRequisito(RequisitoDTO req) throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("Contenedor");
        
        AppContext.getInstance().set("ModalidadRequisitos", "Editar");
        AppContext.getInstance().set("RequisitoEditar", req);
        
        Parent root = FXMLLoader.load(App.class.getResource("requisitosAgregar" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
}
