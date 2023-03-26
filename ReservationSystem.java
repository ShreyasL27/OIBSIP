import java.util.HashMap;
import java.util.Scanner;

public class ReservationSystem {
    private static HashMap<String, String> users = new HashMap<String, String>();
    private static HashMap<String, String> reservations = new HashMap<String, String>();
    private static Scanner scanner = new Scanner(System.in);
    private static boolean loggedIn = false;
    private static String username = "";

    public static void main(String[] args) {
        users.put("user1", "password1");
        users.put("user2", "password2");
        reservations.put("user1", "Reservation for user1");
        reservations.put("user2", "Reservation for user2");

        while (true) {
            if (!loggedIn) {
                login();
            } else {
                System.out.println("\nEnter 1 to view reservation, 2 to make a reservation, 3 to cancel reservation, or 4 to logout:");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewReservation();
                        break;
                    case 2:
                        makeReservation();
                        break;
                    case 3:
                        cancelReservation();
                        break;
                    case 4:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        break;
                }
            }
        }
    }

    private static void login() {
        System.out.println("\nEnter username:");
        String inputUsername = scanner.next();
        System.out.println("Enter password:");
        String password = scanner.next();

        if (users.containsKey(inputUsername) && users.get(inputUsername).equals(password)) {
            loggedIn = true;
            username = inputUsername;
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed! Please try again.");
        }
    }

    private static void viewReservation() {
        String reservation = reservations.get(username);
        System.out.println("\n" + reservation);
    }

    private static void makeReservation() {
        System.out.println("\nEnter reservation details:");
        String reservationDetails = scanner.next();
        reservations.put(username, reservationDetails);
        System.out.println("Reservation made successfully!");
    }

    private static void cancelReservation() {
        reservations.remove(username);
        System.out.println("Reservation cancelled successfully!");
    }

    private static void logout() {
        loggedIn = false;
        username = "";
        System.out.println("Logout successful!");
    }
}

/*
    In this example code, the ReservationSystem class simulates an online reservation system. A HashMap is used to store the user accounts and passwords, and another HashMap is used to store the reservations for each user. The while loop is used to repeatedly prompt the user to either login or perform reservation-related actions if they are logged in. The login method prompts the user for their username and password and checks if they match an entry in the users map. If the login is successful, the loggedIn flag is set to true and the username variable is set to the input username. The user is then presented with a menu of options to view their reservation, make a reservation, cancel their reservation, or logout. The switch statement is used to select the appropriate method based on the user's choice. Each method performs the necessary actions to update the reservations map, and the viewReservation method displays the reservation for the current user. If the user chooses to logout, the loggedIn flag is set to false, and the username variable is reset to an empty string.
*/
