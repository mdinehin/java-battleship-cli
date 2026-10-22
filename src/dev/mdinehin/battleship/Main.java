package dev.mdinehin.battleship;

public class Main
{
    public static void main(String[] args)
    {
        printColumnHeader();
    }

    static void printColumnHeader()
    {
        System.out.print("  ");

        for (int i = 0; i < 10; i++) {
            System.out.print((char) ('A' + i));
        }

        System.out.print('\n');
    }
}
