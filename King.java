
public class King extends Piece{
	private Loc loc;
	public King (String img) {
		super(img);
		super.setWhite(img.equals("WhiteKing.PNG"));
	
	}
	
	public King(String img, Loc loc) {
		super(img);
		super.setWhite(img.equals("WhiteKing.PNG"));
		this.loc = loc;
	}
	@Override
	public boolean isValid (Loc start, Loc end, Piece[][] pieces) {
		if(Math.abs(end.getX() - start.getX()) < 2 && Math.abs(end.getY() - start.getY()) < 2 && (pieces[end.getY()][end.getX()] == null || pieces[end.getY()][end.getX()].isWhite()!=isWhite() )) {
			this.setLoc(end);
			return true;
		}
		return false;
	}
	
	public boolean inCheck (Piece[][] pieces) {
		/*8 directions (4 lines) to be tested 
		 * (x, y)
		 * (1, 1) and (-1, -1)
		 * (1, -1) and (-1, 1)
		 * (0, 1) and (0, -1)
		 * (-1, 0) and (1, 0)
		*/
		boolean inCheck = false;
		Loc dir1 = new Loc (1, 1);
		Piece intersect = intersectPiece(dir1, pieces);
		String intersectName = "";
		if(intersect != null)
			intersectName = intersect.getName();
		if(intersect != null && intersect.isWhite() != isWhite() &&(intersectName.equals("Bishop") || intersectName.equals("Queen")))
			return true;
		return false;
	}
	public Piece intersectPiece(Loc dir, Piece[][] pieces) {
		return null;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "King";
	}
	public Loc getLoc() {
		return loc;
	}
	public void setLoc(Loc loc) {
		this.loc = loc;
	}
}
