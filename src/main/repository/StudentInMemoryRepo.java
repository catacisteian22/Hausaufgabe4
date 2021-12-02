package main.repository;

import main.model.Kurs;
import main.model.Student;
import main.exceptions.RepositoryExceptions.StudentRepoExceptions;

import java.util.List;

public class StudentInMemoryRepo implements CrudRepository<Student> {
    private List<Student> student;

    public StudentInMemoryRepo(List<Student> student) {
        this.student = student;
    }

    /**
     * @param id -die ID der Entity, die zurückgegeben sein muss(muss nicht NULL sein)
     * @return die Entity mit der spezifizierten ID oder NULL (ob keine Entity mit dieser ID existiert)
     * @throws StudentRepoExceptions, ob die ID NULL ist oder die Liste der Studenten leer ist
     */
    @Override
    public Student findOne(Long id) {
        if (student.isEmpty()) {
            throw new StudentRepoExceptions("Error! Liste der Studenten ist leer!");
        }
        if (id == null) {
            throw new StudentRepoExceptions("Error! ID darf nicht NULL sein!");
        } else {
            for (Student s : student) {
                if (id == s.getStudentId()) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     * @return alle Entities
     */
    @Override
    public Iterable<Student> findAll() {
        return student;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die gegebene Entity gespeichert war, andernfalls gibt die Entity zurück(ID existiert schon)
     */
    @Override
    public Student save(Student entity) {
        for (Student s : student) {
            if (s.equals(entity)) {
                return s;
            }
        }
        student.add(entity);
        return null;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return die entfernte Entity oder NULL, ob keine Entity mit der gegebene ID existiert
     * @throws StudentRepoExceptions, ob die Liste der Studenten leer ist
     */
    @Override
    public Student delete(Student entity) {
        if (student.isEmpty()) {
            throw new StudentRepoExceptions("Error! Liste der Studenten ist leer!");
        } else {
            for (Student s : student) {

                if (s.equals(entity)) {
                    student.remove(entity);
                    return entity;
                }
            }
        }
        return null;
    }

    /**
     * @param entity Entity muss nicht NULL sein
     * @return NULL, ob die Entity aktualisiert ist, andernfalls gibt die Entity zurück
     */
    @Override
    public Student update(Student entity) {

        for (Student student : student) {
            if (student.getStudentId() == entity.getStudentId()) {
                student.setLastName(entity.getLastName());
                student.setFirstName(entity.getFirstName());
                student.setTotalCredit(entity.getTotalCredit());
                student.setEnrolledKurse(entity.getEnrolledKurse());
                return null;
            }
        }
        return entity;
    }

    /**
     * @param studentId ID der Student
     * @param kurs      Objekt Kurs
     * @return der Student mit dem Kurs hinzugefügt oder NULL, ob die ID des Studenten nicht gefunden war
     */
    public Student addKurseToStudent(long studentId, Kurs kurs) {
        for (Student student : student) {
            if (studentId == student.getStudentId()) {
                student.getEnrolledKurse().add(kurs);
                student.setTotalCredit(student.getTotalCredit() + kurs.getCredits());
                return student;
            }
        }
        return null;
    }

}
