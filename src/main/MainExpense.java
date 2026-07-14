package main;

import dao.ExpenseDAO;
import model.Expense;

import java.util.ArrayList;
import java.util.Scanner;

public class MainExpense {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ExpenseDAO dao = new ExpenseDAO();

        while (true) {

            System.out.println("\n===== SMART EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Category: ");
                    String category = sc.nextLine();

                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();

                    Expense expense = new Expense(title, category, amount);

                    dao.addExpense(expense);

                    break;

                case 2:

                    ArrayList<Expense> list = dao.getAllExpenses();

                    System.out.println("\nID\tTitle\tCategory\tAmount");

                    for (Expense e : list) {

                        System.out.println(
                                e.getId() + "\t"
                                        + e.getTitle() + "\t"
                                        + e.getCategory() + "\t"
                                        + e.getAmount());

                    }

                    break;

                case 3:

                    System.out.print("Enter Expense ID to Delete: ");

                    int id = sc.nextInt();

                    dao.deleteExpense(id);

                    break;

                case 4:

                    System.out.println("Thank You!");

                    System.exit(0);

                default:

                    System.out.println("Invalid Choice!");

            }

        }

    }

}