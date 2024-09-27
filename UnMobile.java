import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable {
	int saLargeur, saHauteur, sonDebDessin;
	final int sonPas = 10, sonTemps = 50, sonCote = 40;
	private boolean enMouvement = true; // Variable pour contrôler la pause/reprise
	private final Object verrou = new Object(); // Verrou pour la synchronisation

	UnMobile(int telleLargeur, int telleHauteur) {
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
	}

	public void run() {
		while (true) {
			for (sonDebDessin = 0; sonDebDessin < saLargeur - sonPas; sonDebDessin += sonPas) {
				synchronized (verrou) {
					while (!enMouvement) { // Si en pause, attendre
						try {
							verrou.wait();
						} catch (InterruptedException telleExcp) {
							telleExcp.printStackTrace();
						}
					}
				}
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			for (sonDebDessin = saLargeur; sonDebDessin > 0; sonDebDessin -= sonPas) {
				synchronized (verrou) {
					while (!enMouvement) { // Si en pause, attendre
						try {
							verrou.wait();
						} catch (InterruptedException telleExcp) {
							telleExcp.printStackTrace();
						}
					}
				}
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
		}
	}

	public void paintComponent(Graphics telCG) {
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur / 2, sonCote, sonCote);
	}

	// Méthode pour mettre en pause le mobile
	public void pause() {
		synchronized (verrou) {
			enMouvement = false;
		}
	}

	// Méthode pour reprendre le mobile
	public void resume() {
		synchronized (verrou) {
			enMouvement = true;
			verrou.notify(); // Réveille le thread
		}
	}

	public boolean isPaused() {
		return !enMouvement;
	}
}
