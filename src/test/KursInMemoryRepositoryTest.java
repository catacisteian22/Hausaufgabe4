//package test;
//
//import main.controller.KursController;
//import main.controller.MainController;
//import main.controller.ProfessorController;
//import main.controller.StudentController;
//import main.model.Kurs;
//import main.model.Professor;
//import main.model.Student;
//import main.repository.KursInMemoryRepo;
//import main.repository.ProfessorInMemoryRepo;
//import main.repository.StudentInMemoryRepo;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class KursInMemoryRepositoryTest {
//
//    @Test
//    void findOne() {
//
//        Kurs c1 = new Kurs("C1", null, 100, 1, 6, null);
//        Kurs c2 = new Kurs("C2", null, 2, 2, 25, null);
//        Kurs c3 = new Kurs("C3", null, 50, 3, 4, null);
//        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(Arrays.asList(c1,c2,c3));
//
//        Kurs kurs = kursInMemoryRepo.findOne(Long.parseLong("1"));
//        assertNotNull(kurs);
//
//    }
//
//    @Test
//    void findAll() {
//
//        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
//        Kurs c2 = new Kurs("C2", null, 2, 2, null, 25);
//        Kurs c3 = new Kurs("C3", null, 50, 3, null, 4);
//        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(Arrays.asList(c1, c2, c3));
//
//        List<Kurs> kursList = (List<Kurs>) kursInMemoryRepo.findAll();
//        assertEquals(3, kursList.size());
//    }
//
//    @Test
//    void save() {
//        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(new ArrayList<>());
//
//        Kurs c4 = new Kurs("C4", null, 60, 4, null, 12);
//        kursInMemoryRepo.save(c4);
//
//        List<Kurs> kursList = (List<Kurs>) kursInMemoryRepo.findAll();
//        assertEquals(1, kursList.size());
//    }
//
//    @Test
//    void delete() {
//        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
//        Kurs c2 = new Kurs("C2", null, 2, 2, null, 25);
//        Kurs c3 = new Kurs("C3", null, 50, 3, null, 4);
//        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(new ArrayList<>());
//        kursInMemoryRepo.save(c1);
//        kursInMemoryRepo.save(c2);
//        kursInMemoryRepo.save(c3);
//
//        List<Kurs> kursList = (List<Kurs>) kursInMemoryRepo.findAll();
//        assertEquals(3, kursList.size());
//
//        kursInMemoryRepo.delete(c2);
//        assertEquals(2, kursList.size());
//    }
//
//    @Test
//    void update() {
//        Kurs c1 = new Kurs("C1", null, 100, 1, null, 6);
//        KursInMemoryRepo kursInMemoryRepo = new KursInMemoryRepo(List.of(c1));
//        Kurs c2 = new Kurs("C2", null, 2, 1, null, 25);
//
//        kursInMemoryRepo.update(c2);
//        assertEquals("C2", kursInMemoryRepo.findOne(Long.parseLong("1")).getName());
//    }
//
//    @Test
//    void addStudentToCourse() {
//
//    }
//}