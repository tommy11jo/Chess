import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Piece {
	//if you add positions just base them off the array of pieces
	private Image image;
	
	private boolean isWhite;
	
	public abstract boolean isValid (Loc start, Loc end, Piece[][] pieces);
	
	public abstract String getName();
	
	public abstract boolean isCheckingKing(Loc pieceLoc, Loc KingLoc, Piece[][] pieces);
	public Piece (String img) {
		ClassLoader classLoader = getClass().getClassLoader();
		java.io.InputStream in = classLoader.getResourceAsStream(img);
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
		}
	}
	
	public Piece intersectPiece(Loc start, Loc dir,  Piece[][] pieces) {
		int dx = dir.getX();
		int dy = dir.getY();
		
		//int c = dx >= 0 ? (dx == 0 ? 0:-1): 1;
		int c = dx >= 0 ? -1: 0;
		int d = dy >= 0 ? -1: 0;
		//int d = dy >= 0 ? (dx == 0 ? 0:-1): 1;
		//System.out.println("KingLoc" + loc);
		int x = start.getX();
		int y = start.getY();
		while(x < 8 + c && x > c && y < 8 + d && y > d) {
			//System.out.println("x " + x + " y " + y);
			x+=dx;
			y+=dy;
			if(pieces[y][x] != null)
				return pieces[y][x];
		}
		return null;
	}
	
	public Piece (Image image) {
		this.image = image;
		
	}
	public Image getImg() {
		return image;
	}

	public void setImg(Image img) {
		this.image = img;
	}
	
	public boolean isWhite() {
		return isWhite;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	public String toString () {
		return getName();
	}
	
}
