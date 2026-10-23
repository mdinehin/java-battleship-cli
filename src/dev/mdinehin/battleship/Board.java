package dev.mdinehin.battleship;

import java.util.Random;

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
                boardGrid[row][col] = new Cell(row, col, CellState.EMPTY);
            }
        }
    }

    public void print()
    {
        printColumnHeaders();

        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print((char) ('A' + row));

            for (int col = 0; col < BOARD_SIZE; col++) {
                char cellValue = boardGrid[row][col].stateToChar();
                System.out.print(cellValue);
            }

            System.out.print('\n');
        }
    }

    public void populate()
    {
        Random random = new Random();

        for (int deckCount = 1; deckCount <= SHIP_TYPE_COUNT; deckCount++) {
            for (int shipCount = 0; shipCount < getMaxShipTypeCount(deckCount); shipCount++) {
                boolean tryAgain;

                do {
                    int row = random.nextInt(BOARD_SIZE);
                    int col = random.nextInt(BOARD_SIZE);
                    Orientation orientation = random.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;

                    Ship ship = getShip(deckCount, orientation);

                    try {
                        placeShip(ship, row, col);
                        tryAgain = false;
                    } catch (IllegalPlacementException e) {
                        tryAgain = true;
                    }
                } while (tryAgain);
            }
        }
    }

    private Ship getShip(int deckCount, Orientation orientation)
    {
        return switch (deckCount) {
            case 1 -> new Submarine();
            case 2 -> new Destroyer(orientation);
            case 3 -> new Cruiser(orientation);
            case 4 -> new Battleship(orientation);
            default -> throw new IllegalArgumentException("Unexpected deck count value: " + deckCount);
        };
    }

    public void placeShip(Ship ship, int row, int col) throws IllegalPlacementException
    {
        int count = ship.getDeckCount();

        if (shipCountsByDeck[count - 1] == getMaxShipTypeCount(count)) {
            String errorMessage = String.format(
                "The maximum number of ships of this type has been reached [deck count: %d]", count
            );
            throw new IllegalPlacementException(errorMessage);
        }

        Cell[] shipCells = new Cell[count];

        int rowToSet = row, colToSet = col;

        for (int i = 0; i < count; i++) {
            if (ship.getOrientation() == Orientation.VERTICAL) {
                rowToSet = row + i;
            } else {
                colToSet = col + i;
            }

            if (isCellOutsideBoundaries(rowToSet, colToSet)) {
                throw new IllegalPlacementException("Cannot place ship outside the board boundaries");
            }

            shipCells[i] = boardGrid[rowToSet][colToSet];

            if (isCellOccupiedOrHasAdjacentShip(shipCells[i])) {
                throw new IllegalPlacementException(
                    "Ship placement overlaps existing ships or violates the no-touching rule"
                );
            }
        }

        for (int i = 0; i < count; i++) {
            ship.assignCell(shipCells[i], i);
        }

        shipCount++;

        shipCountsByDeck[count - 1]++;
    }

    private boolean isCellOccupiedOrHasAdjacentShip(Cell shipCell)
    {
        for (int row = shipCell.getRow() - 1; row <= shipCell.getRow() + 1; row++) {
            for (int col = shipCell.getCol() - 1; col <= shipCell.getCol() + 1; col++) {
                if (isCellOutsideBoundaries(row, col)) {
                    continue;
                }

                if (boardGrid[row][col].getState() != CellState.EMPTY) {
                    return true;
                }
            }
        }

        return false;
    }

    private int getMaxShipTypeCount(int deckCount)
    {
        return SHIP_TYPE_COUNT - deckCount + 1;
    }

    private static void printColumnHeaders()
    {
        System.out.print(" ");

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i);
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

    private boolean isCellOutsideBoundaries(int row, int col)
    {
        return row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE;
    }

    public void fireAt(int row, int col) throws Exception
    {
        Cell cell = getCell(row, col);

        if (
            cell.getState() == CellState.MISS
            || cell.getState() == CellState.HIT
            || cell.getState() == CellState.SUNK
        ) {
            throw new IllegalMoveException("Cell has already been fired upon");
        }

        if (cell.getState() == CellState.EMPTY) {
            cell.setState(CellState.MISS);
            System.out.println("Miss!");
        } else if (cell.getState() == CellState.SHIP) {
            cell.setState(CellState.HIT);
            cell.getShip().registerHit();

            if (cell.getShip().isSunk()) {
                shipCount--;
                System.out.println("You sunk a ship!");
            } else {
                System.out.println("Hit!");
            }
        }
    }
}
