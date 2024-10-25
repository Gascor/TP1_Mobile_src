package TP3_APIConcurrent_Boulangerie_src;

public  class Boulanger  implements Runnable {

    Boulangerie boulangerie;

    public Boulanger(Boulangerie boulangerie) {
        this.boulangerie = boulangerie;
    }

    public  void run() {

        try {
            while (true) {

                // toutes les secondes un boulanger produit un pain
                Thread.sleep(1000) ;
                boolean added = boulangerie.depose(new Pain()) ;

                if (added) {
                    System.out.println(STR."[\{Thread.currentThread().getName()}][\{boulangerie.getStock()}] je livre.") ;
                }  else {
                    System.out.println(STR."[\{Thread.currentThread().getName()}][\{boulangerie.getStock()}] la boulangerie est pleine.") ;
                }
            }

        }  catch (InterruptedException e) {
            System.out.println("[" + Thread.currentThread().getName() +  "] je m'arrête") ;
        }
    }
}