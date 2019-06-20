
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
		//System.out.println("check tested");
		boolean inCheck = false;
		Loc dir1 = new Loc (1, 1);
		Loc [] diagDirs = {new Loc(1, -1), new Loc(-1, 1), new Loc(1, 1), new Loc(-1, -1)}; 
		Loc[] straightDirs = {new Loc(0, 1), new Loc(0, -1), new Loc(-1, 0), new Loc(1, 0)};
		for(Loc dir: diagDirs) {
			Piece intersect = intersectPiece(dir, pieces);
			String intersectName = "";
			if(intersect != null) {
				intersectName = intersect.getName();
				if(intersect.isWhite() != isWhite() && (intersectName.equals("Bishop") || intersectName.equals("Queen")))
					inCheck = true;
			}
		}
		for(Loc dir: straightDirs) {
			Piece intersect = intersectPiece(dir, pieces);
			String intersectName = "";
			if(intersect != null) {
				intersectName = intersect.getName();
				if(intersect.isWhite() != isWhite() && (intersectName.equals("Rook") || intersectName.equals("Queen")))
					inCheck = true;
			}
		}
		
		return inCheck;
	}
	public Piece intersectPiece(Loc dir, Piece[][] pieces) {
		int dx = dir.getX();
		int dy = dir.getY();
		
		int c = dx > 0 ? -1: 1;
		int d = dy > 0 ? -1: 1;
		//System.out.println("KingLoc" + loc);
		int x = loc.getX();
		int y = loc.getY();
		while(x < 8 + c && x > c && y < 8 + d && y > d) {
			//System.out.println("x " + x + " y " + y);
			x+=dx;
			y+=dy;
			if(pieces[y][x] != null)
				return pieces[y][x];
		}
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
