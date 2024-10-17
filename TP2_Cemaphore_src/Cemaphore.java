package TP2_Cemaphore_src;

public abstract class Cemaphore {

    protected int valeur=0;

    protected Cemaphore(int valeurInitiale){
	valeur = valeurInitiale>0 ? valeurInitiale:0;
    }

    public synchronized void syncWait(){
	try {
	    while(valeur<=0){ // si val est inf ou égale à 0 , on attend.
		wait();
        }
	    valeur--;
	} catch(InterruptedException e){}
    }

    public synchronized void syncSignal(){
	if(++valeur > 0) notifyAll();
    }
}
