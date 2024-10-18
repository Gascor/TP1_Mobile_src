package TP3_BAL_src;

public class Consommateur extends Thread  {
    BAL chbal;

    public Consommateur(BAL parbal) {
        this.chbal = parbal;
    }

    public void run() {
        try {
            while (true) {
                // Retire la lettre de la boîte et l'affiche
                String lettre = chbal.read();
                System.out.println("Lettre retirée : " + lettre);
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
