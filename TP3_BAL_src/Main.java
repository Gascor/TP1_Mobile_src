package TP3_BAL_src;

public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL("", true);  // La boîte est vide au début
        Producteur prod = new Producteur("ABCDE", bal);  // Producteur de lettres
        Consommateur cons = new Consommateur(bal);  // Consommateur

        prod.start();
        cons.start();
    }
}