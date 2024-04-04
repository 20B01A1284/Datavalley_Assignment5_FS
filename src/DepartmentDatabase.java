import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

public class DepartmentDatabase {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Teja@7075";

    public static void main(String[] args) {
        Department department1 = new Department(1, "HR");
        Department department2 = new Department(2, "IT");
        storeDepartment(department1);
        storeDepartment(department2);
    }

    private static void storeDepartment(Department department) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sql = String.format("INSERT INTO department VALUES (%d, '%s')",
                    department.getId(), department.getName());

            // Execute the SQL query
            int rowsAffected = statement.executeUpdate(sql);

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Department inserted successfully: " + department.getName());
            } else {
                System.out.println("Failed to insert department: " + department.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
