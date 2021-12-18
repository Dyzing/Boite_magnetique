package com.company;

import java.util.ArrayList;

public class Bloc {

    Case[][] matrice;
    int _m, _n; //m la hauteur de la matrice; n la largeur de la matrice

    ArrayList<Cote> list_cote;
    //ArrayList magnetisme_NSWE;


    Bloc(int m, int n)
    {
        matrice = new Case[15][16]; // MODULE 1
        _m = m;
        _n = n;
        setAllCaseCoord();
        //matrice = new Case[m][n];
    }


    public Case[][] Creation_Module1()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                matrice[i][j].isValide = false;
            }
        }

        ArrayList<Case> list_case_valide = new ArrayList()
        {{
            add(matrice[8][1]);
            add(matrice[7][1]);
            add(matrice[6][1]);

            add(matrice[6][2]);
            add(matrice[6][3]);
            add(matrice[6][4]);
            add(matrice[6][5]);
            add(matrice[6][6]);
            add(matrice[6][7]);
            add(matrice[6][8]);
            add(matrice[6][9]);
            add(matrice[6][10]);
            add(matrice[6][11]);
            add(matrice[6][12]);
            add(matrice[6][13]);
            add(matrice[6][14]);
            add(matrice[6][15]);

            add(matrice[7][15]);
            add(matrice[8][15]);
            add(matrice[9][15]);
            add(matrice[10][15]);
            add(matrice[11][15]);
            add(matrice[13][15]);
            add(matrice[14][15]);

            add(matrice[10][15]);
            add(matrice[10][14]);
            add(matrice[10][13]);
            add(matrice[10][12]);

            add(matrice[14][15]);
            add(matrice[14][14]);
            add(matrice[14][13]);
            add(matrice[14][12]);
            add(matrice[14][11]);
            add(matrice[14][10]);
            add(matrice[14][9]);

            add(matrice[14][9]);
            add(matrice[13][9]);
            add(matrice[12][9]);
            add(matrice[11][9]);
            add(matrice[10][9]);
            add(matrice[9][9]);
            add(matrice[8][9]);
            add(matrice[7][9]);
            add(matrice[6][9]);
            add(matrice[5][9]);
            add(matrice[4][9]);
            add(matrice[3][9]);
            add(matrice[2][9]);
            add(matrice[1][9]);

            add(matrice[2][8]);
            add(matrice[2][7]);
            add(matrice[2][6]);

            add(matrice[1][10]);
            add(matrice[1][11]);
            add(matrice[1][12]);
            add(matrice[1][13]);
            add(matrice[1][14]);
            add(matrice[1][15]);

            add(matrice[2][12]);
            add(matrice[3][12]);

            add(matrice[2][15]);
            add(matrice[3][15]);

        }};

        for (Case c : list_case_valide)
        {
            matrice[c.x][c.y].isValide = true;
        }

        matrice[8][1].statut = 1;

        matrice[3][15].statut = 2;

        return  matrice;
    }


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
