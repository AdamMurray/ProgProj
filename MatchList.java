
/**
 * Defines an object representing an array
 * of Match objects
 * @author Adam John Campbell Murray
 *
 */
public class MatchList
{
	/* Instance variables */
	private Match [] matchArray; // array of all matches
	private int elementsInArray; // number of matches allocated already
	
	/* Class constants */
	/** The maximum number of matches the program allows */
	private final int MAX_MATCHES = 52; // only one match per week
	
	/**
	 * Default constructor for the MatchList class.
	 */
	public MatchList()
	{
		// initialise number of array elements to zero
		elementsInArray = 0;
		
		// create array of matches, initialise each entry to null
		matchArray = new Match[MAX_MATCHES];
		for (int i = 0; i < MAX_MATCHES; i++)
			matchArray[i] = null;
	}
	
	/**
	 * Adds a match to the match array at the next available
	 * position.
	 * @param m - the Match object being added
	 */
	public void addMatchToList(Match m)
	{
		// adds new match in order of allocation
		// instead of week (for output to file)
		matchArray[elementsInArray] = m;
		elementsInArray++;
	}

	/*
	 * Getters and Setters
	 */
	public Match[] getMatchArray()
	{
		return matchArray;
	}
	
	public Match getMatchArrayAtX(int x)
	{
		return matchArray[x];
	}

	public int getElementsInArray()
	{
		return elementsInArray;
	}
}
