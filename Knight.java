
public class Knight extends Piece{

	public Knight (String img) {
		super(img);
		super.setWhite(img.equals("WhiteKnight.PNG"));
	
	}
	@Override
	public boolean isValid (Loc start, Loc end, Piece[][] pieces) {
		Loc validDif1 = new Loc (2, 1);
		Loc validDif2 = new Loc (1, 2);
		
		Loc dif = new Loc(Math.abs(end.getX() - start.getX()), Math.abs(end.getY() - start.getY()));
		if(pieces[end.getY()][end.getX()] != null && pieces[end.getY()][end.getX()].isWhite() == isWhite())
			return false;
		if(dif.equals(validDif1) || dif.equals(validDif2)) {
			return true;
		}
		return false;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Knight";
	}
	@Override
	public boolean isCheckingKing(Loc knightLoc, Loc kingLoc, Piece[][] pieces) {
		Loc delta = kingLoc.minus(knightLoc);
		return delta.equals(new Loc(-1, -2)) || delta.equals(new Loc(-1, 2)) || delta.equals(new Loc(1, 2)) ||
				delta.equals(new Loc(-2, -1)) || delta.equals(new Loc(2, -1)) || delta.equals(new Loc (-2, 1));
	}
}
