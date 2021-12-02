package main.controller;
import main.model.Kurs;
import main.model.Professor;
import main.model.Student;
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
     * @return alle Kurse
     */
    public Iterable<Kurs> getAllKurse() {
        return this.kursController.getAllKurse();
    }

    /**
     * @param kursId ID des Kurses
     * @return alle Studenten des Kurses mit übereinstimmender ID
     */
    public Iterable<Student> getAllStudentsByKursId(Long kursId) {
        List<Student> studentList = new ArrayList<>();
        List<Long> studentsId = this.kursController.findKursById(kursId).getStudentsEnrolled();
        studentsId.forEach(studentId -> studentList.add(this.studentController.findStudentById(studentId)));
        return studentList;
    }

    /**
     * @return alle Kurse mit freie Plätze
     */
    public Iterable<Kurs> getAllAvailableKurse() {
        return this.kursController.getAvailableKurse();
    }

    /**
     * @param kursName    Name des Kurses
     * @param professorId ID des Professors für diesen Kurs
     * @param maxEnrolled maximale Anzahl von Studenten für diesen Kurs
     * @param kursId      ID des Kurses
     * @param credits     die ECTS des Kurses
     * @return der aktualisierte Kurs
     */
    public boolean updateKurs(String kursName, long professorId, int maxEnrolled, long kursId, int credits) {
        Kurs existingKurs = this.kursController.findKursById(kursId);
        Kurs kurs = new Kurs(kursName, professorId, maxEnrolled, kursId, credits, null);
        if (existingKurs.getCredits() != credits) {
            for(Long studentId: existingKurs.getStudentsEnrolled()){
                Student student = studentController.findStudentById(studentId);
                Student newStudent = new Student(student.getLastName(),student.getFirstName(),student.getStudentId(),student.getTotalCredit(),student.getEnrolledKurse());
                newStudent.getEnrolledKurse().removeIf(course1->course1.getKursId()==kursId);
                newStudent.getEnrolledKurse().add(kurs);
                this.studentController.updateStudent(newStudent);
            }
        }
        return this.kursController.updateKurs(kurs) == null;
    }

    /**
     * @param kursId      ID des Kurses
     * @param professorId ID des Professors
     * @return True or False, ob der neue Professor war aktualisiert oder nicht
     */
    public boolean deleteKursFromProfessor(long kursId, long professorId) {
        Professor existingProfessor = this.professorController.findById(professorId);
        List<Kurs> newKursList = existingProfessor.getKurse();
        newKursList.removeIf(kurs1 -> kurs1.getKursId() == kursId);

        Professor newProfessor = new Professor(existingProfessor.getLastName(), existingProfessor.getFirstName(), existingProfessor.getProfessorId(), newKursList);
        Kurs kurs = this.kursController.findKursById(kursId);
        for(Long studentId: kurs.getStudentsEnrolled()){
            Student student = this.studentController.findStudentById(studentId);
            Student newStudent = new Student(student.getLastName(), student.getFirstName(), student.getStudentId(), student.getTotalCredit(), student.getEnrolledKurse());
            newStudent.getEnrolledKurse().removeIf(course1 -> course1.getKursId()==kursId);
            this.studentController.updateStudent(newStudent);
        }
        this.kursController.emptyKursStudentList(kursId);
        return this.professorController.updateProfessor(newProfessor) == null;
    }

    /**
     *
     * @return getSortierteKurseByName() vom KursController;
     */
    public Iterable<Kurs> getSortierteKurseByName(){
        return this.kursController.getSortierteKurseByName();
    }

    /**
     * @param maxCredit maximale Anzahl von ECTS, die ein Kurs haben kann
     * @return getGefilterteKurseByCreditsMax vom KursController
     */
    public Iterable<Kurs> getGefilterteKurseByCreditsMax(int maxCredit){
        return this.kursController.getGefilterteKurseByCreditsMax(maxCredit);
    }

    /**
     * @param minCredit minimale Anzahl von ECTS, die ein Kurs haben kann
     * @return getGefilterteKurseByCreditsMin vom KursController
     */
    public Iterable<Kurs> getGefilterteKurseByCreditsMin(int minCredit){
        return this.kursController.getGefilterteKurseByCreditsMin(minCredit);
    }

    /**
     *
     * @return getSortiertStudentenByName() vom StudentController;
     */
    public Iterable<Student> getSortiertStudentenByName() {
        return this.studentController.getSortiertStudentenByName();
    }

    /**
     *
     * @param maxCredit max amount of credit
     * @return getFilteredStudentsByCreditsMax from student controller
     */
    public Iterable<Student> getGefilterteStudentenByCreditsMax(int maxCredit){
        return this.studentController.getGefilterteStudentenByCreditsMax(maxCredit);
    }

    /**
     * '
     * @param minCredit min amount of credit
     * @return getFilteredStudentsByCreditsMin from student controller
     */
    public Iterable<Student> getGefilterteStudentenByCreditsMin(int minCredit){
        return this.studentController.getGefilterteStudentenByCreditsMin(minCredit);
    }
}
