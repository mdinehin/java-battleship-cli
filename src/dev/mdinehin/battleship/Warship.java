package dev.mdinehin.battleship;

public abstract class Warship implements Ship
{
    private Orientation orientation;

    private int hitCount;

    private Cell[] occupiedCells;

    public Warship()
    {
        occupiedCells = new Cell[getDeckCount()];
    }

    @Override
    public void assignCell(Cell cell, int deckNumber)
    {
        cell.setShip(this);
        cell.setState(CellState.SHIP);
        occupiedCells[deckNumber] = cell;
    }

    @Override
    public boolean isSunk()
    {
        return hitCount == getDeckCount();
    }

    @Override
    public void registerHit()
    {
        hitCount++;

        if (isSunk()) {
            for (int i = 0; i < occupiedCells.length; i++) {
                occupiedCells[i].setState(CellState.SUNK);
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
