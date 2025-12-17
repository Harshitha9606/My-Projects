package com.ems.services;

import java.util.LinkedList;

import com.ems.model.Student;

public class StudentService {
    private LinkedList<Student> students = new LinkedList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    public void updateStudent(int id, String newName) {
        Student student = findStudentById(id);
        if (student != null) student.setName(newName);
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    public LinkedList<Student> getAllStudents() {
        return students;
    }
}
