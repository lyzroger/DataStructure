/* MachinePlayer.java */

package player;
import java.util.Random;
import list.*;
/**
 *  An implementation of an automatic Network player.  Keeps track of moves
 *  made by both players.  Can select a move for itself.
 */
public class MachinePlayer extends Player {
	
	public final static int DEPTH = 2;
    public final static int WHITEWIN = 100;
    public final static int BLACKWIN = -100;
	
    protected int color;
    protected int searchDepth;
    protected Board gameBoard;

    //Create a move with score.
    public class BestMove{
	
	  protected Move move;
	  protected int score;
	
	  public BestMove() {
	  	move = new Move();
	  	score = 0;
	  }
	
  }
  // Creates a machine player with the given color.  Color is either 0 (black)
  // or 1 (white).  (White has the first move.)
  public MachinePlayer(int color) {
	  this.color = color;
      this.searchDepth = DEPTH;
      gameBoard = new Board();
  }

  // Creates a machine player with the given color and search depth.  Color is
  // either 0 (black) or 1 (white).  (White has the first move.)
  public MachinePlayer(int color, int searchDepth) {
	  this.color = color;
      this.searchDepth = searchDepth;
      gameBoard = new Board();
  }

  // Returns a move by this player.  Updates the game board as a move by this player.
  public Move chooseMove() {
	  Move best = miniMax(color, searchDepth);
	  doMove(best, this.color);
	  return best;
  } 

  // If the Move m is legal, records the move as a move by the opponent and returns true.  
  // If the move is illegal, returns false without modifying the status of this
  // player. This method allows opponents to inform you of their moves.
  public boolean opponentMove(Move m) {
	  return doMove(m, opponentColor(color));
  }

  private boolean doMove(Move m, int color) {
	  Evaluator evaluation = new Evaluator(gameBoard);
	  if(m.moveKind == Move.ADD) {
		  if(evaluation.isValidMove(m, color)) {
			  gameBoard.setContent(m.x1, m.y1, color);
			  return true;
		  }
	  } else if(m.moveKind == Move.STEP){
		  gameBoard.setContent(m.x2, m.y2, Board.EMPTY);
		  if(!evaluation.isValidMove(m, color)) {
			  gameBoard.setContent(m.x2, m.y2, color);
			  return false;
		  } else if(evaluation.isValidMove(m, color)) {
			  gameBoard.setContent(m.x1, m.y1, color);
			  return true;
		  }
	  }
	  return false;
  }
  // If the Move m is legal, records the move as a move by this player and returns true.  
  // If the move is illegal, returns false without modifying the status of this
  // player. This method is used to help set up "Network problems" for your player to solve.
  public boolean forceMove(Move m) {
    return doMove(m, color);
  }
  
  /**
   * minimax returns the best move after searching to depth
   * @return the best move
   */

  public Move miniMax(int color, int depth) {
	  int alpha = BLACKWIN - 1;
	  int beta = WHITEWIN + 1;
	  boolean side;
	  if(color == this.color) {
		  side = true;
	  } else {
		  side = false;
	  }
	  BestMove best = moveHelper(color, side, depth, alpha, beta);
	  
	  return best.move;
  }
  
  private BestMove moveHelper(int color, boolean side, int depth, int alpha, int beta) {
	  BestMove myBest = new BestMove();
	  BestMove reply;
	  
	  Random r = new Random();
	  Move firstMove = new Move();
	  firstMove.moveKind = Move.ADD;
	  
	  Evaluator evaluation = new Evaluator(gameBoard);
	  
	  int chips = gameBoard.numberOfChips();
	  if(chips <= 2) {
		  if(color == Board.BLACK) {
			  firstMove.y1 = 0;
			  firstMove.x1 = r.nextInt(Board.DIMENSION - 2) + 1;
			  myBest.move=firstMove;
			  return myBest;
		  }
		  if(color == Board.WHITE){
			  firstMove.x1 = 0;
			  firstMove.y1 = r.nextInt(Board.DIMENSION - 2) + 1;
			  myBest.move = firstMove;
			  return myBest;
		  }
	  }
	  if(chips > 2 && chips <= 4) {
		  if(color == Board.BLACK) {
			  firstMove.y1 = Board.DIMENSION - 1;
			  firstMove.x1 = r.nextInt(Board.DIMENSION - 2) + 1;
			  myBest.move = firstMove;
			  return myBest;
		  }
		  if(color == Board.WHITE) {
			  firstMove.x1 = Board.DIMENSION - 1;
			  firstMove.y1 = r.nextInt(Board.DIMENSION - 2) + 1;
			  myBest.move = firstMove;
			  return myBest;
		  }
	  }
	  if(evaluation.winningBoard() || depth == 0) {
		  myBest.score = evaluation.boardEval(color);
	  }
	  
	  if(side) {
		  myBest.score = alpha;
	  } else {
		  myBest.score = beta;
	  }
	  
	  try {
		  SList validMoves = evaluation.generateMoves(color);
		  SListNode currNode = (SListNode) validMoves.front();
		  for(int i = 0; i < validMoves.length(); i++) {
			  Board tempBoard = gameBoard.clone();
			  Move currentMove = (Move) currNode.item();
			  doMove(currentMove, color);
			  reply = moveHelper(MachinePlayer.opponentColor(color), !side, depth - 1, alpha, beta);
			  gameBoard = tempBoard;
			  if(side && (reply.score > myBest.score)) {
				  myBest.move = currentMove;
				  myBest.score = reply.score;
				  alpha = reply.score;
			  } else if(!side && (reply.score < myBest.score)) {
				  myBest.move = currentMove;
				  myBest.score = reply.score;
				  beta = reply.score;
			  }
			  if(alpha >= beta) {
				  return myBest;
			  }
		  }
	  } catch(InvalidNodeException e) {
		  System.out.println(e + "In moveHelper");
	  }
	  return myBest;
  }

  /**
   * Get opponent's color
   * @param color my color
   * @return opponent's color
   */
  
  protected static int opponentColor(int color) {
	  if(color == Board.WHITE) {
		  return Board.BLACK;
	  } else {
		  return Board.WHITE;
	  }
  }

}
