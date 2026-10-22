package dev.mdinehin.battleship;

public class Cell
{
    private final int x;
    private final int y;
    private CellState cellState;

    public Cell(int x, int y, CellState cellState)
    {
        this.x = x;
        this.y = y;
        this.cellState = cellState;
    }

    public char stateToChar()
    {
        return switch (this.cellState) {
            case EMPTY -> ' ';
            case HIT -> 'O';
            case MISS -> 'X';
            case SUNK -> 'U';
        };
    }
}
