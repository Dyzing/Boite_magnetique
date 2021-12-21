package com.company;

import java.util.ArrayList;

public class Bloc {

    private static Bloc bloc_instance = null;
    Case[][] matrice;
    int _m;
    int _n; //m la hauteur de la matrice; n la largeur de la matrice

    ArrayList<Cote> list_cote;
    //ArrayList magnetisme_NSWE;


    Bloc(int m, int n)
    {
        matrice = new Case[150][160]; // MODULE 1
        _m = m;
        _n = n;
        setAllCaseCoord();
        matrice = Creation_Module1();

        //matrice = new Case[m][n];
    }

    Bloc()
    {
        matrice = new Case[150][160]; // MODULE 1
        _m = 150;
        _n = 160;
        setAllCaseCoord();
        matrice = Creation_Module1();

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
            add(matrice[12][15]);
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

            add(matrice[13][9]);
            add(matrice[12][9]);
            add(matrice[11][9]);
            add(matrice[10][9]);
            add(matrice[9][9]);
            add(matrice[8][9]);
            add(matrice[7][9]);
            //add(matrice[6][9]);
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
            matrice[c.x][c.y].setPM(1);
        }

        matrice[8][1].statut = 1;
        matrice[8][1].isChecked = true;

        matrice[3][15].statut = 2;

        Cote nord = Cote.getInstanceNord(4);
        Cote sud = Cote.getInstanceSud(-1);
        Cote west = Cote.getInstanceWest(3);
        Cote est = Cote.getInstanceEst(-2);

        System.out.println("cote nord : " + nord.getMagnétisme());
        System.out.println("cote sud : " + sud.getMagnétisme());
        System.out.println("cote west : " + west.getMagnétisme());
        System.out.println("cote est : " + est.getMagnétisme());

        return  matrice;
    }


    public void setAllCaseCoord()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                matrice[i][j] = new Case(0, false);
                matrice[i][j].x = i;
                matrice[i][j].y = j;
                matrice[i][j].statut = 0;
            }
        }
    }

    public Case[][] getMatrice() {
        return matrice;
    }

    public ArrayList<Cote> getList_cote() {
        return list_cote;
    }

    public int get_m() {
        return _m;
    }

    public int get_n() {
        return _n;
    }

    public static Bloc getInstance()
    {
        if (bloc_instance == null)
        {
            bloc_instance = new Bloc();
        }
        return bloc_instance;
    }

}
