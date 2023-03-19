package com.example.softwaremethodologyproject03;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Tuition Manager controller manages all the actions entered by the user and responds
 * according to the implementation designed under this class
 * @author Joshua McArdle, Yovanny Moscoso
 */
public class TuitionManagerController {
    private EnrollStudent enrollStudent;
    private Student student;
    private Profile enrollmentProfile;
    private Date enrollmentDob;
    private int numEnrollments;
    @FXML
    private TextField enrollmentFirstName;
    @FXML
    private TextField enrollmentLastName;
    @FXML
    private DatePicker enrollmentDate;
    @FXML
    private TextField enrollmentCredits;
    @FXML
    private TextArea enrollmentTextArea;
    @FXML
    private TextArea printTextArea;
    private static final int MIN_CREDITS_FULL_TIME = 12;
    private static final int MIN_AGE = 16;
    private static final int MIN_SCHOLARSHIP_AMOUNT = 1;
    private static final int MAX_SCHOLARSHIP_AMOUNT = 10000;
    private static final int NUM_CREDITS_FOR_GRADUATION = 120;
    @FXML
    private TextField rosterFirstName, rosterLastName, rosterCreditsCompleted, scholarshipAmount;
    @FXML
    private TextField scholarshipFirstName, scholarshipLastName;
    @FXML
    private DatePicker rosterDOB, scholarshipDOB;
    @FXML
    private RadioButton rbBAIT;
    @FXML
    private RadioButton rbCS;
    @FXML
    private RadioButton rbEE;
    @FXML
    private RadioButton rbITI;
    @FXML
    private RadioButton rbMATH;
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
    private TextArea rosterTextArea;
    @FXML
    private TextArea scholarshipTextArea;
    private Profile stProfile;
    private Major stMajor;
    private Date date, scholarshipDate;
    private String state;
    private boolean studyAbroad;
    private int numStudents;
    private String listFirstName, listLastName, listDOB, listState, listMajor, listCredits;
    private String opCode;
    private boolean listStudyAbroad;
    private String[] arrOfTokens;
    private Roster roster = new Roster();
    private Enrollment enrollment = new Enrollment();// initialazing enrollment

    /**
     * On Action command for rbResident, calls setResident() method to enable and disable the appropriate buttons in the
     * GUI.
     * @param event
     */
    @FXML
    void Resident(ActionEvent event){
        setResident();
    }

    /**
     * On Action command for rbNonResident, calls setNonResident() method to enable and disable the appropriate buttons
     * in the GUI.
     * @param event
     */
    @FXML
    void NonResident(ActionEvent event) {
        setNonResident();
    }

    /**
     * On Action command for rbTriState, calls setTriState() method to enable and disable the appropriate buttons in the
     * GUI.
     * @param event
     */
    @FXML
    void TriState(ActionEvent event) {
        setTriState();
    }

    /**
     * On Action command for rbInternational, calls setInternational() method to enable and disable the appropriate buttons
     * in the GUI.
     * @param event
     */
    @FXML
    void International(ActionEvent event) {
        setInternational();
    }

    /**
     * On Action command for rbBAIT, sets the value of a student's major to BAIT.
     * @param event
     */
    @FXML
    void majorBAIT(ActionEvent event) {

        stMajor = Major.valueOf(rbBAIT.getText());
    }

    /**
     * On Action command for rbCS, sets the value of a student's major to CS.
     * @param event
     */
    @FXML
    void majorCS(ActionEvent event) {

        stMajor = Major.valueOf(rbCS.getText());
    }

    /**
     * On Action command for rbEE, sets the value of a student's major to EE.
     * @param event
     */
    @FXML
    void majorEE(ActionEvent event) {

        stMajor = Major.valueOf(rbEE.getText());
    }

    /**
     * On Action command for rbITI, sets the value of a student's major to ITI.
     * @param event
     */
    @FXML
    void majorITI(ActionEvent event) {

        stMajor = Major.valueOf(rbITI.getText());
    }

    /**
     * On Action command for rbMATH, sets the value of a student's major to MATH.
     * @param event
     */
    @FXML
    void majorMATH(ActionEvent event) {

        stMajor = Major.valueOf(rbMATH.getText());
    }


    /**
     * On Action command for rbCT, sets the value of a Tristate student's state to CT.
     * @param event
     */
    @FXML
    void stateCT(ActionEvent event) {

        state = rbCt.getText();
    }

    /**
     * On Action command for rbNY, sets the value of a Tristate student's state to NY.
     * @param event
     */
    @FXML
    void stateNY(ActionEvent event) {

        state = rbNy.getText();
    }

