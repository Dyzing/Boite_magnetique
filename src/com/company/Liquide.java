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


        if ((caseEst.isValide ==true && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == true && caseSud.isValide ==false && caseOuest.isValide ==true) ||
                (caseEst.isValide ==true && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==false) ||
                (caseEst.isValide ==false && caseNord.isValide == false && caseSud.isValide ==true && caseOuest.isValide ==true)){
            System.out.println("EMBRANCHEMENT !!");
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
