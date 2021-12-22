package com.company;

import javafx.util.Pair;

import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

public class Liquide {

    private List<Case> casesVues;
    private Map<Character, Integer> magnetisme_NSWE;

    public Liquide()
    {
        casesVues = new ArrayList<>();
        magnetisme_NSWE = new HashMap<Character, Integer>();
    }

    public List<Case> getCasesVues() {
        return casesVues;
    }

    public void setCasesVues(List<Case> casesVides) {
        this.casesVues = casesVides;
    }

    private void CheckEmbranchement(Case currentCase, Case[][] matrice_bloc){
        Bloc bloc = Bloc.getInstance();
        Case caseEst = matrice_bloc[currentCase.getX()][currentCase.getY() + 1];
        Case caseOuest= matrice_bloc[currentCase.getX()][currentCase.getY() - 1];
        Case caseSud = matrice_bloc[currentCase.getX() + 1][currentCase.getY()];
        Case caseNord = matrice_bloc[currentCase.getX() - 1][currentCase.getY()];
        Case derniereCase = casesVues.get( casesVues.size() -1);

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

        if /*((caseEst.isValide ==false && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==true) ||
            (caseEst.isValide ==true && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==true))*/
        (nbVertical >= 1 && nbHorizontal >= 1){
            System.out.println("");
            System.out.println("EMBRANCHEMENT !!");
            System.out.println("Embranchement aux coordonnées ("+ currentCase.getX() +" , " +currentCase.getY()+ ")");
            System.out.println("");

            currentCase.setEmbramchement(true);
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

        return ((caseEst.isValide() ==true && caseNord.isValide() == false && caseSud.isValide() ==false && caseOuest.isValide() ==false) ||
                (caseEst.isValide() ==false && caseNord.isValide() == true && caseSud.isValide() ==false && caseOuest.isValide() ==false) ||
                (caseEst.isValide() ==false && caseNord.isValide() == false && caseSud.isValide() ==true && caseOuest.isValide() ==false) ||
                (caseEst.isValide() ==false && caseNord.isValide() == false && caseSud.isValide() ==false && caseOuest.isValide() ==true));

    }

    private void Init_Magnetisme_NSWE()
    {
        magnetisme_NSWE.put('N', 0);
        magnetisme_NSWE.put('S', 0);
        magnetisme_NSWE.put('W', 0);
        magnetisme_NSWE.put('E', 0);
    }

    private void Reset_Magnetisme_NSWE()
    {
        for (Map.Entry mapentry : magnetisme_NSWE.entrySet())
        {
            mapentry.setValue(0);
        }
    }



    private void UpdateMagnetisme_NSWE(Bloc bloc, Case currentCase, Case[][] matrice_bloc)
    {
        Case derniereCase = casesVues.get( casesVues.size() -1);
        Case case_to_check;
        Reset_Magnetisme_NSWE();
        boolean isStillValid;

        for(int i = 0; i <= 3; i++)
        {
            isStillValid = true;
            int n;

            if(i == 0) // NORD
            {
                if((currentCase.getX() - 1 != derniereCase.getX() || currentCase.getY() != derniereCase.getY()))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.getX() - n][currentCase.getY()];
                        if (case_to_check.isValide() && !case_to_check.isChecked()) {
                            magnetisme_NSWE.put('N', magnetisme_NSWE.get('N') + case_to_check.getPM());
                            matrice_bloc[currentCase.getX() - n][currentCase.getY()].setValide(true);
                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1) {
                                Cote nord = Cote.getInstanceNord('N');
                                magnetisme_NSWE.put('N', magnetisme_NSWE.get('N') + nord.getMagnétisme());
                            }

                            else
                            {
                                magnetisme_NSWE.put('N', -20);
                            }
                            System.out.println("");
                            System.out.println("magnetisme nord : " + magnetisme_NSWE.get('N'));
                        }
                    } while(isStillValid);
                }
            }

            else if(i == 1) // SUD
            {
                if((currentCase.getX() + 1 != derniereCase.getX() || currentCase.getY() != derniereCase.getY()))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.getX() + n][currentCase.getY()];
                        if (case_to_check.isValide() && !case_to_check.isChecked()) {
                            magnetisme_NSWE.put('S', magnetisme_NSWE.get('S') + case_to_check.getPM());
                            matrice_bloc[currentCase.getX() + n][currentCase.getY()].setValide(true);

                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1) {
                                Cote sud = Cote.getInstanceSud('S');
                                magnetisme_NSWE.put('S', magnetisme_NSWE.get('S') + sud.getMagnétisme());
                            }

                            else
                            {
                                magnetisme_NSWE.put('S', -20);
                            }
                            System.out.println("magnetisme sud : " + magnetisme_NSWE.get('S'));
                        }
                    } while(isStillValid);
                }
            }

            else if(i == 2) // WEST
            {
                if((currentCase.getX() != derniereCase.getX() || currentCase.getY() - 1 != derniereCase.getY()))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.getX()][currentCase.getY() - n];
                        if (case_to_check.isValide() && !case_to_check.isChecked()) {
                            magnetisme_NSWE.put('W', magnetisme_NSWE.get('W') + case_to_check.getPM());
                            matrice_bloc[currentCase.getX()][currentCase.getY() - n].setValide(true);

                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1) {
                                Cote west = Cote.getInstanceWest('W');
                                magnetisme_NSWE.put('W', magnetisme_NSWE.get('W') + west.getMagnétisme());
                            }
                            else
                            {
                                magnetisme_NSWE.put('W', -20);
                            }
                            System.out.println("magnetisme west : " + magnetisme_NSWE.get('W'));
                        }
                    } while(isStillValid);
                }
            }

            else // EST
            {
                if((currentCase.getX() != derniereCase.getX() || currentCase.getY() + 1 != derniereCase.getY()))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.getX()][currentCase.getY() + n];
                        if (case_to_check.isValide() && !case_to_check.isChecked()) {
                            magnetisme_NSWE.put('E', magnetisme_NSWE.get('E') + case_to_check.getPM());
                            matrice_bloc[currentCase.getX()][currentCase.getY() + n].setValide(true);
                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1)
                            {
                                Cote est = Cote.getInstanceEst('E');
                                magnetisme_NSWE.put('E', magnetisme_NSWE.get('E') + est.getMagnétisme());
                            }

                            else
                            {
                                magnetisme_NSWE.put('E', -20);
                            }
                            System.out.println("magnetisme est : " + magnetisme_NSWE.get('E'));
                            System.out.println("");

                        }
                    } while(isStillValid);
                }
            }
        }
    }

    private Map<Character, Integer> TriTabMagnetisme_NSWE()
    {
        return magnetisme_NSWE.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e2,
                        HashMap::new
                ));
    }

    private Case FindStartCase(Case[][] matrice_bloc)
    {
        Bloc bloc = Bloc.getInstance();
        int matrice_length, matrice_height;
        matrice_height = bloc.get_m();
        matrice_length = bloc.get_n();
        Case caseRes = new Case(0, 0);

        for (int i = 0; i < matrice_height; i++)
        {
            for (int j = 0; j < matrice_length; j++)
            {
                if(matrice_bloc[i][j].getStatut() == 1)
                {
                    caseRes = matrice_bloc[i][j];
                    break;
                }
            }
        }
        System.out.println("Case start : (" +  caseRes.getX() + ", " + caseRes.getY() + ").");

        return caseRes;
    }

    private Case FindEndCase(Case[][] matrice_bloc)
    {
        Bloc bloc = Bloc.getInstance();
        int matrice_length, matrice_height;
        matrice_height = bloc.get_m();
        matrice_length = bloc.get_n();
        Case caseRes = new Case(0, 0);

        for (int i = 0; i < matrice_height; i++)
        {
            for (int j = 0; j < matrice_length; j++)
            {
                if(matrice_bloc[i][j].getStatut() == 2)
                {
                    caseRes = matrice_bloc[i][j];
                    break;
                }
            }
        }
        System.out.println("Case end : (" +  caseRes.getX() + ", " + caseRes.getY() + ").");
        System.out.println("");
        System.out.println("Magnétisme à -20 ==> chemin interdit\n");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("");



        return caseRes;
    }

    private Case FindNextCaseCorridor(Case caseCurrent, Case[][] matrice_bloc)
    {
        Case casePotentielle = new Case(0, 0);

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY()];
        if(casePotentielle.isValide()
                && !casePotentielle.isChecked())
        {
            return casePotentielle;
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() -1][caseCurrent.getY()];
        if(casePotentielle.isValide()
                && !casePotentielle.isChecked())
        {
            return casePotentielle;
        }

        casePotentielle = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() + 1];
        if(casePotentielle.isValide()
                && !casePotentielle.isChecked())
        {
            return casePotentielle;
        }

        casePotentielle = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() - 1];
        if(casePotentielle.isValide()
                && !casePotentielle.isChecked())
        {
            return casePotentielle;
        }

        caseCurrent.setChecked(true);

        return casePotentielle;
    }

    private void FillPileChoix(Stack<Pair> pile, Map<Character, Integer> tabMagnetismeProche_NSWE)
    {
        Iterator iterator = tabMagnetismeProche_NSWE.keySet().iterator();

        while(iterator.hasNext())
        {
            Object key   = iterator.next();
            Object value = tabMagnetismeProche_NSWE.get(key);

            if((int)value == -20)
            {
                continue;
            }
            else
            {
                pile.push(new Pair((Character)key, (int)value));
            }
        }
    }

    private Case DepileEtChoisit(Stack<Pair> pile, Case caseCurrent, Case[][] matrice_bloc)
    {
        Bloc bloc = Bloc.getInstance();
        Case caseChoisie = new Case(0, 0);

        Pair p = pile.pop();
        if((Character)p.getKey() == 'N')
        {
            caseChoisie = matrice_bloc[caseCurrent.getX() - 1][caseCurrent.getY()];
        }
        else if((Character)p.getKey() == 'S')
        {
            caseChoisie = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY()];
        }
        else if((Character)p.getKey() == 'W')
        {
            caseChoisie = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() - 1];
        }
        else if((Character)p.getKey() == 'E')
        {
            caseChoisie = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() + 1];
        }

        return caseChoisie;

    }

    void AvancerLiquide()
    {
        Stack<Pair> pileChoix = new Stack();
        Bloc bloc = Bloc.getInstance();
        Case[][] matrice_bloc = bloc.getMatrice();
        Case caseStart = FindStartCase(matrice_bloc);
        Case caseEnd = FindEndCase(matrice_bloc);
        Case caseCurrent = caseStart;
        Init_Magnetisme_NSWE();

        casesVues.add(caseStart);

        while(caseCurrent != caseEnd)
        {
            caseCurrent.setChecked(true);
            CheckEmbranchement(caseCurrent, matrice_bloc);
            casesVues.add(caseCurrent);
            if(caseCurrent.isEmbramchement())
            {
                ToString(caseCurrent);
                //FillTabMagnetismeProche_NSWE(magnetisme_NSWE, caseCurrent, matrice_bloc);
                UpdateMagnetisme_NSWE(bloc, caseCurrent, matrice_bloc);
                TriTabMagnetisme_NSWE();
                FillPileChoix(pileChoix, magnetisme_NSWE);
                caseCurrent = DepileEtChoisit(pileChoix, caseCurrent, matrice_bloc);

            }
            else
            {
                ToString(caseCurrent);
                caseCurrent = FindNextCaseCorridor(caseCurrent, matrice_bloc);
                caseCurrent.setChecked(true);
            }
        }
        if(caseCurrent == caseEnd)
        {
            System.out.println("\n-------------------------------------------------------------------------------------");
            System.out.println("\nBravo !! Le Liquide sombre à su rejoindre la case de fin. Félicitation !\n");
            System.out.println("-------------------------------------------------------------------------------------\n");
        }
    }

    private void ToString(Case caseCurrent)
    {
        System.out.println("Case actuelle : (" +  caseCurrent.getX() + ", " + caseCurrent.getY() + ").");
    }

}
