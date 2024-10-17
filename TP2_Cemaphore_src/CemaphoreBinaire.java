package TP2_Cemaphore_src;

public final class CemaphoreBinaire extends Cemaphore {
public CemaphoreBinaire(int valeurInitiale){
	// CemaphoreBinaire est une classe. Constructeur possédant une valeur initiale permettant de statuer l'Etat du Cemaphore.
	super((valeurInitiale != 0) ? 1:0);
	//System.out.print(valeurInitiale); // Debug
}
public final synchronized void syncSignal(){
	super.syncSignal();
	if (valeur>1) valeur = 1;
	//System.out.print(valeur); // Debug
}
}

