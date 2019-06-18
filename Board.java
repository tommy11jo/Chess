import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel{
	private ArrayList<Piece> pieces;
	private final int width;
	private final int height;
	public Board () {
		width = 800;
		height = 800;
		Dimension d = new Dimension(width, height);
		setPreferredSize(d);
		setBackground(Color.black);
		System.out.println("reached2");
		//this.add(new JLabel(new ImageIcon("Chess/resource/BlackBishop.PNG")));
	}
	
	public ArrayList<Piece> getPieces() {
		return pieces;
	}
	
	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public void update () {
		repaint();
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		//Graphics2D g2d = (Graphics2D) g;
		//g2d.setColor(Color.black);
		//g2d.fillRect(0, 0, width, height);
		//Pawn p = new Pawn(new Vec2(200, 200), true);
		//g2d.drawImage(p.getImgIcon(), 100, 100, this);
		ImageIcon imgIcon = new ImageIcon (getClass().getResource("Chess/resource/BlackBishop.PNG"));
		//p.getImgIcon().paintIcon(this, g,  100, 100);
		imgIcon.paintIcon(this, g, 0, 0);
		/*
		 for(Piece p: pieces) {
			g.drawImage(p.getImgIcon().getImage(), p.getLoc().getX(), p.getLoc().getY(), this);
		}
		*/
	}
}

