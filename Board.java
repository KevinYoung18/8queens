import java.util.Random;
public class Board implements Comparable<Board>
{
	private int board[];
	private int size;
	
	Board()
	{
		size = 8;
		generateBoard();
	}
	Board(int size)
	{
		this.size = size;
		generateBoard();
	}
	Board(Board b)
	{
		size = b.getSize();
		this.board = b.getBoard().clone();
	}

	
	
	//generates a random board
	public void generateBoard()
	{
		Random rand = new Random();
		board = new int[size];
		for(int i = 0; i < size; i++)
		{
			board[i] = rand.nextInt(size);
		}
	}
	
	//returns heuristic value of this instance
	public int getVal()
	{
		int val = 0;
		for(int i = 0; i < size; i++)
		{
			for(int j = i+1; j < size; j++)
			{
				if(attacking(i,j))
					val++;
			}
		}
		return val;
	}
	
	private boolean attacking(int queen1, int queen2 ) 
	{
		if(board[queen1] == board[queen2]
			|| Math.abs(queen1-queen2) == Math.abs(board[queen1]- board[queen2]))
		{
			return true;
		}
		return false;
	}
	
	//set the position of the queen at index to 0,0
	public void resetQueen(int queen)
	{
		board[queen] = 0;
	}
	
	
	//moves queen to specified location, returns false if out of bounds
	public boolean moveQueen(int queen, int row)
	{
		try 
		{
			board[queen] = row;
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	// returns false at the end of the board
	public boolean moveQueen(int queen)
	{
		int row = board[queen];
		if(row < size-1)
		{
			row++;
			board[queen] = row;
		}
		else
			return false;
		return true;
	}
	
//	public String toString()
//	{
//		//puts queens on to matrix
//		board = new boolean[size][size];
//		for(int i = 0; i <size; i++)
//		{
//			board[queens[i].getRow()][queens[i].getColumn()] = true;
//		}
//		//generates string
//		String str ="";
//		for(int i = 0; i <size; i++)
//		{
//			for(int j = 0; j < size; j++)
//			{
//				if(board[i][j])
//					str += "1 ";
//				else
//					str += "0 ";
//			}
//			str +="\r\n";
//		}
//		return str;
//	}
	
	@Override
	public int compareTo(Board b) {
		if(this.getVal() < b.getVal())
			return -1;
		if(this.getVal() > b.getVal())
			return 1;
		return 0;
	}
	
	//getters and setters
	public int[] getBoard() {
		return board;
	}
	public int getSize() {
		return size;
	}
}
