package main;

import main.controller.KursController;
import main.controller.MainController;
import main.controller.StudentController;
import main.controller.ProfessorController;
import main.model.Professor;
import main.model.Student;
import main.view.AnmeldungSystem;
import main.repository.KursFileRepository;
import main.repository.CrudRepository;
import main.repository.StudentFileRepository;
import main.repository.ProfessorFileRepository;

public class Main {

    public static void main(String[] args) {

        KursFileRepository kursRepo = new KursFileRepository("C:\\Users\\catac\\OneDrive\\Desktop\\Scoala\\MAP\\Labor_Hausaufgaben\\Hausaufgabe4\\src\\main\\dateien\\Kurs.txt");
        CrudRepository<Student> studentRepo = new StudentFileRepository("C:\\Users\\catac\\OneDrive\\Desktop\\Scoala\\MAP\\Labor_Hausaufgaben\\Hausaufgabe4\\src\\main\\dateien\\Student.txt", kursRepo);
        CrudRepository<Professor> professorRepo = new ProfessorFileRepository("C:\\Users\\catac\\OneDrive\\Desktop\\Scoala\\MAP\\Labor_Hausaufgaben\\Hausaufgabe4\\src\\main\\dateien\\Professor.txt",kursRepo);
        kursRepo.setStudentRepo(studentRepo);
        kursRepo.setProfessorRepo(professorRepo);


        KursController kursController = new KursController(kursRepo);
        ProfessorController professorController = new ProfessorController(professorRepo);
        StudentController studentController = new StudentController(studentRepo);

        MainController mainController = new MainController(kursController, studentController, professorController);

        AnmeldungSystem anmeldungSystem = new AnmeldungSystem(mainController);
        anmeldungSystem.startApplication();
    }
}
