import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
public class Genetic 
{
	private int size;
	private int solveCost = 0;
	private long runTime = 0;
	private Board[] population;
	private  int mFactor = 6; //likelihood of mutation, larger numbers = more likely
	
	Genetic()
	{
		size = 8;
		initializePopulation();
	}
	Genetic(int size)
	{
		this.size = size;
		initializePopulation();
	}
	Genetic(int size, int mFactor)
	{
		this.size = size;
		this.mFactor = mFactor;
		initializePopulation();
	}
	
	//create random set of boards 
	public void initializePopulation()
	{
		population = new Board[size];
		for(int i = 0; i < size; i++)
		{
			Board b = new Board(size);
			population[i] = b;
		}
	}
	
	//solve the n-queens problem
	public Board solve()
	{
		long time = System.nanoTime();
		solveCost = 0;
		do
		{
			solveCost++;
			population = newGen();
		}while(population[0].getVal() > 0);
		runTime = System.nanoTime() - time;
		return population[0];
	}
	
	//creates a new generation of boards based on the best children of the previous generation
 	public Board[] newGen()
	{
		Board[] boards = population.clone();
		Arrays.sort(boards);
		ArrayList<Board> children = new ArrayList<Board>(size*2);
		Random rand = new Random();
		
		//crosses boards
		for(int i = 0; i < size; i++)
		{
			
			int midpoint = rand.nextInt(size);
			if(i == 0)
			{
				children.add(cross(boards[i],boards[i+1], midpoint));
				children.add(cross(boards[i+1],boards[i], midpoint));
			}
			if(i == size-1)
			{
				children.add(cross(boards[i],boards[i-1], midpoint));
				children.add(cross(boards[i-1],boards[i], midpoint));
			}
			else
			{
				children.add(cross(boards[i],boards[i+1], midpoint));
				children.add(cross(boards[i+1],boards[i], midpoint));
			}
		}
		
		//checks for solution and possibly add mutation
		for(int i = 0; i < children.size(); i++)
		{
			//if solution is found returns array with solution at front
			if(children.get(i).getVal() == 0)
			{
				boards[0] = children.get(i);
				return boards;
			}
			else if(rand.nextInt(mFactor) > 1) 
			{
				mutate(children.get(i));
			}
		}
		
		Collections.sort(children);
		for(int i = 0; i < size; i++)
		{
			boards[i] = children.get(i);
		}

		return boards; 
	}
	
	// moves a random queen to a random place on the board
	public void mutate(Board b)
	{
		Random rand = new Random();
		int rQueen = rand.nextInt(size);
		boolean bool = true;
		int row = rand.nextInt(size);
		int column = rand.nextInt(size);
		Queen q = new Queen(row, column);
		while(bool)
		{
			bool = false;
			row = rand.nextInt(size);
			column = rand.nextInt(size);
			q.setRow(row);
			q.setColumn(column);
			for(int i = 0; i < size; i++)
			{
				if(q.equals(b.getQueens()[i]))
					bool = true;
			}
		}
		b.getQueens()[rQueen] = q;
	}
	
	//takes two board and returns a board with some queens from each
	public Board cross(Board board1, Board board2, int midpoint)
	{
		Board b1 = new Board(board1);
		Board b2 = new Board(board2);
		
		
		for(int i = midpoint; i < size; i++)
		{
			Queen q = b1.getQueens()[i].clone();
			//set if queen is not equal to any other in b2
			for(int j = i; j >= 0; j--)
			{
				if(b2.getQueens()[j].equals(q))
					break;
				if(j == 0)
					b2.setQueen(i, q);
			}
		}
		return b2;
	}
	
	//getters and setters
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSolveCost() {
		return solveCost;
	}
	public Board[] getPopulation() {
		return population;
	}
	public void setPopulation(Board[] population) {
		this.population = population;
	}
	public int getmFactor() {
		return mFactor;
	}
	public void setmFactor(int mFactor) {
		this.mFactor = mFactor;
	}
	public long getRunTime() {
		return runTime;
	}

	
}
