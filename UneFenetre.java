import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile tache;
    private final int LARG=1000, HAUT=550;
    
    public UneFenetre()
    {
    tache = new UnMobile(LARG, HAUT);
	Thread supportTache = new Thread(tache);
    this.add(tache);
    this.setVisible(true);
    this.setSize(LARG, HAUT);
    supportTache.start();

    }
}
