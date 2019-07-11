
public class Queen extends Piece{
	
	public Queen (String img) {
		super(img);
		super.setWhite(img.equals("WhiteQueen.PNG"));
	
	}
	@Override
	public boolean isValid (Loc start, Loc end, Piece[][] pieces) {
		Piece p = pieces[end.getY()][end.getX()];
		if(p == null || p.isWhite() != isWhite()) {
			if(Math.abs(end.getX()-start.getX()) == Math.abs(end.getY()-start.getY())) {
				int consX = end.getX() > start.getX() ? 1: -1;
				int consY = end.getY() > start.getY() ? 1: -1;
				int range = Math.abs(end.getX() - start.getX());
				for(int i = 1; i < range; i++) {
					if(pieces[start.getY()+i*consY][start.getX()+i*consX] != null)
						return false;
				}
				return true;
			}
			if(start.getX() == end.getX()) { 
				int z = end.getY() > start.getY() ? 1 : -1;
				int range = Math.abs(end.getY()-start.getY());
				
				for(int y = 1; y < range; y++) {
					if(pieces[start.getY() + y*z][start.getX()] != null)
						return false;
				}
				return true;
			}
			
			if(start.getY() == end.getY()) {
				int z = end.getX() > start.getX() ? 1 : -1;
				int range = Math.abs(end.getX()-start.getX());
				for(int x = 1; x < range; x++) {
					if(pieces[start.getY()][start.getX() + x*z] != null)
						return false;
				}
				return true;
			}
		}
		return false;
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Queen";
	}
	@Override
	public boolean isCheckingKing(Loc queenLoc, Loc kingLoc, Piece[][] pieces) {
		Loc delta = queenLoc.minus(kingLoc);
		int dx = delta.getX();
		int dy = delta.getY();
		
		Piece firstIntersect = null;
		if(dx == 0) {
			firstIntersect = intersectPiece(queenLoc, new Loc(0, dy > 0 ? -1:1), pieces);
		}
		else if(dy == 0) {
			firstIntersect = intersectPiece(queenLoc, new Loc(dx > 0 ? -1: 1, 0), pieces);
		}
		else if(Math.abs(dx) == Math.abs(dy)) {
			firstIntersect = intersectPiece(queenLoc, new Loc(dx > 0 ? -1:1, dy > 0 ? -1:1), pieces);
		}
		return firstIntersect!= null && (firstIntersect.getName().equals("King") && firstIntersect.isWhite() != isWhite());
	}
}
