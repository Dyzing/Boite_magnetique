package com.company;

import java.util.ArrayList;

public class Bloc {

    Case[][] matrice;
    int _m, _n; //m la hauteur de la matrice; n la largeur de la matrice

    ArrayList<Cote> list_cote;
    //ArrayList magnetisme_NSWE;

    Bloc(int m, int n)
    {
        matrice = new Case[16][17]; // MODULE 1
        _m = m;
        _n = n;
        //matrice = new Case[m][n];
    }

    /*
    public Case[][] bloc1()
    {

        return  matrice;
    }
    */

    public void setAllCaseCoord()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                matrice[i][j].x = i;
                matrice[i][j].y = j;
            }
        }
    }

    public Case[][] getMatrice() {
        return matrice;
    }

    public ArrayList<Cote> getList_cote() {
        return list_cote;
    }

}
