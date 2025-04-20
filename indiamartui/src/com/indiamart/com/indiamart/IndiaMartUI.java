package com.indiamart;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class IndiaMartUI {

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ArrayList<Product> cartItems;
    private DefaultListModel<String> cartModel;
    private JLabel cartTotalLabel;
    private double cartTotal = 0.0;

    // List of all products
    private final Product[] products = {
            new Product("Laptop", 70000, "laptop.jpg"),
            new Product("Smartphone", 42000, "smartphones.jpg"),
            new Product("Headphones", 6900, "headphone.jpg"),
            new Product("Smart Watch", 5499, "smartwatch.jpg"),
            new Product("Camera", 34999, "camera.jpg"),
            new Product("Shoes", 7999, "shoe.jpg"),
            new Product("Tablet", 19999, "tablet.jpg"),
            new Product("Gaming Console", 39999, "console.jpg"),
            new Product("Bluetooth Speaker", 4999, "speaker.jpg"),
            new Product("Fitness Tracker", 2999, "fitnesstracker.jpg"),
            new Product("Backpack", 2999, "backpack.jpg"),
            new Product("Wireless Mouse", 999, "mouse.jpg"),
            new Product("Keyboard", 1599, "keyboard.jpg"),
            new Product("Monitor", 15999, "monitor.jpg"),
            new Product("Printer", 8999, "printer.jpg"),
            new Product("Desk Lamp", 1299, "desklamp.jpg"),
            new Product("Coffee Maker", 6999, "coffeemaker.jpg"),
            new Product("Blender", 4999, "blender.jpg"),
            new Product("Water Bottle", 599, "waterbottle.jpg"),
            new Product("Electric Kettle", 1999, "kettle.jpg"),
            new Product("Blender", 4999, "blender.jpg"),
            new Product("Water Bottle", 599, "waterbottle.jpg"),
            new Product("Electric Kettle", 1999, "kettle.jpg"),
    };

    public IndiaMartUI() {
        // Initialize the main frame
        frame = new JFrame("IndiaMart - Online Shopping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1500, 900);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);

        cartItems = new ArrayList<>();
        cartModel = new DefaultListModel<>();

        // CardLayout for different views
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(240, 200, 240));

        // Add panels to CardLayout
        mainPanel.add(createLoginPanel(), "Login");
        mainPanel.add(createProductPanel(), "Products");
        mainPanel.add(createCartPanel(), "Cart");
        mainPanel.add(createCheckoutPanel(), "Checkout");

        frame.add(mainPanel);
        frame.setVisible(true);
        cardLayout.show(mainPanel, "Login");
    }

    /**
     * Create the Login Panel
     */
    private JPanel createLoginPanel() {
        GradientPanel loginPanel = new GradientPanel(); 
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
    
        // Add the logo to the left side
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 4; // Span multiple rows
        gbc.anchor = GridBagConstraints.WEST;
        try {
            // Load image from resources
            JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("logo.jpg"))); // Add your logo image path here
            logoLabel.setPreferredSize(new Dimension(300, 300));
            logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            loginPanel.add(logoLabel, gbc);
        } catch (Exception e) {
            System.out.println("Error loading logo image.");
        }
    
        // Add the login form to the right side
        gbc.gridheight = 1; // Reset row span
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
    
        JLabel loginLabel = new JLabel("Login to IndiaMart");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginPanel.add(loginLabel, gbc);
    
        // Email Field
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loginPanel.add(emailLabel, gbc);
    
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JTextField emailField = new JTextField(20);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        loginPanel.add(emailField, gbc);
    
        // Password Field
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        loginPanel.add(passwordLabel, gbc);
    
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        loginPanel.add(passwordField, gbc);
    
        // Login Button
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        // GradientPanel loginButton = new GradientPanel(); 
        loginButton.setBackground(new Color(99, 100, 237));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> cardLayout.show(mainPanel, "Products"));
        loginPanel.add(loginButton, gbc);
    
        return loginPanel;
    }
    

      /**
     * Create the Product Panel
     */
    private JPanel createProductPanel() {
        JPanel productPanel = new JPanel(new BorderLayout(10, 10));
        productPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        productPanel.setBackground(Color.WHITE);
    
        JLabel productsLabel = new JLabel("Products");
        productsLabel.setFont(new Font("Arial", Font.BOLD, 24));
        productPanel.add(productsLabel, BorderLayout.NORTH);

        // Create the product grid panel
        JPanel productGridPanel = new JPanel(new GridLayout(0, 4, 10, 10));
        productGridPanel.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(productGridPanel);
        productPanel.add(scrollPane, BorderLayout.CENTER);
    
        // Display initial products
        displayProducts(productGridPanel, products);
    
        // Create the search panel with text field and button
        JPanel searchPanel = new JPanel(new FlowLayout());
        JTextField searchField = new JTextField(50);
        searchField.setPreferredSize(new Dimension(400, 30));
        searchPanel.add(searchField);
    
        // KeyListener for real-time search
        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                filterProducts(searchField.getText(), productGridPanel); // Pass the grid panel directly
            }
        });

        // Bottom panel for buttons
        JPanel buttomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton viewCartButton = createButton("View Cart");
        viewCartButton.addActionListener(e -> cardLayout.show(mainPanel, "Cart"));
        buttomPanel.add(viewCartButton);

        JButton backButton = createButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));
        buttomPanel.add(backButton);

        productPanel.add(buttomPanel, BorderLayout.SOUTH);
    
        JButton searchButton = createButton("Search");
        searchButton.addActionListener(e -> filterProducts(searchField.getText(), productGridPanel));
        searchPanel.add(searchButton);
    
        productPanel.add(searchPanel, BorderLayout.NORTH); // Add search panel to product panel
    
        return productPanel;
    }
    
    /**
     * Filters products based on search input and updates the product grid panel.
     */
    private void filterProducts(String searchText, JPanel productGridPanel) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.name.toLowerCase().contains(searchText.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        displayProducts(productGridPanel, filteredProducts.toArray(new Product[0]));
    }
    
    /**
     * Displays the products in the grid panel.
     */
    private void displayProducts(JPanel productGridPanel, Product[] products) {
        productGridPanel.removeAll(); // Clear existing products
        for (Product product : products) {
            JPanel productCard = createProductCard(product);
            productGridPanel.add(productCard);
        }
        productGridPanel.revalidate(); // Refresh the panel
        productGridPanel.repaint();
    }
    
    /**
     * Create a product card with image, name, price, and "Add to Cart" button.
     */
    private JPanel createProductCard(Product product) {
        JPanel productCard = new JPanel();
        productCard.setLayout(new BoxLayout(productCard, BoxLayout.Y_AXIS));
        productCard.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        productCard.setBackground(Color.WHITE);
        productCard.setPreferredSize(new Dimension(200, 450));

        // Load the product image
        try {
            JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource(product.imagePath)));
            imageLabel.setPreferredSize(new Dimension(100, 100));
            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            productCard.add(imageLabel);
        } catch (Exception e) {
            System.out.println("Error loading image: " + product.imagePath);
        }

        // Product name and price labels
        JLabel nameLabel = new JLabel(product.name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("₹" + String.format("%.2f", product.price));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // "Add to Cart" button
        JButton addToCartButton = createButton("Add to Cart");
        addToCartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addToCartButton.addActionListener(e -> addToCart(product));

        productCard.add(Box.createVerticalStrut(10));
        productCard.add(nameLabel);
        productCard.add(priceLabel);
        productCard.add(Box.createVerticalStrut(10));
        productCard.add(addToCartButton);

        return productCard;
    }

    /**
     * Adds a product to the shopping cart and updates the cart total.
     */
    private void addToCart(Product product) {
        cartItems.add(product);
        cartModel.addElement(product.name + " - ₹" + String.format("%.2f", product.price));
        cartTotal += product.price;
        cartTotalLabel.setText("Total: ₹" + String.format("%.2f", cartTotal));
    }

    /**
     * Create the Cart Panel
     */
    private JPanel createCartPanel() {
        JPanel cartPanel = new JPanel(new BorderLayout(10, 10));
        cartPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        cartPanel.setBackground(Color.WHITE);

        JLabel cartLabel = new JLabel("Shopping Cart");
        cartLabel.setFont(new Font("Arial", Font.BOLD, 24));
        cartPanel.add(cartLabel, BorderLayout.NORTH);

        JList<String> cartList = new JList<>(cartModel);
        cartPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);

        cartTotalLabel = new JLabel("Total: ₹0.00");
        cartTotalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        cartPanel.add(cartTotalLabel, BorderLayout.SOUTH);

        // Checkout button
        JButton checkoutButton = createButton("Checkout");
        checkoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Checkout");
            updateCheckoutTotal(); // Ensure the total is up-to-date on the checkout panel
        });
        cartPanel.add(checkoutButton, BorderLayout.EAST);

        return cartPanel;
    }

    /**
     * Update the total displayed on the checkout panel.
     */
    private void updateCheckoutTotal() {
        JLabel totalLabel = (JLabel) ((JPanel) mainPanel.getComponent(3)).getComponent(1);
        totalLabel.setText("Total: ₹" + String.format("%.2f", cartTotal));
    }

    /**
     * Create the Checkout Panel
     */
    private JPanel createCheckoutPanel() {
        JPanel checkoutPanel = new JPanel(new GridBagLayout());
        checkoutPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        checkoutPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel checkoutLabel = new JLabel("Checkout");
        checkoutLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        checkoutPanel.add(checkoutLabel, gbc);

        // Total label to be updated dynamically
        JLabel totalLabel = new JLabel("Total: ₹" + String.format("%.2f", cartTotal));
        totalLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridy++;
        checkoutPanel.add(totalLabel, gbc);

        // Confirm purchase button
        gbc.gridy++;
        JButton confirmButton = createButton("Confirm Purchase");
        confirmButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for your purchase!");
            resetCart(); // Clear cart after purchase
            cardLayout.show(mainPanel, "Products"); // Return to products
        });
        checkoutPanel.add(confirmButton, gbc);

        // Back button to return to cart
        gbc.gridy++;
        JButton backButton = createButton("Back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Cart"));
        checkoutPanel.add(backButton, gbc);

        return checkoutPanel;
    }

    /**
     * Resets the shopping cart and related data
     */
    private void resetCart() {
        cartItems.clear();
        cartModel.clear();
        cartTotal = 0.0;
        cartTotalLabel.setText("Total: ₹0.00");
    }

    /**
     * Creates a styled button for a modern appearance.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 123, 255)); // Bootstrap primary color
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 30));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(IndiaMartUI::new);
    }

    /**
     * Product class to hold product details.
     */
    static class Product {
        String name;
        double price;
        String imagePath;

        Product(String name, double price, String imagePath) {
            this.name = name;
            this.price = price;
            this.imagePath = imagePath;
        }
    }
    class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Constructor to load the background image
    public BackgroundPanel(String imagePath) {
        try {
            backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        } catch (Exception e) {
            System.out.println("Error loading background image: " + imagePath);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Create a gradient from top to bottom
        Color color1 = new Color(255, 255, 255); // Start color (white)
        Color color2 = new Color(170, 230, 230); // End color (light blue)
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
    }
}

}



