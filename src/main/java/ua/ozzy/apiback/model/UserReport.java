package ua.ozzy.apiback.model;

import java.util.List;

public class UserReport {

    private String username;

    private List<StatusReport> statusesReport;

    public UserReport(String username, List<StatusReport> statusesReport) {
        this.username = username;
        this.statusesReport = statusesReport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<StatusReport> getStatusesReport() {
        return statusesReport;
    }

    public void setStatusesReport(List<StatusReport> statusesReport) {
        this.statusesReport = statusesReport;
    }

}
