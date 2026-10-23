package dev.mdinehin.battleship;

public class Cell
{
    private final int row;

    private final int col;

    private CellState cellState;

    private Ship ship;

    public Cell(int row, int col, CellState cellState)
    {
        this.row = row;
        this.col = col;
        this.cellState = cellState;
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

    public char stateToChar()
    {
        return switch (cellState) {
            case EMPTY, SHIP -> ' ';
            case MISS -> '!';
            case HIT -> 'O';
            case SUNK -> 'X';
        };
    }

    public void setState(CellState cellState)
    {
        this.cellState = cellState;
    }

    public CellState getState()
    {
        return cellState;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Ship getShip()
    {
        return ship;
    }
}
