package com.ems.model;

public class Result {
    private Student student;
    private Exam exam;
    private int marks;

    public Result(Student student, Exam exam, int marks) {
        this.student = student;
        this.exam = exam;
        this.marks = marks;
    }

    public Student getStudent() { 
    	return student; 
    	}
    public Exam getExam() {
    	return exam;
    	}
    public int getMarks() { 
    	return marks;
    	}
    public void setMarks(int marks) {
    	this.marks = marks; 
    	}

    @Override
    public String toString() {
        return student.getName() + " scored " + marks + " in " + exam.getSubject();
    }
}
