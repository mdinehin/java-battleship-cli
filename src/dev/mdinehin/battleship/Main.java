package dev.mdinehin.battleship;

public class Main
{
    enum CellState
    {
        EMPTY, HIT, MISS, SUNK
    }

    public static void main(String[] args)
    {
        CellState[][] board = new CellState[10][10];
        populateBoard(board);

        printColumnHeader();
        printBoard(board);
    }

    private static void populateBoard(CellState[][] board)
    {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = getRandomCellState();
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

    static void printBoard(CellState[][] board)
    {
        for (int i = 0; i < 10; i++) {
            int rowLabel = i + 1;

            if (rowLabel < 10) {
                System.out.print(' ');
            }

            System.out.print(rowLabel);

            for (int j = 0; j < 10; j++) {
                char shipValue = cellStateToChar(board[i][j]);
                System.out.print(shipValue);
            }

            System.out.print('\n');
        }
    }

    private static char cellStateToChar(CellState cellState)
    {
        return switch (cellState) {
            case EMPTY -> ' ';
            case HIT -> 'O';
            case MISS -> 'X';
            case SUNK -> 'U';
        };
    }

    private static CellState getRandomCellState()
    {
        double random = Math.random();

        if (random < 0.2) {
            return CellState.HIT;
        }

        if (random > 0.8) {
            return CellState.EMPTY;
        }

        return CellState.MISS;
    }
}
