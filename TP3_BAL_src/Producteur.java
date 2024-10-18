package TP3_BAL_src;

public class Producteur {
    private String lettre;
    private BAL chbal;

    public Producteur(String parlettre, BAL parbal) {
        this.lettre = parlettre;
        this.chbal = parbal;
    }

    public void deposerLettre() throws InterruptedException {
        for (int i = 0; i < lettre.length(); i++) {
            chbal.write(String.valueOf(lettre.charAt(i)));
            System.out.println("Lettre déposée : " + lettre.charAt(i));
            Thread.sleep(1000);
        }
    }
}
