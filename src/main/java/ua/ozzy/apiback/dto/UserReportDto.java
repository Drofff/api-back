package ua.ozzy.apiback.dto;

import java.util.List;

public class UserReportDto {

    private String username;

    private List<StatusReportDto> statusReports;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<StatusReportDto> getStatusReports() {
        return statusReports;
    }

    public void setStatusReports(List<StatusReportDto> statusReports) {
        this.statusReports = statusReports;
    }

}
