package test;

import main.model.Professor;
import main.repository.ProfessorInMemoryRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorInMemoryRepositoryTest {

    @Test
    void findOne() {
        Professor t1 = new Professor("T1", "TF1", 1, null);
        Professor t2 = new Professor("T2", "TF2", 2, null);
        Professor t3 = new Professor("T3", "TF3", 3, null);
        ProfessorInMemoryRepo professorInMemoryRepo = new ProfessorInMemoryRepo(Arrays.asList(t1, t2, t3));

        Professor professor = professorInMemoryRepo.findOne(Long.parseLong("1"));
        assertNotNull(professor);
    }

    @Test
    void findAll() {

        Professor t1 = new Professor("T1", "TF1", 1, null);
        Professor t2 = new Professor("T2", "TF2", 2, null);
        Professor t3 = new Professor("T3", "TF3", 3, null);
        ProfessorInMemoryRepo professorInMemoryRepo = new ProfessorInMemoryRepo(Arrays.asList(t1, t2, t3));

        List<Professor> professorList = (List<Professor>) professorInMemoryRepo.findAll();
        assertEquals(3, professorList.size());
    }

    @Test
    void save() {
        ProfessorInMemoryRepo professorInMemoryRepo = new ProfessorInMemoryRepo(new ArrayList<>());

        Professor t4 = new Professor("T4", "TF4", 4, null);
        professorInMemoryRepo.save(t4);

        List<Professor> professorList = (List<Professor>) professorInMemoryRepo.findAll();
        assertEquals(1, professorList.size());
    }

    @Test
    void delete() {
        Professor t1 = new Professor("T1", "TF1", 1, null);
        Professor t2 = new Professor("T2", "TF2", 2, null);
        Professor t3 = new Professor("T3", "TF3", 3, null);
        ProfessorInMemoryRepo professorInMemoryRepo = new ProfessorInMemoryRepo(new ArrayList<>());
        professorInMemoryRepo.save(t1);
        professorInMemoryRepo.save(t2);
        professorInMemoryRepo.save(t3);

        List<Professor> professorList = (List<Professor>) professorInMemoryRepo.findAll();
        assertEquals(3, professorList.size());

        professorInMemoryRepo.delete(t2);
        assertEquals(2, professorList.size());
    }

    @Test
    void update() {
        Professor t1 = new Professor("T1", "TF1", 1, null);
        ProfessorInMemoryRepo professorInMemoryRepo = new ProfessorInMemoryRepo(List.of(t1));
        Professor t2 = new Professor("T2", "TF2", 1, null);

        professorInMemoryRepo.update(t2);
        assertEquals("T2", professorInMemoryRepo.findOne(Long.parseLong("1")).getFirstName());
    }
}