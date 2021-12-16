package com.company;

public class Cote {

    char cardinal; //NSWE
    double magnétisme;

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
        magnétisme = Math.floor(Math.random()*(10-(-10)+1)+(-10));
    }

    Cote(char c, double m)
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

    public double getMagnétisme() {
        return magnétisme;
    }
}
