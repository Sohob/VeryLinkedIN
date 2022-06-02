package com.verylinkedin.core.requests;

public class GetReportRequest {
    private Long ID;

    public GetReportRequest(Long ID) {
        this.ID = ID;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "GetReportRequest{" +
                "ID=" + ID +
                '}';
    }
}
