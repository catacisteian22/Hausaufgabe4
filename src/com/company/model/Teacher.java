package com.company.model;

import java.util.List;

/**
 * @author sncam
 */
public class Teacher extends Person {

    private long teacherId;
    private List<Course> courses;

    public Teacher(String name, String firstName, long professorId, List<Course> courses) {
        super(name, firstName);
        this.teacherId = professorId;
        this.courses = courses;
    }

    /*
    getter and setter
    */

    /**
     * @return teacher id
     */
    public long getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId, id of the teacher
     */
    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return courses
     */
    public List<Course> getCourses() {
        return courses;
    }

    /**
     * @param courses courses teched by the teacher
     */
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     *
     * @return tostring
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", courses=" + courses +
                '}';
    }
}