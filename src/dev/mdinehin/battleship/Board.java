package dev.mdinehin.battleship;

public class Board
{
    public static final int BOARD_SIZE = 10;

    public static final int SHIP_TYPE_COUNT = 4;

    private Cell[][] cells = new Cell[BOARD_SIZE][BOARD_SIZE];

    private int shipCount;

    private int shipCountsByDeck[] = new int[SHIP_TYPE_COUNT];

    public Board()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cells[row][col] = new Cell(col, row, CellState.EMPTY);
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
                char cellValue = cells[row][col].stateToChar();
                System.out.print(cellValue);
            }

            System.out.print('\n');
        }
    }

    public void populate()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                cells[row][col].setState(getRandomCellState());
            }
        }
    }

    public void placeShip(int row, int col, Ship ship) throws IllegalPlacementException
    {
        if (shipCountsByDeck[ship.getDeckCount() - 1] == getMaxShipTypeCount(ship.getDeckCount())) {
            String errorMessage = String.format("The maximum number of ships of this type has been reached [Deck count: %d]", ship.getDeckCount());
            throw new IllegalPlacementException(errorMessage);
        }

        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            throw new IllegalPlacementException("Cannot place ship outside the board boundaries");
        }

        ship.assignCell(cells[row][col], 0);

        shipCount++;
        shipCountsByDeck[ship.getDeckCount() - 1]++;
    }

    private int getMaxShipTypeCount(int deckCount)
    {
        return SHIP_TYPE_COUNT - deckCount + 1;
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

    public int getShipCount()
    {
        return shipCount;
    }

    public Cell getCell(int row, int col)
    {
        return cells[row][col];
    }
}
