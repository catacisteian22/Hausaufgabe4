package main.controller;

import main.model.Kurs;
import main.model.Student;
import main.exceptions.ControllerExceptions.ControllerExceptions;
import main.repository.CrudRepository;

import java.util.List;

public class StudentController {
    private CrudRepository<Student> repository;

    public StudentController(CrudRepository<Student> studentRepo) {
        this.repository = studentRepo;
    }

    /**
     * @param studentId ID des Studenten
     * @return der Student mit dieselbe ID
     */
    public Student findStudentById(Long studentId) {
        try {
            return this.repository.findOne(studentId);
        } catch (Exception e) {
            throw new ControllerExceptions("Error");
        }
    }

    /**
     * @param studentId ID des Studenten
     * @param kurs      Objekt Kurs
     * @return True oder False, ob der Kurs war yu den Studenten hinzugefügt
     */
    public Boolean addKursToStudent(Long studentId, Kurs kurs) {
        try {
            Student updatedStudent = this.repository.findOne(studentId);
            updatedStudent.getEnrolledKurse().add(kurs);
            this.repository.update(updatedStudent);
        } catch (Exception e) {
            throw new ControllerExceptions("Error");
        }
        return true;
    }

    /**
     * @param student Objekt Student
     * @return der aktualisierte Student
     */
    public Student updateStudent(Student student) {
        return this.repository.update(student);
    }

    /**
     * @return sortierte List von Studenten
     */
    public List<Student> getSortiertStudentenByName() {
        List<Student> studentList = (List<Student>) this.repository.findAll();
        return studentList.stream().sorted((Student s1, Student s2) -> s1.getLastName().compareTo(s2.getLastName())).toList();
    }

    /**
     * @param maxCredit maximale Anzahl von ECTS, die ein Kurs haben kann
     * @return die gefilterte Liste von Studenten
     */
    public List<Student> getGefilterteStudentenByCreditsMax(int maxCredit) {
        List<Student> studentList = (List<Student>) this.repository.findAll();
        return studentList.stream().filter(s1 -> s1.getTotalCredit() < maxCredit).toList();
    }

    /**
     * @param minCredit minimale Anzahl von ECTS, die ein Kurs haben kann
     * @return die gefilterte Liste von Studenten
     */
    public List<Student> getGefilterteStudentenByCreditsMin(int minCredit) {
        List<Student> studentList = (List<Student>) this.repository.findAll();
        return studentList.stream().filter(s1 -> s1.getTotalCredit() > minCredit).toList();
    }
}