    /**
     * On Action command for cbStudyAbroad, sets the value of an International student's studyAbroad variable to true
     * if selected and false otherwise.
     * @param event
     */
    @FXML
    void studyAbroad(ActionEvent event) {

        if(cbStudyAbroad.isSelected()) {

            studyAbroad = true;
        }
        else {

            studyAbroad = false;
        }

    }

    /**
     * Helper method for Resident(), disables buttons related to all NonResident students and instances of NonResident
     * students.
     */
    @FXML
    protected void setResident() {
        this.rbInternational.setDisable(true);
        this.rbTristate.setDisable(true);
        this.rbNy.setDisable(true);
        this.rbCt.setDisable(true);
        this.cbStudyAbroad.setDisable(true);
        this.rbInternational.setSelected(false);
        this.rbTristate.setSelected(false);
        this.rbNy.setSelected(false);
        this.rbCt.setSelected(false);
        this.cbStudyAbroad.setSelected(false);
    }

    /**
     * Helper method for NonResident(), enables buttons related to all NonResident students and instances of NonResident
     * students.
     */
    @FXML
    protected void setNonResident() {
        this.rbInternational.setDisable(false);
        this.rbTristate.setDisable(false);
        this.rbNy.setDisable(false);
        this.rbCt.setDisable(false);
        this.cbStudyAbroad.setDisable(false);
        this.rbInternational.setSelected(false);
        this.rbTristate.setSelected(false);
        this.rbNy.setSelected(false);
        this.rbCt.setSelected(false);
        this.cbStudyAbroad.setSelected(false);

    }

    /**
     * Helper method for TriState(), disables buttons related to International students and enables the rbNY and rbCT
     * buttons to select a TriState student's state.
     */
    @FXML
    protected void setTriState() {
        this.rbInternational.setDisable(false);
        this.rbTristate.setDisable(false);
        this.rbNy.setDisable(false);
        this.rbCt.setDisable(false);
        this.cbStudyAbroad.setDisable(true);
    }

    /**
     * Helper method for International(), disables buttons related to TriState students and enables the cbStudyAbroad
     * checkbox.
     */
    @FXML
    protected void setInternational() {
        this.rbInternational.setDisable(false);
        this.rbTristate.setDisable(false);
        this.rbNy.setDisable(true);
        this.rbCt.setDisable(true);
        this.cbStudyAbroad.setDisable(false);
    }

    /**
     * Helper method for the remove, and changeMajor method. It returns false if the user enter a blank inf the GUI for
     * the firstname,last name or if the date is incorrect
     * @return true if everything the inputs are correct.
     */
    private boolean rosterProfileErrors(){
        if(rosterFirstName.getText().equals("")){
            rosterTextArea.appendText("You must insert a first name." + "\n");
            return false;
        }else if(rosterLastName.getText().equals("")){
            rosterTextArea.appendText("You must insert a last name." + "\n");
            return false;
        }else if(dateError() == false){
            return false;
        }
        return true;
    }

    /**
     * Helper method for add(), remove(), changeMajor(), updateScholarship(), checks to see if any text fields in the GUI
     * are empty or if a given date or amount of credits is invalid.
     * @return false if any of the text fields are empty or date is invalid or number of credits is invalid and true
     * otherwise.
     */
    private boolean rosterManageErrors(){
        if(rosterFirstName.getText().equals("")){
            rosterTextArea.appendText("You must insert a first name." + "\n");
            return false;
        }else if(rosterLastName.getText().equals("")){
            rosterTextArea.appendText("You must insert a last name." + "\n");
            return false;
        }else if(dateError() == false){
            return false;
        }else if(creditsError() == false){
            return false;
        }
        return true;
    }

