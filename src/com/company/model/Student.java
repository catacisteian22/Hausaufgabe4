package com.company.model;
import java.util.List;

/**
 * @author sncam
 */
public class Student extends Person {

    private long studentId;
    private int totalCredit;
    private List<Course> enrolledCourses;

    public Student(String name, String firstName, long studentId, int totalCredit, List<Course> enrolledCourses) {
        super(name, firstName);
        this.studentId = studentId;
        this.totalCredit = totalCredit;
        this.enrolledCourses = enrolledCourses;
    }

    /*
    getter and setter
    */

    /**
     * @return studentId
     */
    public long getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the id of the student
     */
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    /**
     * @return totalCredit
     */
    public int getTotalCredit() {
        return totalCredit;
    }

    /**
     *
     * @param totalCredit credits of the student
     */
    public void setTotalCredit(int totalCredit) {
        this.totalCredit = totalCredit;
    }

    /**
     * @return enrolledCourses
     */
    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    /**
     * @param enrolledCourses courses where the student goes
     */
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    /**
     *
     * @return tostring
     */
    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", totalCredit=" + totalCredit +
                ", enrolledCourses=" + enrolledCourses +
                '}';
    }
}
