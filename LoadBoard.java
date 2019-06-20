/* By Tommy Joseph
 * A SIMPLE CHESS GAME
 * UPDATE: Efficiency greatly improved by shooting rays from pieces once they are moved, 
 * rather than from the king after every move
 * NEXT STEPS: 
 * 1. Pins - I could do this by keeping track of "intersected" pieces, pieces that come between
 * an attacking piece and the opposing king. Before each move, I could check this array.
 */
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.JFrame;
import javax.swing.JPanel;

public class LoadBoard extends JPanel implements MouseListener, MouseMotionListener{
		
	private Piece [][] pieces;
	
	private Color lightCol, darkCol;
	
	private Piece currentPiece, previousPiece;
	
	private Loc prevGoalLoc;
	
	private Loc curLoc, mouseLoc;
	
	private boolean whiteTurn;
	
	private boolean mousePressed;
	
	private King whiteKing, blackKing;
	
	public LoadBoard () {
		lightCol = new Color(224, 190, 130);
		darkCol = new Color(175, 120, 0);
		pieces = new Piece [8][8];
		whiteTurn = true;
		prevGoalLoc = new Loc(0, 0);
		setUpPieces();
		previousPiece = pieces[1][1];
		addMouseListener(this);
		addMouseMotionListener(this);	
	}
	
	public Piece[][] move (Piece p, Loc goalLoc) {
		//converting pixel locations to array location
		Piece[][] holder = new Piece[8][8];
		for(int row = 0; row < pieces.length; row++) {
			for(int col = 0; col < pieces[0].length; col++) {
				holder[row][col] = pieces[row][col];
			}
		}
		
		double d = 0.01;
		Loc start = curLoc.scaledBy(d);
		Loc end = goalLoc.scaledBy(d);
		//tests for valid move and also keeps track of King location for future use in checking check
		if(p.isWhite() == whiteTurn && p.isValid(start, end, holder)) {
			holder[end.getY()][end.getX()] = p;
			if(p.getName().equals("King")) {
				if(p.isWhite()) {
					whiteKing = (King) p;
				}
				else
					blackKing = (King) p;
			}
			King k = p.isWhite() ? blackKing: whiteKing;
			k.setInCheck(setChecks(p, end, holder));
			holder[start.getY()][start.getX()] = null;
			whiteTurn =!whiteTurn;
		}
		else {
			System.out.println("Invalid");
		}
		
		return holder;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mousePressed = false;
		if(currentPiece != null && e.getX() < 800 && e.getY() < 800) {
			Loc goalLoc = new Loc (e.getX(), e.getY());
			double d = 0.01;
			boolean sameLoc = goalLoc.scaledBy(d).equals(curLoc.scaledBy(d));
			if(currentPiece.isWhite() == whiteTurn && !sameLoc) {
				
				King k = whiteTurn ? whiteKing: blackKing;
				if(!k.isInCheck() || !setChecks(previousPiece, prevGoalLoc.scaledBy(d), move(currentPiece, goalLoc))) {
					pieces = move(currentPiece, goalLoc);
				}
				else {
						System.out.println("Invalid Move");
				}
				prevGoalLoc = goalLoc;
				previousPiece = currentPiece;
			}
		}
		else System.out.println("Cannot move here");
	}

	private boolean setChecks(Piece p, Loc end, Piece[][] holder) {
		// TODO Auto-generated method stub
		King k = p.isWhite() ? blackKing: whiteKing;
		if(p.isCheckingKing(end, k.getLoc(), holder))
			return true;
		return false;
	}

	public void paintComponent(Graphics g) {
		//Update 1: accounts for order of pieces/proper precedence
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for(int row = 0; row < pieces[0].length; row++) {
			for(int col = 0; col < pieces.length; col++) {
				Color color = (row + col) % 2 == 0 ? lightCol: darkCol;
				g2d.setColor(color);
				g2d.fillRect(col*100, row*100, 100, 100);
				Piece current = pieces[row][col];	
				if(current!= null && (!mousePressed || current != currentPiece)) {
					g2d.drawImage(current.getImg(), col*100, row*100, null);
				}
			}
		}
		if(mousePressed && currentPiece != null) {
			int width = currentPiece.getImg().getWidth(null);
			int height = currentPiece.getImg().getHeight(null);
			g2d.drawImage(currentPiece.getImg(), mouseLoc.getX() - width/2, mouseLoc.getY() - height/2, null);
		}
			
	}
	
	
	public void setUpPieces () {
		for(int x = 0; x < pieces[0].length; x++) {
			pieces[1][x] = new Pawn("BlackPawn.PNG");
		}

		for(int x = 0; x < pieces[7].length; x++) {
			pieces[6][x] = new Pawn("WhitePawn.png");
		}
		
		pieces[0][0] = new Rook("BlackRook.PNG");
		pieces[0][7] = new Rook("BlackRook.PNG");
		
		pieces[7][0] = new Rook("WhiteRook.PNG");
		pieces[7][7] = new Rook("WhiteRook.PNG");
		
		pieces[0][1] = new Knight("BlackKnight.PNG");
		pieces[0][6] = new Knight("BlackKnight.PNG");
		
		pieces[7][1] = new Knight("WhiteKnight.PNG");
		pieces[7][6] = new Knight("WhiteKnight.PNG");
		
		pieces[0][2] = new Bishop("BlackBishop.PNG");
		pieces[0][5] = new Bishop("BlackBishop.PNG");
		
		pieces[7][2] = new Bishop("WhiteBishop.PNG");
		pieces[7][5] = new Bishop("WhiteBishop.PNG");
		
		pieces[0][3] = new Queen("BlackQueen.PNG");
		pieces[7][3] = new Queen("WhiteQueen.PNG");
		
		pieces[0][4] = new King("BlackKing.PNG", new Loc(4, 0));
		pieces[7][4] = new King("WhiteKing.PNG", new Loc(4, 7));
		blackKing = (King)pieces[0][4];
		whiteKing = (King)pieces[7][4];
	}
	
	public Dimension getPreferredSize() {
		return new Dimension (800, 800);
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//f.setResizable(false);
		LoadBoard l = new LoadBoard();
		f.add(l);
		f.pack();
		f.setVisible(true);
		
		while(true) {
			l.repaint();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		mousePressed = true;
		curLoc = new Loc(e.getX(), e.getY());
		mouseLoc = new Loc(e.getX(), e.getY());
		currentPiece = pieces[curLoc.getY()/100][curLoc.getX()/100];
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(mousePressed)
			mouseLoc = new Loc(e.getX(), e.getY());		
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
	}

}
