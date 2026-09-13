import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Employee {
    private int employeeId;
    private String name;
    private String department;
    private double salary;
    private int age;
    private LocalDate joiningDate;
    private double rating;

    public Employee(int employeeId, String name, String department, double salary, int age, LocalDate joiningDate,
            double rating) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.joiningDate = joiningDate;
        this.rating = rating;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public double getRating() {
        return rating;
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee(1, "Aarav", "Engineering", 120000, 29, LocalDate.of(2021, 3, 15), 4.5),
                new Employee(2, "Anika", "Sales", 95000, 34, LocalDate.of(2019, 7, 1), 3.8),
                new Employee(3, "Bhavya", "Engineering", 135000, 41, LocalDate.of(2015, 1, 10), 4.9),
                new Employee(4, "Chirag", "Marketing", 88000, 25, LocalDate.of(2022, 11, 5), 4.1),
                new Employee(5, "Aditi", "Sales", 110000, 30, LocalDate.of(2020, 6, 20), 4.6),
                new Employee(6, "Aman", "Engineering", 102000, 27, LocalDate.of(2021, 9, 12), 3.9),
                new Employee(7, "Divya", "Marketing", 75000, 23, LocalDate.of(2023, 2, 1), 4.0),
                new Employee(8, "Aryan", "Sales", 140000, 38, LocalDate.of(2016, 4, 18), 4.7));

        employees.stream().filter(e -> e.startsWith("A") && e.getSalary() > 100000)
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .collect(Collectors.toList());
                System.out.println(employees);
    }
}
