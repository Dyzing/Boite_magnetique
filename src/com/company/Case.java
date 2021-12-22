package com.company;

public class Case {

    private int statut; //1 = depart || 2 = arrivée || 0 = normale
    private int PM;
    private boolean isChecked = false;
    private boolean isEmbramchement = false;
    private boolean isValide = false;
    private int x, y;

    public Case(int statut, boolean isChecked) {
        this.statut = statut;
        this.isChecked = isChecked;
    }

    public Case(int x, int y) {
        this.x = x;
        this.y = y;

    }


    public boolean isValide() {
        return isValide;
    }

    public void setValide(boolean valide) {
        isValide = valide;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getPM() {

        if( isEmbramchement && isValide()) {
            PM = 3;
        }

        else if (!isEmbramchement && isValide()) {
            PM = 1;
        }
        else if (isValide() == false){
            PM = 0;
        }
        return PM;
    }

    public void setPM(int PM) {
        this.PM = PM;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public boolean isEmbramchement() {
        return isEmbramchement;
    }

    public void setEmbramchement(boolean embramchement) {
        isEmbramchement = embramchement;
    }
}
