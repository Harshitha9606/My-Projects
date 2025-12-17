package com.ems.services;

import java.util.LinkedList;

import com.ems.model.Result;

public class ResultService {
    private LinkedList<Result> results = new LinkedList<>();

    public void addResult(Result result) {
        results.add(result);
    }

    public Result findResult(int studentId, int examId) {
        for (Result result : results) {
            if (result.getStudent().getId() == studentId && result.getExam().getId() == examId) {
                return result;
            }
        }
        return null;
    }

    public void updateResult(int studentId, int examId, int newMarks) {
        Result result = findResult(studentId, examId);
        if (result != null) result.setMarks(newMarks);
    }

    public void deleteResult(int studentId, int examId) {
        results.removeIf(result -> result.getStudent().getId() == studentId && result.getExam().getId() == examId);
    }

    public LinkedList<Result> getAllResults() {
        return results;
    }

    public double calculateAverage(int examId) {
        int total = 0, count = 0;
        for (Result result : results) {
            if (result.getExam().getId() == examId) {
                total += result.getMarks();
                count++;
            }
        }
        return count > 0 ? (double) total / count : 0;
    }

    public Result getTopper(int examId) {
        Result topper = null;
        for (Result result : results) {
            if (result.getExam().getId() == examId) {
                if (topper == null || result.getMarks() > topper.getMarks()) topper = result;
            }
        }
        return topper;
    }
}
