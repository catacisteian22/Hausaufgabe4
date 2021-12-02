package test.java.com.company.repository;

import com.company.controller.CourseController;
import com.company.controller.MainController;
import com.company.controller.StudentController;
import com.company.controller.TeacherController;
import com.company.model.Course;
import com.company.model.Student;
import com.company.model.Teacher;
import com.company.repository.CourseInMemoryRepo;
import com.company.repository.StudentInMemoryRepo;
import com.company.repository.TeacherInMemoryRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseInMemoryRepoTest {

    @Test
    void findOne() {

        Course c1 = new Course("C1", null, 100, 1, 6, null);
        Course c2 = new Course("C2", null, 2, 2, 25, null);
        Course c3 = new Course("C3", null, 50, 3, 4, null);
        CourseInMemoryRepo courseInMemoryRepo = new CourseInMemoryRepo(Arrays.asList(c1,c2,c3));

        Course course = courseInMemoryRepo.findOne(Long.parseLong("1"));
        assert course != null;

    }

    @Test
    void findAll() {

        Course c1 = new Course("C1", null, 100, 1, 6, null);
        Course c2 = new Course("C2", null, 2, 2, 25, null);
        Course c3 = new Course("C3", null, 50, 3, 4, null);
        CourseInMemoryRepo courseInMemoryRepo = new CourseInMemoryRepo(Arrays.asList(c1,c2,c3));

        List<Course> courseList = (List<Course>) courseInMemoryRepo.findAll();
        assertEquals(3, courseList.size());
    }

    @Test
    void save() {
        CourseInMemoryRepo courseInMemoryRepo = new CourseInMemoryRepo(new ArrayList<>());

        Course c4 = new Course("C4", null, 60, 4, 12, null);
        courseInMemoryRepo.save(c4);

        List<Course> courseList = (List<Course>) courseInMemoryRepo.findAll();
        assertEquals(1, courseList.size());
    }

    @Test
    void delete() {
        Course c1 = new Course("C1", null, 100, 1, 6, null);
        Course c2 = new Course("C2", null, 2, 2, 25, null);
        Course c3 = new Course("C3", null, 50, 3, 4, null);
        CourseInMemoryRepo courseInMemoryRepo = new CourseInMemoryRepo(new ArrayList<>());
        courseInMemoryRepo.save(c1);
        courseInMemoryRepo.save(c2);
        courseInMemoryRepo.save(c3);

        List<Course> courseList = (List<Course>) courseInMemoryRepo.findAll();
        assertEquals(3, courseList.size());

        courseInMemoryRepo.delete(c2);
        assertEquals(2, courseList.size());
    }

    @Test
    void update() {
        Course c1 = new Course("C1", null, 100, 1, 6, null);
        CourseInMemoryRepo courseInMemoryRepo = new CourseInMemoryRepo(Arrays.asList(c1));
        Course c2 = new Course("C2", null, 2, 1, 25, null);

        courseInMemoryRepo.update(c2);
        assertEquals("C2", courseInMemoryRepo.findOne(Long.parseLong("1")).getName() );
    }

    @Test
    void addStudentToCourse() {

        List<Long> listStudentsCourse1 = new ArrayList<>();
        List<Long> listStudentsCourse2 = new ArrayList<>();
        List<Long> listStudentsCourse3 = new ArrayList<>();

        List<Course> listCoursesTeacher1 = new ArrayList<>();
        List<Course> listCoursesTeacher2 = new ArrayList<>();
        List<Course> listCoursesTeacher3 = new ArrayList<>();

        List<Course> listCoursesStudent1 = new ArrayList<>();
        List<Course> listCoursesStudent2 = new ArrayList<>();
        List<Course> listCoursesStudent3 = new ArrayList<>();


        Student s1 = new Student("S1", "SF1", 1, 0, null);
        Student s2 = new Student("S2", "SF2", 2, 0, null);
        Student s3 = new Student("S3", "SF3", 3, 0, null);

        Teacher t1 = new Teacher("T1", "TF1", 1, null);
        Teacher t2 = new Teacher("T2", "TF2", 2, null);
        Teacher t3 = new Teacher("T3", "TF3", 3, null);

        Course c1 = new Course("C1", null, 100, 1, 6, null);
        Course c2 = new Course("C2", null, 2, 2, 25, null);
        Course c3 = new Course("C3", null, 50, 3, 4, null);


        listCoursesStudent1.add(c1);
        listCoursesStudent1.add(c3);

        listCoursesStudent2.add(c2);
        listCoursesStudent2.add(c3);

        listCoursesStudent3.add(c1);

        s1.setEnrolledCourses(listCoursesStudent1);
        for(Course course: listCoursesStudent1)
            s1.setTotalCredit(s1.getTotalCredit()+ course.getCredits());

        s2.setEnrolledCourses(listCoursesStudent2);
        for(Course course: listCoursesStudent2)
            s2.setTotalCredit(s2.getTotalCredit()+ course.getCredits());

        s3.setEnrolledCourses(listCoursesStudent3);
        for(Course course: listCoursesStudent3)
            s3.setTotalCredit(s3.getTotalCredit()+ course.getCredits());

        listCoursesTeacher1.add(c1);
        listCoursesTeacher2.add(c2);
        listCoursesTeacher3.add(c3);

        t1.setCourses(listCoursesTeacher1);
        t2.setCourses(listCoursesTeacher2);
        t3.setCourses(listCoursesTeacher3);

        listStudentsCourse1.add(s1.getStudentId());
        listStudentsCourse1.add(s2.getStudentId());


        listStudentsCourse2.add(s1.getStudentId());

        listStudentsCourse3.add(s2.getStudentId());
        listStudentsCourse3.add(s3.getStudentId());

        c1.setStudentsEnrolled(listStudentsCourse1);
        c2.setStudentsEnrolled(listStudentsCourse2);
        c3.setStudentsEnrolled(listStudentsCourse3);


        List<Course> courseList = new ArrayList<>(Arrays.asList(c1,c2,c3));
        List<Student> studentList = new ArrayList<>(Arrays.asList(s1,s2,s3));
        List<Teacher> teacherList = new ArrayList<>(Arrays.asList(t1,t2,t3));

        CourseInMemoryRepo courseRepo = new CourseInMemoryRepo(courseList);
        TeacherInMemoryRepo teacherRepo = new TeacherInMemoryRepo(teacherList);
        StudentInMemoryRepo studentRepo = new StudentInMemoryRepo(studentList);

        CourseController courseController = new CourseController(courseRepo);
        TeacherController teacherController = new TeacherController(teacherRepo);
        StudentController studentController = new StudentController(studentRepo);

        MainController mainController = new MainController(courseController, studentController, teacherController);
        mainController.registerStudentToCourse(Long.parseLong("3"), (Long.parseLong("1")));

        List<Student> newStudentList = (List<Student>) mainController.getAllStudentsByCourseId(Long.parseLong("1"));
        assertEquals(3,newStudentList.size());
    }
}