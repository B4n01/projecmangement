package libraryFunctions;

import Objects.*;
import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
//This class gets things in and out of the database

public class repository {

    private static final String DatabaseLocation = System.getProperty("user.dir") + "\\ProjectManagment.accdb";
    private static Connection con;
    private static Employee currentUser;

    public static Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:ucanaccess://" + DatabaseLocation, "", "");
            return con;
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return null;
    }

// <editor-fold defaultstate="collapsed" desc="User operations">
    public static Employee getCurrentUser() {
        return currentUser;
    }

    public static Department getCurrentUsersDepartment() {
        Department department = null;
        try {

            String sql = "SELECT Department.Department_Number, Department.Department_Name,  Department.Department_Head FROM Department, Employee where Employee_ID = '" + currentUser.getEmployee_Id() + "' AND Department.Department_Number = Employee.Employee_Dept";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                department = new Department(rs.getInt("Department_Number"), rs.getString("Department_Name"), rs.getString("Department_Head"));
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return department;
    }

    public static boolean EmployeeLogIn(String userID, String password) {
        try {
            String sql = "SELECT * FROM Employee where Employee_ID = '" + userID + "'";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                currentUser = new Employee(rs.getString("Employee_Id"), rs.getString("Employee_Fname"), rs.getString("Employee_Lname"), rs.getString("Employee_Password"), rs.getString("Employee_Dept"), rs.getString("Employee_Office"), rs.getString("Employee_Phone"), rs.getDate("Employee_HireDate"), rs.getDouble("Employee_HourlyRate"));
                if (!helper.CompareHashed(currentUser.getEmployee_Password(), password)) {
                    return false;
                }
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return currentUser != null;
    }

    public static ArrayList<Project> getUsersProjects(String UserID) {
        ArrayList<Project> ProjectList = new ArrayList<>();
        try {

            String sql = "SELECT Project.Project_ProjectID, Project.Project_Title, Project.Project_StartDate, Project.Project_EndDate, Project.Project_Budget\n"
                    + "FROM Project INNER JOIN ProjectAssignment ON Project.Project_ProjectID = ProjectAssignment.ProjectAssignment_ProjectID\n"
                    + "WHERE (((ProjectAssignment.ProjectAssignment_EmployeeID)='" + UserID + "'))";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                Project nextProject = new Project(rs.getInt("Project_ProjectID"), rs.getString("Project_Title"), rs.getDate("Project_StartDate").toLocalDate(), rs.getDate("Project_EndDate").toLocalDate(), rs.getDouble("Project_Budget"));
                ProjectList.add(nextProject);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return ProjectList;
    }

    public static ArrayList<ProjectTimeLog> getAllUsersTimeLogs(String userID) {
        ArrayList<ProjectTimeLog> timeLogs = new ArrayList<>();
        try {

            String sql = "SELECT ProjectTimeLog.*\n"
                    + "FROM ProjectAssignment INNER JOIN ProjectTimeLog ON ProjectAssignment.ProjectAssignment_Id = ProjectTimeLog.ProjectTimeLog_ProjectAssignmentId\n"
                    + "WHERE (((ProjectAssignment.ProjectAssignment_EmployeeID)='" + userID + "'));";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                ProjectTimeLog ptl = new ProjectTimeLog(rs.getInt("ProjectTimeLog_Id"), rs.getDate("ProjectTimeLog_DateLogged"), rs.getString("ProjectTimeLog_DescriptionOfWork"), rs.getInt("ProjectTimeLog_HoursLogged"), rs.getInt("ProjectTimeLog_ProjectAssignmentId"));
                timeLogs.add(ptl);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return timeLogs;
    }

    public static ArrayList<ProjectTimeLog> getUsersTimeLogsInDateRange(String userID, String fromDate, String toDate) {

        ArrayList<ProjectTimeLog> timeLogs = new ArrayList<>();
        try {
            String sql = "SELECT ProjectTimeLog.ProjectTimeLog_Id, ProjectTimeLog.ProjectTimeLog_DateLogged, ProjectTimeLog.ProjectTimeLog_DescriptionOfWork, ProjectTimeLog.ProjectTimeLog_HoursLogged, ProjectTimeLog.ProjectTimeLog_ProjectAssignmentId\n"
                    + "FROM ProjectAssignment INNER JOIN ProjectTimeLog ON ProjectAssignment.ProjectAssignment_Id = ProjectTimeLog.ProjectTimeLog_ProjectAssignmentId\n"
                    + "WHERE (((ProjectAssignment.ProjectAssignment_EmployeeID)='" + userID + "') AND ((ProjectTimeLog.ProjectTimeLog_DateLogged)>=" + getSQLDateString(fromDate) + " And (ProjectTimeLog.ProjectTimeLog_DateLogged)<=" + getSQLDateString(toDate) + "));";

            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            while (rs.next()) {
                ProjectTimeLog ptl = new ProjectTimeLog(rs.getInt("ProjectTimeLog_Id"), rs.getDate("ProjectTimeLog_DateLogged"), rs.getString("ProjectTimeLog_DescriptionOfWork"), rs.getInt("ProjectTimeLog_HoursLogged"), rs.getInt("ProjectTimeLog_ProjectAssignmentId"));
                timeLogs.add(ptl);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return timeLogs;
    }

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Return Arraylist of all objects">
    public static ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> EmployeeList = new ArrayList<>();
        try {

            String sql = "SELECT * FROM Employee";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                Employee nextEmployee = new Employee(rs.getString("Employee_Id"), rs.getString("Employee_Fname"), rs.getString("Employee_Lname"), rs.getString("Employee_Password"), rs.getString("Employee_Dept"), rs.getString("Employee_Office"), rs.getString("Employee_Phone"), rs.getDate("Employee_HireDate"), rs.getDouble("Employee_HourlyRate"));
                EmployeeList.add(nextEmployee);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return EmployeeList;
    }

    public static ArrayList<Project> getAllProjects() {
        ArrayList<Project> ProjectList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Project";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            while (rs.next()) {
                Project nextProject = new Project(rs.getInt("Project_ProjectID"), rs.getString("Project_Title"), rs.getDate("Project_StartDate").toLocalDate(), rs.getDate("Project_EndDate").toLocalDate(), rs.getDouble("Project_Budget"));
                ProjectList.add(nextProject);
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return ProjectList;
    }

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="Returns 1 field">    
    public static String getProjectNameFromProjectID(int projectID) {
        String projectName = null;
        try {

            String sql = "SELECT Project_Title FROM Project where projectID = " + projectID;
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                projectName = rs.getString("Project_Title");
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return projectName;
    }

    public static String getProjectNameFromTimeLogID(int TimeLogID) {
        String projectName = null;
        try {

            String sql = "SELECT Project.Project_Title\n"
                    + "FROM (Project INNER JOIN ProjectAssignment ON Project.Project_ProjectID = ProjectAssignment.ProjectAssignment_ProjectID) INNER JOIN ProjectTimeLog ON ProjectAssignment.ProjectAssignment_Id = ProjectTimeLog.ProjectTimeLog_ProjectAssignmentId\n"
                    + "WHERE (((ProjectTimeLog.ProjectTimeLog_Id)=" + TimeLogID + "))";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                projectName = rs.getString("Project_Title");
            }

            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return projectName;
    }

    public static double getEmployeePay(String employeeID) {
        double hourlyPay = 0.0;
        try {
            String sql = "SELECT Employee.Employee_HourlyRate\n"
                    + "FROM Employee\n"
                    + "WHERE Employee.Employee_Id= '" + employeeID + "';";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                hourlyPay = rs.getInt("Employee_HourlyRate");
            }
            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return hourlyPay;
    }

    public static String getEmployeeName(int employeeID) {
        String employeeName = null;
        try {

            String sql = "SELECT Employee.Employee_Lname, Employee.Employee_Fname\n"
                    + "FROM Employee\n"
                    + "WHERE (((Employee.Employee_Id)='" + employeeID + "'));";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            if (rs.next()) {
                employeeName = rs.getString("Employee_Fname") + rs.getString("Employee_Lname");
            }

            rs.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);

        }
        return employeeName;
    }

    public static HashMap<String, Integer> getAllEmployeeHoursInDateRange(String fromDate, String toDate) {
        HashMap<String, Integer> EmployeeHours = new HashMap<String, Integer>();
        try {
            String sql = "SELECT DISTINCT ProjectAssignment.ProjectAssignment_EmployeeID\n"
                    + "FROM ProjectAssignment INNER JOIN ProjectTimeLog ON ProjectAssignment.ProjectAssignment_Id = ProjectTimeLog.ProjectTimeLog_ProjectAssignmentId\n"
                    + "GROUP BY ProjectTimeLog.ProjectTimeLog_DateLogged, ProjectAssignment.ProjectAssignment_EmployeeID\n"
                    + "HAVING (((ProjectTimeLog.ProjectTimeLog_DateLogged)>=" + getSQLDateString(fromDate) + "  And (ProjectTimeLog.ProjectTimeLog_DateLogged)<=" + getSQLDateString(toDate) + "));";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);

            //loop through the users arraylist and add up the time, add user Id and total to employee hours hashmap
            while (rs.next()) {
                ArrayList<ProjectTimeLog> IndividualTimeLog = getUsersTimeLogsInDateRange(rs.getString("ProjectAssignment_EmployeeID"), fromDate, toDate);
                int totalHours = 0;
                for (int i = 0; i < IndividualTimeLog.size(); i++) {
                    totalHours = totalHours + IndividualTimeLog.get(i).getProjectTimeLog_HoursLogged();
                }

                EmployeeHours.put(rs.getString("ProjectAssignment_EmployeeID"), totalHours);
            }

            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return EmployeeHours;
    }

// </editor-fold>
    
// <editor-fold defaultstate="collapsed" desc="SQL date conversion functions">
    private static String getSQLDateString(Date date) {
        //Be careful with dates in Access queries, they are not in standard SQL format (they often have extra #s and are the wrong way round). 
        //This converts the date into standard SQL format which will work with access when accesses from your java code
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        return "'" + DATE_FORMAT.format(date) + "'";
    }

    private static String getSQLDateString(String date) {
        try {
            SimpleDateFormat UK_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
            return "'" + DATE_FORMAT.format(UK_DATE_FORMAT.parse(date)) + "'";
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
        return "";
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Insert/Update functions">  
    public static void insertNewEmployee(Employee employee) {

        try {
            String sql = "SELECT Employee.* FROM Employee";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.moveToInsertRow();
                rs.updateString("Employee_Id", employee.getEmployee_Id());
                rs.updateString("Employee_Lname", employee.getEmployee_Lname());
                rs.updateString("Employee_Fname", employee.getEmployee_Fname());
                rs.updateString("Employee_Password", employee.getEmployee_Password());
                rs.updateString("Employee_Dept", employee.getEmployee_Dept());
                rs.updateString("Employee_Office", employee.getEmployee_Office());
                rs.updateString("Employee_Phone", employee.getEmployee_Phone());
                
                rs.updateDate("Employee_HireDate", employee.getEmployee_HireDate());
                rs.updateDouble("Employee_HourlyRate", employee.getEmployee_HourlyRate());
                
                rs.insertRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }
    public static void insertNewProject(Project project) {

        try {
            String sql = "SELECT Project.* FROM Project";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                rs.moveToInsertRow();
                //Primary key not needed as it is an autonumber, it adds that field automatically
                rs.updateString("Project_Title", project.getProject_Title());
                rs.updateDate("Project_StartDate", Date.valueOf(project.getProject_StartDate()));
                rs.updateDate("Project_EndDate", Date.valueOf(project.getProject_EndDate()));
                rs.updateDouble("Project_Budget", project.getProject_Budget());
                rs.insertRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }
    
    public static void updateEmployee(Employee employee) {
        try {
            String sql = "SELECT Employee.* FROM Employee where Employee_Id = '"+employee.getEmployee_Id()+"'";
            ResultSet rs = executeSQL.executeQuery(getConnection(), sql);
            if (rs.next()) {
                //rs.moveToInsertRow();
                //Primary key not needed as it is an autonumber, it adds that field automatically
                rs.updateString("Employee_Fname", employee.getEmployee_Fname());
                rs.updateString("Employee_Lname", employee.getEmployee_Lname());
                rs.updateString("Employee_Dept", employee.getEmployee_Dept());
                rs.updateString("Employee_Office", employee.getEmployee_Office());
                rs.updateDouble("Employee_HourlyRate", employee.getEmployee_HourlyRate());
                rs.updateRow();
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error in the repository class: " + e);
        }
    }

// </editor-fold>
}
