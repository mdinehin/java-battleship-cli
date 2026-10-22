package dev.mdinehin.battleship;

public class Main
{
    public static void main(String[] args)
    {
        char[][] board = new char[10][10];
        populateBoard(board);

        printColumnHeader();
        printBoard(board);
    }

    private static void populateBoard(char[][] board)
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = getRandomShip();
            }
        }
    }

    static void printColumnHeader()
    {
        System.out.print("  ");

        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i));
        }

        System.out.print('\n');
    }

    static void printBoard(char [][] board)
    {
        for (int i = 0; i < 10; i++) {
            int rowLabel = i + 1;

            if (rowLabel < 10) {
                System.out.print(' ');
            }

            System.out.print(rowLabel);

            for (int j = 0; j < 10; j++) {
                char shipValue = board[i][j];
                System.out.print(shipValue);
            }

            System.out.print('\n');
        }
    }

    private static char getRandomShip()
    {
        if (Math.random() < 0.2) {
            return 'O';
        }

        return ' ';
    }
}
