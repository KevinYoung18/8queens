import java.util.Random;
public class Board implements Comparable<Board>
{
	private boolean board[][];
	private Queen[] queens;
	private int size;
	
	Board()
	{
		size = 8;
		queens = new Queen[size];
		generateBoard();
	}
	Board(int size)
	{
		this.size = size;
		queens = new Queen[size];
		generateBoard();
	}
	Board(Board b)
	{
		size = b.getSize();
		queens =new Queen[size];
		for(int i = 0 ; i <size; i++)
		{
			queens[i] = new Queen(b.getQueens()[i].getRow(), b.getQueens()[i].getColumn());
		}
	}

	
	
	//generates a random board
	public void generateBoard()
	{
		Random rand = new Random();
		for(int i = 0; i < size; i++)
		{
			Queen q = null;
			boolean duplicates = true;
			while(duplicates)
			{
				int row = rand.nextInt(size);
				int column = rand.nextInt(size);
				q = new Queen(row, column);
				duplicates = false;
				for(int j = 0; j < i; j++)
				{
					if(q.equals(queens[j]))
					{
						duplicates = true;
						break;
					}
				}
			}
			queens[i] = q;
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
				if(queens[i].attacking(queens[j]))
					val++;
			}
		}
		return val;
	}
	
	//set the position of the queen at index to 0,0
	public void resetQueen(int index)
	{
		queens[index].setColumn(0);
		queens[index].setRow(0);
	}
	
	
	//moves queen, returns false at the end of the board
	public boolean moveQueen(int index)
	{
		int row = queens[index].getRow();
		int column = queens[index].getColumn();
		if(column < size-1)
		{
			column++;
			queens[index].setColumn(column);
		}
		else if(row < size-1)
		{
			column = 0;
			row++;
			queens[index].setColumn(column);
			queens[index].setRow(row);
		}
		else
			return false;
		return true;
	}
	
	public String toString()
	{
		//puts queens on to matrix
		board = new boolean[size][size];
		for(int i = 0; i <size; i++)
		{
			board[queens[i].getRow()][queens[i].getColumn()] = true;
		}
		//generates string
		String str ="";
		for(int i = 0; i <size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(board[i][j])
					str += "1 ";
				else
					str += "0 ";
			}
			str +="\r\n";
		}
		return str;
	}
	
	@Override
	public int compareTo(Board b) {
		if(this.getVal() < b.getVal())
			return -1;
		if(this.getVal() > b.getVal())
			return 1;
		return 0;
	}
	
	//getters and setters
	public boolean[][] getBoard() {
		return board;
	}
	public Queen[] getQueens() {
		return queens;
	}
	public int getSize() {
		return size;
	}
	public void setQueen(int index, Queen q)
	{
		queens[index] = q;
	}
	
}
