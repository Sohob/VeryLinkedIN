package com.verylinkedin.reporting;

import com.verylinkedin.reporting.commands.GetReport;
import com.verylinkedin.reporting.requests.GetReportRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import com.verylinkedin.reporting.*;

@SpringBootTest(classes = ReportingApplication.class)
class ReportingApplicationTests {
    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void GetReport() {
        Command cmd = applicationContext.getBean(GetReport.class);
        GetReportRequest r = new GetReportRequest(1l);
//        assertEquals(1, cmd.execute(r));
    }

    @Test
    void contextLoads() {
    }

}
