package com.company.model;

import java.util.List;

/**
 * @author sncam
 */
public class Kurs {

    private String name;
    private Long professorId;
    private int maxEnrolled;
    private long kursId;
    private int credits;
    private List<Long> studentsEnrolled;

    public Kurs(String name, Long professor, int maxEnrolled, long kursId, int credits, List<Long> studentsEnrolled) {
        this.name = name;
        this.professorId = professor;
        this.maxEnrolled = maxEnrolled;
        this.kursId = kursId;
        this.credits = credits;
        this.studentsEnrolled = studentsEnrolled;

    }

    /*
getter and setter
*/

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @return teacher
     */
    public Long getTeacher() {
        return professorId;
    }

    /**
     * @param teacher, person who teach the course
     */
    public void setTeacher(Long teacher) {
        this.professorId = teacher;
    }

    /**
     * @return number max of student in the course
     */
    public int getMaxEnrolled() {
        return maxEnrolled;
    }

    /**
     * @param maxEnrolled number max of student in the course
     */
    public void setMaxEnrolled(int maxEnrolled) {
        this.maxEnrolled = maxEnrolled;
    }

    /**
     * @return course id
     */
    public long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId, id of the teacher
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * @param credits, vale of the course
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * @return students that are enrolled
     */
    public List<Long> getStudentsEnrolled() {
        return studentsEnrolled;
    }

    /**
     * @param studentsEnrolled, list of students thate are enrolled
     */
    public void setStudentsEnrolled(List<Long> studentsEnrolled) {
        this.studentsEnrolled = studentsEnrolled;
    }


    /**
     * @return tostring
     */
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", teacher=" + professorId +
                ", maxEnrolled=" + maxEnrolled +
                ", courseId=" + courseId +
                ", credits=" + credits +
                ", studentsEnrolled=" + studentsEnrolled +
                '}';
    }
}
