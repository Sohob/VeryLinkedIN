package com.veryLinkedin.reporting.getreport;

import com.veryLinkedin.reporting.Command;
import com.veryLinkedin.reporting.ReportingRepository;
import org.hibernate.mapping.List;

public class GetReport implements Command {
    private GetReportRequest request;
    private ReportingRepository repository;

    public GetReport(GetReportRequest request, ReportingRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Object execute() {
        return repository.findById(request.id()).toString();
//        return repository.findAll();
//        return null;
    }

    public GetReportRequest getRequest() {
        return request;
    }

    public void setRequest(GetReportRequest request) {
        this.request = request;
    }

    public ReportingRepository getRepository() {
        return repository;
    }

    public void setRepository(ReportingRepository repository) {
        this.repository = repository;
    }
}
