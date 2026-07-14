package dao;

import database.DBConnection;
import model.Expense;

import java.sql.*;
import java.util.ArrayList;

public class ExpenseDAO {

    // Add Expense
    public void addExpense(Expense expense) {

        String sql = "INSERT INTO expense(title, category, amount) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, expense.getTitle());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());

            ps.executeUpdate();

            System.out.println("Expense Added Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View Expenses
    public ArrayList<Expense> getAllExpenses() {

        ArrayList<Expense> list = new ArrayList<>();

        String sql = "SELECT * FROM expense";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Expense expense = new Expense();

                expense.setId(rs.getInt("id"));
                expense.setTitle(rs.getString("title"));
                expense.setCategory(rs.getString("category"));
                expense.setAmount(rs.getDouble("amount"));

                list.add(expense);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // Delete Expense
    public void deleteExpense(int id) {

        String sql = "DELETE FROM expense WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Expense Deleted Successfully!");
            } else {
                System.out.println("Expense ID Not Found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}