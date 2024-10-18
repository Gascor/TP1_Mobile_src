package TP3_BAL_src;

public class Consommateur {
    private BAL chbal;

    public Consommateur(BAL parbal) {
        this.chbal = parbal;
    }

    public void retirerLettre() throws InterruptedException {
        while (true) {
            String lettre = chbal.read();
            System.out.println("Lettre retirée : " + lettre);
            Thread.sleep(1500);
        }
    }
}
