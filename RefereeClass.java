import java.util.*;

/**
 * Defines an object representing a single referee
 * @author Adam John Campbell Murray
 *
 */
public class RefereeClass implements Comparable<RefereeClass>
{
	/* Instance variables */
	private String refereeID; // consists of initials and a sequence number
	private String refereeName; // consists of first and last name (assumed unique)
	private String qualification; // e.g. NJB2, IJB1 (highest number is 4)
	private int allocatedMatches; // matches allocated to a referee
	private String homeLocality; // where the referee lives
	private String visitingLocalities; // where the referee is prepared to travel to
	private static String whichOrder; // defines the ordering of RefereeClass objects

	/**
	 * Constructor that takes an input string from which
	 * instance variables are set.
	 * @param inputString - String containing instance variable information
	 */
	public RefereeClass(String inputString)
	{
		// create Scanner for input string
		Scanner stringScan = new Scanner(inputString);

		// check that there are still tokens to be checked
		while(stringScan.hasNext())
		{
			// initialise instance variables
			refereeID = stringScan.next();
			refereeName = stringScan.next() + " " + stringScan.next();
			qualification = stringScan.next();
			allocatedMatches = stringScan.nextInt();
			homeLocality = stringScan.next();
			visitingLocalities = stringScan.next();
		}

		// close Scanner for input string
		stringScan.close();
	}

	/**
	 * Constructor that takes the referee's ID, name,
	 * qualification, allocated matches, home locality,
	 * and visiting localities as parameters.
	 * @param rID - referee's ID
	 * @param rName - referee's name
	 * @param qual - referee's qualification
	 * @param matches - number of matches allocated to referee
	 * @param hl - referee's home locality
	 * @param vl - referee's visiting localities
	 */
	public RefereeClass(String rID, String rName, String qual,
			int matches, String hl, String vl)
	{
		// initialise instance variables
		refereeID = rID;
		refereeName = rName;
		qualification = qual;
		allocatedMatches = matches;
		homeLocality = hl;
		visitingLocalities = vl;
	}

	/**
	 * Returns a string formatted for output in the main GUI text area.
	 * @return guiReportString
	 */
	public String guiReportString()
	{
		// formatted string containing information on
		// all instance variables
		String guiReportString = String.format("%-5s%-20s%-6s%-4d%-10s%-4s", refereeID,
				refereeName, qualification, allocatedMatches, homeLocality, visitingLocalities);

		return guiReportString;
	}

	/**
	 * Returns a string formatted for output in the RefereeDisplayFrame.
	 * @return individualReportString
	 */
	public String individualReportString()
	{
		// formatted string containing information on
		// all instance variables
		String individualReportString = String.format("Referee ID: %s%nReferee Name: %s%n" +
				"Qualification: %s%nAllocated Matches: %d%nHome Locality: %s%n" +
				"Visiting Localities: %s%n", refereeID, refereeName, qualification,
				allocatedMatches,homeLocality, visitingLocalities);

		return individualReportString;
	}

	/**
	 * Returns a string formatted for output in the MatchFrame.
	 * @return matchAllocationsReportString
	 */
	public String matchAllocationsReportString()
	{
		// formatted string containing information on
		// referee name and number of allocated matches
		String matchAllocationsReportString = String.format("%-20s%d", refereeName, allocatedMatches);

		return matchAllocationsReportString;
	}

	/**
	 * Defines a comparison between RefereeClass objects.
	 */
	public int compareTo(RefereeClass other)
	{
		int returnValue = 0;

		if (whichOrder.equals("ID"))
		{
			if (this.getRefereeID().charAt(0) < other.getRefereeID().charAt(0)) // i.e. 'A' and 'K'
				returnValue = -1;
			else if (this.getRefereeID().charAt(0) == other.getRefereeID().charAt(0)) // i.e. 'A' and 'A'
			{
				if (this.getRefereeID().charAt(1) < other.getRefereeID().charAt(1)) // i.e. 'AB' and 'AM'
					returnValue = -1;
				else if (this.getRefereeID().charAt(1) > other.getRefereeID().charAt(1)) // i.e 'AM' and 'AB'
					returnValue = 1;
				else if (this.getRefereeID().charAt(1) == other.getRefereeID().charAt(1)) // i.e. 'AM' and 'AM'
				{
					if (this.getRefereeID().charAt(2) < other.getRefereeID().charAt(2)) // i.e. 'AM1' and 'AM2'
						returnValue = -1;
					else  // i.e. 'AM2' and 'AM1'
						returnValue = 1;
				}
			}
			else
				returnValue = 1;
		}
		else if (whichOrder.equals("matches"))
		{
			if (this.getAllocatedMatches() < other.getAllocatedMatches())
				returnValue = -1;
			else if (this.getAllocatedMatches() == other.getAllocatedMatches())
				returnValue = 0;
			else
				returnValue = 1;
		}

		return returnValue;
	}

	/*
	 * Getters and setters
	 */
	public String getRefereeID()
	{
		return refereeID;
	}

	public String getRefereeName()
	{
		return refereeName;
	}

	public String getQualification()
	{
		return qualification;
	}

	public int getAllocatedMatches()
	{
		return allocatedMatches;
	}

	public String getHomeLocality()
	{
		return homeLocality;
	}

	public String getVisitingLocalities()
	{
		return visitingLocalities;
	}

	public void setQualification(String qualification)
	{
		this.qualification = qualification;
	}

	public void setHomeLocality(String homeLocality)
	{
		this.homeLocality = homeLocality;
	}

	public void setVisitingLocalities(String visitingLocalities)
	{
		this.visitingLocalities = visitingLocalities;
	}

	public static String getWhichOrder()
	{
		return whichOrder;
	}

	public static void setWhichOrder(String whichOrder)
	{
		RefereeClass.whichOrder = whichOrder;
	}

	public void setAllocatedMatches(int allocatedMatches)
	{
		this.allocatedMatches = allocatedMatches;
	}
}
