package TP3_BAL_src;

public class BAL {
    String Boite;
    Boolean avaible;

    public BAL(String Boite, Boolean paravaible) {
        this.Boite = Boite;
        this.avaible = paravaible;
    }

    public synchronized Boolean write(String Lettre) throws InterruptedException {
        // Si la boîte n'est pas disponible (pleine), le producteur attend
        while (!avaible) {
            wait();
        }
        Boite = Lettre;
        avaible = false;
        notifyAll();  // Notifier les autres threads (notamment le consommateur)
        return true;
    }

    public synchronized String read() throws InterruptedException {
        // Si la boîte est vide, le consommateur attend
        while (avaible) {
            wait();
        }
        avaible = true;
        String lettre = Boite;
        notifyAll();  // Notifier les autres threads (notamment le producteur)
        return lettre;
    }
}
