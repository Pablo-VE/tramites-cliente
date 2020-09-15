package org.una.tramites.cliente.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import java.util.Date;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.una.tramites.cliente.dto.DepartamentoDTO;
import org.una.tramites.cliente.dto.UsuarioDTO;
import org.una.tramites.cliente.service.DepartamentoService;
import org.una.tramites.cliente.service.UsuarioService;
import org.una.tramites.cliente.util.AppContext;
import org.una.tramites.cliente.util.AutoCompleteComboBoxListener;
import org.una.tramites.cliente.util.Respuesta;
import org.una.tramites.cliente.util.Mensaje;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosInformacionController implements Initializable {


    /**
     * Initializes the controller class.
     */
    
    private String modalidad;
    UsuarioDTO usuario = new UsuarioDTO();
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtCedula;
    @FXML
    private ComboBox<String> cbxDepartamento;
    @FXML
    private CheckBox rbJefeSi;
    @FXML
    private CheckBox rbJefeNo;
    @FXML
    private CheckBox rbEstadoActivo;
    @FXML
    private CheckBox rbEstadoInactivo;
    
    UsuarioService usuService = new UsuarioService();
    
    DepartamentoService depService = new DepartamentoService();
    ArrayList<DepartamentoDTO> departamentos = new ArrayList<>();
    @FXML
    private Button btnGuardar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        estado=true;
        jefe=false;
        rbJefeNo.setSelected(true);
        rbJefeSi.setSelected(false);
        rbEstadoActivo.setSelected(true);
        rbEstadoInactivo.setSelected(false);
        
        modalidad = (String) AppContext.getInstance().get("ModalidadUsuarios");
        
        Respuesta res = depService.getDepartamentoByEstado(Boolean.TRUE);
     
        departamentos = (ArrayList<DepartamentoDTO>) res.getResultado("Departamentos");
        for(int i=0; i<departamentos.size(); i++){
            cbxDepartamento.getItems().add(departamentos.get(i).toString());
        }
        
       // cbxDepartamento.getItems().addAll(departamentos);
        new AutoCompleteComboBoxListener<>(cbxDepartamento); 
        
        
        
        if(modalidad.equals("Ver")||modalidad.equals("Modificar")){
            usuario = (UsuarioDTO) AppContext.getInstance().get("UsuarioEnCuestion");
            txtNombre.setText(usuario.getNombreCompleto());
            txtCedula.setText(usuario.getCedula());
            iniciarEstado(usuario);
            iniciarjefe(usuario);
            if(usuario.getDepartamento()!=null){
                cbxDepartamento.setValue(usuario.getDepartamento().toString());
            }
            if(modalidad.equals("Ver")){
                btnGuardar.setVisible(false);
                btnGuardar.setDisable(true);
                txtNombre.setDisable(true);
                txtCedula.setDisable(true);
                cbxDepartamento.setDisable(true);
                rbJefeSi.setDisable(true);
                rbJefeNo.setDisable(true);
                rbEstadoActivo.setDisable(true);
                rbEstadoInactivo.setDisable(true);
            }
        }else{
            if(modalidad.equals("Agregar")){
                usuario = new UsuarioDTO();
                usuario= (UsuarioDTO) AppContext.getInstance().get("UsuarioNuevo");
                if(usuario.getCedula()!=null){
                    txtNombre.setText(usuario.getNombreCompleto());
                    txtCedula.setText(usuario.getCedula());
                    iniciarEstado(usuario);
                    iniciarjefe(usuario);
                }
                
            }
        }
        
        // TODO
    }  
    
    public void iniciarEstado(UsuarioDTO usu){
        if(usu.getEstado()){
            estado=true;
            rbEstadoActivo.setSelected(true);
            rbEstadoInactivo.setSelected(false);
        }else{
            estado=false;
            rbEstadoActivo.setSelected(false);
            rbEstadoInactivo.setSelected(true);
        }
    }
    
    public void iniciarjefe(UsuarioDTO usu){
        if(usu.getEstado()){
            jefe=true;
            rbJefeSi.setSelected(true);
            rbJefeNo.setSelected(false);
        }else{
            jefe=false;
            rbJefeSi.setSelected(false);
            rbJefeNo.setSelected(true);
        }
    }

    
    private boolean jefe;
    private boolean estado;
    DepartamentoDTO depa = null;
    @FXML
    private void actDepartamento(ActionEvent event) {
        String[] i=  cbxDepartamento.getValue().toString().split("-");
        Long id = Long.valueOf(i[0]);
        depa=departamento(id);
    }
    
    
    public DepartamentoDTO departamento(Long id){
        Respuesta res =depService.getById(id);
        return (DepartamentoDTO) res.getResultado("Departamento");
    }

    
    
    @FXML
    private void actJefeSi(ActionEvent event) {
        jefe=true;
        rbJefeNo.setSelected(false);
    }

    @FXML
    private void actJefeNo(ActionEvent event) {
        jefe=false;
        rbJefeSi.setSelected(false);
    }

    @FXML
    private void actEstadoActivo(ActionEvent event) {
        estado=true;
        rbEstadoInactivo.setSelected(false);
    }

    @FXML
    private void actEstadoInactivo(ActionEvent event) {
        estado=false;
        rbEstadoActivo.setSelected(false);
    }

    @FXML
    private void actGuardar(ActionEvent event) {
        if(validar()){
            if(modalidad.equals("Modificar")){
                usuario.setNombreCompleto(txtNombre.getText());
                usuario.setCedula(txtCedula.getText());
                usuario.setEsJefe(jefe);
                usuario.setEstado(estado);
                Date date = new Date();
                usuario.setFechaModificacion(date);
                if(depa!=null){
                    usuario.setDepartamento(depa);
                }
                Respuesta res = usuService.modificarUsuario(usuario.getId(), usuario);
                if(res.getEstado()){
                     AppContext.getInstance().set("UsuarioEnCuestion", usuario);
                    Mensaje.showAndWait(Alert.AlertType.INFORMATION, "Informacion de usuario", "La informacion del usuario ha sido modificada con exito");
                }else{
                    Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de usuario", "Ha surgido un error por favor intentar mas tarde");
                }
            }else{
                if(modalidad.equals("Agregar")){
                    usuario.setNombreCompleto(txtNombre.getText());
                    usuario.setCedula(txtCedula.getText());
                    usuario.setEsJefe(jefe);
                    usuario.setEstado(estado);
                    if(depa!=null){
                        usuario.setDepartamento(depa);
                    }
                    UsuariosController uc = (UsuariosController) AppContext.getInstance().get("ConUsuarios");
                    uc.lblCedula.setText(txtCedula.getText());
                    uc.lblUsuario.setText(txtNombre.getText());
                    AppContext.getInstance().set("UsuarioNuevo", usuario);
                }
            }
        }else{
            Mensaje.showAndWait(Alert.AlertType.ERROR, "Informacion de usuario", "Faltan datos por ingresar");
        }
    }
    
    public boolean validar(){
        if(txtCedula.getText().isEmpty() || txtNombre.getText().isEmpty()){
            return false;
        }
        return true;
    }
    
}
