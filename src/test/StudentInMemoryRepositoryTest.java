package test;

import main.controller.KursController;
import main.controller.MainController;
import main.controller.ProfessorController;
import main.controller.StudentController;
import main.model.Kurs;
import main.model.Professor;
import main.model.Student;
import main.repository.KursInMemoryRepo;
import main.repository.ProfessorInMemoryRepo;
import main.repository.StudentInMemoryRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StudentInMemoryRepositoryTest {

    @Test
    void findOne() {
        Student s1 = new Student("S1", "SF1", 1, 0, null);
        Student s2 = new Student("S2", "SF2", 2, 0, null);
        Student s3 = new Student("S3", "SF3", 3, 0, null);
        StudentInMemoryRepo studentInMemoryRepo = new StudentInMemoryRepo(Arrays.asList(s1, s2, s3));

        Student student = studentInMemoryRepo.findOne(Long.parseLong("1"));
        assertNotNull(student);
    }

    @Test
    void findAll() {
        Student s1 = new Student("S1", "SF1", 1, 0, null);
        Student s2 = new Student("S2", "SF2", 2, 0, null);
        Student s3 = new Student("S3", "SF3", 3, 0, null);
        StudentInMemoryRepo studentInMemoryRepo = new StudentInMemoryRepo(Arrays.asList(s1, s2, s3));

        List<Student> studentList = (List<Student>) studentInMemoryRepo.findAll();
        assertEquals(3, studentList.size());
    }

    @Test
    void save() {
        StudentInMemoryRepo studentInMemoryRepo = new StudentInMemoryRepo(new ArrayList<>());

        Student s4 = new Student("S4", "SF4", 4, 0, null);
        studentInMemoryRepo.save(s4);

        List<Student> studentList = (List<Student>) studentInMemoryRepo.findAll();
        assertEquals(1, studentList.size());
    }

    @Test
    void delete() {
        Student s1 = new Student("S1", "SF1", 1, 0, null);
        Student s2 = new Student("S2", "SF2", 2, 0, null);
        Student s3 = new Student("S3", "SF3", 3, 0, null);
        StudentInMemoryRepo studentInMemoryRepo = new StudentInMemoryRepo(new ArrayList<>());

        studentInMemoryRepo.save(s1);
        studentInMemoryRepo.save(s2);
        studentInMemoryRepo.save(s3);

        List<Student> studentList = (List<Student>) studentInMemoryRepo.findAll();
        assertEquals(3, studentList.size());

        studentInMemoryRepo.delete(s2);
        assertEquals(2, studentList.size());
    }

    @Test
    void update() {
        Student s1 = new Student("S1", "SF1", 1, 0, null);
        StudentInMemoryRepo studentInMemoryRepo = new StudentInMemoryRepo(List.of(s1));
        Student s2 = new Student("S2", "SF2", 1, 0, null);

        studentInMemoryRepo.update(s2);
        assertEquals("SF2", studentInMemoryRepo.findOne(Long.parseLong("1")).getLastName());
    }

    @Test
    void addKurseToStudent() {
        List<Long> listStudentsCourse1 = new ArrayList<>();
        List<Long> listStudentsCourse2 = new ArrayList<>();
        List<Long> listStudentsCourse3 = new ArrayList<>();

        List<Kurs> listCoursesTeacher1 = new ArrayList<>();
        List<Kurs> listCoursesTeacher2 = new ArrayList<>();
        List<Kurs> listCoursesTeacher3 = new ArrayList<>();

        List<Kurs> listCoursesStudent1 = new ArrayList<>();
        List<Kurs> listCoursesStudent2 = new ArrayList<>();
        List<Kurs> listCoursesStudent3 = new ArrayList<>();


        Student s1 = new Student("S1", "SF1", 1, 0, null);
        Student s2 = new Student("S2", "SF2", 2, 0, null);
        Student s3 = new Student("S3", "SF3", 3, 0, null);

        Professor t1 = new Professor("T1", "TF1", 1, null);
        Professor t2 = new Professor("T2", "TF2", 2, null);
        Professor t3 = new Professor("T3", "TF3", 3, null);

        Kurs c1 = new Kurs("C1", null, 100, 1, 6, null);
        Kurs c2 = new Kurs("C2", null, 2, 2, 25, null);
        Kurs c3 = new Kurs("C3", null, 50, 3, 4, null);


        listCoursesStudent1.add(c1);
        listCoursesStudent1.add(c3);

        listCoursesStudent2.add(c2);
        listCoursesStudent2.add(c3);

        listCoursesStudent3.add(c1);

        s1.setEnrolledKurse(listCoursesStudent1);
        for(Kurs kurs: listCoursesStudent1)
            s1.setTotalCredit(s1.getTotalCredit()+ kurs.getCredits());

        s2.setEnrolledKurse(listCoursesStudent2);
        for(Kurs kurs: listCoursesStudent2)
            s2.setTotalCredit(s2.getTotalCredit()+ kurs.getCredits());

        s3.setEnrolledKurse(listCoursesStudent3);
        for(Kurs kurs: listCoursesStudent3)
            s3.setTotalCredit(s3.getTotalCredit()+ kurs.getCredits());

        listCoursesTeacher1.add(c1);
        listCoursesTeacher2.add(c2);
        listCoursesTeacher3.add(c3);

        t1.setKurse(listCoursesTeacher1);
        t2.setKurse(listCoursesTeacher2);
        t3.setKurse(listCoursesTeacher3);

        listStudentsCourse1.add(s1.getStudentId());
        listStudentsCourse1.add(s2.getStudentId());


        listStudentsCourse2.add(s1.getStudentId());

        listStudentsCourse3.add(s2.getStudentId());
        listStudentsCourse3.add(s3.getStudentId());

        c1.setStudentsEnrolled(listStudentsCourse1);
        c2.setStudentsEnrolled(listStudentsCourse2);
        c3.setStudentsEnrolled(listStudentsCourse3);


        List<Kurs> courseList = new ArrayList<>(Arrays.asList(c1,c2,c3));
        List<Student> studentList = new ArrayList<>(Arrays.asList(s1,s2,s3));
        List<Professor> teacherList = new ArrayList<>(Arrays.asList(t1,t2,t3));

        KursInMemoryRepo courseRepo = new KursInMemoryRepo(courseList);
        ProfessorInMemoryRepo teacherRepo = new ProfessorInMemoryRepo(teacherList);
        StudentInMemoryRepo studentRepo = new StudentInMemoryRepo(studentList);

        KursController courseController = new KursController(courseRepo);
        ProfessorController teacherController = new ProfessorController(teacherRepo);
        StudentController studentController = new StudentController(studentRepo);

        MainController mainController = new MainController(courseController, studentController, teacherController);
        mainController.registerStudentToKurs(Long.parseLong("3"), (Long.parseLong("1")));

        List<Student> newCourseList = (List<Student>) mainController.getAllStudentsByKursId(Long.parseLong("1"));
        assertEquals(3,newCourseList.size());
    }
}
