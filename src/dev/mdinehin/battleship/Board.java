package dev.mdinehin.battleship;

public class Board
{
    public static final int BOARD_SIZE = 10;

    public static final int SHIP_TYPE_COUNT = 4;

    private Cell[][] boardGrid = new Cell[BOARD_SIZE][BOARD_SIZE];

    private int shipCount;

    private int shipCountsByDeck[] = new int[SHIP_TYPE_COUNT];

    public Board()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardGrid[row][col] = new Cell(col, row, CellState.EMPTY);
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
                char cellValue = boardGrid[row][col].stateToChar();
                System.out.print(cellValue);
            }

            System.out.print('\n');
        }
    }

    public void populate()
    {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                boardGrid[row][col].setState(getRandomCellState());
            }
        }
    }

    public void placeShip(int row, int col, Ship ship) throws IllegalPlacementException
    {
        int count = ship.getDeckCount();

        if (shipCountsByDeck[count - 1] == getMaxShipTypeCount(count)) {
            String errorMessage = String.format("The maximum number of ships of this type has been reached [Deck count: %d]", count);
            throw new IllegalPlacementException(errorMessage);
        }

        Cell[] shipCells = new Cell[count];

        int rowToSet = row, colToSet = col;

        for (int i = 0; i < count; i++) {
            if (ship.getOrientation() == Orientation.HORIZONTAL) {
                colToSet = col + i;
            } else {
                rowToSet = row + i;
            }

            if (canPlaceShip(rowToSet, colToSet)) {
                throw new IllegalPlacementException("Cannot place ship outside the board boundaries");
            }

            shipCells[i] = boardGrid[rowToSet][colToSet];
        }

        for (int i = 0; i < count; i++) {
            ship.assignCell(shipCells[i], i);
        }

        shipCount++;
        shipCountsByDeck[count - 1]++;
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
        return boardGrid[row][col];
    }

    private boolean canPlaceShip(int row, int col)
    {
        return row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE;
    }
}
