package com.company;

public class Cote {

    private char cardinal; //NSWE
    private int magnétisme;

    private static Cote cote_instance_nord = null;
    private static Cote cote_instance_sud = null;
    private static Cote cote_instance_west = null;
    private static Cote cote_instance_est = null;

    Cote(char c)
    {
        switch (c)
        {
            case 'N':
            case 'S':
            case 'W':
            case 'E':
                cardinal = c;
                break;
            default:
                break;
        }
        magnétisme = (int)Math.floor(Math.random()*(10-(-10)+1)+(-10));
    }

    Cote(char c, int m)
    {
        switch (c)
        {
            case 'N':
            case 'S':
            case 'W':
            case 'E':
                cardinal = c;
                break;
            default:
                break;
        }
        magnétisme = m;
    }

    public char getCardinal() {
        return cardinal;
    }

    public int getMagnétisme() {
        return magnétisme;
    }

    public static Cote getInstanceNord_Random()
    {
        if (cote_instance_nord == null)
        {
            cote_instance_nord = new Cote('N');
        }
        return cote_instance_nord;
    }

    public static Cote getInstanceNord(int magnetisme)
    {
        if (cote_instance_nord == null)
        {
            cote_instance_nord = new Cote('N', magnetisme);
        }
        return cote_instance_nord;
    }

    public static Cote getInstanceSud_Random()
    {
        if (cote_instance_sud == null)
        {
            cote_instance_sud = new Cote('S');
        }
        return cote_instance_sud;
    }

    public static Cote getInstanceSud(int magnetisme)
    {
        if (cote_instance_sud == null)
        {
            cote_instance_sud = new Cote('S', magnetisme);
        }
        return cote_instance_sud;
    }

    public static Cote getInstanceWest_Random()
    {
        if (cote_instance_west == null)
        {
            cote_instance_west = new Cote('W');
        }
        return cote_instance_west;
    }

    public static Cote getInstanceWest(int magnetisme)
    {
        if (cote_instance_west == null)
        {
            cote_instance_west = new Cote('W', magnetisme);
        }
        return cote_instance_west;
    }

    public static Cote getInstanceEst_Random()
    {
        if (cote_instance_est == null)
        {
            cote_instance_est = new Cote('E');
        }
        return cote_instance_est;
    }

    public static Cote getInstanceEst(int magnetisme)
    {
        if (cote_instance_est == null)
        {
            cote_instance_est = new Cote('E', magnetisme);
        }
        return cote_instance_est;
    }
}
