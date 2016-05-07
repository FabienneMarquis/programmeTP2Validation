package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.employe.Employe;

public class ControleurVue {
	
	public static Map<Employe, ControleurVue> vues = new HashMap<>();
	
	public Employe employe;

    @FXML
    private TextField smsSortant;

    @FXML
    private TextArea smsEntrant;

    @FXML
    private Button btnEnvoi;

    @FXML
    void handleSendSMSButton(ActionEvent event) {
    	this.sendSMS(smsSortant.getText());
    }
    
    protected void sendSMS(String message) {
    	ControleurSMS.newSMS(message, this);
    }
    
    public void afficherMessage(String message) {
    	smsEntrant.setText(smsEntrant.getText() + message + "\n");
    }
    
    public void lierEmplolye(Employe employe) {
    	this.employe = employe;
    	vues.put(employe, this);
    }
    
    public static ControleurVue allumerTelephone(Employe employe) throws IOException {
        vues.put(employe, allumerTelephone());
        return vues.get(employe);
    }
    
    public static ControleurVue allumerTelephone() throws IOException {
    	FXMLLoader fxmlloader = new FXMLLoader(ControleurVue.class.getResource("/views/view.fxml"));
    	Parent root = fxmlloader.load();
    	Stage stage = new Stage();
        stage.setTitle("Programme SMS");
        stage.setScene(new Scene(root));
        stage.show();
        ControleurVue ctrl = fxmlloader.<ControleurVue>getController();
        stage.setOnCloseRequest((e) -> vues.remove(ctrl.employe));
        return ctrl;
    }
    
    

}