
    import java.util.Random;

    public class Main {

        private static final Random RANDOM = new Random();
        private static final String[] NAMES = {"Егор", "Андрей", "Юрий", "Макар", "Степан", "Иван", "Павел", "Руслан","Лев", "Мирон"};
        private static final String[] SURNAMES = {"Смирнов", "Тарасов", "Кузин", "Капустин", "Поздняков", "Смирнов", "Греков", "Фролов", "Соколов", "Уваров"};
        private static final String[] MIDDLE_NAMES = {"Александрович", "Павлович", "Тимурович", "Леонидович", "Даниилович", "Матвеевич", "Михайлович", "Родионович", "Максимович", "Львович"};

        private static void fillEmployees(EmployeeBook employeeBook) {
            for (int i = 0; i < 10; i++) {
                employeeBook.addEmployee(generate());
            }
        }

        private static Employee generate() {
            String fullName = SURNAMES[RANDOM.nextInt(SURNAMES.length)]
                    + ' ' + NAMES[RANDOM.nextInt(NAMES.length)]
                    + ' ' + MIDDLE_NAMES[RANDOM.nextInt(MIDDLE_NAMES.length)];
            return new Employee(
                    fullName,
                    RANDOM.nextInt(30_000, 100_000),
                    RANDOM.nextInt(1, 6)
            );
        }
        public static void main(String[] args) {

            // ** Задания уровня "Очень сложно"
            System.out.println("================= Уровень \"Очень сложно\" ======================");
            EmployeeBook employeeBook = new EmployeeBook();

            // ** Добавить сотрудника
            fillEmployees(employeeBook);

            // ** Удалить сотрудника по ID
            employeeBook.removeEmployee(1);
            employeeBook.removeEmployee(3);

            // ** Удалить сотрудника по ФИО
            employeeBook.removeEmployee("Капустин Лев Михайлович");

            // ** Обратно добавить удаленного сотрудника
            employeeBook.addEmployee(generate());
            employeeBook.addEmployee(new Employee("Капустин Лев Михайлович", 65_000, 3));

            // ** Изменить зарплату сотрудника по ФИО
            employeeBook.setSalaryByName("Капустин Лев Михайлович", 87_000);
            employeeBook.setSalaryByName(employeeBook.getEmployeeWithMinSalary().getFullName(), 90_000);

            // ** Изменить отдел, где работает сотрудник по его ФИО
            employeeBook.setDepartmentByName("Капустин Лев Михайлович", 1);

            // ** Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
            employeeBook.printAllEmployeesDataGroupedByDepartment();
            // Задания уровня "Базовая сложность"
            System.out.println("================= Уровень \"Базовая сложность\" ======================");
            // Получить список всех сотрудников со всеми имеющимися по ним данными
            // (вывести в консоль значения всех полей (toString))
            employeeBook.printAllEmployeesData();
            // Посчитать сумму затрат на зарплаты в месяц
            int totalMonthlySalary = employeeBook.calcTotalMonthlySalary();
            System.out.println("Сумма затрат на зарплаты в месяц: " + totalMonthlySalary + " руб.");

            // Найти сотрудника с минимальной зарплатой
            Employee employeeWithMinimumSalary = employeeBook.getEmployeeWithMinSalary();
            System.out.println("Сотрудник с мин. ЗП: " + employeeWithMinimumSalary);

            // Найти сотрудника с максимальной зарплатой
            Employee employeeWithMaximumSalary = employeeBook.getEmployeeWithMaxSalary();
            System.out.println("Сотрудник с макс. ЗП: " + employeeWithMaximumSalary);

            // Подсчитать среднее значение зарплат
            double averageMonthlySalary = employeeBook.calcAverageMonthlySalary();
            System.out.println("Среднее значение зарплат в месяц: " + averageMonthlySalary + " руб.");

            // Получить Ф. И. О. всех сотрудников (вывести в консоль)
            employeeBook.printEmployeesFullNames();
            // Задания уровня "Повышенная сложность"
            System.out.println("================= Уровень \"Повышенная сложность\" ======================");
            // * 1. Проиндексировать зарплату (вызвать изменение зарплат у всех сотрудников на величину аргумента в %)
            System.out.println("================= Задача 1 ======================");

            int indexPercent = 12;
            employeeBook.changeEmployeesSalary(indexPercent);
            System.out.printf("ЗП после индексации на %d%%%n", indexPercent);
            employeeBook.printAllEmployeesData();

            // * 2. Получить в качестве параметра номер отдела (1–5) и найти (всего 6 методов):
            System.out.println("================= Задача 2 ======================");
            int department = 5;

            //    1. Сотрудника с минимальной зарплатой.
            String employeeWithMinimumSalaryInDepartment = employeeBook.getEmployeeWithMinSalary(department) == null
                    ? "Зарплаты сотрудников в отделе %d равны %n"
                    : "Сотрудник с мин. ЗП в отделе %d: " + employeeBook.getEmployeeWithMinSalary(department) + "%n";
            System.out.printf(employeeWithMinimumSalaryInDepartment, department);

            //    2. Сотрудника с максимальной зарплатой.
            String employeeWithMaximumSalaryInDepartment = employeeBook.getEmployeeWithMaxSalary(department) == null
                    ? "Зарплаты сотрудников в отделе %d равны %n"
                    : "Сотрудник с макс. ЗП в отделе %d: " + employeeBook.getEmployeeWithMaxSalary(department) + "%n";
            System.out.printf(employeeWithMaximumSalaryInDepartment, department);

            //    3. Сумму затрат на зарплату по отделу.
            int totalMonthlySalaryInDepartment = employeeBook.calcTotalMonthlySalary(department);
            System.out.printf("Сумма затрат на зарплаты отдела %d в месяц: %d руб.%n", department, totalMonthlySalaryInDepartment);

            //    4. Среднюю зарплату по отделу (учесть, что количество людей в отделе отличается от employees.length).
            double averageMonthlySalaryInDepartment = employeeBook.calcAverageMonthlySalary(department);
            System.out.println("Среднее значение зарплат отдела " + department + " в месяц: " + averageMonthlySalaryInDepartment + " руб.");

            //    5. Проиндексировать зарплату всех сотрудников отдела на процент, который приходит в качестве параметра.
            employeeBook.changeEmployeesSalary(indexPercent, department);
            employeeBook.printAllEmployeesData();

            //    6. Напечатать всех сотрудников отдела (все данные, кроме отдела).
            employeeBook.printDepartmentEmployeesData(department);
            // * 3. Получить в качестве параметра число и найти:
            //    1. Всех сотрудников с зарплатой меньше числа (вывести id, Ф. И. О. и зарплатой в консоль).
            //    2. Всех сотрудников с зарплатой больше (или равно) числа (вывести id, Ф. И. О. и зарплатой в консоль).
            System.out.println("================= Задача 3 ======================");

            int benchmark = 75_000;

            System.out.println("Сотрудники с зарплатой меньше чем " + benchmark + " рублей:");
            employeeBook.printEmployeesWithSalaryLowerThanBenchmark(benchmark);

            System.out.println("Сотрудники с зарплатой больше или равной " + benchmark + " рублей:");
            employeeBook.printEmployeesWithSalaryHigherThanBenchmark(benchmark);
        }


}