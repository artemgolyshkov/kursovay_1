public class EmployeeBook {
    private static final int SIZE = 10;
    private final Employee[] employees;

    public EmployeeBook() {
        this.employees = new Employee[SIZE];
    }

    public void addEmployee(Employee employee) {

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = employee;
                break;
            }
        }
    }
    //Удаляет сотрудника по ID (обнуляет ячейку в массиве сотрудников)
    public void removeEmployee(int id) {
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            if (employee != null && employee.getId() == id) {
                employees[i] = null;
                System.out.println("Сотрудник с ID " + id + " удалён!");
                return;
            }
        }
    }

     //Удаляет сотрудника по ФИО (обнуляет ячейку в массиве сотрудников)
    public void removeEmployee(String fullName) {
        Employee foundEmployee = findEmployeeByFullName(fullName);
        if (foundEmployee == null) {
            System.out.println("Сотрудник \"" + fullName + "\" не найден!");
        } else {
            int id = foundEmployee.getId();
            employees[id] = null;
            System.out.println("Сотрудник \"" + fullName + "\" удалён!");
        }
    }

     // Ищет сотрудника по ФИО
    private Employee findEmployeeByFullName(String fullName) {
        for (Employee employee : employees) {
            if (employee != null && employee.getFullName().equals(fullName)) {
                return employee;
            }
        }
        return null;
    }

    public void setSalaryByName(String fullName, int newSalary) {
        Employee foundEmployee = findEmployeeByFullName(fullName);
        if (foundEmployee != null) {
            foundEmployee.setSalary(newSalary);
            System.out.printf("Зарплата сотрудника %s изменена. Новая зарплата: %d руб.%n", fullName, newSalary);
        } else {
            System.out.printf("Сотрудник %s не найден!%n", fullName);
        }
    }

    public void setDepartmentByName(String fullName, int newDepartment) {
        Employee foundEmployee = findEmployeeByFullName(fullName);
        if (foundEmployee != null) {
            foundEmployee.setDepartment(newDepartment);
            System.out.printf("Отдел сотрудника %s изменён. Новый отдел: %d%n", fullName, newDepartment);
        } else {
            System.out.printf("Сотрудник %s не найден!%n", fullName);
        }
    }


     // Выводит в консоль список сотрудников с зарплатой больше заданного числа или равной
    public void printEmployeesWithSalaryHigherThanBenchmark(int benchmark) {
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() >= benchmark) {
                System.out.println(employee.getEmployeeData());
            }
        }
    }

     //Выводит в консоль список сотрудников с зарплатой меньше заданного числа
    public void printEmployeesWithSalaryLowerThanBenchmark(int benchmark) {
        for (Employee employee : employees) {
            if (employee != null && employee.getSalary() < benchmark) {
                System.out.println(employee.getEmployeeData());
            }
        }
    }

     //Получает массив сотрудников одного отдела длиной исходного массива
    private Employee[] getEmployeesByDepartment(int department) {
        Employee[] temp = new Employee[employees.length];
        int count = 0;
        for (Employee employee : employees) {
            if (employee != null && employee.getDepartment() == department) {
                temp[count++] = employee;
            }
        }
        return trim(temp, count);
    }

     // Обрезает массив
    private Employee[] trim(Employee[] array, int count) {
        Employee[] result = new Employee[count];
        System.arraycopy(array, 0, result, 0, result.length);
        return result;
    }

     // Выводит на экран данные сотрудников одного отдела
    public void printDepartmentEmployeesData(int department) {
        Employee[] departmentEmployees = getEmployeesByDepartment(department);
        for (Employee employee : departmentEmployees) {
            System.out.println(employee.getEmployeeData());
        }
    }

    // Индексирует зарплату всех сотрудников
    public void changeEmployeesSalary(int percent) {
        double coefficient = 1 + percent / 100D;
        for (Employee employee : employees) {
            if (employee != null) {
                employee.setSalary((int) (employee.getSalary() * coefficient));
            }
        }
    }
     // Индексирует зарплату сотрудников по отделу
    public void changeEmployeesSalary(int percent, int department) {
        Employee[] departmentEmployees = getEmployeesByDepartment(department);
        for (Employee employee : departmentEmployees) {
            int salary = employee.getSalary() + employee.getSalary() * percent / 100;
            employee.setSalary(salary);
        }
    }
    public void printEmployeesFullNames() {
        for (Employee employee : employees) {
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }

    public Employee getEmployeeWithMaxSalary(int department) {
        Employee[] departmentEmployees = getEmployeesByDepartment(department);
        return getEmployeeWithMaxSalary(departmentEmployees);
    }

    public Employee getEmployeeWithMaxSalary() {
        return getEmployeeWithMaxSalary(employees);
    }


     // Получает сотрудника с максимальной зарплатой
    private Employee getEmployeeWithMaxSalary(Employee[] array) {
        int max = Integer.MIN_VALUE;
        Employee employee = null;
        for (Employee emp : array) {
            if (emp != null && emp.getSalary() > max) {
                max = emp.getSalary();
                employee = emp;
            }
        }
        return employee;
    }

    public Employee getEmployeeWithMinSalary(int department) {
        Employee[] departmentEmployees = getEmployeesByDepartment(department);
        return getEmployeeWithMinSalary(departmentEmployees);
    }

    public Employee getEmployeeWithMinSalary() {
        return getEmployeeWithMinSalary(employees);
    }

    // Получает объект сотрудника с минимальной зарплатой
    private Employee getEmployeeWithMinSalary(Employee[] array) {
        int min = Integer.MAX_VALUE;
        Employee employee = null;
        for (Employee emp : array) {
            if (emp != null && emp.getSalary() < min) {
                min = emp.getSalary();
                employee = emp;
            }
        }
        return employee;
    }

    public double calcAverageMonthlySalary(int department) {
        Employee[] departmentEmployees = getEmployeesByDepartment(department);
        return (double) calcTotalMonthlySalary(department) / departmentEmployees.length;
    }

    public double calcAverageMonthlySalary() {
        return (double) calcTotalMonthlySalary() / getSize();
    }

    // Считает общую зарплату за месяц
    public int calcTotalMonthlySalary() {
        int total = 0;
        for (Employee employee : employees) {
            if (employee != null) {
                total += employee.getSalary();
            }
        }
        return total;
    }
    // Считает общую зарплату за месяц по отделу
    public int calcTotalMonthlySalary(int department) {
        Employee[] departmentEmployees = getEmployeesByDepartment(department);
        int total = 0;
        for (Employee employee : departmentEmployees) {
            if (employee != null) {
                System.out.println(employee.getFullName() + " | " + employee.getSalary());
                total += employee.getSalary();
            }
        }
        return total;
    }

     // Выводит методом toString() все данные о всех сотрудниках
    public void printAllEmployeesData() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

     // Получает количество заполненных элементов массива сотрудников
    public int getSize() {
        return SIZE;
    }


    // Выводит на экран список сотрудников по каждому отделу
    public void printAllEmployeesDataGroupedByDepartment() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Отдел " + i);
            Employee[] departmentEmployees = getEmployeesByDepartment(i);
            if (departmentEmployees.length > 0) {
                for (Employee employee : departmentEmployees) {
                    System.out.println(employee.getFullName());
                }
            } else {
                System.out.println("В отделе нет сотрудников");
            }
        }
    }
}
