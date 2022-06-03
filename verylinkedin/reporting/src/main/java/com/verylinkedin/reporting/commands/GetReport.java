package com.verylinkedin.reporting.commands;

import com.verylinkedin.reporting.Command;
import com.verylinkedin.reporting.ReportingRepository;
import com.verylinkedin.reporting.requests.GetReportRequest;

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
