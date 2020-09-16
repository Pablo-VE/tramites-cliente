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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;
import org.una.tramites.cliente.App;
import org.una.tramites.cliente.dto.RequisitoDTO;
import org.una.tramites.cliente.dto.TramiteTipoDTO;
import org.una.tramites.cliente.dto.VariacionDTO;
import org.una.tramites.cliente.service.VariacionService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.Mensaje;
import org.una.tramites.cliente.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Pablo-VE
 */
public class VariacionesController implements Initializable {

    @FXML
    private AnchorPane apVariaciones;
    @FXML
    private TableView<VariacionDTO> tvVariaciones;
    @FXML
    private Button btnAgregarVariacion;
    VariacionService varService = new VariacionService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TramiteTipoDTO tramite = (TramiteTipoDTO)AppContext.getInstance().get("tramiteTipo");
        tvVariaciones.setRowFactory(tv -> {
            TableRow<VariacionDTO> row = new TableRow();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton()==MouseButton.PRIMARY && event.getClickCount()==1){
                    VariacionDTO var = row.getItem();
		    AppContext.getInstance().set("VariacionSeleccionada", var);
                }
            });
            return row;
        });
        cargarVariaciones(tramite.getId());

    }    

    @FXML
    private void actAgregar(ActionEvent event) throws IOException {
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("ContenedorDisenoTramite");
        AppContext.getInstance().set("ModalidadVariacion", "Agregar");
        
        Parent root = FXMLLoader.load(App.class.getResource("variacionesDetalle" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    public void cargarVariaciones(Long id){
        ArrayList<VariacionDTO> variaciones = new ArrayList<VariacionDTO>();
        Respuesta res = varService.getByTramiteTipo(id);
        variaciones=(ArrayList<VariacionDTO>) res.getResultado("Variaciones");
        llenarTabla(variaciones);
    }
    public void llenarTabla(ArrayList<VariacionDTO> requisitos){
        tvVariaciones.getColumns().clear();
        if(requisitos!=null){
            ObservableList items = FXCollections.observableArrayList(requisitos);   


            TableColumn colId = new TableColumn("ID");
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            TableColumn colDescripcion = new TableColumn("Descripcion");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
            
            TableColumn<VariacionDTO, String> colEstado = new TableColumn("Estado");
            colEstado.setCellValueFactory(per -> {
                String estadoString;
                if(per.getValue().getEstado())
                    estadoString = "Activo";
                else
                    estadoString = "Inactivo";
                return new ReadOnlyStringWrapper(estadoString);
            });
            TableColumn colGrupo = new TableColumn("Grupo");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("grupo"));
            
            TableColumn colVariacion = new TableColumn("Tipo Tramite");
            colDescripcion.setCellValueFactory(new PropertyValueFactory("tramitetipo")); //EEVISAR SI EL NOMBRE ESTA BIEN
            
            tvVariaciones.getColumns().addAll(colId);
            tvVariaciones.getColumns().addAll(colDescripcion);
            tvVariaciones.getColumns().addAll(colEstado);
            tvVariaciones.getColumns().addAll(colVariacion);
            
            addButtonToTable();
            tvVariaciones.setItems(items);
        }
    }
    private void addButtonToTable() {
        TableColumn<VariacionDTO, Void> colBtn = new TableColumn("Acciones");

        Callback<TableColumn<VariacionDTO, Void>, TableCell<VariacionDTO, Void>> cellFactory = new Callback<TableColumn<VariacionDTO, Void>, TableCell<VariacionDTO, Void>>() {
            @Override
            public TableCell<VariacionDTO, Void> call(final TableColumn<VariacionDTO, Void> param) {
                final TableCell<VariacionDTO, Void> cell = new TableCell<VariacionDTO, Void>() {

                    private final Button btn = new Button("Modificar");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            try{
                            VariacionDTO data = getTableView().getItems().get(getIndex());
                            editarVariacion(data);
                            }catch(Exception ex){}
                        });
                    }
                    
                    private final Button btn2 = new Button("Ver");

                    {
                        btn2.setOnAction((ActionEvent event) -> {
                            try{
                            VariacionDTO data = getTableView().getItems().get(getIndex());
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

        tvVariaciones.getColumns().add(colBtn);

    }
    public void  ver(VariacionDTO dep) throws IOException{
        if(dep!=null){
            StackPane Contenedor = (StackPane) AppContext.getInstance().get("ContenedorDisenoTramite");

            AppContext.getInstance().set("ModalidadVariacion", "Ver");
            AppContext.getInstance().set("VariacionVer", dep);

            Parent root = FXMLLoader.load(App.class.getResource("variacionesDetalle" + ".fxml"));
            Contenedor.getChildren().clear();
            Contenedor.getChildren().add(root);
        }else{
            Mensaje.showAndWait(Alert.AlertType.WARNING, "Ver Variacion", "Debes seleccionar una variacion");
        }
    }
    public void editarVariacion(VariacionDTO req) throws IOException{
        StackPane Contenedor = (StackPane) AppContext.getInstance().get("ContenedorDisenoTramite");
        
        AppContext.getInstance().set("ModalidadVariacion", "Editar");
        AppContext.getInstance().set("VariacionEditar", req);
        
        Parent root = FXMLLoader.load(App.class.getResource("variacionesAgregar" + ".fxml"));
        Contenedor.getChildren().clear();
        Contenedor.getChildren().add(root);
    }
    
}
