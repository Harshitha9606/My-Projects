package com.ems.services;

import java.util.LinkedList;

import com.ems.model.Exam;

public class ExamService {
    private LinkedList<Exam> exams = new LinkedList<>();

    public void addExam(Exam exam) {
        exams.add(exam);
    }

    public Exam findExamById(int id) {
        for (Exam exam : exams) {
            if (exam.getId() == id) return exam;
        }
        return null;
    }

    public void updateExam(int id, String newSubject) {
        Exam exam = findExamById(id);
        if (exam != null) exam.setSubject(newSubject);
    }

    public void deleteExam(int id) {
        exams.removeIf(exam -> exam.getId() == id);
    }

    public LinkedList<Exam> getAllExams() {
        return exams;
    }
}

