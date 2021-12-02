package main.repository;
import com.company.model.Course;
import main.model.Teacher;
import main.exceptions.RepositoryExceptions.StudentRepoExceptions;
import main.exceptions.RepositoryExceptions.ProfessorRepoExceptions;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProfessorFileRepository implements IFileRepository<Teacher> {

    private final String fileName;
    private final CrudRepository<Course> courseRepository;

    public ProfessorFileRepository(String fileName, CrudRepository<Course> courseRepository) {
        this.fileName = fileName;
        this.courseRepository = courseRepository;
    }

    @Override
    public Teacher findOne(Long id) throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        this.findAll().forEach(teacherList::add);
        return teacherList.stream().filter(teacher -> teacher.getTeacherId() == id).collect(Collectors.toList()).get(0);
    }

    @Override
    public Iterable<Teacher> findAll() {
        File teacherFile = new File(fileName);
        FileReader teacherFileReader;
        try {
            teacherFileReader = new FileReader(teacherFile);
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("file not found");
        }

        BufferedReader teacherBufferReader = new BufferedReader(teacherFileReader);
        Scanner teacherScanner = new Scanner(teacherBufferReader);
        List<Teacher> teacherList = new ArrayList<>();
        while (teacherScanner.hasNextLine()) {
            String teacherLine = teacherScanner.nextLine();
            List<String> stringList = List.of(teacherLine.split(","));
            List<Course> teacherCourseList = new ArrayList<>();
            courseRepository.findAll()
                    .forEach(course -> {
                        if (course.getTeacher() == Integer.parseInt(stringList.get(2)))
                            teacherCourseList.add(course);
                    });

            Teacher t1 = new Teacher(stringList.get(0), stringList.get(1), Integer.parseInt(stringList.get(2)), teacherCourseList);
            teacherList.add(t1);
        }

        return teacherList;
    }

    @Override
    public Teacher save(Teacher entity) {
        File teacherFile = new File(fileName);
        FileWriter teacherFileWriter;
        try {
            teacherFileWriter = new FileWriter(teacherFile, true);
            BufferedWriter teacherBufferWriter = new BufferedWriter(teacherFileWriter);
            teacherBufferWriter.write(entity.getName() + "," + entity.getFirstName() + "," + entity.getTeacherId() + "," + "\n");
        } catch (Exception e) {
            throw new StudentRepoExceptions("file not found");
        }
        return entity;
    }

    @Override
    public Teacher delete(Teacher entity) throws Exception {
        List<Teacher> teacherList = new ArrayList<>();
        this.findAll().forEach(teacherList::add);
        File teacherFile = new File(fileName);
        FileWriter teacherFileWriter;
        try {
            teacherFileWriter = new FileWriter(teacherFile, false);
            PrintWriter teacherPrintWriter = new PrintWriter(teacherFileWriter, false);
            teacherPrintWriter.print("");
            teacherPrintWriter.close();
            teacherFileWriter.close();
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("file not found");
        }

        try {
            teacherFileWriter = new FileWriter(teacherFile, true);
            BufferedWriter teacherBufferWriter = new BufferedWriter(teacherFileWriter);
            for (Teacher teacher : teacherList)
                if (teacher.getTeacherId() != entity.getTeacherId())
                    teacherBufferWriter.write(teacher.getName() + "," + teacher.getFirstName() + "," + teacher.getTeacherId() + "," + "\n");
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("file not found");
        }
        return entity;
    }

    @Override
    public Teacher update(Teacher entity) {
        List<Teacher> teacherList = new ArrayList<>();
        this.findAll().forEach(teacherList::add);
        File teacherFile = new File(fileName);
        FileWriter teacherFileWriter;
        try {
            teacherFileWriter = new FileWriter(teacherFile, false);
            PrintWriter teacherPrintWriter = new PrintWriter(teacherFileWriter, false);
            teacherPrintWriter.print("");
            teacherPrintWriter.close();
            teacherFileWriter.close();
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("file not found");
        }

        try {
            teacherFileWriter = new FileWriter(teacherFile, true);
            BufferedWriter teacherBufferWriter = new BufferedWriter(teacherFileWriter);
            for (Teacher teacher : teacherList)
                if (teacher.getTeacherId() != entity.getTeacherId())
                    teacherBufferWriter.write(teacher.getName() + "," + teacher.getFirstName() + "," + teacher.getTeacherId() + "," + "\n");
                else
                    teacherBufferWriter.write(entity.getName() + "," + entity.getFirstName() + "," + entity.getTeacherId() + "," + "\n");
        } catch (Exception e) {
            throw new ProfessorRepoExceptions("file not found");
        }
        return entity;
    }

    @Override
    public String getFileName() {
        return fileName;
    }
}
