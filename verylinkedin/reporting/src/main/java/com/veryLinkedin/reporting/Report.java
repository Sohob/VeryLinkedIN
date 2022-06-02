package com.veryLinkedin.reporting;


import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String ReporterID;
    private String ReportedID;
    private LocalDateTime time;
    private String reason;

    public Report(long ID, String reporterID, String reportedID, LocalDateTime time, String reason) {
        this.ID = ID;
        ReporterID = reporterID;
        ReportedID = reportedID;
        this.time = time;
        this.reason = reason;
    }

    public Report(String reporterID, String reportedID, LocalDateTime time, String reason) {
        ReporterID = reporterID;
        ReportedID = reportedID;
        this.time = time;
        this.reason = reason;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getReporterID() {
        return ReporterID;
    }

    public void setReporterID(String reporterID) {
        ReporterID = reporterID;
    }

    public String getReportedID() {
        return ReportedID;
    }

    public void setReportedID(String reportedID) {
        ReportedID = reportedID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Report{" +
                "ID=" + ID +
                ", ReporterID='" + ReporterID + '\'' +
                ", ReportedID='" + ReportedID + '\'' +
                ", time=" + time +
                ", reason='" + reason + '\'' +
                '}';
    }

    public Report() {

    }
}
