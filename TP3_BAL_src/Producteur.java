package TP3_BAL_src;

public class Producteur extends Thread {
    String lettre;
    BAL chbal;

    public Producteur(String parlettre, BAL parbal) {
        lettre = parlettre;
        this.chbal = parbal;
    }

    public void run() {
        try {
            for (int i = 0; i < lettre.length(); i++) {
                chbal.write(String.valueOf(lettre.charAt(i)));
                System.out.println("Lettre déposée : " + lettre.charAt(i));
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
