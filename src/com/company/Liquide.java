package com.company;

import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Liquide {

    List<Case> casesVues;
    Map<Character, Integer> magnetisme_NSWE;

    public Liquide()
    {
        casesVues = new ArrayList<>();
        magnetisme_NSWE = new HashMap<Character, Integer>();
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

    public void Init_Magnetisme_NSWE()
    {
        magnetisme_NSWE.put('N', 0);
        magnetisme_NSWE.put('S', 0);
        magnetisme_NSWE.put('W', 0);
        magnetisme_NSWE.put('E', 0);
    }

    public void Reset_Magnetisme_NSWE()
    {
        for (Map.Entry mapentry : magnetisme_NSWE.entrySet())
        {
            mapentry.setValue(0);
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
                            magnetisme_NSWE.replace('N', magnetisme_NSWE.get('N') + case_to_check.getPM());
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
                            magnetisme_NSWE.replace('S', magnetisme_NSWE.get('N') + case_to_check.getPM());
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
                            magnetisme_NSWE.replace('W', magnetisme_NSWE.get('N') + case_to_check.getPM());
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
                            magnetisme_NSWE.replace('E', magnetisme_NSWE.get('N') + case_to_check.getPM());
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

    public Map<Character, Integer> TriTabMagnetisme_NSWE()
    {
        return magnetisme_NSWE.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        HashMap::new
                ));
    }

    public Case FindStartCase()
    {
        Bloc bloc = Bloc.getInstance();
        Case[][] matrice_bloc = bloc.getMatrice();
        int matrice_length, matrice_height;
        matrice_height = bloc._m;
        matrice_length = bloc._n;
        Case caseRes = new Case(0, 0);

        for (int i = 0; i < matrice_height; i++)
        {
            for (int j = 0; j < matrice_length; j++)
            {
                if(matrice_bloc[i][j].statut == 1)
                {
                    caseRes = matrice_bloc[i][j];
                    break;
                }
            }
        }

        return caseRes;
    }

    public Case FindEndCase()
    {
        Bloc bloc = Bloc.getInstance();
        Case[][] matrice_bloc = bloc.getMatrice();
        int matrice_length, matrice_height;
        matrice_height = bloc._m;
        matrice_length = bloc._n;
        Case caseRes = new Case(0, 0);

        for (int i = 0; i < matrice_height; i++)
        {
            for (int j = 0; j < matrice_length; j++)
            {
                if(matrice_bloc[i][j].statut == 2)
                {
                    caseRes = matrice_bloc[i][j];
                    break;
                }
            }
        }

        return caseRes;
    }

    public Case FindNextCaseCorridor(Case caseCurrent)
    {
        Bloc bloc = Bloc.getInstance();
        Case[][] matrice_bloc = bloc.matrice;
        Case casePotentielle = new Case(0, 0);

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY()];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            return casePotentielle;
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() -1][caseCurrent.getY()];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            return casePotentielle;
        }

        casePotentielle = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() + 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            return casePotentielle;
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY() - 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            return casePotentielle;
        }

        caseCurrent.isChecked = true;

        return casePotentielle;
    }

    public void FillTabMagnetismeProche_NSWE(Map<Character, Integer> tabMagnetismeProche_NSWE, Case caseCurrent)
    {
        Bloc bloc = Bloc.getInstance();
        Case[][] matrice_bloc = bloc.matrice;
        Case casePotentielle = new Case(0, 0);

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY()];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            tabMagnetismeProche_NSWE.replace('S', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.replace('S', -20);
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() -1][caseCurrent.getY()];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            tabMagnetismeProche_NSWE.replace('N', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.replace('N', -20);
        }

        casePotentielle = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() + 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            tabMagnetismeProche_NSWE.replace('E', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.replace('E', -20);
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY() - 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            tabMagnetismeProche_NSWE.replace('W', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.replace('W', -20);
        }

    }

    public void FillPileChoix(Stack pile, Map<Character, Integer> tabMagnetismeProche_NSWE)
    {
        for(int i = 3; i >= 0; i--)
        {
            if(i == 3)
                pile.push(tabMagnetismeProche_NSWE.get('E'));
            else if(i == 2)
                pile.push(tabMagnetismeProche_NSWE.get('W'));
            else if(i == 1)
                pile.push(tabMagnetismeProche_NSWE.get('S'));
            else
                pile.push(tabMagnetismeProche_NSWE.get('N'));
        }
    }

    public void AvancerLiquide()
    {
        Stack pileChoix = new Stack();
        Bloc bloc = Bloc.getInstance();
        Case caseStart = FindStartCase();
        Case caseEnd = FindEndCase();
        Case caseCurrent = caseStart;

        while(caseCurrent != caseEnd)
        {
            CheckEmbranchement(caseCurrent);
            if(caseCurrent.isEmbramchement)
            {
                FillTabMagnetismeProche_NSWE(magnetisme_NSWE, caseCurrent);
                TriTabMagnetisme_NSWE();
                FillPileChoix(pileChoix, magnetisme_NSWE);
                caseCurrent.isChecked = true;
            }
            else
            {
                caseCurrent = FindNextCaseCorridor(caseCurrent);
                caseCurrent.isChecked = true;
            }
        }
    }

}
