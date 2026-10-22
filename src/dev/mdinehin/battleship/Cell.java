package dev.mdinehin.battleship;

public class Cell
{
    private final int col;
    private final int row;
    private CellState cellState;
    private Ship ship;

    public Cell(int col, int row, CellState cellState)
    {
        this.col = col;
        this.row = row;
        this.cellState = cellState;
    }

    public void setShip(Ship ship)
    {
        this.ship = ship;
    }

    public char stateToChar()
    {
        return switch (this.cellState) {
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
}
