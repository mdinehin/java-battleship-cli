package dev.mdinehin.battleship;

import java.util.Scanner;

public class Main
{
    static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        Board board = new Board();

        board.populate();

        while (board.getShipCount() > 0) {
            board.print();

            System.out.print("Enter target coordinates: ");
            String inputValue = scanner.nextLine().trim();

            Integer row = null, col = null;

            if (!inputValue.isEmpty()) {
                if (inputValue.length() > 2) {
                    System.out.println("Error: Please provide valid coordinates");
                    continue;
                }

                // Convert the first character to lowercase (handles both 'A' and 'a')
                char firstChar = Character.toLowerCase(inputValue.charAt(0));

                // Convert 'a' -> 0, 'b' -> 1, 'c' -> 2, etc.
                row = firstChar - 'a';

                // Converts '0' -> 0, '1' -> 1, '5' -> 5, etc.
                col = inputValue.charAt(1) - '0';
            }

            try {
                board.fireAt(row, col);
            } catch (IllegalMoveException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("Error: Please provide some coordinates");
            } catch (Exception e) {
                System.out.println("Error: Please provide valid coordinates");
            }
        }

        System.out.print("Game over");
    }
}
