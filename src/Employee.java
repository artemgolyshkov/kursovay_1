import java.util.Objects;

public class Employee {
 private final String fullName;
 private int salary;
 private int department;
 private static int counter = 1;
 private final int id;

 public Employee( String fullName, int salary, int department){
     this.fullName = fullName;
     this.salary = salary;
     this.department = department;
     this.id = counter++;
 }

    public String getFullName() {
        return fullName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = Math.max(salary,0);
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = (department >= 1 && department <= 5) ? department : 1;
    }

    public static int getCounter() {
        return counter;
    }

    public int getId() {
        return id;
    }
    public String toString(){
     return String.format("ID: %d, ФИО: %s, ЗП: %d, отдел: %d", id, getFullName(), salary, department);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return fullName.equals(employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
    public String getEmployeeData() {
        return "ID: " + getId() + " | ФИО: " + getFullName() + " | зарплата: " + getSalary();
    }

}
