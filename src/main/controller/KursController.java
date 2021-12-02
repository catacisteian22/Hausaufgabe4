package main.controller;

import main.exceptions.ControllerExceptions.ControllerExceptions;
import main.model.Kurs;
import main.model.Student;
import main.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KursController {

    private final CrudRepository<Kurs> repository;

    public KursController(CrudRepository<Kurs> kursInMemoryRepo) {
        this.repository = kursInMemoryRepo;
    }

    /**
     * @param kursId ID des Kurses
     * @return der Kurs mit derselbe Id
     */
    public Kurs findKursById(Long kursId) {
        try {
            return this.repository.findOne(kursId);
        } catch (Exception e) {
            throw new ControllerExceptions(e.getMessage());
        }

    }

    /**
     * @param kursId  ID des Kurses
     * @param student Objekt Student
     * @return True oder False, ob der Student zu einem Kurs hinzugefügt war oder nicht
     */
    public Boolean addStudentToKurs(Long kursId, Student student) {
        try {
            Kurs updatedKurs = this.repository.findOne(kursId);
            updatedKurs.getStudentsEnrolled().add(student.getStudentId());
            this.repository.update(updatedKurs);
        } catch (Exception e) {
            throw new ControllerExceptions("Error");
        }
        return true;
    }

    /**
     * @return alle Kurse
     */
    public Iterable<Kurs> getAllKurse() {
        return this.repository.findAll();
    }

    /**
     * @return alle Kurse mit freie Plätze
     */
    public Iterable<Kurs> getAvailableKurse() {
        Iterable<Kurs> kursList = this.repository.findAll();
        List<Kurs> availableKurs = new ArrayList<>();
        for (Kurs c : kursList) {
            if (c.getStudentsEnrolled().size() < c.getMaxEnrolled()) {
                availableKurs.add(c);
            }
        }
        return availableKurs;
    }

    /**
     * @param kurs Objekt Kurs
     * @return die aktualisierte Kurse
     */
    public Kurs updateKurs(Kurs kurs) {
        return this.repository.update(kurs);
    }

    /**
     * lauf die Methode emptyList aus dem Repository an
     *
     * @param kursId ID des Kurses
     */
    public void emptyKursStudentList(Long kursId) {
        try {
            Kurs updatedKurs = this.repository.findOne(kursId);
            updatedKurs.setStudentsEnrolled(new ArrayList<>());
            this.repository.update(updatedKurs);
        } catch (Exception e) {
            throw new ControllerExceptions("Error");
        }
    }

    /**
     * @return sortierte Liste von Kurse
     */
    public List<Kurs> getSortierteKurseByName() {
        List<Kurs> kurseList = (List<Kurs>) this.repository.findAll();
        return kurseList.stream().sorted(Comparator.comparing(Kurs::getName)).toList();
    }

    /**
     * @param maxCredit maximale Anzahl von ECTS, die ein Kurs haben kann
     * @return die gefilterte Liste von Kurse
     */
    public List<Kurs> getGefilterteKurseByCreditsMax(int maxCredit) {
        List<Kurs> kurseList = (List<Kurs>) this.repository.findAll();
        return kurseList.stream().filter(s1 -> s1.getCredits() < maxCredit).toList();
    }

    /**
     * @param minCredit minimale Anzahl von ECTS, die ein Kurs haben kann
     * @return die gefilterte Liste von Kurse
     */
    public List<Kurs> getGefilterteKurseByCreditsMin(int minCredit) {
        List<Kurs> kurseList = (List<Kurs>) this.repository.findAll();
        return kurseList.stream().filter(s1 -> s1.getCredits() > minCredit).toList();
    }

}

