
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class NumberGuessingGameSwing extends JFrame implements ActionListener {

    private int numberToGuess;
    private int attempts;
    private JTextField guessField;
    private JButton guessButton;
    private JLabel messageLabel;

    public NumberGuessingGameSwing() {
        super("Number Guessing Game");

        Random rand = new Random();
        numberToGuess = rand.nextInt(100) + 1;
        attempts = 0;

        // UI Components
        JLabel title = new JLabel("Guess a number between 1 and 100");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        guessField = new JTextField();
        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);

        messageLabel = new JLabel("Enter your guess and click Guess");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Layout
        setLayout(new GridLayout(4, 1, 10, 10));
        add(title);
        add(guessField);
        add(guessButton);
        add(messageLabel);

        // Window setup
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = guessField.getText();

        try {
            int guess = Integer.parseInt(input);
            attempts++;

            if (guess < numberToGuess) {
                messageLabel.setText("Too low! Try again.");
            } 
            else if (guess > numberToGuess) {
                messageLabel.setText("Too high! Try again.");
            } 
            else {
                messageLabel.setText("Correct! You guessed it in " + attempts + " attempts!");

                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Play Again?",
                        "You Won!",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid number!");
        }
    }

    private void resetGame() {
        Random rand = new Random();
        numberToGuess = rand.nextInt(1000) + 1;
        attempts = 0;
        guessField.setText("");
        messageLabel.setText("New game started! Guess a number.");
    }

    public static void main(String[] args) {
        new NumberGuessingGameSwing();
    }
}
