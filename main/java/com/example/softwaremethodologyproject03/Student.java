package com.example.softwaremethodologyproject03;

/**
 Student class will represent a student entity in our program
 @author Yovanny Moscoso
 */
public abstract class Student implements Comparable<Student>{

    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;
    private Standing standing;
    private static final int MIN_CREDITS = 3; //new constant for Resident, Nonresident classes
    private static final int MAX_CREDITS = 24; //new constant for Resident, Nonresident classes

    /**
     * Empty constructor
     */
    public Student() {

    } // Empty constructor

    /**
     * Constructor that initializes all the attributes of the student
     * @param profile
     * @param major
     * @param creditCompleted
     */
    public Student(Profile profile, Major major, int creditCompleted) {
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Constructor that initializes only the profile of the student
     * @param profile
     */
    public Student(Profile profile) {

        this.profile = profile;
    }

    /**
     * getProfile will return the student profile
     * @return profile
     */
    public Profile getProfile() {
        return this.profile;
    }

    /**
     * Setter method that sets the given profile for a student.
     * @param profile
     */
    public void setProfile(Profile profile) {this.profile = profile;}

    /**
     * getMajor will return the student major
     * @return major
     */
    public Major getMajor() {
        return this.major;
    }

    /**
     * setMajor will set the major to a new value
     * @param major
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     * getCreditCompleted method will return the number of credits that a student has completed
     * @return return an int which represents the number of credits that a student has completed
     */
    public int getCreditCompleted() {
        return this.creditCompleted;
    }

    /**
     * setCreditCompleted will receive the number of credits and will set it to the number of credits that a student has.
     * @param creditCompleted
     */

    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    /**
     * The equals method will compare two students and see if they are equal
     * @param obj the param obj appears because we need to override this method which appears as an object type
     * @return it will return true if the students are equal. If not, it will return false
     */
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            Student s = (Student) obj;
            if(this.profile.equals(s.profile)){
                return true;
            }
        }else {
            return false;
        }
        return false;
    }

    /**
     * The toString method will print a string representation of a student object
     * @return the string representation of the student
     */
    @Override
    public String toString() {

        return this.profile + " " + this.major.toString() + " credits completed: " + this.creditCompleted
                + " (" + returnStanding(this) + ")";
    }

    /**
     * compareTo will compare the profile of two students.
     * @param student the object to be compared.
     * @return If the current object is greater than the object to be compared, it will return 1, if it is lower it will return -1, if the profiles are equal it will return 0
     */
    @Override
    public int compareTo(Student student) {
        return this.profile.compareTo(student.profile);
    }

    /**
     * compareTo will compare the standing of two students.
     * @param student
     * @return If the current object standing is greater than the standing of the object to be compared, it will return 1, if it is lower it will return -1, if the standings are equal it will return 0
     */
    public int compareByStanding(Student student){
        if(this.returnStanding(this).compareTo(student.returnStanding(student)) > 0){
            return 1;
        }else if(this.returnStanding(this).compareTo(student.returnStanding(student)) < 0){
            return -1;
        }else{
            return 0;
        }
    }

    /**
     * compareTo will compare the standing of two students.
     * @param student the object to be compared.
     * @return If the current object major is greater than the major of the object to be compared, it will return 1, if it is lower it will return -1, if the majors are equal it will return 0
     */
    public int compareByMajor(Student student){
        if(this.major.getSchool().compareTo(student.major.getSchool()) > 0){
            return 1;
        } else if (this.major.getSchool().compareTo(student.major.getSchool()) < 0) {
            return -1;
        }else if(this.major.name().compareTo(student.major.name()) > 0){
            return 1;
        }else if(this.major.name().compareTo(student.major.name()) < 0){
            return -1;
        }else {
            return 0;
        }
    }

    /**
     * returnStanding will return the what is the student status (FRESMAN, SOPHOMORE, JUNIOR, SENIOR) based on the credits completed
     * @param s
     * @return it will return s string representation of the status of a student
     */
    public String returnStanding(Student s){
        if(s.getCreditCompleted() >= 0 && s.getCreditCompleted() < standing.FRESHMAN.getNumberOfCredits()){
            return standing.FRESHMAN.name().substring(0, 1).toUpperCase()
                    + standing.FRESHMAN.name().substring(1).toLowerCase();

        } else if(s.getCreditCompleted() >= standing.FRESHMAN.getNumberOfCredits()
                && s.getCreditCompleted() < standing.SOPHOMORE.getNumberOfCredits()){
            return standing.SOPHOMORE.name().substring(0, 1).toUpperCase()
                    + standing.SOPHOMORE.name().substring(1).toLowerCase();

        } else if(s.getCreditCompleted() >= standing.SOPHOMORE.getNumberOfCredits()
                && s.getCreditCompleted() < standing.JUNIOR.getNumberOfCredits()){
            return standing.JUNIOR.name().substring(0, 1).toUpperCase()
                    + standing.JUNIOR.name().substring(1).toLowerCase();

        } else if(s.getCreditCompleted() >= standing.JUNIOR.getNumberOfCredits()){
            return standing.SENIOR.name().substring(0, 1).toUpperCase()
                    + standing.SENIOR.name().substring(1).toLowerCase();

        }else{
            return "not a valid number of credits";
        }

    }

    /**
     * compareBySchool method will compare the student major.
     * @param s
     * @return it returns 1 if the current object major is greater than the student object major to be compared. If is lower, it will return -1,
     * if the majors are equal, it will compare the profiles.
     */
    public int compareBySchool(Student s){
        if(this.getMajor().getSchool().compareTo(s.getMajor().getSchool()) > 0){
            return 1;
        }else if(this.getMajor().getSchool().compareTo(s.getMajor().getSchool()) < 0){
            return -1;
        }else if(this.profile.compareTo(s.profile) > 0){
            return 1;
        }else if(this.profile.compareTo(s.profile) < 0){
            return -1;
        }else {
            return 0;
        }

    }

    /**
     * Checks if the given credits enrolled for a student is valid or not.
     * @param creditEnrolled, the credits enrolled for a student.
     * @return false if credits enrolled are less than the minimum amount of credits for enrollment or greater than
     * the maximum amount of credits for enrollment and true otherwise.
     */
    public boolean isValid(int creditEnrolled) {

        int credits = creditEnrolled;
        if(credits < MIN_CREDITS || credits > MAX_CREDITS) {

            return false;
        }

        return true;
    }

    /**
     * Abstract method to calculate the tuition due for each type of student.
     * @param creditsEnrolled, the credits enrolled for a student.
     * @return
     */
    public abstract double tuitionDue(int creditsEnrolled);

    /**
     * Abstract method that determines whether a student is a resident or not.
     * @return
     */
    public abstract boolean isResident();

}