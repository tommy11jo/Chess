import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Piece {
	//if you add positions just base them off the array of pieces
	private Image image;
	
	private boolean isWhite;
	
	public abstract boolean isValid (Loc start, Loc end, Piece[][] pieces);
	
	public abstract String getName();
	
	public Piece (String img) {
		ClassLoader classLoader = getClass().getClassLoader();
		java.io.InputStream in = classLoader.getResourceAsStream(img);
		try {
			image = ImageIO.read(in);
		} catch (IOException e) {
		}
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
	
}
