package com.example.demo;

import java.util.List;

public class TestSubmission {
    private String timestamp;
    private String testName;
    private Candidate candidate;
    private List<Answer> answers;

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }

    public Candidate getCandidate() { return candidate; }
    public void setCandidate(Candidate candidate) { this.candidate = candidate; }

    public List<Answer> getAnswers() { return answers; }
    public void setAnswers(List<Answer> answers) { this.answers = answers; }
}