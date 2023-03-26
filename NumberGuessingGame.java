import javax.swing.JOptionPane;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        int secretNumber = random.nextInt(100) + 1;
        int numGuesses = 0;
        int guess;

        JOptionPane.showMessageDialog(null, "Welcome to the number guessing game!\nI'm thinking of a number between 1 and 100.\nTry to guess it!");

        do {
            String input = JOptionPane.showInputDialog(null, "Enter your guess:");
            guess = Integer.parseInt(input);
            numGuesses++;

            if (guess < secretNumber) {
                JOptionPane.showMessageDialog(null, "Too low!");
            } else if (guess > secretNumber) {
                JOptionPane.showMessageDialog(null, "Too high!");
            } else {
                JOptionPane.showMessageDialog(null, "You got it in " + numGuesses + " guesses!");
            }
        } while (guess != secretNumber);
    }
}

/*
    In this code, `the JOptionPane.showMessageDialog()` method is used to display a message dialog box with the welcome message. The `JOptionPane.showInputDialog()` method is used to display an input dialog box for the user to enter their guess. The input is then converted from a `String` to an `int` using `Integer.parseInt()`. Finally, the `JOptionPane.showMessageDialog()` method is used again to display a message dialog box with the feedback on the guess.
*/