
public class SteepestHill 
{
	private Board b;
	private int solveCost = 0;
	private long runTime = 0;
	
	SteepestHill()
	{
		b = new Board();
	}
	SteepestHill(int size)
	{
		b = new Board(size);
	}
	SteepestHill(Board b)
	{
		this.b =  new Board(b);
	}
	
	//attempts to solve 
	public Board solve()
	{
		long time = System.nanoTime();
		solveCost = 0;
		int hVal = b.getVal();
		do
		{
			Board nextBest = getNextBest();
			solveCost++;
			if(b.getVal() == nextBest.getVal())
			{
				return nextBest;
			}
			b = nextBest;
			hVal = nextBest.getVal();
			
		}while(hVal > 0);
		runTime = System.nanoTime() - time;
		return b;
	}
	
	//returns a board that moves a queen to improve the heuristic value of the current instance
 	public Board getNextBest()
	{
		Board best = new Board(b);
		for(int i = 0; i < b.getSize(); i++)
		{
			Board temp = new Board(b);
			temp.resetQueen(i);
			boolean hasNext = true;
			while(hasNext)
			{
				hasNext  = temp.moveQueen(i);
				if(temp.getVal() < best.getVal())
				{
					best = new Board(temp);
				}
			}
		}
		return best;
	}
 	
 	//getters
 	public Board getBoard() {
		return b;
	}
	public int getSolveCost() {
		return solveCost;
	}
	public long getRunTime() {
		return runTime;
	}
}
