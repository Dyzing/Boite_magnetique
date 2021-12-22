package com.company;

import java.util.Scanner;

public class Main {

    public static String mode = "";

    public static void main(String[] args) {
        System.out.println("Veuillez entrer votre choix de module : taper 1 ou 2");
        Scanner sc = new Scanner(System.in);
        mode = sc.nextLine();
        Liquide liquide = new Liquide();
        liquide.AvancerLiquide();
    }
}
