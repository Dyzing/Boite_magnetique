package com.company;

import java.util.List;

public class Liquide {

    List<Case> casesVues;

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

}
