package main.view;

import main.controller.MainController;
import main.model.Kurs;
import main.model.Student;

import java.util.Scanner;

public class AnmeldungSystem {

    private final MainController mainController;

    public AnmeldungSystem(MainController mainController) {
        this.mainController = mainController;
    }

    public void startApplication() {
        this.showMenu();
        this.startConsole();
    }

    /**
     * drücken des Menüs
     */
    private void showMenu(){
        StringBuilder builder = new StringBuilder();
        builder.append("Willkommen zu der App der Universität\n")
                .append("Drücke: 1 um einen Studenten zu einem Kurs einzuschreiben\n")
                .append("Drücke: 2 zeigt alle Kurse an\n")
                .append("Drücke: 3 zeigt alle Studenten für einen bestimmten Kurs an\n")
                .append("Drücke: 4 zeigt alle Kurse mit freie Plätze\n")
                .append("Drücke: 5 ein Professor will einen Kurs von seiner Liste zu löschen\n")
                .append("Press: 6 for get sort courses by name\n")
                .append("Press: 7 for get filtered courses by credits max\n")
                .append("Press: 8 for get filtered courses by credits min\n")
                .append("Press: 9 for get sort students by name\n")
                .append("Press: 10 for get filtered students by credits max\n")
                .append("Press: 11 for get filtered students by credits min\n")
                .append("Press: 0 to exit application\n")
                .append("Your choice: ");
        System.out.println(builder.toString());
    }

    /**
     * run the methode of each case
     */
    private void startConsole() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String opt = scanner.nextLine();

            switch (opt) {

                case "1":
                    System.out.println("Bitte fügt die ID des Kurses ein: ");
                    String kursId = scanner.nextLine();
                    System.out.println("Bitte fügt die ID des Studenten ein: ");
                    String studentId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.registerStudentToKurs(Long.parseLong(kursId), Long.parseLong(studentId));
                        if (response) System.out.println("Student hinzugefügt mit Erfolg!");
                        else System.out.println("Student war nicht hinzugefügt :(");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "2":
                    Iterable<Kurs> kursList = this.mainController.getAllKurse();
                    for (Kurs kurs : kursList) {
                        System.out.println(kurs.getName() + " , freie Plätze = " + (kurs.getStudentsEnrolled().size() - kurs.getMaxEnrolled()) * (-1) + "\n");
                    }
                    break;

                case "3":
                    System.out.println("Bitte fügt die ID des Kurses ein: ");
                    kursId = scanner.nextLine();

                    try {
                        Iterable<Student> studentListInAKurs = this.mainController.getAllStudentsByKursId(Long.parseLong(kursId));
                        for (Student student : studentListInAKurs) {
                            System.out.println("ID des Studenten in diesem Kurs: " + student.getStudentId() + "\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    Iterable<Kurs> availableKurseList = this.mainController.getAllAvailableKurse();
                    System.out.println("Alle Kurse mit freie Plätze: " + availableKurseList + "\n");
                    break;

                case "5":
                    System.out.println("Bitte fügt die ID des Kurses ein: ");
                    kursId = scanner.nextLine();
                    System.out.println("Bitte fügt die ID des Professors ein: ");
                    String professorId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.deleteKursFromProfessor(Long.parseLong(kursId), Long.parseLong(professorId));
                        if (response) System.out.println("Kurs gelöscht mit Erfolg!");
                        else System.out.println("Der Kurs war nicht gelöscht :(");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Auf Wiedersehen!!!");
                    return;
            }
            this.showMenu();
        }
    }

}

public class RegistrationSystem {

    private MainController mainController;

    public RegistrationSystem(MainController mainController) {
        this.mainController = mainController;
    }

    public void startApplication(){
        this.showMenu();
        this.startConsole();
    }

    /**
     * print the menu
     */

    private void showMenu(){
        StringBuilder builder = new StringBuilder();
        builder.append("Welcome to the University's Application\n")
                .append("Press: 1 for add student to course\n")
                .append("Press: 2 for get all courses\n")
                .append("Press: 3 for get all student of a course\n")
                .append("Press: 4 for get all course with free places\n")
                .append("Press: 5 for a teacher to delete a course in his list\n")
                .append("Press: 6 for get sort courses by name\n")
                .append("Press: 7 for get filtered courses by credits max\n")
                .append("Press: 8 for get filtered courses by credits min\n")
                .append("Press: 9 for get sort students by name\n")
                .append("Press: 10 for get filtered students by credits max\n")
                .append("Press: 11 for get filtered students by credits min\n")
                .append("Press: 0 to exit application\n")
                .append("Your choice: ");
        System.out.println(builder.toString());
    }

