package com.veryLinkedin.reporting;

import com.veryLinkedin.reporting.addreport.AddReport;
import com.veryLinkedin.reporting.addreport.AddReportRequest;
import com.veryLinkedin.reporting.getreport.GetReport;
import com.veryLinkedin.reporting.getreport.GetReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportingService {

    private ReportingRepository repository;

    @Autowired
    public ReportingService(ReportingRepository repository) {
        this.repository = repository;
    }


    public Long AddReport(AddReportRequest request) {
        //ToDo  implement as Query.
//        System.out.println("Added");
        AddReport cmd = new AddReport(request, repository);
        Thread thread = new Thread(new CommandRunnable(cmd));
        thread.run();
        return 1l;
//        Report r = new Report(request.getReporterID(), request.getReportedID(), LocalDateTime.now(), request.getReason());
//        repository.save(r);
//        return r.getID();
//        SendMessage sendMessage = new SendMessage(request, groupRepository);
//        Thread thread = new Thread(new CommandRunnable(sendMessage));
//        thread.start();
    }

    public Optional<Report> GetReport(GetReportRequest request) {
        //ToDo  implement as Query.
//        System.out.println("here get");
        GetReport cmd = new GetReport(request, repository);
        return (Optional<Report>) cmd.execute();
//        return repository.findAll();

//        return new Report(1, "1234", "456", LocalDateTime.now(), "bad");
//        ViewChat viewChat = new ViewChat(request, groupRepository);
//        ViewChatResponse response = (ViewChatResponse) viewChat.execute();
//         TODO Multithreading here
//        Thread thread = new Thread(new CommandRunnable(createGroup));
//        thread.start();
//        return response;

//        return null;
    }
}
