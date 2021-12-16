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
        Case caseOuest= new Case(currentCase.getX()+1, currentCase.getY());
        Case caseSud = new Case (currentCase.getX(), currentCase.getY()-1);
        Case caseNord = new Case (currentCase.getX(), currentCase.getY()+1);
        Case derniereCase = casesVues.get( casesVues.size() -1);

        //if ((caseEst.getY() && ) || caseArriere)


    }

    public void UpdateMagnetisme_NSWE(Case currentCase)
    {
        Case derniereCase = casesVues.get( casesVues.size() -1);

        for(int i = 0; i < 3; i++)
        {
            if(((currentCase.x + 1 != derniereCase.x || currentCase.x - 1 != derniereCase.x)) && (currentCase.y + 1 != derniereCase.y || (currentCase.y - 1 != derniereCase.y)))
            {

            }
        }
    }

}
