package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.employe.Employe;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
    	ControleurSMS.newSMS("RE2016abcd A22aaaa-aaa TE2016aaaa TE2016vvvv", ControleurVue.allumerTelephone());
    	//ControleurSMS.newSMS("EM2016abce B12356as??as AB2016aaaa SE2016aaaa", ControleurVue.allumerTelephone());
    	//ControleurSMS.newSMS("EM2016abce B12356as??as 2222 Bonjour mon ami!", ControleurVue.allumerTelephone());
    	//Employe emp = new Employe("EM2016aso", "Savoie", "Marie","C123WW4545&*88", "2222");

    }

    public static void main(String[] args) {
        launch(args);
    }
}