public class Demo 
{
	
	public static void main(String[] args)
	{	
		//attempts to solve shInstances instances of 8-queens using steepest hill climbing
		int shInstances = 1000;
		int successes = 0;
		int stepCost = 0;
		long runTime = 0;
		System.out.println("Steepest hill climb:");
		System.out.println("Number of instances: " + shInstances );
		for(int i = 0; i < shInstances; i++)
		{
			SteepestHill sh = new SteepestHill();
			Board b = sh.solve();
			stepCost += sh.getSolveCost();
			runTime += sh.getRunTime();
			if(b.getVal() == 0)
				successes++;
		}
		double successPerc = ((double)successes*100)/(double)shInstances;
		stepCost /= shInstances;
		runTime /= shInstances;
		System.out.println("Average steps taken: " + stepCost );
		System.out.println("Average run time(nanoseconds): " + runTime);
		System.out.println("Percentage of successful runs: " + successPerc +"%");
		
		
		//solves gaInstances instances of 8-queens using genetic algorithm
		int gaInstances = 100;
		stepCost = 0;
		runTime = 0;
		System.out.println("\nGenetic algorithm:");
		System.out.println("Number of instances: " + gaInstances );
		for(int i = 0; i < gaInstances; i++)
		{
			
			Genetic g = new Genetic();
			g.solve();
			stepCost += g.getSolveCost();
			runTime += g.getRunTime();
			
		}
		stepCost /= gaInstances;
		runTime /= gaInstances;
		System.out.println("Average steps taken: " + stepCost );
		System.out.println("Average run time(nanoseconds): " + runTime);
		
		
		//examples
		System.out.println("\nExamples of solutions using Genetic algorithm");
		for(int i = 0; i < 3; i++)
		{
			
			Genetic g = new Genetic();
			System.out.println(g.solve());
			
		}
		Genetic g = new Genetic(16);
		System.out.println(g.solve());
	}
}
