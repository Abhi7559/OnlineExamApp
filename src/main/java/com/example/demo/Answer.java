package com.example.demo;

import java.util.List;
import java.util.Map;

public class Answer {
    private int questionId;
    private String questionTitle;
    private String code;
    private String status;
    private String timestamp;
    private boolean hasError;
    private String errorMessage;
    private List<Map<String, Object>> testResults;

    public int getQuestionId() { return questionId; }
    public void setQuestionId(int questionId) { this.questionId = questionId; }

    public String getQuestionTitle() { return questionTitle; }
    public void setQuestionTitle(String questionTitle) { this.questionTitle = questionTitle; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    public boolean isHasError() { return hasError; }
    public void setHasError(boolean hasError) { this.hasError = hasError; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public List<Map<String, Object>> getTestResults() { return testResults; }
    public void setTestResults(List<Map<String, Object>> testResults) { this.testResults = testResults; }
}