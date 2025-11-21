package com.example.demo;

import com.example.demo.*;
import com.example.demo.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TestSubmissionController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/submit-test")
    public String submitTest(@RequestBody TestSubmission submission) {
        try {
            String subject = "Coding Test | " +
                    (submission.getCandidate() != null ? submission.getCandidate().getName() : "Unknown") +
                    " | " + submission.getTestName();

            String htmlBody = buildEmailBody(submission);

            emailService.sendSubmissionEmail(subject, htmlBody);

            return "{\"status\":\"ok\",\"message\":\"Submission emailed successfully\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"status\":\"error\",\"message\":\"Email sending failed: " + e.getMessage() + "\"}";
        }
    }

    private String buildEmailBody(TestSubmission submission) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div style='font-family:Segoe UI, sans-serif;font-size:15px;'>");

        // Header Info
        sb.append("<h2 style='color:#4c7af3;'>Coding Test Submission</h2>");
        sb.append("<p><b>Submitted At:</b> ")
          .append(submission.getTimestamp()).append("</p>");

        if (submission.getCandidate() != null) {
            sb.append("<p><b>Student Name:</b> ")
              .append(escape(submission.getCandidate().getName())).append("<br/>");
            sb.append("<b>Student Email:</b> ")
              .append(escape(submission.getCandidate().getEmail())).append("</p>");
        }

        sb.append("<hr style='margin:16px 0;border:none;border-top:2px solid #e0e0e0;'>");

        // Each question
        if (submission.getAnswers() != null) {
            int count = 1;
            for (Answer a : submission.getAnswers()) {
                sb.append("<h3 style='color:#1f2430;'>Q")
                  .append(count++).append(". ")
                  .append(escape(a.getQuestionTitle())).append("</h3>");
                sb.append("<p><b>Status:</b> ")
                  .append(escape(a.getStatus())).append("</p>");

                if (a.getErrorMessage() != null)
                    sb.append("<p><b>Error:</b> ")
                      .append(escape(a.getErrorMessage())).append("</p>");

                sb.append("<details style='background:#f8f9fa;border:1px solid #ccc;padding:10px;border-radius:6px;'>")
                  .append("<summary><b>View Code</b></summary>")
                  .append("<pre style='font-family:Consolas,monospace;font-size:13px;color:#333;overflow-x:auto;'>")
                  .append(escape(a.getCode()))
                  .append("</pre></details>");

                // small table of testResults if present
                if (a.getTestResults() != null && !a.getTestResults().isEmpty()) {
                    sb.append("<table style='width:100%;border-collapse:collapse;margin-top:8px;'>");
                    sb.append("<tr style='background:#eef3ff;'><th align='left'>Test</th><th>Status</th></tr>");
                    for (var tr : a.getTestResults()) {
                        boolean passed = Boolean.TRUE.equals(tr.get("passed"));
                        sb.append("<tr><td style='padding:4px;border:1px solid #ccc;'>")
                          .append(escape(String.valueOf(tr.get("description"))))
                          .append("</td><td style='padding:4px;border:1px solid #ccc;'>")
                          .append(passed ? "✅ Passed" : "❌ Failed")
                          .append("</td></tr>");
                    }
                    sb.append("</table>");
                }

                sb.append("<hr style='margin:18px 0;border:none;border-top:1px solid #ddd;'>");
            }
        }

        sb.append("</div>");
        return sb.toString();
    }

    private String escape(String s) {
        if (s == null) return "";
        return s.replace("&","&amp;")
                .replace("<","&lt;")
                .replace(">","&gt;");
    }
}