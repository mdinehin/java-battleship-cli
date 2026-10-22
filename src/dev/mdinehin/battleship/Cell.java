package dev.mdinehin.battleship;

public class Cell
{
    private final int x;
    private final int y;
    private CellState cellState;
    private Ship ship;

    public Cell(int x, int y, CellState cellState)
    {
        this.x = x;
        this.y = y;
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
