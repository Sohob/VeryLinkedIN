package com.verylinkedin.reporting.commands;

import com.verylinkedin.reporting.Command;
import com.verylinkedin.reporting.Report;
import com.verylinkedin.reporting.ReportingRepository;
import com.verylinkedin.reporting.requests.DeleteReportRequest;

import java.time.LocalDateTime;

public class DeleteReport implements Command {
    private DeleteReportRequest request;
    private ReportingRepository repository;

    public DeleteReport(DeleteReportRequest request, ReportingRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Object execute() {
//        Report r = new Report(request.getReporterID(), request.getReportedID(), LocalDateTime.now(), request.getReason());
        try {
            repository.deleteById(request.id());
            return "Done";
        } catch (Exception e) {
            return "No ValidID";
        }
//        return null;
    }

    public DeleteReportRequest getRequest() {
        return request;
    }

    public void setRequest(DeleteReportRequest request) {
        this.request = request;
    }

    public ReportingRepository getRepository() {
        return repository;
    }

    public void setRepository(ReportingRepository repository) {
        this.repository = repository;
    }
}
