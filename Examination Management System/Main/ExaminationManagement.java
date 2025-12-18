package com.ems.main;

import java.util.Scanner;

import com.ems.model.Exam;
import com.ems.model.Result;
import com.ems.model.Student;
import com.ems.services.ExamService;
import com.ems.services.ResultService;
import com.ems.services.StudentService;

public class ExaminationManagement {
	
    private static Scanner scanner = new Scanner(System.in);
    private static StudentService studentService = new StudentService();
    private static ExamService examService = new ExamService();
    private static ResultService resultService = new ResultService();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Examination Management System ---");
            System.out.println("1. Register Student");
            System.out.println("2. Manage Students");
            System.out.println("3. Manage Exams");
            System.out.println("4. Manage Results");
            System.out.println("5. View Students");
            System.out.println("6. View Exams");
            System.out.println("7. View Results");
            System.out.println("8. Show Topper");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: registerStudent(); break;
                case 2: manageStudents(); break;
                case 3: manageExams(); break;
                case 4: manageResults(); break;
                case 5: viewStudents(); break;
                case 6: viewExams(); break;
                case 7: viewResults(); break;
                case 8: showTopper(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void registerStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        studentService.addStudent(new Student(id, name));
        System.out.println("Student registered successfully!");
    }

    private static void manageStudents() {
        System.out.println("1. Update Student  2. Delete Student");
        int choice = scanner.nextInt();
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Enter new name: ");
            String name = scanner.nextLine();
            studentService.updateStudent(id, name);
            System.out.println("Student updated successfully!");
        } else if (choice == 2) {
            studentService.deleteStudent(id);
            resultService.getAllResults().removeIf(r -> r.getStudent().getId() == id);
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void manageExams() {
        System.out.println("1. Add Exam  2. Update Exam  3. Delete Exam");
        int choice = scanner.nextInt();
        System.out.print("Enter Exam ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            System.out.print("Enter subject: ");
            String subject = scanner.nextLine();
            examService.addExam(new Exam(id, subject));
            System.out.println("Exam added successfully!");
        } else if (choice == 2) {
            System.out.print("Enter new subject: ");
            String subject = scanner.nextLine();
            examService.updateExam(id, subject);
            System.out.println("Exam updated successfully!");
        } else if (choice == 3) {
            examService.deleteExam(id);
            resultService.getAllResults().removeIf(result -> result.getExam().getId() == id);
            System.out.println("Exam deleted successfully!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void manageResults() {
        System.out.println("1. Add Result  2. Update Result  3. Delete Result");
        int choice = scanner.nextInt();
        System.out.print("Enter Student ID: ");
        int studentId = scanner.nextInt();
        System.out.print("Enter Exam ID: ");
        int examId = scanner.nextInt();
        scanner.nextLine();
        Student student = studentService.findStudentById(studentId);
        Exam exam = examService.findExamById(examId);
        if (student == null || exam == null) {
            System.out.println("Invalid Student or Exam ID!");
            return;
        }
        
        if (choice == 1) {
            System.out.print("Enter marks: ");
            int marks = scanner.nextInt();
            resultService.addResult(new Result(student, exam, marks));
            System.out.println("Result added successfully!");
        } else if (choice == 2) {
            System.out.print("Enter new marks: ");
            int marks = scanner.nextInt();
            resultService.updateResult(studentId, examId, marks);
            System.out.println("Result updated successfully!");
        } else if (choice == 3) {
            resultService.deleteResult(studentId, examId);
            System.out.println("Result deleted successfully!");
        } else {
            System.out.println("Invalid choice!");
        }
    }

    private static void viewStudents() {
        System.out.println("\n--- Students ---");
        for (Student student : studentService.getAllStudents()) System.out.println(student);
    }

    private static void viewExams() {
        System.out.println("\n--- Exams ---");
        for (Exam exam : examService.getAllExams()) System.out.println(exam);
    }

    private static void viewResults() {
        System.out.println("\n--- Results ---");
        for (Result result : resultService.getAllResults()) System.out.println(result);
    }


     private static void showTopper() {
        System.out.print("Enter Exam ID: ");
        int examId = scanner.nextInt();
        Result topper = resultService.getTopper(examId);
        if (topper == null) System.out.println("No results found for this exam.");
        else System.out.println("Topper: " + topper.getStudent().getName() + " with " + topper.getMarks() + " marks");
    } 
}
