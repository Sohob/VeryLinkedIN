package com.verylinkedin.core.requests;


public class AddReportRequest {
    private String reporterID;
    private String reportedID;
    private String reason;

    public AddReportRequest(String reporterID, String reportedID, String reason) {
        this.reporterID = reporterID;
        this.reportedID = reportedID;
        this.reason = reason;
    }

    public AddReportRequest() {

    }

    public String getReporterID() {
        return reporterID;
    }

    public void setReporterID(String reporterID) {
        this.reporterID = reporterID;
    }

    public String getReportedID() {
        return reportedID;
    }

    public void setReportedID(String reportedID) {
        this.reportedID = reportedID;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "AddReportRequest{" +
                "reporterID='" + reporterID + '\'' +
                ", reportedID='" + reportedID + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
