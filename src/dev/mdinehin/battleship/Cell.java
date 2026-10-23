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
            case EMPTY -> ' ';
            case SHIP -> 'S';
            case HIT -> 'O';
            case MISS -> 'X';
            case SUNK -> 'U';
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
}
