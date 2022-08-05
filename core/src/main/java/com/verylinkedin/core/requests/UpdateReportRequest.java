package com.verylinkedin.core.requests;

public record UpdateReportRequest(Long id, String reporterID, String reportedID, String reason) {
}
