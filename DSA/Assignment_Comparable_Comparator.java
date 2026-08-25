import java.util.*;
import java.time.LocalDate;

// 1. Sort by name (natural alphabetical order using a Comparator, not
// Comparable)
// 2. Sort by salary, descending
// 3. Give preference: name starts with "A" on top AND salary > 100000, sorted
// by salary descending
// 4. Sort by department, then by name within each department
// 5. Sort by age, ascending
// 6. Sort by joining date, oldest employee (earliest date) first
// 7. Sort by rating descending, then by salary descending as a tiebreaker

public class Assignment_Comparable_Comparator {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(1, "Aarav", "Engineering", 120000, 29, LocalDate.of(2021, 3, 15), 4.5),
                new Employee(2, "Anika", "Sales", 95000, 34, LocalDate.of(2019, 7, 1), 3.8),
                new Employee(3, "Bhavya", "Engineering", 135000, 41, LocalDate.of(2015, 1, 10), 4.9),
                new Employee(4, "Chirag", "Marketing", 88000, 25, LocalDate.of(2022, 11, 5), 4.1),
                new Employee(5, "Aditi", "Sales", 110000, 30, LocalDate.of(2020, 6, 20), 4.6),
                new Employee(6, "Aman", "Engineering", 102000, 27, LocalDate.of(2021, 9, 12), 3.9),
                new Employee(7, "Divya", "Marketing", 75000, 23, LocalDate.of(2023, 2, 1), 4.0),
                new Employee(8, "Aryan", "Sales", 140000, 38, LocalDate.of(2016, 4, 18), 4.7)));

        // 1. Collections.sort(employees, (a, b) -> a.getName().compareTo(b.getName()));
        // 2. Collections.sort(employees,(a,b) -> Double.compare(b.getSalary(),
        // a.getSalary()));
        Collections.sort(employees, (a, b) -> {

            boolean aPriority = a.getName().startsWith("A") && a.getSalary() > 100000;

            boolean bPriority = b.getName().startsWith("A") && b.getSalary() > 100000;

            // Priority employees come first
            if (aPriority && !bPriority) {
                return -1;
            }

            if (!aPriority && bPriority) {
                return 1;
            }

            // Same priority → salary descending
            return Double.compare(b.getSalary(), a.getSalary());
        });
        // 4. Collections.sort(employees,(a,b) -> {
        // if(a.getDepartment().equals(b.getDepartment())){
        // return a.getName().compareTo(b.getName());
        // }

        // return a.getDepartment().compareTo(b.getDepartment());
        // });

        // 5. Collections.sort(employees,(a,b) -> {
        // return Integer.compare(b.getAge(),a.getAge());
        // });

        // 6. Collections.sort(employees,(a,b) -> {
        // LocalDate x = a.getJoiningDate();
        // LocalDate y = b.getJoiningDate();
        // return x.compareTo(y);
        // });

        // 7. Collections.sort(employees, (a, b) -> {
        //     if (a.getRating() == b.getRating()) {
        //         return Double.compare(b.getSalary(), a.getSalary());
        //     }
        //     return Double.compare(b.getRating(), a.getRating());
        // });

        System.out.println(employees);
    }
}

class Employee {
    private int employeeId;
    private String name;
    private String department;
    private double salary;
    private int age;
    private LocalDate joiningDate;
    private double rating;

    public Employee(int employeeId, String name, String department,
            double salary, int age, LocalDate joiningDate, double rating) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
        this.joiningDate = joiningDate;
        this.rating = rating;
    }

    // Getters
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

    @Override
    public String toString() {
        return name + " | " + department + " | ₹" + salary + " | age " + age + " | joined " + joiningDate;
    }
}
