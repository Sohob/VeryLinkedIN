package com.verylinkedin.reporting.commands;

import com.verylinkedin.reporting.Command;
import com.verylinkedin.reporting.Report;
import com.verylinkedin.reporting.ReportingRepository;
import com.verylinkedin.reporting.requests.AddReportRequest;

import java.time.LocalDateTime;

public class AddReport implements Command {
    private AddReportRequest request;
    private ReportingRepository repository;

    public AddReport(AddReportRequest request, ReportingRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Object execute() {
        Report r = new Report(request.getReporterID(), request.getReportedID(), LocalDateTime.now(), request.getReason());
        repository.save(r);
        return r.getID() + "";
//        return null;
    }

    public AddReportRequest getRequest() {
        return request;
    }

    public void setRequest(AddReportRequest request) {
        this.request = request;
    }

    public ReportingRepository getRepository() {
        return repository;
    }

    public void setRepository(ReportingRepository repository) {
        this.repository = repository;
    }
}
