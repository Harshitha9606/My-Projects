package com.ems.model;

public class Exam {
    private int id;
    private String subject;

    public Exam(int id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public int getId() {
    	return id; 
    	}
    
    public String getSubject() {
    	return subject; 
    	}
    
    public void setSubject(String subject) { 
    	this.subject = subject; 
    	}

    @Override
    public String toString() {
        return "Exam ID: " + id + ", Subject: " + subject;
    }
}
