
package Objects;

import java.sql.Date;
import java.text.SimpleDateFormat;
import libraryFunctions.iPrintable;

public class ProjectAssignment implements iPrintable{

    private int ProjectAssignment_id;
    private String ProjectAssignment_EmployeeID;
    private int ProjectAssignment_ProjectID;
    private String ProjectAssignment_ProjectJobTitle;
    private String ProjectAssignment_AssignedBy;
    private Date ProjectAssignment_StartDate;
    private Date ProjectAssignment_EndDate;

    public ProjectAssignment(int ProjectAssignment_id, String ProjectAssignment_EmployeeID, int ProjectAssignment_ProjectID, String ProjectAssignment_ProjectJobTitle, String ProjectAssignment_AssignedBy, Date ProjectAssignment_StartDate, Date ProjectAssignment_EndDate) {
        this.ProjectAssignment_id = ProjectAssignment_id;
        this.ProjectAssignment_EmployeeID = ProjectAssignment_EmployeeID;
        this.ProjectAssignment_ProjectID = ProjectAssignment_ProjectID;
        this.ProjectAssignment_ProjectJobTitle = ProjectAssignment_ProjectJobTitle;
        this.ProjectAssignment_AssignedBy = ProjectAssignment_AssignedBy;
        this.ProjectAssignment_StartDate = ProjectAssignment_StartDate;
        this.ProjectAssignment_EndDate = ProjectAssignment_EndDate;
    }

    @Override
    public String getPrintableString() { // Prints all non-sensitive data
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "Project Job Title: " + ProjectAssignment_ProjectJobTitle + ", Assigned By: " + ProjectAssignment_AssignedBy + ", Start Date: " + dateFormat.format(ProjectAssignment_StartDate) + ", End Date: "+dateFormat.format(ProjectAssignment_EndDate);
    }

    public int getProjectAssignment_id() {
        return ProjectAssignment_id;
    }

    public void setProjectAssignment_id(int ProjectAssignment_id) {
        this.ProjectAssignment_id = ProjectAssignment_id;
    }

    public String getProjectAssignment_EmployeeID() {
        return ProjectAssignment_EmployeeID;
    }

    public void setProjectAssignment_EmployeeID(String ProjectAssignment_EmployeeID) {
        this.ProjectAssignment_EmployeeID = ProjectAssignment_EmployeeID;
    }

    public int getProjectAssignment_ProjectID() {
        return ProjectAssignment_ProjectID;
    }

    public void setProjectAssignment_ProjectID(int ProjectAssignment_ProjectID) {
        this.ProjectAssignment_ProjectID = ProjectAssignment_ProjectID;
    }

    public String getProjectAssignment_ProjectJobTitle() {
        return ProjectAssignment_ProjectJobTitle;
    }

    public void setProjectAssignment_ProjectJobTitle(String ProjectAssignment_ProjectJobTitle) {
        this.ProjectAssignment_ProjectJobTitle = ProjectAssignment_ProjectJobTitle;
    }

    public String getProjectAssignment_AssignedBy() {
        return ProjectAssignment_AssignedBy;
    }

    public void setProjectAssignment_AssignedBy(String ProjectAssignment_AssignedBy) {
        this.ProjectAssignment_AssignedBy = ProjectAssignment_AssignedBy;
    }

    public Date getProjectAssignment_StartDate() {
        return ProjectAssignment_StartDate;
    }

    public void setProjectAssignment_StartDate(Date ProjectAssignment_StartDate) {
        this.ProjectAssignment_StartDate = ProjectAssignment_StartDate;
    }

    public Date getProjectAssignment_EndDate() {
        return ProjectAssignment_EndDate;
    }

    public void setProjectAssignment_EndDate(Date ProjectAssignment_EndDate) {
        this.ProjectAssignment_EndDate = ProjectAssignment_EndDate;
    }
    
   
    
    
    
    
}
