package com.example.lawre.week3test;

public class Room
{
    public final boolean isInfected;
    public boolean visited = false;
    Room(boolean infected)
    {
        isInfected = infected;
    }

    public static boolean isOutbreak(Room[][] floor)
    {
        int connectedCells = 0;
        int rows = floor.length;
        int columns = floor[0].length;
        for(int i = 0; i<rows;i++)
        {
            for(int j = 0; j < columns; j++)
            {
               if(!floor[i][j].hasBeenChecked() && floor[i][j].checkInfection())
               {
                   connectedCells += 1;
                   int horizontal = floor[i][j].checkHorizontal(floor[i],j);
                   if(horizontal > 0 )
                   {
                       connectedCells += horizontal;
                   }
                   Room[] column = new Room[floor.length];
                   for(int k = 0; k < column.length;k++)
                   {
                       column[k] = floor[k][j];
                   }
                   int vertical = floor[i][j].checkVertical(column,i);
                   if(horizontal > 0 )
                   {
                       connectedCells += vertical;
                   }
               }
               else
               {
                   if(connectedCells >= 5)
                       return true;
                   else
                       connectedCells = 0;
               }
            }
        }
        return false;
    }

    public boolean checkInfection()
    {
        visited = true;
        return isInfected;
    }

    public boolean hasBeenChecked()
    {
        return visited;
    }

    public int checkHorizontal(Room[] row, int start)
    {
        int connect = 0;
        for(int i = start; i < row.length; i++)
        {
            if(!row[i].hasBeenChecked() && row[i].checkInfection())
            {
                connect++;
            }
            else
            {
                return connect;
            }
        }
        return connect;
    }

    public int checkVertical(Room[] column, int start)
    {
        int connect = 0;
        for(int i = start; i < column.length; i++)
        {
            if(!column[i].hasBeenChecked() && column[i].checkInfection())
            {
                connect++;
            }
            else
            {
                return connect;
            }
        }
        return connect;
    }
}
