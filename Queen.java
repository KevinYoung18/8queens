
public class Queen
{
	private int row;
	private int column;
	
	Queen(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	//checks if queen q is in same row, column, or diagonal as this queen
	public boolean attacking(Queen q) 
	{
		if(row == q.getRow() || column == q.getColumn() 
			|| Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
		{
			return true;
		}
		return false;
	}
	
	
	//returns true if another queen has the same row and column value
	public boolean equals(Object o)
	{
		if(o.getClass() == this.getClass())
		{
			Queen q = (Queen) o;
			if(q.getRow() == row && q.getColumn() == column)
				return true;
		}
		return false;
	}
	
	public Queen clone() 
	{
		return new Queen(row, column);
	}
	
	//getters and setters
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
}
