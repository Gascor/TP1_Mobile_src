import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame
{
    UnMobile tache;
    private final int LARG=1000, HAUT=550;

    public UneFenetre(int unNombre)
    {
        Container conteneur = getContentPane();
        conteneur.setLayout(new BoxLayout(conteneur,BoxLayout.Y_AXIS));
        CemaphoreGlobal cg = new CemaphoreGlobal(4);
        for(int e = 0;e < unNombre;e++){
            tache = new UnMobile(LARG, 10,cg);
            Thread supportTache = new Thread(tache);
            conteneur.add(tache);
            supportTache.start();

        }
        this.setVisible(true);
        this.setSize(LARG, HAUT);

    }
}
