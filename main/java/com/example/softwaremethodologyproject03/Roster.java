package com.example.softwaremethodologyproject03;

/**
 Roster class is a student array based class.
 @author Yovanny Moscoso
 */
public class Roster {
    private Student[] roster;
    private int size = 100;
    public int numStudents = 0;
    private int location;
    private static final int INCREASE_CAPACITY = 4;
    private static final int NOT_FOUND= -1;
    /**
     * Empty roster constructor
     */
    public Roster(){

        roster = new Student[size];
    }

    /**
     * The find method will receive a student and will try to find a student in the roster.
     * @param student
     * @return If the student is in the roster, the method will return the index where the student is located. If not, it will return -1.
     */
    private int find(Student student) {
        location = 0;
        int result = NOT_FOUND;
        for (int i = location; i < numStudents; i++) {
            if (roster[location].equals(student)) {
                result = location;
                return result;
            }else{ //re-added
                location++; //re-added
            } //re-added
        }
        return result;
    } //search the given student in roster

    /**
     * The grow method will increase the capacity of the array by 4;
     */
    private void grow() {
        Student[] newArray = new Student[size + INCREASE_CAPACITY];
        for(int i=0; i< numStudents; i++){
            newArray[i]= roster[i];
        }
        roster = newArray;
    } //increase the array capacity by 4

    /**
     * This method will add a student to the roster
     * @param student
     * @return it returns true if we add an student
     */
    public boolean add(Student student){
        if(numStudents == roster.length-1) {
            grow();

        }else if(contains(student)){
            return false;
        }else {
            roster[numStudents] = student;
            numStudents++;
            return true;
        }
        return false;
    } //add student to end of array
    /**
     * This method will attempt to find a student in the roster and remove it.
     * @param student
     * @return it returns true if the student was removed. If not it will return false.
     */
    public boolean remove(Student student){
        if(contains(student)){
            for (int i = location; i <= numStudents -2; i++){
                roster[i] = roster[i+1];
            }
            numStudents--;
            return true;
        }else {
            return false;
        }
    }//maintain the order after remove

    /**
     * The contains method will check if a student is in the roster
     * @param student
     * @return it returns true if the student was found. If not, it will return false
     */
    public boolean contains(Student student){
        if(find(student) >= 0){
            return true;
        }else {
            return false;
        }
    } //if the student is in roster

    /**
     * Helper method. This method will print all the contents in any the array of Students
     * @param students
     */
    private String iterate(Student[] students){
        String s = "";
        for(int i = 0; i < numStudents; i++){
            s += students[i] + "\n";
        }// This method will iterate the array of students without iterate de null values
        return s;
    }

    /**
     * Print method prints the contents of the roster sorted by profile (lname, fname, dob)
     */
    public String print() {
        boolean needToSwap = true;
        while(needToSwap){
            needToSwap = false;
            for (int i = 0; i < numStudents - 1; i++) {
                if (roster[i].compareTo(roster[i + 1]) > 0) {
                    needToSwap = true;
                    Student temp = roster[i];
                    roster[i] = roster[i + 1];
                    roster[i + 1] = temp;
                }
            }
        }
        return iterate(roster);
    } //print roster sorted by profiles

    /**
     * printBySchoolMajor method will print the students by major and school using alphabetical order
     */
    public String printBySchoolMajor() {
        boolean needToSwap = true;
        while (needToSwap) {
            needToSwap = false;
            for (int i = 0; i < numStudents - 1; i++) {
                if (roster[i].compareByMajor(roster[i + 1]) > 0) {
                    needToSwap = true;
                    Student temp = roster[i];
                    roster[i] = roster[i + 1];
                    roster[i + 1] = temp;
                }
            }
        }
        return iterate(roster);
    }

    /**
     * printByStanding method will print the roster sorted by standing from lower to highest
     */
    public String printByStanding() {
        boolean needToSwap = true;
        while (needToSwap) {
            needToSwap = false;
            for (int i = 0; i < numStudents - 1; i++) {
                if (roster[i].compareByStanding(roster[i + 1]) > 0) {
                    needToSwap = true;
                    Student temp = roster[i];
                    roster[i] = roster[i + 1];
                    roster[i + 1] = temp;
                }
            }
        }
        return iterate(roster);
    }//print roster sorted by standing

    /**
     * This method will receive the schoolName as a parameter and print all the students in that school ordered alphabetically
     * @param schoolName
     */
    public String printMajor(String schoolName){
        String s = "";
        for(int i = 0; i < numStudents; i++){
            if(schoolName.equals(roster[i].getMajor().getSchool())){
                s += roster[i] + "\n";
            }
        }
        return s;
    }

    /**
     * The updateMajor will update the major of a student in the roster
     * @param s, the student whose major to update.
     * @return if the student is in the roster, it will update it and return true. If not, it will return false;
     */
    public boolean updateMajor(Student s){
        if (find(s) >= 0){
            roster[find(s)].setMajor(s.getMajor());
            return true;
        }else{
            return false;
        }

    }

    /**
     * Method to find a given student in the roster and return that student.
     * @param s, the student to find in the roster.
     * @return the student found in the roster, else return the instance of the student that was passed into the method.
     */
    public Student returnStudent (Student s) {

        if(contains(s) == true) {

            s = roster[find(s)];
            return s;
        }
        return s;
    }
}