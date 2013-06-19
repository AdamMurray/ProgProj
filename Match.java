
/**
 * Defines an object representing a single match
 * @author Adam John Campbell Murray
 *
 */
public class Match
{
	/* Instance variables */
	private int matchWeekNumber; // week (1 - 52) in which the match is held
	private String matchArea; // the area in which the match to take place
	private String matchLevel; // the level of the match; 'junior' or 'senior'	
	private String assignedReferee1;
	private String assignedReferee2;
	
	/**
	 * Constructor for the Match object.
	 */
	public Match(int week, String area, String level, String ref1, String ref2)
	{
		// instantiate instance variables
		matchWeekNumber = week;
		matchArea = area;
		matchLevel = level;
		assignedReferee1 = ref1;
		assignedReferee2 = ref2;
	}
	
	/*
	 * Getters and Setters
	 */
	public int getMatchWeekNumber()
	{
		return matchWeekNumber;
	}

	public String getMatchArea()
	{
		return matchArea;
	}

	public String getMatchLevel()
	{
		return matchLevel;
	}

	public String getAssignedReferee1()
	{
		return assignedReferee1;
	}

	public String getAssignedReferee2()
	{
		return assignedReferee2;
	}
}
