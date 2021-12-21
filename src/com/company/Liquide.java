package com.company;

import javafx.util.Pair;

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

        if(caseNord.isValide) {
            ++nbVertical;
        }
        if(caseSud.isValide) {
            ++nbVertical;
        }
        if(caseOuest.isValide) {
            ++nbHorizontal;
        }
        if(caseEst.isValide) {
            ++nbHorizontal;
        }

        if /*((caseEst.isValide ==false && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==true) ||
            (caseEst.isValide ==true && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==true))*/
        (nbVertical >= 1 && nbHorizontal >= 1){
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



    public void UpdateMagnetisme_NSWE(Bloc bloc, Case currentCase, Case[][] matrice_bloc)
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
                if((currentCase.x - 1 != derniereCase.x || currentCase.y != derniereCase.y))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x - n][currentCase.y];
                        if (case_to_check.isValide && !case_to_check.isChecked) {
                            magnetisme_NSWE.put('N', magnetisme_NSWE.get('N') + case_to_check.getPM());
                            matrice_bloc[currentCase.x - n][currentCase.y].isValide = true;
                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1) {
                                Cote nord = Cote.getInstanceNord('N');
                                magnetisme_NSWE.put('N', magnetisme_NSWE.get('N') + nord.getMagnétisme());
                            }
                            System.out.println("magnetisme nord : " + magnetisme_NSWE.get('N'));
                        }
                    } while(isStillValid);
                }
            }

            else if(i == 1) // SUD
            {
                if((currentCase.x + 1 != derniereCase.x || currentCase.y != derniereCase.y))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x + n][currentCase.y];
                        if (case_to_check.isValide && !case_to_check.isChecked) {
                            magnetisme_NSWE.put('S', magnetisme_NSWE.get('S') + case_to_check.getPM());
                            matrice_bloc[currentCase.x + n][currentCase.y].isValide = true;

                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1) {
                                Cote sud = Cote.getInstanceNord('S');
                                magnetisme_NSWE.put('S', magnetisme_NSWE.get('S') + sud.getMagnétisme());
                            }
                            System.out.println("magnetisme sud : " + magnetisme_NSWE.get('S'));
                        }
                    } while(isStillValid);
                }
            }

            else if(i == 2) // WEST
            {
                if((currentCase.x != derniereCase.x || currentCase.y - 1 != derniereCase.y))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x][currentCase.y - n];
                        if (case_to_check.isValide && !case_to_check.isChecked) {
                            magnetisme_NSWE.put('W', magnetisme_NSWE.get('W') + case_to_check.getPM());
                            matrice_bloc[currentCase.x][currentCase.y - n].isValide = true;

                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1) {
                                Cote west = Cote.getInstanceNord('W');
                                magnetisme_NSWE.put('W', magnetisme_NSWE.get('W') + west.getMagnétisme());
                            }
                            System.out.println("magnetisme west : " + magnetisme_NSWE.get('W'));
                        }
                    } while(isStillValid);
                }
            }

            else // EST
            {
                if((currentCase.x != derniereCase.x || currentCase.y + 1 != derniereCase.y))
                {
                    n = 1;
                    do
                    {
                        case_to_check = matrice_bloc[currentCase.x][currentCase.y + n];
                        if (case_to_check.isValide && !case_to_check.isChecked) {
                            magnetisme_NSWE.put('E', magnetisme_NSWE.get('E') + case_to_check.getPM());
                            matrice_bloc[currentCase.x][currentCase.y + n].isValide = true;
                            ++n;
                        }
                        else
                        {
                            isStillValid = false;
                            if(n != 1)
                            {
                            Cote est = Cote.getInstanceNord('E');
                            magnetisme_NSWE.put('E', magnetisme_NSWE.get('E') + est.getMagnétisme());}
                            System.out.println("magnetisme est : " + magnetisme_NSWE.get('E'));

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

    public Case FindStartCase(Case[][] matrice_bloc)
    {
        Bloc bloc = Bloc.getInstance();
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
        System.out.println("Case start : (" +  caseRes.getX() + ", " + caseRes.getY() + ").");

        return caseRes;
    }

    public Case FindEndCase(Case[][] matrice_bloc)
    {
        Bloc bloc = Bloc.getInstance();
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
        System.out.println("Case end : (" +  caseRes.getX() + ", " + caseRes.getY() + ").");

        return caseRes;
    }

    public Case FindNextCaseCorridor(Case caseCurrent, Case[][] matrice_bloc)
    {
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

        casePotentielle = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() - 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            return casePotentielle;
        }

        caseCurrent.isChecked = true;

        return casePotentielle;
    }

    public void FillTabMagnetismeProche_NSWE(Map<Character, Integer> tabMagnetismeProche_NSWE, Case caseCurrent, Case[][] matrice_bloc)
    {
        Cote nord = Cote.getInstanceNord(4);
        Cote sud = Cote.getInstanceSud(-1);
        Cote west = Cote.getInstanceWest(3);
        Cote est = Cote.getInstanceEst(-2);
        Bloc bloc = Bloc.getInstance();
        Case casePotentielle = new Case(0, false);

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY()];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            //tabMagnetismeProche_NSWE.put('S', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.put('S', -20);
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() -1][caseCurrent.getY()];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            //tabMagnetismeProche_NSWE.put('N', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.put('N', -20);
        }

        casePotentielle = matrice_bloc[caseCurrent.getX()][caseCurrent.getY() + 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            //tabMagnetismeProche_NSWE.put('E', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.put('E', -20);
        }

        casePotentielle = matrice_bloc[caseCurrent.getX() + 1][caseCurrent.getY() - 1];
        if(casePotentielle.isValide
                && !casePotentielle.isChecked)
        {
            //tabMagnetismeProche_NSWE.put('W', casePotentielle.getPM());
        }
        else
        {
            tabMagnetismeProche_NSWE.put('W', -20);
        }
        AfficherValeurTabMagnetismeProche_NSWE(tabMagnetismeProche_NSWE);
    }

    public void AfficherValeurTabMagnetismeProche_NSWE(Map<Character, Integer> tabMagnetismeProche_NSWE)
    {
        for(int i = 0; i < 4; i++)
        {
            if(i == 3) {
                System.out.println(tabMagnetismeProche_NSWE.get('E'));
            }
            else if(i == 2) {
                System.out.println(tabMagnetismeProche_NSWE.get('W'));
            }
            else if(i == 1) {
                System.out.println(tabMagnetismeProche_NSWE.get('S'));
            }
            else {
                System.out.println(tabMagnetismeProche_NSWE.get('N'));
            }
        }
    }

    public void FillPileChoix(Stack<Pair> pile, Map<Character, Integer> tabMagnetismeProche_NSWE)
    {
        for(int i = 0; i <= 3; i++)
        {
            if(i == 3) {
                if(tabMagnetismeProche_NSWE.get('E') == -20)
                    continue;
                pile.push(new Pair('E', tabMagnetismeProche_NSWE.get('E')));
            }
            else if(i == 2) {
                if(tabMagnetismeProche_NSWE.get('W') == -20)
                    continue;
                pile.push(new Pair('W', tabMagnetismeProche_NSWE.get('W')));
            }
            else if(i == 1) {
                if(tabMagnetismeProche_NSWE.get('S') == -20)
                    continue;
                pile.push(new Pair('S', tabMagnetismeProche_NSWE.get('S')));
            }
            else {
                if(tabMagnetismeProche_NSWE.get('N') == -20)
                    continue;
                pile.push(new Pair('N', tabMagnetismeProche_NSWE.get('N')));
            }
        }
    }

    public Case DepileEtChoisit(Stack<Pair> pile, Case caseCurrent, Case[][] matrice_bloc)
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

    public void AvancerLiquide()
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
            caseCurrent.isChecked = true;
            CheckEmbranchement(caseCurrent, matrice_bloc);
            casesVues.add(caseCurrent);
            if(caseCurrent.isEmbramchement)
            {
                ToString(caseCurrent);
                FillTabMagnetismeProche_NSWE(magnetisme_NSWE, caseCurrent, matrice_bloc);
                UpdateMagnetisme_NSWE(bloc, caseCurrent, matrice_bloc);
                TriTabMagnetisme_NSWE();
                FillPileChoix(pileChoix, magnetisme_NSWE);
                caseCurrent = DepileEtChoisit(pileChoix, caseCurrent, matrice_bloc);

            }
            else
            {
                ToString(caseCurrent);
                caseCurrent = FindNextCaseCorridor(caseCurrent, matrice_bloc);
                caseCurrent.isChecked = true;
            }
        }
    }

    public void ToString(Case caseCurrent)
    {
        System.out.println("Case actuelle : (" +  caseCurrent.getX() + ", " + caseCurrent.getY() + ").");
    }

}
