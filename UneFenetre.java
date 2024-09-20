import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    private final int LARG=400, HAUT=250;
    
    public UneFenetre()
    {
	sonMobile = new UnMobile(LARG, HAUT);
	Thread laTache = new Thread(sonMobile);
    this.add(sonMobile);
    this.setVisible(true);
    this.setSize(LARG, HAUT);
	laTache.start();

    }
}
