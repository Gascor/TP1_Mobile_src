import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class UneFenetre extends JFrame {
    UnMobile tache;
    private final int LARG = 1920, HAUT = 1030;
    private Thread supportTache;

    public UneFenetre() {
        // Création du conteneur principal
        Container leConteneur = getContentPane();
        leConteneur.setLayout(new BorderLayout());

        // Création d'un panneau pour contenir les boutons
        JPanel panneauBoutons = new JPanel();
        panneauBoutons.setLayout(new GridLayout(2, 1)); // Pour empiler les boutons verticalement
        JButton sonBouton1 = new JButton("Start/Stop");
        JButton sonBouton2 = new JButton("Bouton 2");

        panneauBoutons.add(sonBouton1);
        panneauBoutons.add(sonBouton2);

        // Orientation du panneau de contrôle sur la gauche
        leConteneur.add(panneauBoutons, BorderLayout.WEST);

        // Zone de vision
        tache = new UnMobile(LARG, HAUT);
        supportTache = new Thread(tache);
        leConteneur.add(tache, BorderLayout.CENTER);

        sonBouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tache.isPaused()) {
                    tache.resume(); // Reprendre
                    sonBouton1.setText("Pause");
                } else {
                    tache.pause(); // Pause
                    sonBouton1.setText("Resume");
                }
            }
        });

        // Configuration de la fenêtre
        this.setVisible(true);
        this.setSize(LARG, HAUT);
        supportTache.start();
    }

    // Méthode main pour exécuter le programme
    public static void main(String[] args) {
        new UneFenetre();
    }
}