    /**
     * run the methode of each case
     */
    private void startConsole(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String opt = scanner.nextLine();

            switch (opt) {


                case "1":
                    System.out.println("Please insert course id");
                    String courseId = scanner.nextLine();
                    System.out.println("Please insert student id");
                    String studentId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.registerStudentToCourse(Long.parseLong(courseId), Long.parseLong(studentId));
                        if (response) System.out.println("Student added successfully");
                        else System.out.println("Failed to add student to course");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }

                    break;

                case "2":
                    Iterable<Course> courseList = this.mainController.getAllCourses();
                    for (Course course : courseList) {
                        System.out.println(course.getName() + " , available places = " + String.valueOf((course.getStudentsEnrolled().size() - course.getMaxEnrolled())*(-1)) + "\n");
                    }
                    break;

                case "3":
                    System.out.println("Please insert course id");
                    courseId = scanner.nextLine();

                    try {
                        Iterable<Student> studentListInACourse = this.mainController.getAllStudentsByCourseId(Long.parseLong(courseId));
                        for (Student student : studentListInACourse) {
                            System.out.println("Id of the student in this course: " + student.getStudentId() + "\n");
                        }
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "4":
                    Iterable<Course> availableCourseList = this.mainController.getAllAvailableCourses();
                        System.out.println("all the available courses: " + availableCourseList + "\n");
                    break;

                case "5":
                    System.out.println("Please insert course id");
                    courseId = scanner.nextLine();
                    System.out.println("Please insert teacher id");
                    String teacherId = scanner.nextLine();

                    try {
                        boolean response = this.mainController.deleteCourseFromTeacher(Long.parseLong(courseId), Long.parseLong(teacherId));
                        if (response) System.out.println("Course deleted successfully");
                        else System.out.println("Failed to delete course");
                    } catch (Exception e) {
                        System.out.println("Exception occurred:");
                        System.out.println(e.getMessage());
                    }
                    break;

                case "6":
                    Iterable<Course> getSortCoursesByNamelist = this.mainController.getSortCoursesByName();
                    System.out.println("all the courses sorted by name: " + getSortCoursesByNamelist + "\n");
                    break;

                case "7":
                    System.out.println("Please insert max Credit");
                     int maxCredit= scanner.nextInt();
                    Iterable<Course> getFilteredCoursesByCreditsMaxList = this.mainController.getFilteredCoursesByCreditsMax(maxCredit);
                    System.out.println("all the a courses filtered by max credit: " + getFilteredCoursesByCreditsMaxList + "\n");
                    break;

                case "8":
                    System.out.println("Please insert min Credit");
                    int minCredit= scanner.nextInt();
                    Iterable<Course> getFilteredCoursesByCreditsMinList = this.mainController.getFilteredCoursesByCreditsMin(minCredit);
                    System.out.println("all the courses filtered by min credit: " + getFilteredCoursesByCreditsMinList + "\n");
                    break;

                case "9":
                    Iterable<Student> getSortStudentsByNameList = this.mainController.getSortStudentsByName();
                    System.out.println("all the students sorted by name: " + getSortStudentsByNameList + "\n");
                    break;

                case "10":
                    System.out.println("Please insert max Credit");
                    maxCredit= scanner.nextInt();
                    Iterable<Student> getFilteredStudentsByCreditsMaxList = this.mainController.getFilteredStudentsByCreditsMax(maxCredit);
                    System.out.println("all the students filtered by max credit: " + getFilteredStudentsByCreditsMaxList + "\n");
                    break;

                case "11":
                    System.out.println("Please insert min Credit");
                     minCredit= scanner.nextInt();
                    Iterable<Student> getFilteredStudentsByCreditsMinList = this.mainController.getFilteredStudentsByCreditsMin(minCredit);
                    System.out.println("all the students filtered by min credit: " + getFilteredStudentsByCreditsMinList + "\n");
                    break;

                case "0":
                    System.out.println("Good-Bye!!");
                    return;
            }
            this.showMenu();
        }
    }

}
