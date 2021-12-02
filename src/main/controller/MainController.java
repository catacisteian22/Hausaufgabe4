package main.controller;
import com.company.model.Course;
import main.model.Kurs;
import main.model.Student;
import main.model.Teacher;
import main.exceptions.ControllerExceptions.MainControllerExceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sncam
 */
public class MainController {
    private final KursController kursController;
    private final StudentController studentController;
    private final ProfessorController professorController;


    public MainController(KursController kursController, StudentController studentController, ProfessorController professorController) {
        this.kursController = kursController;
        this.studentController = studentController;
        this.professorController = professorController;
    }
    /**
     *
     * @param studentId id of the student
     * @param courseId  id of the course

     * @return the methode courseAddedToStudent or studentAddedToCourse
     */
    /**
     * @param studentId ID des Studenten
     * @param kursId ID des Kurses
     * @throws MainControllerExceptions ob der ID null ist oder ob die Liste der Studenten leer ist
     * @return die Methode kursAddedToStudent oder studentAddedToKurs
     */
    public boolean registerStudentToKurs(Long studentId, Long kursId) {
        Student student = this.studentController.findStudentById(studentId);
        Kurs kurs = this.kursController.findKursById(kursId);

        if (kurs.getStudentsEnrolled().size() == kurs.getMaxEnrolled())
            throw new MainControllerExceptions("Kurs ist voll");

        if (student.getTotalCredit() + kurs.getCredits() > 30)
            throw new MainControllerExceptions("Zu viele ECTS");

        Boolean kursAddedToStudent = this.studentController.addKursToStudent(studentId, kurs);
        Boolean studentAddedToKurs = this.kursController.addStudentToKurs(kursId, student);

        return kursAddedToStudent | studentAddedToKurs;
    }

    /**
     *
     * @return all the course
     */
    public Iterable<Course> getAllCourses(){
        return this.courseController.getAllCourses();
    }

    /**
     *
     * @param courseId id of the course
     * @return all the student of the course with the matching id
     */
    public Iterable<Student> getAllStudentsByCourseId(Long courseId){
        List<Student> studentList = new ArrayList<>();
        List<Long> studentsId = this.courseController.findCourseById(courseId).getStudentsEnrolled();
        studentsId.forEach(studentId -> studentList.add(this.studentController.findStudentById(studentId)));
        return studentList;
    }

    /**
     *
     * @return all the available courses
     */
    public Iterable<Course> getAllAvailableCourses(){
        return this.courseController.getAvailableCourses();
    }

    /**
     *
     * @param courseName the name of the course
     * @param teacherId the id of the teacher in this course
     * @param maxEnrolled number of max student for this course
     * @param courseId id of this course
     * @param credits the credits of the course
     * @return the updated course
     */
    public boolean updateCourse(String courseName, long teacherId, int maxEnrolled, long courseId, int credits){
        Course existingCourse = this.courseController.findCourseById(courseId);
        Course course = new Course(courseName, teacherId, maxEnrolled, courseId, credits, null);
        if(existingCourse.getCredits() != credits)
        {
            for(Long studentId: existingCourse.getStudentsEnrolled()){
                Student student = studentController.findStudentById(studentId);
                Student newStudent = new Student(student.getName(),student.getFirstName(),student.getStudentId(),student.getTotalCredit(),student.getEnrolledCourses());
                newStudent.getEnrolledCourses().removeIf(course1->course1.getCourseId()==courseId);
                newStudent.getEnrolledCourses().add(course);
                this.studentController.updateStudent(newStudent);
            }
        }
        return this.courseController.updateCourse(course) == null;
    }

    /**
     *
     * @param courseId id of the course
     * @param teacherId id of the teacher
     * @return true or false if the new teacher was updated or not
     */
    public boolean deleteCourseFromTeacher(long courseId, long teacherId){
        Teacher existingTeacher = this.teacherController.findById(teacherId);
        List<Course> newCourseList = existingTeacher.getCourses();
        newCourseList.removeIf(course1->course1.getCourseId()==courseId);

        Teacher newTeacher= new Teacher(existingTeacher.getName(), existingTeacher.getFirstName(), existingTeacher.getTeacherId(), newCourseList);
        Course course = this.courseController.findCourseById(courseId);
        for(Long studentId: course.getStudentsEnrolled()){
            Student student = this.studentController.findStudentById(studentId);
            Student newStudent = new Student(student.getName(),student.getFirstName(),student.getStudentId(),student.getTotalCredit(),student.getEnrolledCourses());
            newStudent.getEnrolledCourses().removeIf(course1 -> course1.getCourseId()==courseId);
            this.studentController.updateStudent(newStudent);
        }
        this.courseController.emptyCourseStudentList(courseId);
        return this.teacherController.updateTeacher(newTeacher) == null;
    }

    /**
     *
     * @return getSortCoursesByName() from course controller;
     */
    public Iterable<Course> getSortCoursesByName(){
        return this.courseController.getSortCoursesByName();
    }

    /**
     *
     * @param maxCredit max amount of credit
     * @return getFilteredCoursesByCreditsMax from course controller
     */
    public Iterable<Course> getFilteredCoursesByCreditsMax(int maxCredit){
        return this.courseController.getFilteredCoursesByCreditsMax(maxCredit);
    }

    /**
     * '
     * @param minCredit min amount of credit
     * @return getFilteredCoursesByCreditsMin from course controller
     */
    public Iterable<Course> getFilteredCoursesByCreditsMin(int minCredit){
        return this.courseController.getFilteredCoursesByCreditsMin(minCredit);
    }

    /**
     *
     * @return getSortStudentsByName() from student controller;
     */
    public Iterable<Student> getSortStudentsByName() {
        return this.studentController.getSortStudentsByName();
    }

    /**
     *
     * @param maxCredit max amount of credit
     * @return getFilteredStudentsByCreditsMax from student controller
     */
    public Iterable<Student> getFilteredStudentsByCreditsMax(int maxCredit){
        return this.studentController.getFilteredStudentsByCreditsMax(maxCredit);
    }

    /**
     * '
     * @param minCredit min amount of credit
     * @return getFilteredStudentsByCreditsMin from student controller
     */
    public Iterable<Student> getFilteredStudentsByCreditsMin(int minCredit){
        return this.studentController.getFilteredStudentsByCreditsMin(minCredit);
    }
}
