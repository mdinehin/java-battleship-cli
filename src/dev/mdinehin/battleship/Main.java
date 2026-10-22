package dev.mdinehin.battleship;

public class Main
{
    static void main(String[] args)
    {
        Board board = new Board();

        board.populate();
        board.print();
    }
}
