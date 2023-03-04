package com.example.softwaremethodologyproject03;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class TuitionManagerController {
    @FXML
    private Label welcomeText;
    @FXML
    private RadioButton rbResident;
    @FXML
    private RadioButton rbNonResident;
    @FXML
    private RadioButton rbInternational;
    @FXML
    private RadioButton rbTristate;
    @FXML
    private RadioButton rbCt;
    @FXML
    private RadioButton rbNy;
    @FXML
    private CheckBox cbStudyAbroad;

    @FXML
    private TextField rosterFirstName;
    @FXML
    void Resident(ActionEvent event){
        setResident();
    }

    void add(ActionEvent event){
       String firstName = this.rosterFirstName.getText();
       //Student s = new Student(firstName, );
       // Date        mm/dd/yyyy;
       // DatePicker yyyy-mm-dd;
        // String[] arr = text.split("-");
        //int year = arr[0];
        //String sum= "";
        //sum += arr[i] + "\n";
    }
    @FXML
    protected void setResident() {
        this.rbInternational.setDisable(true);
        this.rbTristate.setDisable(true);
        this.rbNy.setDisable(true);
        this.rbCt.setDisable(true);
        this.cbStudyAbroad.setDisable(true);
    }

}