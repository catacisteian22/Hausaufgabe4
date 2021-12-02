package main.repository;
import main.model.Kurs;
import main.model.Student;
import main.exceptions.RepositoryExceptions.StudentRepoExceptions;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentFileRepository implements IFileRepository<Student> {

    private final String fileName;
    private final CrudRepository<Kurs> kursRepository;

    public StudentFileRepository(String fileName, CrudRepository<Kurs> kursRepository) {
        this.fileName = fileName;
        this.kursRepository = kursRepository;
    }

    @Override
    public Student findOne(Long id){
        List<Student> studentList = new ArrayList<>();
        this.findAll().forEach(studentList::add);
        return studentList.stream().filter(student -> student.getStudentId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Iterable<Student> findAll() {
        File studentFile = new File(fileName);
        FileReader studentFileReader;
        try {
            studentFileReader = new FileReader(studentFile);
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }

        BufferedReader studentBufferReader = new BufferedReader(studentFileReader);
        Scanner studentScanner = new Scanner(studentBufferReader);
        List<Student> studentList = new ArrayList<>();
        while (studentScanner.hasNextLine()) {
            String studentLine = studentScanner.nextLine();
            List<String> stringList = List.of(studentLine.split(","));
            List<Kurs> studentKurseList = new ArrayList<>();
            kursRepository.findAll()
                    .forEach(kurs -> {
                        if (kurs.getStudentsEnrolled().stream().filter(student ->
                                student == Integer.parseInt(stringList.get(2))).toList().size() != 0)
                            studentKurseList.add(kurs);
                    });

            Student s1 = new Student(stringList.get(0), stringList.get(1), Integer.parseInt(stringList.get(2)), Integer.parseInt(stringList.get(3)), studentKurseList);
            studentList.add(s1);
        }

        studentScanner.close();
        try{
            studentBufferReader.close();
            studentFileReader.close();
        }
        catch(Exception e){
            throw new StudentRepoExceptions("Die Datei konnte nicht richtig geschlossen werden");
        }


        return studentList;
    }

    @Override
    public Student save(Student entity) {
        File studentFile = new File(fileName);
        FileWriter studentFileWriter;
        try {
            studentFileWriter = new FileWriter(studentFile, true);
            BufferedWriter studentBufferWriter = new BufferedWriter(studentFileWriter);
            studentBufferWriter.write(entity.getLastName() + "," + entity.getFirstName() + "," + entity.getStudentId() + "," + entity.getTotalCredit() + "\n");
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }
        return entity;
    }

    @Override
    public Student delete(Student entity) throws Exception {

        List<Student> studentList = new ArrayList<>();
        this.findAll().forEach(studentList::add);
        File studentFile = new File(fileName);
        FileWriter studentFileWriter;
        try {
            studentFileWriter = new FileWriter(studentFile, false);
            PrintWriter studentPrintWriter = new PrintWriter(studentFileWriter, false);
            studentPrintWriter.print("");
            studentPrintWriter.close();
            studentFileWriter.close();
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }

        try {
            studentFileWriter = new FileWriter(studentFile, true);
            BufferedWriter studentBufferWriter = new BufferedWriter(studentFileWriter);
            for (Student student : studentList)
                if (student.getStudentId() != entity.getStudentId())
                    studentBufferWriter.write(student.getLastName() + "," + student.getFirstName() + "," + student.getStudentId() + "," + student.getTotalCredit() + "\n");
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }
        return entity;
    }

    @Override
    public Student update(Student entity) {

        List<Student> studentList = new ArrayList<>();
        this.findAll().forEach(studentList::add);
        File studentFile = new File(fileName);
        FileWriter studentFileWriter;
        try {
            studentFileWriter = new FileWriter(studentFile, false);
            PrintWriter studentPrintWriter = new PrintWriter(studentFileWriter, false);
            studentPrintWriter.print("");
            studentPrintWriter.close();
            studentFileWriter.close();
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }

        try {
            studentFileWriter = new FileWriter(studentFile, true);
            BufferedWriter studentBufferWriter = new BufferedWriter(studentFileWriter);
            for (Student student : studentList)
                if (student.getStudentId() != entity.getStudentId())
                    studentBufferWriter.write(student.getLastName() + "," + student.getFirstName() + "," + student.getStudentId() + "," + student.getTotalCredit() + "\n");
                else
                    studentBufferWriter.write(entity.getLastName() + "," + entity.getFirstName() + "," + entity.getStudentId() + "," + entity.getTotalCredit() + "\n");
        } catch (Exception e) {
            throw new StudentRepoExceptions("Datei nicht gefunden");
        }
        return entity;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
