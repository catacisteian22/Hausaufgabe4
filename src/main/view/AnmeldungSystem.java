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
                .append("Drücke:  1 um einen Studenten zu einem Kurs einzuschreiben\n")
                .append("Drücke:  2 zeigt alle Kurse an\n")
                .append("Drücke:  3 zeigt alle Studenten für einen bestimmten Kurs an\n")
                .append("Drücke:  4 zeigt alle Kurse mit freie Plätze\n")
                .append("Drücke:  5 ein Professor will einen Kurs von seiner Liste zu löschen\n")
                .append("Drücke:  6 bekommt die sortierte Liste der Kurse\n")
                .append("Drücke:  7 bekommt die gefilterte Liste von Kurse nach maximale ECTS\n")
                .append("Drücke:  8 bekommt die gefilterte Liste von Kurse nach minimale ECTS\n")
                .append("Drücke:  9 bekommt die sortierten Studenten nach Name\n")
                .append("Drücke: 10 bekommt die gefilterte Liste von Studenten nach maximale ECTS\n")
                .append("Drücke: 11 bekommt die gefilterte Liste von Studenten nach minimale ECTS\n")
                .append("Drücke:  0 um die App zu verlassen\n")
                .append("Deine Auswahl: ");
        System.out.println(builder.toString());
    }

    /**
     * lauf die Methode fur jeden Fall
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

                case "6":
                    Iterable<Kurs> getSortierteKurseByNamelist = this.mainController.getSortierteKurseByName();
                    System.out.println("Alle Kurse sortiert nach Name: " + getSortierteKurseByNamelist + "\n");
                    break;

                case "7":
                    System.out.println("Bitte fügt den maximalen ECTS Anzahl ein: ");
                    int maxCredit= scanner.nextInt();
                    Iterable<Kurs> getGefilterteKurseByCreditsMaxList = this.mainController.getGefilterteKurseByCreditsMax(maxCredit);
                    System.out.println("Alle Kurse gefiltert nach maximale Anzahl von ECTS: " + getGefilterteKurseByCreditsMaxList + "\n");
                    break;

                case "8":
                    System.out.println("Bitte fügt den minimale ECTS Anzahl ein: ");
                    int minCredit= scanner.nextInt();
                    Iterable<Kurs> getGefilterteKurseByCreditsMinList = this.mainController.getGefilterteKurseByCreditsMin(minCredit);
                    System.out.println("Alle Kurse gefiltert nach minimale Anzahl von ECTS: " + getGefilterteKurseByCreditsMinList + "\n");
                    break;

                case "9":
                    Iterable<Student> getSortierteStudentenByNameList = this.mainController.getSortiertStudentenByName();
                    System.out.println("Alle Studenten gefiltert nach Name: " + getSortierteStudentenByNameList + "\n");
                    break;

                case "10":
                    System.out.println("Bitte fügt den maximalen ECTS Anzahl ein: ");
                    maxCredit= scanner.nextInt();
                    Iterable<Student> getGefilterteStudentenByCreditsMaxList = this.mainController.getGefilterteStudentenByCreditsMax(maxCredit);
                    System.out.println("Alle Studenten gefiltert nach maximalen ECTS: " + getGefilterteStudentenByCreditsMaxList + "\n");
                    break;

                case "11":
                    System.out.println("Bitte fügt den minimalen ECTS Anzahl ein: ");
                    minCredit= scanner.nextInt();
                    Iterable<Student> getGefilterteStudentenByCreditsMinList = this.mainController.getGefilterteStudentenByCreditsMin(minCredit);
                    System.out.println("Alle Studenten gefiltert nach minimalen ECTS: " + getGefilterteStudentenByCreditsMinList + "\n");
                    break;

                case "0":
                    System.out.println("Auf Wiedersehen!!!");
                    return;
            }
            this.showMenu();
        }
    }

}