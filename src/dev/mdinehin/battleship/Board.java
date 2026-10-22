package dev.mdinehin.battleship;

public class Board
{
    public static final int BOARD_SIZE = 10;
    private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];

    public Board()
    {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                this.cells[y][x] = new Cell(x, y, CellState.EMPTY);
            }
        }
    }

    public void print()
    {
        printColumnHeaders();

        for (int y = 0; y < BOARD_SIZE; y++) {
            int rowLabel = y + 1;

            if (rowLabel < BOARD_SIZE) {
                System.out.print(' ');
            }

            System.out.print(rowLabel);

            for (int x = 0; x < BOARD_SIZE; x++) {
                char cellValue = this.cells[y][x].stateToChar();
                System.out.print(cellValue);
            }

            System.out.print('\n');
        }
    }

    public void populate()
    {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                this.cells[y][x].setState(getRandomCellState());
            }
        }
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
