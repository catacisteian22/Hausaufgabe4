package main.model;

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
    public Long getProfessor() {
        return professorId;
    }

    /**
     * @param professor, person who teach the course
     */
    public void setProfessor(Long professor) {
        this.professorId = professor;
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
    public long getKursId() {
        return kursId;
    }

    /**
     * @param kursId, id of the teacher
     */
    public void setKursId(long kursId) {
        this.kursId = kursId;
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
        return "Kurs{" +
                "name='" + name + '\'' +
                ", Professor=" + professorId +
                ", maxEnrolled=" + maxEnrolled +
                ", kursId=" + kursId +
                ", credits=" + credits +
                ", studentsEnrolled=" + studentsEnrolled +
                '}';
    }
}