    /**
     * Helper method for rosterManageErrors(), checks if a given date is null or an invalid input and prints an
     * appropriate error message.
     * @return false if date is null or an invalid input i.e. a dob younger than 16 or invalid calendar date
     * and true otherwise.
     */
    private boolean dateError() {

        if(this.rosterDOB.getValue() == null) {
            rosterTextArea.appendText("You must enter in a date of birth." + "\n");
            return false;
        }

        date = new Date(convertToDateFormat(rosterDOB.getValue().toString()));

        Date today = new Date();

        if(date.isValid() == false) {

            if(date.equals(today) || date.compareTo(today) < 0 && date.getYear() >= today.getYear() - MIN_AGE) {
                rosterTextArea.appendText("DOB invalid: " + date + " younger than 16 years old." + "\n");
                return false;
            }
            else {
                rosterTextArea.appendText("DOB invalid: " + date + " not a valid calendar date!" + "\n");
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method for RosterManageErrors(), checks if a given number of credits is null or an invalid number and prints
     * an appropriate error message.
     * @return false if credits is null or not an integer or a negative amount and true otherwise.
     */
    private boolean creditsError() {

        if(rosterCreditsCompleted.getText() == null) {

            rosterTextArea.appendText("You must enter in a number of credits." + "\n");
            return false;
        }

        try {
            Integer.parseInt(rosterCreditsCompleted.getText());
        }
        catch (NumberFormatException e) {
            rosterTextArea.appendText("Credits completed invalid: not an integer!" + "\n");
            return false;
        }

        if(Integer.parseInt(rosterCreditsCompleted.getText()) < 0) {

            rosterTextArea.appendText("Credits completed invalid: cannot be negative!" + "\n");
            return false;
        }
        return true;
    }
    /**
     * Helper method for add(), checks to see whether a state has been selected in the GUI or not.
     * @return false if state is null and true otherwise.
     */
    @FXML
    private boolean stateError() {

        if(rbNonResident.isSelected() && rbTristate.isSelected()) {

            if(!rbCt.isSelected() && !rbNy.isSelected()) {
                rosterTextArea.appendText("You must select a state for this TriState student." + "\n");
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method for scholarshipManageErrors(), checks if a given amount is null or an invalid amount and prints an
     * appropriate error message.
     * @return false if the amount is null or not an integer or an invalid amount and true otherwise.
     */
    private boolean scholarshipAmountError() {

        if(scholarshipAmount.getText() == null) {

            scholarshipTextArea.appendText("Enter in a scholarship amount.");
            return false;
        }

        try {
            Integer.parseInt(scholarshipAmount.getText());
        }
        catch (NumberFormatException e) {
            scholarshipTextArea.appendText("Amount is not an integer.");
            return false;
        }

        if(Integer.parseInt(scholarshipAmount.getText()) < MIN_SCHOLARSHIP_AMOUNT ||
                Integer.parseInt(scholarshipAmount.getText()) > MAX_SCHOLARSHIP_AMOUNT ) {

            scholarshipTextArea.appendText(scholarshipAmount.getText() + ": invalid amount.");
            return false;
        }
        return true;
    }

    /**
     * Helper method for scholarshipManageErrors(), checks if a given date is null and prints an appropriate error message.
     * @return false if date is null and true otherwise.
     */
    @FXML
    private boolean scholarshipDateError() {
        if (this.scholarshipDOB.getValue() == null) {
            this.scholarshipTextArea.appendText("Must insert a value for date\n");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Helper method for updateScholarship(), checks to see if any of the text fields in the GUI are empty or if a given
     * date or scholarship amount is invalid.
     * @return false if any of the text fields are empty or date is invalid or scholarship amount is invalid and true
     * otherwise.
     */
    @FXML
    private boolean scholarshipManageErrors() {

        if (this.scholarshipFirstName.getText().isBlank()) {
            this.scholarshipTextArea.appendText("You must insert a first name\n");
            return false;
        }
        if (this.scholarshipLastName.getText().isBlank()) {
            this.scholarshipTextArea.appendText("You must insert a last name\n");
            return false;
        }
        if (scholarshipDateError() == false) {
            return false;
        }
        if (scholarshipAmountError() == false) {
            return false;
        }
        return true;
    }

    /**
     * Helper method for add(), checks to see whether a major has been selected in the GUI or not.
     * @return false if major is null and true otherwise.
     */
    @FXML
    private boolean majorError() {

        if (this.stMajor == null) {
            rosterTextArea.appendText("You must select a major." + "\n");
            return false;
        }
        return true;
    }

    /**
     * On Action command for bAdd, adds a student to the roster.
     */
    @FXML
    private void add() {
        this.rosterTextArea.setText("");
        if(rosterManageErrors() == false) {
            return;
        }
        if(majorError() == false) {
            return;
        }if(stateError() == false){
            return;
        }
        //date = new Date(convertToDateFormat(dob.getValue().toString()));

        this.stProfile = new Profile(rosterLastName.getText(), rosterFirstName.getText(), date);

        createStudentType(stProfile);

        if(roster.contains(student)) {
            rosterTextArea.appendText(rosterFirstName.getText() + " " + rosterLastName.getText() + " " + date
                    + " already in the roster." + "\n");
        }
        else{
            roster.add(student);
            numStudents++;
            rosterTextArea.appendText(rosterFirstName.getText() + " " + rosterLastName.getText()
                    + " " + date + " added to the roster." + "\n");
        }
        rosterFirstName.setText("");
        rosterLastName.setText("");
        rosterDOB.setValue(null);
        rosterCreditsCompleted.setText("");

    }

    /**
     * On Action command for bRemove, removes a student from the roster.
     */
    @FXML
    private void remove() {
        this.rosterTextArea.setText("");
        if(rosterProfileErrors() == false) {
            return;
        }

        date = new Date(convertToDateFormat(rosterDOB.getValue().toString()));
        Profile stProfile = new Profile(rosterLastName.getText(), rosterFirstName.getText(), date);
        this.student = new Resident(stProfile);

        if(roster.remove(student)) {
            numStudents--;
            rosterTextArea.appendText(rosterFirstName.getText() + " " + rosterLastName.getText() + " " + date
                    + " removed from the roster." + "\n");
        }
        else {
            rosterTextArea.appendText(rosterFirstName.getText() + " " + rosterLastName.getText() + " " + date
                    + " not in the roster." + "\n");
        }
        rosterFirstName.setText("");
        rosterLastName.setText("");
        rosterDOB.setValue(null);
        rosterCreditsCompleted.setText("");

    }

    /**
     * On Action command for bChangeMajor, changes a student's major.
     */
    @FXML
    private void changeMajor() {
        this.rosterTextArea.setText("");
        if(rosterProfileErrors() == false) {
            return;
        }
        if(majorError() == false){
            return;
        }

        date = new Date(convertToDateFormat(rosterDOB.getValue().toString()));
        Profile stProfile = new Profile(rosterLastName.getText(), rosterFirstName.getText(), date);
        this.student = new Resident(stProfile);

        if(this.numStudents == 0) {

            rosterTextArea.appendText("Student roster is empty!" + "\n");
            return;
        }
        if(roster.contains(student) == true) {
            student.setMajor(stMajor);
            roster.updateMajor(student);
            rosterTextArea.appendText(rosterFirstName.getText() + " " + rosterLastName.getText() + " " + date + " major changed to "
                    + stMajor + "." + "\n");
        }

        else {
            rosterTextArea.appendText(rosterFirstName.getText() + " " + rosterLastName.getText() + " " + date
                    + " is not in the roster." + "\n");

        }
        rosterFirstName.setText("");
        rosterLastName.setText("");
        rosterDOB.setValue(null);
        rosterCreditsCompleted.setText("");
    }

    /**
     * On Action command for bLoadFromFile, receives a text file via user input and adds students from the list to
     * the roster.
     */
    @FXML
    private void loadFromFile() {
        this.rosterTextArea.setText("");
        TextInputDialog td = new TextInputDialog("enter file here");
        td.setHeaderText("Enter text file: ");
        Optional<String> file = td.showAndWait();
        try {
            if (file.isPresent()) {
                Scanner studentList = new Scanner(new File(file.get()));
                while (studentList.hasNextLine()) {

                    String command = studentList.nextLine();
                    convertToTokens(command);
                    this.date = new Date(this.listDOB);
                    Profile stProfile = new Profile(this.listLastName, this.listFirstName, date);
                    stMajor = Major.valueOf(this.listMajor.toUpperCase());
                    createStudentFromFile(stProfile);
                    roster.add(student);
                    numStudents++;
                }
                rosterTextArea.appendText("Students loaded to the roster." + "\n");
            }
        }
        catch (FileNotFoundException e) {

            rosterTextArea.appendText("File not found. Enter a valid file." + "\n");
        }

    }

    /**
     * On Action command for bUpdateScholarship, awards a scholarship to a resident student.
     */
    @FXML
    private void updateScholarship() {
        this.scholarshipTextArea.setText("");
        if(scholarshipManageErrors() == false) {
            return;
        }
        scholarshipDate = new Date(convertToDateFormat(scholarshipDOB.getValue().toString()));
        Profile stProfile = new Profile(scholarshipLastName.getText(), scholarshipFirstName.getText(), scholarshipDate);;
        student = new Resident(stProfile);
        student = roster.returnStudent(this.student);
        enrollStudent = new EnrollStudent(stProfile);
        enrollStudent = enrollment.returnEnrollStudent(this.enrollStudent);

        if(checkResidency() == false) {

            //do nothing
        }
        scholarshipFirstName.setText("");
        scholarshipLastName.setText("");
        scholarshipDOB.setValue(null);
        scholarshipAmount.setText("");
    }

    /**
     * Helper method for updateScholarship(), checks if a student is a resident or not and updates their scholarship
     * accordingly.
     * @return false if student is not in the roster or is not a resident or is a part-time student and true otherwise.
     */
    private boolean checkResidency() {

        if(!(roster.contains(student))) {

            scholarshipTextArea.appendText(scholarshipFirstName.getText() + " " + scholarshipLastName.getText() + " "
                    + scholarshipDate + " is not in the roster." + "\n");
            return false;
        }
        else if(student.isResident() == false) {

            scholarshipTextArea.appendText(scholarshipLastName.getText() + " " + scholarshipLastName.getText() + " "
                    + scholarshipDate + " is not eligible for the scholarship." + "\n");
            return false;
        }

        if(student.isResident()) {

            if(enrollStudent.getCreditsEnrolled() < MIN_CREDITS_FULL_TIME) {

                scholarshipTextArea.appendText(scholarshipFirstName.getText() + " " + scholarshipLastName.getText() + " "
                        + scholarshipDate + " part time student is not eligible for the scholarship." + "\n");
                return false;
            }
            else if(scholarshipAmountError() == false) {

                return false;
            }
            else {
                ((Resident)student).setScholarship(Integer.parseInt(scholarshipAmount.getText()));
                scholarshipTextArea.appendText(scholarshipFirstName.getText() + " " + scholarshipLastName.getText() + " "
                        + scholarshipDate + ": scholarship amount updated." + "\n");
            }
        }
        return true;
    }

    /**
     * Helper method for add(), creates a new instance of student based on the radio button selected from the user in the
     * GUI.
     * @param profile, the profile of the student to be added.
     */
    private void createStudentType(Profile profile) {

        if(rbResident.isSelected()) {

            this.student = new Resident(profile, stMajor, Integer.parseInt(rosterCreditsCompleted.getText()),0);

        }
        else if(rbNonResident.isSelected()) {

            if (rbTristate.isSelected()) {

                this.student = new TriState(profile, stMajor, Integer.parseInt(rosterCreditsCompleted.getText()), state);

            } else if (rbInternational.isSelected()) {

                this.student = new International(profile, stMajor, Integer.parseInt(rosterCreditsCompleted.getText()), studyAbroad);

            } else {

                this.student = new NonResident(profile, stMajor, Integer.parseInt(rosterCreditsCompleted.getText()));
            }
        }
    }

    /**
     * Helper method for loadFromFile(), checks to see the type of student to be added based on the opCode
     * and creates an instance of that student.
     * @param profile, the profile of the student to be added.
     */
    private void createStudentFromFile(Profile profile) {

        if(opCode.equals("R")) {
            this.student = new Resident(profile, stMajor, Integer.parseInt(listCredits),0);
        }
        if(opCode.equals("N")) {
            this.student = new NonResident(profile, stMajor, Integer.parseInt(listCredits));
        }
        if(opCode.equals("T")) {
            this.student = new TriState(profile, stMajor, Integer.parseInt(listCredits), listState);
        }
        if(opCode.equals("I")) {
            this.student = new International(profile, stMajor, Integer.parseInt(listCredits),
                    listStudyAbroad);
        }
    }

    /**
     * Helper method for loadFromFile(), uses .split() method to grab the operation code and data tokens necessary to
     * add students to the roster from a given text file.
     * @param tokens, a string from the text file to be parsed into tokens
     */
    private void convertToTokens(String tokens) {

        this.arrOfTokens = tokens.split("\\s+|,");

        this.opCode = arrOfTokens[0];

        if (arrOfTokens.length > 1) {
            listFirstName = arrOfTokens[1];
        }
        if (arrOfTokens.length > 2) {
            listLastName = arrOfTokens[2];
        }
        if (arrOfTokens.length > 3) {
            listDOB = arrOfTokens[3];
        }
        if (arrOfTokens.length > 4) {
            listMajor = arrOfTokens[4];
        }
        if (arrOfTokens.length > 5) {
            listCredits = arrOfTokens[5];
        }
        if (arrOfTokens.length > 6 && (opCode.equals("T"))) {
            listState = arrOfTokens[6];
        }
        if (arrOfTokens.length > 6 && (opCode.equals("I"))) {

            listStudyAbroad = Boolean.parseBoolean(arrOfTokens[6]);

        }
    }


    /**
     * On action method for enroll, it will call the enrollStudentMethod which will perform
     * some actions
     * @param event
     */
    @FXML
    void enroll(ActionEvent event) {
        enrollStudentMethod();
    }

    /**
     * On action method for enroll, it will call the enrollStudent which will perform
     * some actions
     * @param event
     */
    @FXML
    void drop(ActionEvent event) {
        dropStudent();
    }


                             /**HERE WE WILL GENERATE ALL THE ACTION METHODS FOR THE PRINT TAB */


    /**
     * On action method that print all the students enrolled
     * @param event
     */
    @FXML
    void printEnrolled(ActionEvent event){
        printTextArea.setText("");
        printTextArea.appendText(enrollment.print());
        if(numEnrollments == 0){
            printTextArea.appendText("No students enrolled");
        }
    }

    /**
     * On action method that print the students who will complete at least 120 at the end of the
     * semester
     * @param action
     */
    @FXML
    void printSemesterEnd(ActionEvent action) {
        this.printTextArea.setText("");
        int credits =0;

        for (int i = 0; i < numEnrollments; i++) {

            enrollStudent = enrollment.returnEnrollStudent(i);
            this.student = new Resident(enrollStudent.getProfile(), Major.CS, 0, 0);
            //Finding the student in the roster and returning its actual value
            student = roster.returnStudent(student);

            enrollStudent = enrollment.returnEnrollStudent(i);
            this.student = new Resident(enrollStudent.getProfile(), Major.CS, 0, 0);
            student = roster.returnStudent(student);

            credits = student.getCreditCompleted() + enrollStudent.getCreditsEnrolled();

            if (credits >= NUM_CREDITS_FOR_GRADUATION) {
                this.printTextArea.appendText(student.getProfile() + " credits completed: " + credits + "\n");
            }
        }if(this.printTextArea.getText().equals("")){
            this.printTextArea.setText("No students ready for graduation");
        }

    }

    /**
     * On action method that will print the tuition due for all the students enrolled
     * @param action
     */
    @FXML
    void printTuitionDue(ActionEvent action) {
        this.printTextArea.setText("");
        if (numEnrollments == 0) {
            this.printTextArea.setText("No students enrolled");

        } else {

            for (int i = 0; i < numEnrollments; i++) {

                enrollStudent = enrollment.returnEnrollStudent(i);

                this.student = new Resident(enrollStudent.getProfile(), Major.CS, 0, 0);

                student = roster.returnStudent(student);

                printTuitionByStudentType();

            }
        }
    }

    /**
     * On action method that will print the all the students in the roster sorted by profile
     * @param event
     */
    @FXML
    void printByProfile(ActionEvent event){
        printTextArea.setText("");
        if(numStudents == 0){
            printTextArea.appendText("The roster is empty!");
        }else {
            printTextArea.appendText(roster.print());
        }
    }

    /**
     * On action method that will print all the students in the roster sorted by school and major
     * @param event
     */
    @FXML
    void printBySchoolMajor(ActionEvent event){
        printTextArea.setText("");
        if(numStudents == 0){
            printTextArea.appendText("The roster is empty!");
        }else {
            printTextArea.appendText(roster.printBySchoolMajor());
        }
    }

    /**
     * On action method that will print students in the roster sorted by standing
     * @param event
     */
    @FXML
    void printByStanding(ActionEvent event){
        printTextArea.setText("");
        if(numStudents == 0){
            printTextArea.appendText("The roster is empty!");
        }else {
            printTextArea.appendText(roster.printByStanding());
        }
    }

    /**
     * On action method that will print the students in the RBS school
     * @param event
     */
    @FXML
    void printRbs(ActionEvent event){
        String s = "RBS";
        printTextArea.setText("");
        if(roster.printMajor(s).equals("")){
            printTextArea.appendText("No students in " + s + " school.");
        }else {
            printTextArea.appendText(roster.printMajor(s));
        }

    }

    /**
     * On action method that will print the students in the SAS school
     * @param event
     */
    @FXML
    void printSas(ActionEvent event){
        String s = "SAS";
        printTextArea.setText("");
        if(roster.printMajor(s).equals("")){
            printTextArea.appendText("No students in " + s + " school.");
        }else {
            printTextArea.appendText(roster.printMajor(s));
        }
    }

    /**
     * On action method thta will print the students in the SC&I school
     * @param event
     */
    @FXML
    void printSci(ActionEvent event){
        String s = "SC&I";
        printTextArea.setText("");
        if(roster.printMajor(s).equals("")){
            printTextArea.appendText("No students in " + s + " school.");
        }else {
            printTextArea.appendText(roster.printMajor(s));
        }
    }

    /**
     * On action metho that will print the students in the SOE school
     * @param event
     */
    @FXML
    void printSoe(ActionEvent event){
        String s = "SOE";
        printTextArea.setText("");
        if(roster.printMajor(s).equals("")){
            printTextArea.appendText("No students in " + s + " school.");
        }else {
            printTextArea.appendText(roster.printMajor(s));
        }
    }


                            /**HERE WE WILL HAVE ALL THE HELPER METHODS */

    /**
     * Helper method. This method will help us to clear the enrollment Textarea
     */
    @FXML
    private void enrollmentTextAreaClear() {
        this.enrollmentTextArea.setText("");
    }

    /**
     * Helper method. This method will reset the text fields
     */
    @FXML
    private void enrollmentClear() {
        enrollmentFirstName.setText("");
        enrollmentLastName.setText("");
        enrollmentDate.setValue(null);
        enrollmentCredits.setText("");
    }

    /**
     * Helper method. This method will receive a string date
     * @param date will be in the format yyyy-mm-dd
     * @return String. It will return the String date in a format mm/dd/yyyy
     */
    @FXML
    private String convertToDateFormat(String date) {// This method will receive a String in the yyyy-mm-dd format and return mm/dd/yyyy
        String output = "";
        String[] dateArr = date.split("-");
        String year = dateArr[0];
        String month = dateArr[1];
        String day = dateArr[2];
        return month + "/" + day + "/" + year;// mm/dd/yyyy
    }

    /**
     * Helper method. This method prevents that the user inserts a null value from the datePicker.
     * If the user inserts a date, it will return true
     * @return false if the user does not enter anything
     */
    @FXML
    private boolean enrollmentDateError() {
        if (this.enrollmentDate.getValue() == null) {
            this.enrollmentTextArea.appendText("Must insert a value for date\n");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Helper method. This method will prevent if the user a null value, a negative value, or any non numerical values
     * @return false if the values are null value, negative value, or any non numerical values.
     * Otherwise, it will return true
     */
    @FXML
    private boolean creditsEnrolledError() {
        if (this.enrollmentCredits.getText() == null) {
            this.enrollmentTextArea.appendText("Number of credits cannot be empty.\n");
            return false;
        }
        try {
            Integer.parseInt(this.enrollmentCredits.getText());

        } catch (NumberFormatException e) {
            this.enrollmentTextArea.appendText("Credits enrolled is not an integer.\n");
            return false;
        } if(Integer.parseInt(this.enrollmentCredits.getText()) < 0) {

            this.enrollmentTextArea.appendText("Credits completed invalid: cannot be negative!");
            return false;
        }
        return true;
    }

    /**
     * Helper method. This method will check all possible errors when a user tries to enroll a student
     * @return false if the values entered are incorrect or null. Otherwise, it will return true
     */
    @FXML
    private boolean enrollmentManageErrors() {

        if (this.enrollmentFirstName.getText().isBlank()) {
            this.enrollmentTextArea.appendText("You must insert a first name\n");
            return false;
        }
        if (this.enrollmentLastName.getText().isBlank()) {
            this.enrollmentTextArea.appendText("You must insert a last name\n");
            return false;
        }
        if (enrollmentDateError() == false) {
            return false;
        }
        if (creditsEnrolledError() == false) {
            return false;
        }
        return true;
    }

    /**
     * Helper method. This method will prevent the possible errors when the user tries to enter values in the text area
     * for dropping a student from the enrollment. It will check if the firstname, lastname and dob are blank.
     * @return false if the values for firstname, lastname and dob are blank. Otherwise, it will return true
     */
    @FXML
    private boolean dropManageErrors() {

        if (this.enrollmentFirstName.getText().isBlank()) {
            this.enrollmentTextArea.appendText("You must insert a first name\n");
            return false;
        }
        if (this.enrollmentLastName.getText().isBlank()) {
            this.enrollmentTextArea.appendText("You must insert a last name\n");
            return false;
        }
        if (enrollmentDateError() == false) {
            return false;
        }
        return true;
    }

    /**
     * Helper method. This method will attempt to add a student to the enrollment list based on their number of credits hours allowed.  If there is an error when
     * inserting the values, it will call the helper method enrollmentManageErrors and will display the error to the enrollment textarea. If the student is not
     * in the roster, it will display that to the enrollment textarea. If number of credit hours allowed by the type of student is incorrect, it will display
     * that to the enrollment textarea. Otherwise, it will enroll the student.
     */
    @FXML
    private void enrollStudentMethod() {
        enrollmentTextAreaClear();
        if (enrollmentManageErrors() == true) {
            //Geting the input values
            String firstName = this.enrollmentFirstName.getText();
            String lastName = this.enrollmentLastName.getText();
            String dateInput = this.enrollmentDate.getValue().toString();
            String dateConverted = convertToDateFormat(dateInput); //converting the input format from yyyy-mm-dd to mm/dd/yyyy
            this.enrollmentDob = new Date(dateConverted);
            int credits = Integer.valueOf(this.enrollmentCredits.getText());
            this.enrollmentProfile = new Profile(lastName, firstName, new Date(dateConverted));
            this.student = new Resident(enrollmentProfile, Major.CS, 0, 0);
            this.student = roster.returnStudent(student);
            if (!roster.contains(student)){
                enrollmentTextArea.appendText("Student is not in the roster.\n");
            }else if(student.isValid(credits) == false) {
                enrollmentTextArea.appendText(firstName + " " + lastName + " " + studentType(student) + " " + credits + ": invalid credit hours.");
            } else {
                this.enrollStudent = new EnrollStudent(enrollmentProfile, credits);
                this.enrollment.add(enrollStudent);
                enrollmentTextArea.appendText(firstName + " " + lastName + " " + enrollmentDob + " enrolled " + credits + " credits\n");
                numEnrollments++;
            }
            enrollmentClear();
        }
    }

    /**
     * Helper method. This method will attempt to drop an existing enrollment in the enrollment list. First, it will check if the values entered are correct
     * by calling the dropManageErrors. If there is an error the method will display the corresponding error information in the dropManageErrors. Then, it
     * will check if the enrollment exits. If the enrollments exist, it will delete it from the enrollment list.
     */
    @FXML
    private void dropStudent() {
        this.enrollmentTextArea.setText("");
        if (dropManageErrors()) {
            //Geting the input values
            String firstName = this.enrollmentFirstName.getText();
            String lastName = this.enrollmentLastName.getText();
            String dateInput = this.enrollmentDate.getValue().toString();
            String dateConverted = convertToDateFormat(dateInput); //converting the input format from yyyy-mm-dd to mm/dd/yyyy
            this.enrollmentDob = new Date(dateConverted);
            this.enrollmentProfile = new Profile(lastName, firstName, enrollmentDob);
            this.enrollStudent = new EnrollStudent(enrollmentProfile);
            if(enrollment.remove(enrollStudent)) {
                this.enrollmentTextArea.appendText(firstName + " " + lastName + " " + enrollmentDob + " "  + " dropped\n");
                enrollmentClear();
            }else{
                this.enrollmentTextArea.appendText(firstName + " " + lastName + " " + enrollmentDob + " "  +" is not enrolled\n");
                enrollmentClear();
                numEnrollments--;
            }
        }
    }

    /**
     *  Helper method. This method will check the kind of instance of student is the student, and will display the information about a
     *  student and the tuition due in a decimal format.
     */
    @FXML
    private void printTuitionByStudentType() {

        DecimalFormat df = new DecimalFormat("#,###.00");

        if (student instanceof Resident residentStudent) {
            this.printTextArea.appendText(residentStudent.getProfile() + " (Resident) " + "enrolled "
                    + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                    + df.format(residentStudent.tuitionDue(enrollStudent.getCreditsEnrolled())) + "\n");

        } else if (student instanceof NonResident nonResidentStudent) {

            if (nonResidentStudent instanceof TriState triStateStudent) {
                this.printTextArea.appendText(triStateStudent.getProfile() + " (Tri-state " + triStateStudent.getState().toUpperCase()
                        + ")" + " enrolled " + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                        + df.format(triStateStudent.tuitionDue(enrollStudent.getCreditsEnrolled())) + "\n");
            } else if (nonResidentStudent instanceof International internationalStudent) {

                if (internationalStudent.getIsStudyAbroad() == true) {
                    this.printTextArea.appendText(internationalStudent.getProfile() + " (International student study abroad) "
                            + "enrolled " + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                            + df.format(internationalStudent.tuitionDue(enrollStudent.getCreditsEnrolled())) + "\n");

                } else {
                    this.printTextArea.appendText(internationalStudent.getProfile() + " (International student) " + "enrolled "
                            + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                            + df.format(internationalStudent.tuitionDue(enrollStudent.getCreditsEnrolled())) + "\n");
                }
            } else {
                this.printTextArea.appendText(nonResidentStudent.getProfile() + " (Non-Resident) " + "enrolled "
                        + enrollStudent.getCreditsEnrolled() + " credits: " + "tuition due: $"
                        + df.format(nonResidentStudent.tuitionDue(enrollStudent.getCreditsEnrolled())) + "\n");
            }
        }
    }

    /**
     * Helper method. This method will accept a student type as a parameter and will return a string based on the toString method defined on each
     * child classes of Student and will concatenate the student type.
     *
     * @param student
     * @return String representation of the type of the student + (studentType)
     */
    @FXML
    private String studentType(Student student) {

        if (student instanceof Resident residentStudent) {
            return "(Resident)";
        }
        if (student instanceof NonResident nonResidentStudent) {
            if (nonResidentStudent instanceof TriState triStateStudent) {
                return "(Tri-state)";
            }
            else if(nonResidentStudent instanceof International internationalStudent) {
                if(internationalStudent.getIsStudyAbroad() == true) {
                    return "(International student study abroad)";
                }
                else {
                    return "(International student)";
                }
            }
            else {
                return "(Non-Resident)";
            }
        }
        return "";
    }


}