package Games;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class DrawPanel extends JPanel{
	private int stage = 0;

	public DrawPanel() {
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(5, 5, 5, 5), 
				BorderFactory.createLineBorder(Color.red)));
	}
	public Dimension getPreFerredSize() {
		return new Dimension(90, 120);
	}
	boolean drawNext() {
		stage ++ ;
		repaint();
		if (stage > 6) {
				return true;
		} else {		
		return false;
		}
	}
	public void reset() {
		stage = 0;
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawLine(20, 110, 80, 110); // base
		g.drawLine(70, 10, 70, 110); // pole
		g.drawLine(30, 10, 70, 10); // ceil
		g.drawLine(30, 10, 30, 30); // lock
		
		if (stage > 0)
			g.drawOval(20, 30, 20, 20); // head
		if (stage > 1)
			g.drawLine(30, 50, 30, 80); // body
		if (stage > 2)
			g.drawLine(30, 58, 10, 50); // left arm
		if (stage > 3)
			g.drawLine(30, 58, 50, 50); // right arm
		if (stage > 4)
			g.drawLine(30, 80, 10, 100); // left leg
		if (stage > 5)
			g.drawLine(30, 80, 50, 100); // right leg
	}	
}
