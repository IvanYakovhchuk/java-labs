package com.java.labs;

import com.java.labs.entity.Employee;
import com.java.labs.entity.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseDemo {
    static void main() {
//        getAllEmployees();
//        getAllTasks();
//        getAllEmployeesOfDepartment(2);
//        addTaskForEmployee(1);
//        getAllTasksOfAnEmployee(1);
//        removeEmployee(4);
    }

    public static void getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        String query = "SELECT id, first_name, last_name, position, department_id FROM employees";

        try (DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String position = rs.getString("position");
                int departmentId = rs.getInt("department_id");

                employees.add(new Employee(id, lastName, firstName, position, departmentId));
            }

            employees.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        String query = "SELECT id, description, employee_id FROM tasks";

        try (DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int employeeId = rs.getInt("employee_id");

                tasks.add(new Task(id, description, employeeId));
            }

            tasks.forEach(System.out::println);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void getAllEmployeesOfDepartment(int dId) {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT id, last_name, first_name, position, department_id FROM employees WHERE department_id = " + dId;

        try (DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                String position = rs.getString("position");
                int departmentId = rs.getInt("department_id");

                employees.add(new Employee(id, lastName, firstName, position, departmentId));
            }

            employees.forEach(System.out::println);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addTaskForEmployee(int eId) {
        String query = "INSERT INTO tasks (description, employee_id) VALUES (?, ?)";
        String description = IO.readln("Введіть опис задачі: ");

        try (DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, description);
            stmt.setInt(2, eId);

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Створення нової задачі провалилось, жоден рядок не змінився.");
            } else {
                System.out.println("Завдання для співробітника з ID " + eId + " успішно додано.");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void getAllTasksOfAnEmployee(int eId) {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT id, description, employee_id FROM tasks WHERE employee_id = " + eId;

        try (DatabaseConnection db = new DatabaseConnection();
             Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                int employeeId = rs.getInt("employee_id");

                tasks.add(new Task(id, description, employeeId));
            }

            tasks.forEach(System.out::println);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void removeEmployee(int eId) {
        String query = "DELETE FROM employees WHERE id = ?";

        try (DatabaseConnection db = new DatabaseConnection();
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet keys = stmt.getGeneratedKeys()) {

            stmt.setInt(1, eId);

            int rows = stmt.executeUpdate();
            if (rows == 0) {
                throw new SQLException("Видалення співробітника провалилось, жоден рядок не змінився.");
            } else {
                System.out.println("Співробітника з ID " + eId + " успішно видалено");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
