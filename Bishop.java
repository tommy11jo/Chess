
public class Bishop extends Piece{

	public Bishop (String img) {
		super(img);
		super.setWhite(img.equals("WhiteBishop.PNG"));
	
	}
	@Override
	public boolean isValid (Loc start, Loc end, Piece[][] pieces) {
		Piece p = pieces[end.getY()][end.getX()];
		if((p == null || p.isWhite() != isWhite()) && Math.abs(end.getX()-start.getX()) == Math.abs(end.getY()-start.getY())) {
			int consX = end.getX() > start.getX() ? 1: -1;
			int consY = end.getY() > start.getY() ? 1: -1;
			int range = Math.abs(end.getX() - start.getX());
			for(int i = 1; i < range ; i++) {
				if(pieces[start.getY()+i*consY][start.getX()+i*consX] != null)
					return false;
			}
			return true;
		}
		return false;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Bishop";
	}

}
