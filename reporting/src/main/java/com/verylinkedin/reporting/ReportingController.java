package com.verylinkedin.reporting;
/*
import com.veryLinkedin.reporting.requests.AddReportRequest;
import com.veryLinkedin.reporting.requests.GetReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

@RestController
@RequestMapping("api/v1/reports")
public class ReportingController {

    public ReportingService service;

    @Autowired
    public ReportingController(ReportingService service) {
        this.service = service;
    }

    @GetMapping("/get-report/{reportId}")
    public Object getReport(@PathVariable Long reportId) {
//        return "Hello";
//        Report r = new Report(1, "1234", "456", LocalDateTime.now(), "bad");
//        System.ou/t.println(r);
//        return r;
        GetReportRequest r = new GetReportRequest(reportId);
        return service.GetReport(r);
//        return service.GetReport(new GetReportRequest(1l, "abc", "bcd"));
    }

    @PostMapping("/add-report")
    public Long addReport(@RequestBody AddReportRequest r) {
        //TODO check why is not working as AddReportRequest
//
//        System.out.println("HERE");
//        System.out.println(s);
//        AddReportRequest r = new AddReportRequest();
//        StringTokenizer st = new StringTokenizer(s.substring(1, s.length() - 1), ",");
//
//        r.setReporterID(parse(st.nextToken()));
//        r.setReportedID(parse(st.nextToken()));
//        r.setReason(parse(st.nextToken()));

//        return 1l;
//        System.out.println(r);
        return service.AddReport(r);
    }

    static String parse(String s) {
        StringTokenizer a = new StringTokenizer(s, ":");
        a.nextToken();
        String tmp = a.nextToken();
        return tmp.substring(1, tmp.length() - 1);
    }
}
*/
