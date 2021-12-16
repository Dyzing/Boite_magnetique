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
        

        if ((caseEst.isValide ==true && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==true) ||
                (caseEst.isValide ==true && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==true)){
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

}
