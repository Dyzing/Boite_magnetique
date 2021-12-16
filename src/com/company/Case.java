package com.company;

public class Case {

    int statut; //1 = depart || 2 = arrivée || 3 = case normale || 4 = case embranchement || 5 = case vide
    int PM;
    boolean isChecked;

    public Case(int statut, boolean isChecked) {
        this.statut = statut;
        this.isChecked = isChecked;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public int getPM() {

        if (statut == 1 || statut == 2 || statut == 5){
            PM = 0;
        }
        else if (statut == 3) {
            PM = 1;
        }
        else if (statut == 4) {
            PM = 3;
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
}
