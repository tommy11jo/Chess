
public class King extends Piece{
	private Loc loc;
	private boolean inCheck;
	public King (String img) {
		super(img);
		super.setWhite(img.equals("WhiteKing.PNG"));
		setInCheck(false);
	
	}
	
	public King(String img, Loc loc) {
		super(img);
		super.setWhite(img.equals("WhiteKing.PNG"));
		this.loc = loc;
		setInCheck(false);
	}
	@Override
	public boolean isValid (Loc start, Loc end, Piece[][] pieces) {
		if(Math.abs(end.getX() - start.getX()) < 2 && Math.abs(end.getY() - start.getY()) < 2 && (pieces[end.getY()][end.getX()] == null || pieces[end.getY()][end.getX()].isWhite()!=isWhite() )) {
			this.setLoc(end);
			return true;
		}
		return false;
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

	@Override
	public boolean isCheckingKing(Loc pieceLoc, Loc KingLoc, Piece[][] pieces) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isInCheck() {
		return inCheck;
	}

	public void setInCheck(boolean inCheck) {
		this.inCheck = inCheck;
	}
}
