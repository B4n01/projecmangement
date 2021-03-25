
package Objects;

import libraryFunctions.iPrintable;

public class Department implements iPrintable{
    private int Department_Number;
    private String Department_Name;
    private String Department_Head;

    public Department(int Department_Number, String Department_Name, String Department_Head) {
        this.Department_Number = Department_Number;
        this.Department_Name = Department_Name;
        this.Department_Head = Department_Head;
    }
    
    @Override
    public String getPrintableString(){
        return "Department Name: "+Department_Name + "Department Head: " + Department_Head;
    }
    
    public int getDepartment_Number() {
        return Department_Number;
    }

    public void setDepartment_Number(int Department_Number) {
        this.Department_Number = Department_Number;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public void setDepartment_Name(String Department_Name) {
        this.Department_Name = Department_Name;
    }

    public String getDepartment_Head() {
        return Department_Head;
    }

    public void setDepartment_Head(String Department_Head) {
        this.Department_Head = Department_Head;
    }
    
    
    
}
