package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Bloc {

    private String mode = "";
    private static Bloc bloc_instance = null;
    private Case[][] matrice;
    private int _m;
    private int _n; //m la hauteur de la matrice; n la largeur de la matrice

    private ArrayList<Cote> list_cote;
    //ArrayList magnetisme_NSWE;


    Bloc(int m, int n)
    {
        matrice = new Case[150][160]; // MODULE 1
        _m = m;
        _n = n;
        setAllCaseCoord();

        System.out.println("Veuillez entrer votre choix de module : taper 1 ou 2");
        Scanner sc = new Scanner(System.in);
        mode = sc.nextLine();
        if(mode.equals("1"))
            matrice = Creation_Module1();
        else
            matrice = Creation_Module2();
        setRightPM();
        //matrice = new Case[m][n];
    }

    Bloc()
    {
        matrice = new Case[150][160]; // MODULE 1
        _m = 150;
        _n = 160;
        setAllCaseCoord();

        System.out.println("Veuillez entrer votre choix de module : taper 1 ou 2");
        Scanner sc = new Scanner(System.in);
        mode = sc.nextLine();
        if(mode.equals("1"))
            matrice = Creation_Module1();
        else
            matrice = Creation_Module2();
        setRightPM();
    }

    private void CheckEmbranchement(Case currentCase){
        Case caseEst = matrice[currentCase.getX()][currentCase.getY() + 1];
        Case caseOuest= matrice[currentCase.getX()][currentCase.getY() - 1];
        Case caseSud = matrice[currentCase.getX() + 1][currentCase.getY()];
        Case caseNord = matrice[currentCase.getX() - 1][currentCase.getY()];

        int nbHorizontal = 0;
        int nbVertical = 0;

        if(caseNord.isValide()) {
            ++nbVertical;
        }
        if(caseSud.isValide()) {
            ++nbVertical;
        }
        if(caseOuest.isValide()) {
            ++nbHorizontal;
        }
        if(caseEst.isValide()) {
            ++nbHorizontal;
        }

        if(nbVertical >= 1 && nbHorizontal >= 1){
            //System.out.println("Embranchement aux coordonnées ("+ currentCase.getX() +" , " +currentCase.getY()+ ")");
            currentCase.setEmbramchement(true);
        }
        else {
            return;
        }


    }

    public void setRightPM()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                if(matrice[i][j].isValide())
                {
                    CheckEmbranchement(matrice[i][j]);
                    if(matrice[i][j].isEmbramchement())
                    {
                        matrice[i][j].setPM(3);
                    }
                    else
                    {
                        matrice[i][j].setPM(1);
                    }
                }
            }
        }
    }

    private Case[][] Creation_Module1()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                matrice[i][j].setValide(false);
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
            matrice[c.getX()][c.getY()].setValide(true);
            matrice[c.getX()][c.getY()].setPM(1);
        }

        matrice[8][1].setStatut(1);
        matrice[8][1].setChecked(true);

        matrice[3][15].setStatut(2);

        Cote nord = Cote.getInstanceNord(4);
        Cote sud = Cote.getInstanceSud(-1);
        Cote west = Cote.getInstanceWest(3);
        Cote est = Cote.getInstanceEst(-2);

        System.out.println("");
        System.out.println("cote nord : " + nord.getMagnétisme());
        System.out.println("cote sud : " + sud.getMagnétisme());
        System.out.println("cote west : " + west.getMagnétisme());
        System.out.println("cote est : " + est.getMagnétisme());
        System.out.println("");

        return  matrice;
    }

    private Case[][] Creation_Module2()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                matrice[i][j].setValide(false);
            }
        }

        ArrayList<Case> list_case_valide = new ArrayList()
        {{
            add(matrice[8][2]);
            add(matrice[7][2]);
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
            add(matrice[14][8]);
            add(matrice[14][7]);
            add(matrice[14][6]);
            add(matrice[14][5]);
            add(matrice[14][4]);

            add(matrice[13][9]);
            add(matrice[12][9]);
            add(matrice[11][9]);
            add(matrice[10][9]);
            add(matrice[9][9]);
            add(matrice[8][9]);
            add(matrice[7][9]);

            add(matrice[5][9]);
            add(matrice[4][9]);
            add(matrice[3][9]);
            add(matrice[2][9]);

            add(matrice[2][8]);
            add(matrice[2][7]);
            add(matrice[2][6]);
            add(matrice[2][5]);
            add(matrice[2][4]);
            add(matrice[2][3]);


        }};

        for (Case c : list_case_valide)
        {
            matrice[c.getX()][c.getY()].setValide(true);
            matrice[c.getX()][c.getY()].setPM(1);
        }

        matrice[8][1].setStatut(1);
        matrice[8][1].setChecked(true);

        matrice[10][12].setStatut(2);


        System.out.println("Veuillez entrer 3 pour des valeurs de magnétisme aléatoires\n ou bien \nentrer 4 pour des valeurs de magnétisme de votre choix");
        Scanner sc = new Scanner(System.in);
        String valeur_choix_3_4 = sc.nextLine();
        Cote nord, sud, west, est;

        if(valeur_choix_3_4.equals("3"))
        {
            nord = Cote.getInstanceNord_Random();
            sud = Cote.getInstanceSud_Random();
            west = Cote.getInstanceWest_Random();
            est = Cote.getInstanceEst_Random();
        }
        else
        {
            System.out.println("Veuillez entrer une par une vos valeurs de magnétisme : ");
            Scanner sc4 = new Scanner(System.in);
            String valeur_magnetisme;

            valeur_magnetisme = sc4.nextLine();
            nord = Cote.getInstanceNord(Integer.parseInt(valeur_magnetisme));

            valeur_magnetisme = sc4.nextLine();
            sud = Cote.getInstanceSud(Integer.parseInt(valeur_magnetisme));

            valeur_magnetisme = sc4.nextLine();
            west = Cote.getInstanceWest(Integer.parseInt(valeur_magnetisme));

            valeur_magnetisme = sc4.nextLine();
            est = Cote.getInstanceEst(Integer.parseInt(valeur_magnetisme));
        }

        System.out.println("");
        System.out.println("cote nord : " + nord.getMagnétisme());
        System.out.println("cote sud : " + sud.getMagnétisme());
        System.out.println("cote west : " + west.getMagnétisme());
        System.out.println("cote est : " + est.getMagnétisme());
        System.out.println("");

        return  matrice;
    }

    private void setAllCaseCoord()
    {
        for (int i = 0; i < _m; i++)
        {
            for (int j = 0; j < _n; j++)
            {
                matrice[i][j] = new Case(0, false);
                matrice[i][j].setX(i);
                matrice[i][j].setY(j);
                matrice[i][j].setStatut(0);
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
