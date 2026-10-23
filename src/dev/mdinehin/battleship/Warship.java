package dev.mdinehin.battleship;

public abstract class Warship implements Ship
{
    private Orientation orientation;

    private int hitCount;

    private Cell[] occupiedCells;

    public Warship(Orientation orientation)
    {
        this.orientation = orientation;
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

        if (!isSunk()) {
            return;
        }

        for (int i = 0; i < occupiedCells.length; i++) {
            occupiedCells[i].setState(CellState.SUNK);
        }
    }

    public Orientation getOrientation()
    {
        return orientation;
    }
}

class Submarine extends Warship
{
    public Submarine(Orientation orientation)
    {
        super(orientation);
    }

    public Submarine()
    {
        this(Orientation.HORIZONTAL);
    }

    @Override
    public int getDeckCount()
    {
        return 1;
    }
}

class Destroyer extends Warship
{
    public Destroyer(Orientation orientation)
    {
        super(orientation);
    }

    @Override
    public int getDeckCount()
    {
        return 2;
    }
}

class Cruiser extends Warship
{
    public Cruiser(Orientation orientation)
    {
        super(orientation);
    }

    @Override
    public int getDeckCount()
    {
        return 3;
    }
}

class Battleship extends Warship
{
    public Battleship(Orientation orientation)
    {
        super(orientation);
    }

    @Override
    public int getDeckCount()
    {
        return 4;
    }
}
