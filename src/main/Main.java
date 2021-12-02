package main;

import main.controller.CourseController;
import main.controller.MainController;
import main.controller.StudentController;
import main.controller.ProfessorController;
import main.model.Professor;
import main.model.Student;
import main.model.Teacher;
import com.company.repository.*;
import main.view.AnmeldungSystem;
import main.repository.KursFileRepository;
import main.repository.CrudRepository;
import main.repository.StudentFileRepository;
import main.repository.ProfessorFileRepository;

public class Main {

    public static void main(String[] args) {

        KursFileRepository kursRepo = new KursFileRepository("D:\\University\\Info\\materii\\MAP\\tema\\tema3\\src\\com\\company\\RepoData\\Course.txt");
        CrudRepository<Student> studentRepo = new StudentFileRepository("D:\\University\\Info\\materii\\MAP\\tema\\tema3\\src\\com\\company\\RepoData\\Student.txt", kursRepo);
        CrudRepository<Professor> professorRepo = new ProfessorFileRepository("D:\\University\\Info\\materii\\MAP\\tema\\tema3\\src\\com\\company\\RepoData\\Teacher.txt",kursRepo);
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
