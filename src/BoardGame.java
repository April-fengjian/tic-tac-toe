/**
 * This class implements all the support methods needed by the algorithm that plays the board game
 * @author Zhiqi Bei (Student#:250916374)
 *
 */
public class BoardGame {
	/*
	 * Attributes
	 */
	char[][] gameBoard;
	private int board_size;
	private int empty_positions;
	private int max_levels;
/**
 * The constructor for this class	
 * @param board_size specifies the size of the game board
 * @param empty_positions the number of positions on the board that must remain empty
 * @param max_levels the playing quality of the program
 */
	public BoardGame (int board_size, int empty_positions, int max_levels) {
		this.gameBoard = new char[board_size][board_size];
		this.board_size = board_size;
		this.empty_positions = empty_positions;
		this.max_levels = max_levels;
		for(int i=0;i<gameBoard.length;i++){
		    for(int j=0;j<gameBoard[i].length;j++){
		    		gameBoard[i][j] = 'g';
		    }
		}
	}
/**
 * this method is to make an empty HashDictionary of the size that you have selected.	
 * @return an empty HashDictionary of selected size
 */
	public HashDictionary makeDictionary() {
		HashDictionary dictionary = new HashDictionary(10000);
		return dictionary;
	}
/**
 * This method first represents the content of gameBoard as a string as described above; 
 * then it checks whether the string representing the gameBoard is in dict
 * @param dict the hashdictionary that need to check
 * @return its associated score; otherwise it returns the value -1.
 */
	public int isRepeatedConfig(HashDictionary dict) {
		String string = null;
		for(int i=0;i<gameBoard.length;i++){
		    for(int j=0;j<gameBoard[i].length;j++){
		        string = string + String.valueOf(gameBoard[i][j]);		 
		    }		 
		}
		int score = dict.getScore(string);
		return score;
	}
/**
 * This method first represents the content of gameBoard as a string as described above; 
 * then it inserts this string and its score in dict.
 * @param dict the Hashdictionary that used
 * @param score the score need to insert into dict
 */
	public void putConfig(HashDictionary dict, int score) {
		String string = null;
		for(int i=0;i<gameBoard.length;i++){
		    for(int j=0;j<gameBoard[i].length;j++){
		        string = string + String.valueOf(gameBoard[i][j]);		 
		    }		 
		}
		Configuration config = new Configuration(string,score);
		dict.put(config);
	}
/**
 * This method stores symbol in gameBoard[row][col].
 * @param row the row of the symbol
 * @param col the column of the symbol
 * @param symbol the symbol need to save
 */
	public void savePlay(int row, int col, char symbol) {
		if(row>=0&&col>=0&&row<gameBoard.length&&col<gameBoard.length) {
			gameBoard[row][col] = symbol;
		}		
	}
/**
 * This method is to check if the position is empty	
 * @param row the of the position
 * @param col the column of the position
 * @return true if gameBoard[row][col] is ’o’; otherwise it returns false.
 */
	public boolean positionIsEmpty (int row, int col) {
		if(row>=0&&col>=0&&row<gameBoard.length&&col<gameBoard.length) {
			if(gameBoard[row][col] == 'g') {
				return true;
			}
		}		
		return false;
	}
/**
 * This method is to check if the position is an tile of the computer	
 * @param row the row of the position
 * @param col the column of the position
 * @return Returns true if gameBoard[row][col] is ’o’; otherwise it returns false.
 */
	public boolean tileOfComputer (int row, int col) {
		if(row>=0&&col>=0&&row<gameBoard.length&&col<gameBoard.length) {
			if(gameBoard[row][col] == 'o') {
				return true;
			}
		}
		return false;
	}
/**
 * This method id to check if the position is an tile of the human
 * @param row the row of the position
 * @param col the column of the position
 * @return Returns true if gameBoard[row][col] is ’b’; otherwise it returns false.
 */
	public boolean tileOfHuman (int row, int col) {
		if(row>=0&&col>=0&&row<gameBoard.length&&col<gameBoard.length) {
			if(gameBoard[row][col] == 'b') {
				return true;
			}
		}	
		return false;
	}
/**
 * This method is to check if there are n adjacent tiles of type symbol in the same row, column, or diagonal of gameBoard,
 * @param symbol the symbol need to be checked
 * @return true if there are, false, otherwise
 */
	public boolean wins (char symbol) {
		//the result of the row, column,and two diagonal
		boolean row_result = false;
		boolean col_result = false;
		boolean dia1_result = false;
		boolean dia2_result = false;
		//count represent the number of the same symbol in one row
		int count = 0;
	    //check the row
		//using loops to check all the rows of the game board
		for(int i=0;i<gameBoard.length;i++) {
				count = 0;
				for(int j=0;j<gameBoard.length;j++) {						
						if(gameBoard[i][j]==symbol) {
							count++;
						}
						if(count==board_size) {
							row_result = true;
						}
				}	
		}
		//test the column
		//using loops to check all the columns of the game board
		for(int i=0;i<gameBoard.length;i++) {
				count = 0;
				for(int j=0;j<gameBoard.length;j++) {
						if(gameBoard[j][i]==symbol) {
							count++;
						}
						if(count==board_size) {
							col_result = true;
						}
				}
		}		
		//test the left diagonal(begin form (0,0))
		for(int k=0;k<gameBoard.length-1;k++) {
			if(gameBoard[k][k]==gameBoard[k+1][k+1]) {
				dia1_result = true;
								
			}else {
				dia1_result = false;
				break;
				}
			}
		//test the right diagonal(begin form (0,size-1))
		for(int k=0;k<gameBoard.length-1;k++) {
			int index = gameBoard.length-1;
			if(gameBoard[k][index-k]==gameBoard[k+1][index-k-1]) {
				dia2_result = true;
			}else {
				dia2_result = false;
				break;
			}
		}			
		//check the diagonal is not empty
		if(dia1_result==true&&gameBoard[0][0]!='g') {
			dia1_result = true;
		}else {
			dia1_result = false;
		}
		if(dia2_result==true&&gameBoard[0][gameBoard.length-1]!='g') {
			dia2_result = true;
		}else {
			dia2_result = false;
		}
		//return true if one of the row, column, and two diagonals is has the same symbol
		if(row_result==true||col_result==true||dia1_result==true||dia2_result==true) {
			return true;
		}
		return false;		
	}
/**
 * This method is to check if the game id draw	
 * @param symbol the player
 * @param empty_positions the number of positions on the board that must remain empty
 * @return true if the game is draw, false otherwise
 */
	public boolean isDraw(char symbol, int empty_positions) {
		boolean result = false;
		int count = 0;
		int row = 0;
		int col = 0;
		//the situation that no empty position on the board
		if(empty_positions == 0&&wins(symbol)==false) {
			for(int i=0;i<gameBoard.length;i++){
			    for(int j=0;j<gameBoard[i].length;j++){
			    		if(gameBoard[i][j]=='g')
			    			count = count+1;
			    	}
			}
			if(count ==empty_positions) {
				return true;
			}
			return false;
		}
		//the situation that at least one empty position
		if(empty_positions>0) {
			//find the actual number of the empty position on the game board
			for(int i=0;i<gameBoard.length;i++){
			    for(int j=0;j<gameBoard.length;j++){
			    		if(gameBoard[i][j]=='g') {
			    			count = count+1;			    			
			    		}
			    }
			}
		    //if the number of the empty positions is equal to the empty positions that defined at the begining
			//find the empty position and check its surrounding symbol
			if(count==empty_positions&&wins(symbol)==false) {
				for(int i=0;i<gameBoard.length;i++){
				    for(int j=0;j<gameBoard.length;j++){
				    		if(gameBoard[i][j]=='g') {				    				
				    			row = i;
				    			col = j;							
							if(row-1>=0&&col-1>=0&&gameBoard[row-1][col-1]!= symbol) {					
								result = true;					
							}
							if(row-1>=0&&col-1>=0&&gameBoard[row-1][col-1]== symbol) {
								System.out.println("1");
								return false;
							}
							if(row-1>=0&&gameBoard[row-1][col]!= symbol) {
								result = true;
							}
							if(row-1>=0&&gameBoard[row-1][col]== symbol) {
								System.out.println("2");
								return false;
							}
							if(row-1>=0&&col+1<gameBoard.length&&gameBoard[row-1][col+1]!=symbol) {
								result = true;
							}
							if(row-1>=0&&col+1<gameBoard.length&&gameBoard[row-1][col+1]==symbol) {
								System.out.println("3");
								return false;
							}
							if(col-1>=0&&gameBoard[row][col-1]!=symbol) {
								result = true;
							}
							if(col-1>=0&&gameBoard[row][col-1]==symbol) {
								System.out.println("4");
								return false;
							}
							if(col+1<gameBoard.length&&gameBoard[row][col+1]!=symbol) {
								result = true;
							}
							if(col+1<gameBoard.length&&gameBoard[row][col+1]==symbol) {
								System.out.println("5");
								return false;
							}
							if(row+1<gameBoard.length&&col-1>=0&&gameBoard[row+1][col-1]!=symbol) {
								result= true;
							}
							if(row+1<gameBoard.length&&col-1>=0&&gameBoard[row+1][col-1]==symbol) {
								System.out.println("6");
								return false;
							}
							if(row+1<gameBoard.length&&gameBoard[row+1][col]!=symbol) {
								result = true;
							}
							if(row+1<gameBoard.length&&gameBoard[row+1][col]==symbol) {
								System.out.println("7");
								return false;
							}
							if(row+1<gameBoard.length&&col+1<gameBoard.length&&gameBoard[row+1][col+1]!=symbol) {
								result = true;;
							}
							if(row+1<gameBoard.length&&col+1<gameBoard.length&&gameBoard[row+1][col+1]==symbol) {
								System.out.println("8");
								return false;
							}					
				    		}
				    }									
				}
			}
		}			
		return result;
	}
/**
 * this method return different value for the different outcomes of the game	
 * @param symbol the symbol of the current player
 * @param empty_positions the number of positions on the board that must remain empty
 * @return 3, if computer won. 0, if human player won. 2, if game is draw. 1, if the game is still undecided.
 */
	public int evalBoard(char symbol, int empty_positions) {
		if(wins(symbol)==true) {
			if(symbol=='o') {
				return 3;
			}
			if(symbol=='b') {
				return 0;
			}
		}
		if(isDraw(symbol,empty_positions)==true) {
			return 2;
		}
		return 1;
	}
}
