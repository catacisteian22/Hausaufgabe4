package main.model;

import java.util.List;

public class Student extends Person {

    public long studentId;
    public int totalCredit;
    public List<Kurs> enrolledKurse;

    public Student(String firstName, String lastName, long studentId, int totalCredit, List<Kurs> enrolledKurse) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredit = totalCredit;
        this.enrolledKurse = enrolledKurse;
    }

    /**
     * @return studentId
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * @return totalCredit
     */
    public int getTotalCredit() {
        return totalCredit;
    }

    /**
     * @param totalCredit ECTS des Studenten
     */
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * @return enrolledCourses
     */
    public List<Kurs> getEnrolledKurse() {
        return enrolledKurse;
    }

    /**
     * @param enrolledKurse Kurse, wo der Student eingeschrieben ist
     */
    public void setEnrolledKurse(List<Kurs> enrolledKurse) {
        this.enrolledKurse = enrolledKurse;
    }

    /**
     * @return tostring
     */
    @Override
    public String toString() {
        return "Student{" +
                "Nachname='" + lastName + '\'' +
                ", Vorname='" + firstName + '\'' +
                ", studentId=" + studentId +
                ", totalCredit=" + totalCredit +
                '}';
    }
}