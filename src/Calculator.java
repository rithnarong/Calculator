import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField firstNumberField, secondNumberField, resultField;

    public Calculator() {
        setTitle("Calculator");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Creating text fields
        firstNumberField = new JTextField(15);
        secondNumberField = new JTextField(15);
        resultField = new JTextField(15);
        resultField.setEditable(false);

        // Labels
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("First Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(firstNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Second Number:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(secondNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Result:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(resultField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        String[] buttonLabels = {"+", "-", "*", "/", "%", "Clear"};
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setPreferredSize(new Dimension(80, 50));
            buttonPanel.add(button);
            button.addActionListener(new ButtonClickListener());
        }

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(buttonPanel, gbc);

        // Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            try {
                double num1 = Double.parseDouble(firstNumberField.getText());
                double num2 = Double.parseDouble(secondNumberField.getText());
                double result = 0;

                switch (command) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot divide by zero");
                            return;
                        }
                        break;
                    case "%":
                        result = num1 % num2;
                        break;
                    case "Clear":
                        firstNumberField.setText("");
                        secondNumberField.setText("");
                        resultField.setText("");
                        return;
                }
                resultField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}