/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Jeffry
 */
public class UsuariosInformacionController implements Initializable {

    @FXML
    private Button btnVolver;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblCedula;
    @FXML
    private Label lblEstado;
    @FXML
    private Label lblDepartamento;
    @FXML
    private Label lblEsJefe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actVolver(ActionEvent event) {
    }
    
}
