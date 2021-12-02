package main.repository;

import main.model.Kurs;
import main.model.Professor;
import main.model.Student;
import main.exceptions.RepositoryExceptions.KursRepoExceptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KursFileRepository implements IFileRepository<Kurs> {

    private CrudRepository<Student> studentRepo;
    private CrudRepository<Professor> professorRepo;
    private String fileName;

    public KursFileRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Kurs findOne(Long id) {
        List<Kurs> kurseList = new ArrayList<>();
        this.findAll().forEach(kurseList::add);
        return kurseList.stream().filter(course -> course.getKursId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Iterable<Kurs> findAll() {
        File kursFile = new File(fileName);
        FileReader kursFileReader;
        try {
            kursFileReader = new FileReader(kursFile);
        } catch (Exception e) {
            throw new KursRepoExceptions("file not found");
        }

        BufferedReader kursBufferReader = new BufferedReader(kursFileReader);
        Scanner kursScanner = new Scanner(kursBufferReader);
        List<Kurs> kurseList = new ArrayList<>();
        while (kursScanner.hasNextLine()) {
            String kursLine = kursScanner.nextLine();
            List<String> stringList = List.of(kursLine.split(","));

            String listOfIds = stringList.get(stringList.size() - 1);
            listOfIds = listOfIds.substring(1, listOfIds.length() - 1);

            List<Long> studentIdList = Stream.of(listOfIds.split(";")).map(Long::parseLong).toList();

            Kurs c1 = new Kurs(stringList.get(0), Long.parseLong(stringList.get(1)), Integer.parseInt(stringList.get(2)), Integer.parseInt(stringList.get(3)), Integer.parseInt(stringList.get(3)), studentIdList);
            kurseList.add(c1);
        }
        return kurseList;
    }

    @Override
    public Kurs save(Kurs entity) {
        File kursFile = new File(fileName);
        FileWriter kursFileWriter;
        try {
            kursFileWriter = new FileWriter(kursFile, true);
            BufferedWriter kursBufferWriter = new BufferedWriter(kursFileWriter);
            kursBufferWriter.write(entity.getName() + "," + entity.getProfessor() + "," + entity.getMaxEnrolled() + "," + entity.getKursId() + "," + entity.getCredits() + "\n");
        } catch (Exception e) {
            throw new KursRepoExceptions("file not found");
        }
        return entity;
    }

    @Override
    public Kurs delete(Kurs entity) throws Exception {
        List<Kurs> kurseList = new ArrayList<>();
        this.findAll().forEach(kurseList::add);
        File kursFile = new File(fileName);
        FileWriter kursFileWriter;
        try {
            kursFileWriter = new FileWriter(kursFile, false);
            PrintWriter kursPrintWriter = new PrintWriter(kursFileWriter, false);
            kursPrintWriter.print("");
            kursPrintWriter.close();
            kursFileWriter.close();
        } catch (Exception e) {
            throw new KursRepoExceptions("file not found");
        }

        try {
            kursFileWriter = new FileWriter(kursFile, true);
            BufferedWriter kursBufferWriter = new BufferedWriter(kursFileWriter);
            for (Kurs kurs : kurseList)
                if (kurs.getKursId() != entity.getKursId())
                    kursBufferWriter.write(kurs.getName() + "," + kurs.getProfessor() + "," + kurs.getMaxEnrolled() + "," + kurs.getKursId() + "," + kurs.getCredits() + "\n");
        } catch (Exception e) {
            throw new KursRepoExceptions("file not found");
        }
        return entity;
    }

    @Override
    public Kurs update(Kurs entity) {
        List<Kurs> kurseList = new ArrayList<>();
        this.findAll().forEach(kurseList::add);
        File kursFile = new File(fileName);
        FileWriter kursFileWriter;
        try {
            kursFileWriter = new FileWriter(kursFile, false);
            PrintWriter kursPrintWriter = new PrintWriter(kursFileWriter, false);
            kursPrintWriter.print("");
            kursPrintWriter.close();
            kursFileWriter.close();
        } catch (Exception e) {
            throw new KursRepoExceptions("file not found");
        }

        try {
            kursFileWriter = new FileWriter(kursFile, true);
            BufferedWriter kursBufferWriter = new BufferedWriter(kursFileWriter);
            for (Kurs kurs : kurseList)
                if (kurs.getKursId() != entity.getKursId())
                    kursBufferWriter.write(kurs.getName() + "," + kurs.getProfessor() + "," + kurs.getMaxEnrolled() + "," + kurs.getKursId() + "," + kurs.getCredits() + "\n");
                else
                    kursBufferWriter.write(entity.getName() + "," + entity.getProfessor() + "," + entity.getMaxEnrolled() + "," + entity.getKursId() + "," + entity.getCredits() + "\n");
        } catch (Exception e) {
            throw new KursRepoExceptions("file not found");
        }
        return entity;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    public void setStudentRepo(CrudRepository<Student> studentRepo) {
        this.studentRepo = studentRepo;
    }

    public void setProfessorRepo(CrudRepository<Professor> professorRepo) {
        this.professorRepo = professorRepo;
    }
}
