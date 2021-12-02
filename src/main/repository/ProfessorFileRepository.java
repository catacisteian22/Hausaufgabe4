package main.repository;
import main.model.Kurs;
import main.model.Professor;
import main.exceptions.RepositoryExceptions.StudentRepoExceptions;
import main.exceptions.RepositoryExceptions.ProfessorRepoExceptions;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProfessorFileRepository implements IFileRepository<Professor> {

    private final String fileName;
    private final CrudRepository<Kurs> kursRepository;

    public ProfessorFileRepository(String fileName, CrudRepository<Kurs> kursRepository) {
        this.fileName = fileName;
        this.kursRepository = kursRepository;
    }

    @Override
    public Professor findOne(Long id) throws Exception {
        List<Professor> professorList = new ArrayList<>();
        this.findAll().forEach(professorList::add);
        return professorList.stream().filter(professor -> professor.getProfessorId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Iterable<Professor> findAll() {
        File professorFile = new File(fileName);
        FileReader professorFileReader;
        try {
            professorFileReader = new FileReader(professorFile);
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("Datei nicht gefunden");
        }

        BufferedReader professorBufferReader = new BufferedReader(professorFileReader);
        Scanner professorScanner = new Scanner(professorBufferReader);
        List<Professor> professorList = new ArrayList<>();
        while (professorScanner.hasNextLine()) {
            String professorLine = professorScanner.nextLine();
            List<String> stringList = List.of(professorLine.split(","));
            List<Kurs> professorCourseList = new ArrayList<>();
            kursRepository.findAll()
                    .forEach(kurs -> {
                        if (kurs.getProfessor() == Integer.parseInt(stringList.get(2)))
                            professorCourseList.add(kurs);
                    });

            Professor p1 = new Professor(stringList.get(0), stringList.get(1), Integer.parseInt(stringList.get(2)), professorCourseList);
            professorList.add(p1);
        }

        return professorList;
    }

    @Override
    public Professor save(Professor entity) {
        File professorFile = new File(fileName);
        FileWriter professorFileWriter;
        try {
            professorFileWriter = new FileWriter(professorFile, true);
            BufferedWriter professorBufferWriter = new BufferedWriter(professorFileWriter);
            professorBufferWriter.write(entity.getLastName() + "," + entity.getFirstName() + "," + entity.getProfessorId() + "," + "\n");
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }
        return entity;
    }

    @Override
    public Professor delete(Professor entity) throws Exception {
        List<Professor> professorList = new ArrayList<>();
        this.findAll().forEach(professorList::add);
        File professorFile = new File(fileName);
        FileWriter professorFileWriter;
        try {
            professorFileWriter = new FileWriter(professorFile, false);
            PrintWriter professorPrintWriter = new PrintWriter(professorFileWriter, false);
            professorPrintWriter.print("");
            professorPrintWriter.close();
            professorFileWriter.close();
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("Datei nicht gefunden");
        }

        try {
            professorFileWriter = new FileWriter(professorFile, true);
            BufferedWriter professorBufferWriter = new BufferedWriter(professorFileWriter);
            for (Professor professor : professorList)
                if (professor.getProfessorId() != entity.getProfessorId())
                    professorBufferWriter.write(professor.getFirstName() + "," + professor.getFirstName() + "," + professor.getProfessorId() + "," + "\n");
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("Datei nicht gefunden");
        }
        return entity;
    }

    @Override
    public Professor update(Professor entity) {
        List<Professor> professorList = new ArrayList<>();
        this.findAll().forEach(professorList::add);
        File professorFile = new File(fileName);
        FileWriter professorFileWriter;
        try {
            professorFileWriter = new FileWriter(professorFile, false);
            PrintWriter professorPrintWriter = new PrintWriter(professorFileWriter, false);
            professorPrintWriter.print("");
            professorPrintWriter.close();
            professorFileWriter.close();
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("Datei nicht gefunden");
        }

        try {
            professorFileWriter = new FileWriter(professorFile, true);
            BufferedWriter professorBufferWriter = new BufferedWriter(professorFileWriter);
            for (Professor professor : professorList)
                if (professor.getProfessorId() != entity.getProfessorId())
                    professorBufferWriter.write(professor.getLastName() + "," + professor.getFirstName() + "," + professor.getProfessorId() + "," + "\n");
                else
                    professorBufferWriter.write(entity.getLastName() + "," + entity.getFirstName() + "," + entity.getProfessorId() + "," + "\n");
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("Datei nicht gefunden");
        }
        return entity;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
