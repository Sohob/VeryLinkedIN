package com.verylinkedin.reporting.requests;

public record UpdateReportRequest(Long id, String reporterID, String reportedID, String reason) {
}
