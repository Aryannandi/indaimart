// package com.indiamart;

// class GradientPanel extends JPanel {
//     @Override
//     protected void paintComponent(Graphics g) {
//         super.paintComponent(g);
//         Graphics2D g2d = (Graphics2D) g;
//         int width = getWidth();
//         int height = getHeight();

//         // Create a gradient from top to bottom
//         Color color1 = new Color(255, 255, 255); // Start color (white)
//         Color color2 = new Color(173, 216, 230); // End color (light blue)
//         GradientPaint gp = new GradientPaint(0, 0, color1, 0, height, color2);
//         g2d.setPaint(gp);
//         g2d.fillRect(0, 0, width, height);
//     }
// }