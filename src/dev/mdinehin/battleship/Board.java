package dev.mdinehin.battleship;

public class Board
{
    public static final int BOARD_SIZE = 10;
    private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.cells[row][col] = new Cell(col, row, CellState.EMPTY);
            }
        }
    }

    public void print()
    {
        printColumnHeaders();

        for (int row = 0; row < BOARD_SIZE; row++) {
            int rowLabel = row + 1;

            if (rowLabel < BOARD_SIZE) {
                System.out.print(' ');
            }

            System.out.print(rowLabel);

            for (int col = 0; col < BOARD_SIZE; col++) {
                char cellValue = this.cells[row][col].stateToChar();
                System.out.print(cellValue);
            }

            System.out.print('\n');
        }
    }

    public void populate()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                this.cells[row][col].setState(getRandomCellState());
            }
        }
    }

    public boolean addShip(int row, int col, Submarine submarine)
    {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }

        return true;
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

    private static void printColumnHeaders()
    {
        System.out.print("  ");

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) ('A' + i));
        }

        System.out.print('\n');
    }
}
