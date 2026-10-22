package dev.mdinehin.battleship;

public abstract class Warship implements Ship
{
    private Orientation orientation;
    private int hitCount;
    private Cell[] occupiedCells;

    public Warship()
    {
        this.occupiedCells = new Cell[this.getDeckCount()];
    }

    @Override
    public void assignCell(Cell cell, int deckNumber)
    {
        cell.setShip(this);
        cell.setState(CellState.SHIP);
        this.occupiedCells[deckNumber] = cell;
    }

    @Override
    public boolean isSunk()
    {
        return this.hitCount == this.getDeckCount();
    }

    @Override
    public void registerHit()
    {
        this.hitCount++;

        if (this.isSunk()) {
            for (int i = 0; i < this.occupiedCells.length; i++) {
                this.occupiedCells[i].setState(CellState.SUNK);
            }
        }
    }
}

class Submarine extends Warship
{
    @Override
    public int getDeckCount()
    {
        return 1;
    }
}

class Destroyer extends Warship
{
    @Override
    public int getDeckCount()
    {
        return 2;
    }
}

class Cruiser extends Warship
{
    @Override
    public int getDeckCount()
    {
        return 3;
    }
}

class Battleship extends Warship
{
    @Override
    public int getDeckCount()
    {
        return 4;
    }
}
