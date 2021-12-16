package com.company;

import java.util.ArrayList;

public class Bloc {

    Case[][] matrice;

    ArrayList<Cote> list_cote;
    //ArrayList magnetisme_NSWE;

    Bloc(int m, int n)
    {
        matrice = new Case[m][n];
    }


    public Case[][] getMatrice() {
        return matrice;
    }

    public ArrayList<Cote> getList_cote() {
        return list_cote;
    }

}
