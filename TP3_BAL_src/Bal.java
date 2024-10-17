package TP3_BAL_src;

// Classe représentant la Boîte aux Lettres (BAL)
class BAL {
    private String lettre = null;  // Lettre stockée dans la BAL

    public synchronized void deposer(String lettre) throws InterruptedException {
        // Attendre que la BAL soit vide
        while (this.lettre != null) {
            wait();
        }
        this.lettre = lettre;
        System.out.println("Lettre déposée : " + lettre);
        notifyAll();  // Réveille le consommateur
    }

    public synchronized String retirer() throws InterruptedException {
        // Attendre que la BAL ait une lettre
        while (this.lettre == null) {
            wait();
        }
        String lettreRetiree = this.lettre;
        this.lettre = null;
        System.out.println("Lettre retirée : " + lettreRetiree);
        notifyAll();  // Réveille le producteur
        return lettreRetiree;
    }
}