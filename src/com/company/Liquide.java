package com.company;

import java.util.ArrayList;
import java.util.List;

public class Liquide {

    List<Case> casesVues;
    int[] magnetisme_NSWE;

    public Liquide()
    {
        casesVues = new ArrayList<>();
        magnetisme_NSWE = new int[3];
    }

    public List<Case> getCasesVues() {
        return casesVues;
    }

    public void setCasesVuess(List<Case> casesVides) {
        this.casesVues = casesVides;
    }

    private void CheckEmbranchement(Case currentCase){

        Case caseEst = new Case(currentCase.getX() -1 , currentCase.getY());
        Case caseOuest= new Case(currentCase.getX()+ 1, currentCase.getY());
        Case caseSud = new Case (currentCase.getX(), currentCase.getY()-1);
        Case caseNord = new Case (currentCase.getX(), currentCase.getY()+1);
        Case derniereCase = casesVues.get( casesVues.size() -1);

        if ((caseEst.isValide ==false && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==true) ||
            (caseEst.isValide ==true && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==true)){
            System.out.println("EMBRANCHEMENT !!");
            System.out.println("Embranchement aux coordonnées ("+ currentCase.getX() +" , " +currentCase.getY()+ ")");
            currentCase.isEmbramchement = true;
        }
        else {
            return;
        }


    }
    private boolean isSame(Case case1, Case case2){

        return (case1.getX() == case2.getX() && case1.getY() == case2.getY());

    }

    private boolean isCulDeSac(Case currentCase) {

        Case caseEst = new Case(currentCase.getX() -1 , currentCase.getY());
        Case caseOuest= new Case(currentCase.getX()+1, currentCase.getY());
        Case caseSud = new Case (currentCase.getX(), currentCase.getY()-1);
        Case caseNord = new Case (currentCase.getX(), currentCase.getY()+1);

        return ((caseEst.isValide ==true && caseNord.isValide == false && caseSud.isValide ==false && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==false && caseOuest.isValide ==true));

    }




    public void Reset_Magnetisme_NSWE()
    {
        for (int i: magnetisme_NSWE
             ) {
                i = 0;
        }
    }

    public void UpdateMagnetisme_NSWE(Bloc bloc, Case currentCase)
    {
        Case derniereCase = casesVues.get( casesVues.size() -1);
        Case case_to_check;
        Case[][] matrice_bloc = bloc.getMatrice();
        Reset_Magnetisme_NSWE();
        boolean isStillValid;

        for(int i = 0; i < 3; i++)
        {
            isStillValid = true;
            
            if(i == 0) // NORD
            {
                if((currentCase.x - 1 != derniereCase.x || currentCase.y != derniereCase.y))
                {
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x - 1][currentCase.y];
                        if (case_to_check.isValide) {
                            magnetisme_NSWE[i] += case_to_check.getPM();
                        }
                        else
                        {
                            isStillValid = false;
                        }
                    } while(isStillValid);
                }
            }

            else if(i == 1) // SUD
            {
                if((currentCase.x + 1 != derniereCase.x || currentCase.y != derniereCase.y))
                {
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x + 1][currentCase.y];
                        if (case_to_check.isValide) {
                            magnetisme_NSWE[i] += case_to_check.getPM();
                        }
                        else
                        {
                            isStillValid = false;
                        }
                    } while(isStillValid);
                }
            }

            else if(i == 2) // WEST
            {
                if((currentCase.x != derniereCase.x || currentCase.y - 1 != derniereCase.y))
                {
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x][currentCase.y - 1];
                        if (case_to_check.isValide) {
                            magnetisme_NSWE[i] += case_to_check.getPM();
                        }
                        else
                        {
                            isStillValid = false;
                        }
                    } while(isStillValid);
                }
            }

            else // EST
            {
                if((currentCase.x != derniereCase.x || currentCase.y + 1 != derniereCase.y))
                {
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x][currentCase.y + 1];
                        if (case_to_check.isValide) {
                            magnetisme_NSWE[i] += case_to_check.getPM();
                        }
                        else
                        {
                            isStillValid = false;
                        }
                    } while(isStillValid);
                }
            }
        }
    }

}
