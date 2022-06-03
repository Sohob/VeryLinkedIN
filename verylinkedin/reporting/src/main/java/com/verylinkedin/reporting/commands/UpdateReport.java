package com.verylinkedin.reporting.commands;

import com.verylinkedin.reporting.Command;
import com.verylinkedin.reporting.Report;
import com.verylinkedin.reporting.ReportingRepository;
import com.verylinkedin.reporting.requests.UpdateReportRequest;


import java.util.Optional;

public class UpdateReport implements Command {
    private UpdateReportRequest request;
    private ReportingRepository repository;

    public UpdateReport(UpdateReportRequest request, ReportingRepository repository) {
        this.request = request;
        this.repository = repository;
    }

    @Override
    public Object execute() {
//        Report r = new Report(request.getReporterID(), request.getReportedID(), LocalDateTime.now(), request.getReason());
//        repository.save(r);
//        return r.getID() + "";
//        return null;
        if (request.id() == null)
            return "NO valid ID";

        Optional<Report> r = repository.findById(request.id());
        if (!r.isPresent()) {
            return "NO valid ID";
        }
        if (request.reportedID() != null) {
            r.get().setReportedID(request.reportedID());
        }
        if (request.reporterID() != null) {
            r.get().setReporterID(request.reporterID());
        }
        if (request.reason() != null) {
            r.get().setReason(request.reason());
        }
        repository.save(r.get());
        return "Done";

    }
}
