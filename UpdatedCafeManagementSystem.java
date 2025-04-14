
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

// Main Class for the Updated Cafe Management System
public class UpdatedCafeManagementSystem {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}

// LoginFrame Class: Represents the Login GUI
class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginFrame() {
        setTitle("Cafe Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel headingLabel = new JLabel("Welcome to Cafe Management System", SwingConstants.CENTER);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headingLabel.setForeground(Color.BLUE);
        add(headingLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 3, 10, 10));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.NORTH);

        loginButton = new JButton("Login");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(loginButton);
        add(buttonPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        add(statusLabel, BorderLayout.SOUTH);

        loginButton.addActionListener(new LoginActionListener());
    }

    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateCredentials(username, password)) {
                statusLabel.setText("Login successful!");
                statusLabel.setForeground(Color.GREEN);
                dispose(); // Close the login window
                showMainMenu(); // Show the main menu
            } else {
                statusLabel.setText("Invalid username or password.");
                statusLabel.setForeground(Color.RED);
            }
        }

        private boolean validateCredentials(String username, String password) {
            return username.equals("admin") && password.equals("password123");
        }

        private void showMainMenu() {
            MainMenu mainMenu = new MainMenu();
            mainMenu.display();
        }
    }
}

// Main Menu Class
class MainMenu {
    private final MenuManagement menuManagement = new MenuManagement();

    public void display() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display current menu items
            System.out.println("\\n=== Current Menu Items ===");
            menuManagement.viewMenu();

            // Display main menu options
            System.out.println("\\n=== Cafe Management System ===");
            System.out.println("1. Menu Management");
            System.out.println("2. Order Management");
            System.out.println("3. Billing");
            System.out.println("4. Reports");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> menuManagement.manageMenu();
                case 2 -> System.out.println("Order Management selected (Functionality to be implemented).");
                case 3 -> System.out.println("Billing selected (Functionality to be implemented).");
                case 4 -> System.out.println("Reports selected (Functionality to be implemented).");
                case 5 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}

// MenuItem Class
class MenuItem {
    private int id;
    private String name;
    private double price;
    private String category;

    public MenuItem(int id, String name, double price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: $" + price + ", Category: " + category;
    }
}

// MenuManagement Class
class MenuManagement {
    private final ArrayList<MenuItem> menuItems = new ArrayList<>();
    private int nextId = 1;

    public MenuManagement() {
        // Adding initial items
        menuItems.add(new MenuItem(nextId++, "Mocha", 5.50, "Beverage"));
        menuItems.add(new MenuItem(nextId++, "Flat White", 4.50, "Beverage"));
    }

    public void manageMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\\n=== Menu Management ===");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. View Menu");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addItem(scanner);
                case 2 -> updateItem(scanner);
                case 3 -> deleteItem(scanner);
                case 4 -> viewMenu();
                case 5 -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    public void viewMenu() {
        if (menuItems.isEmpty()) {
            System.out.println("The menu is empty.");
        } else {
            for (MenuItem item : menuItems) {
                System.out.println(item);
            }
        }
    }

    private void addItem(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();

        MenuItem item = new MenuItem(nextId++, name, price, category);
        menuItems.add(item);
        System.out.println("Item added successfully!");
    }

    private void updateItem(Scanner scanner) {
        System.out.print("Enter the ID of the item to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        MenuItem item = findItemById(id);

        if (item != null) {
            System.out.print("Enter new name: ");
            item.setName(scanner.nextLine());
            System.out.print("Enter new price: ");
            item.setPrice(scanner.nextDouble());
            scanner.nextLine();
            System.out.print("Enter new category: ");
            item.setCategory(scanner.nextLine());
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    private void deleteItem(Scanner scanner) {
        System.out.print("Enter the ID of the item to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        MenuItem item = findItemById(id);

        if (item != null) {
            menuItems.remove(item);
            System.out.println("Item deleted successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    private MenuItem findItemById(int id) {
        for (MenuItem item : menuItems) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
