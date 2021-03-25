package Objects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import libraryFunctions.iPrintable;

public class ProjectTimeLog implements iPrintable {

    private int ProjectTimeLog_Id;
    private Date ProjectTimeLog_DateLogged;
    private String ProjectTimeLog_DescriptionOfWork;
    private int ProjectTimeLog_HoursLogged;
    private int ProjectTimeLog_ProjectAssignmentId;

    public ProjectTimeLog(int ProjectTimeLog_Id, Date ProjectTimeLog_DateLogged, String ProjectTimeLog_DescriptionOfWork, int ProjectTimeLog_HoursLogged, int ProjectTimeLog_ProjectAssignedProjectId) {
        this.ProjectTimeLog_Id = ProjectTimeLog_Id;
        this.ProjectTimeLog_DateLogged = ProjectTimeLog_DateLogged;
        this.ProjectTimeLog_DescriptionOfWork = ProjectTimeLog_DescriptionOfWork;
        this.ProjectTimeLog_HoursLogged = ProjectTimeLog_HoursLogged;
        this.ProjectTimeLog_ProjectAssignmentId = ProjectTimeLog_ProjectAssignedProjectId;
    }

    @Override
    public String getPrintableString() { // Prints all non-sensitive data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Date Logged: " + dateFormat.format(ProjectTimeLog_DateLogged) + ", Description of work: " + ProjectTimeLog_DescriptionOfWork + ", Hours logged: " + ProjectTimeLog_HoursLogged;
    }

    public int getProjectTimeLog_Id() {
        return ProjectTimeLog_Id;
    }

    public void setProjectTimeLog_Id(int ProjectTimeLog_Id) {
        this.ProjectTimeLog_Id = ProjectTimeLog_Id;
    }

    public Date getProjectTimeLog_DateLogged() {
        return ProjectTimeLog_DateLogged;
    }

    public void setProjectTimeLog_DateLogged(Date ProjectTimeLog_DateLogged) {
        this.ProjectTimeLog_DateLogged = ProjectTimeLog_DateLogged;
    }

    public String getProjectTimeLog_DescriptionOfWork() {
        return ProjectTimeLog_DescriptionOfWork;
    }

    public void setProjectTimeLog_DescriptionOfWork(String ProjectTimeLog_DescriptionOfWork) {
        this.ProjectTimeLog_DescriptionOfWork = ProjectTimeLog_DescriptionOfWork;
    }

    public int getProjectTimeLog_HoursLogged() {
        return ProjectTimeLog_HoursLogged;
    }

    public void setProjectTimeLog_HoursLogged(int ProjectTimeLog_HoursLogged) {
        this.ProjectTimeLog_HoursLogged = ProjectTimeLog_HoursLogged;
    }

    public int getProjectTimeLog_ProjectAssignedProjectId() {
        return ProjectTimeLog_ProjectAssignmentId;
    }

    public void setProjectTimeLog_ProjectAssignmentId(int ProjectTimeLog_ProjectAssignmentId) {
        this.ProjectTimeLog_ProjectAssignmentId = ProjectTimeLog_ProjectAssignmentId;
    }

}
