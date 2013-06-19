import java.util.*;

/**
 * Defines an object representing an array of
 * RefereeClass objects
 * @author Adam John Campbell Murray
 *
 */
public class RefereeProgram
{
	/* Instance variables */
	private RefereeClass [] refereeClassArray; // array to hold referee objects
	private int elementsInArray; // number of (non-null) referee objects in array
	
	/* Class constants */
	/** The maximum number of referees the program will accept */
	private final int MAX_REFEREES = 12; // maximum number of referees in file
	
	/**
	 * Default constructor for the RefereeProgram class.
	 */
	public RefereeProgram()
	{
		// initialise the number of elements in the referee class array
		elementsInArray = 0;
		
		// initialise the referee class array with null values
		refereeClassArray = new RefereeClass[MAX_REFEREES];
		for (int i = 0; i < MAX_REFEREES; i++)
			refereeClassArray[i] = null;
	}

	/**
	 * Builds the referee class array from a file ordered
	 * lexicographically by referee ID and numerically by
	 * sequence number.
	 * @param r - RefereeClass object
	 */
	public void refereeClassArrayBuilder(RefereeClass r)
	{
		// create new RefereeClass array with space for one additional element
		RefereeClass[] updatedrefereeClassArray = new RefereeClass[elementsInArray + 1];
		
		// copy the current refereeClassArray into the new array
		System.arraycopy(refereeClassArray, 0, updatedrefereeClassArray, 0, elementsInArray);
		
		// change pointer of refereeClassArray to new array
		refereeClassArray = updatedrefereeClassArray;
		
		// add new RefereeClass object to array
		refereeClassArray[elementsInArray] = r;
		
		// increment number of elements in the array
		elementsInArray++;
	}
	
	/**
	 * Deletes a referee class from the referee class array
	 * and then updates the referee class array, removing the
	 * null value resulting from the deletion.
	 * @param r - the referee class being deleted
	 */
	public void deleteRefereeClassFromList(RefereeClass r)
	{
		// set relevant position in refereeClassArray to null
		refereeClassArray[getPositionInRefereeClassArray(r.getRefereeName())] = null;
		
		// decrement number of elements in the array
		elementsInArray--;
		
		// create a new array of non-null values and
		// change pointer of refereeClassArray to new array
		int arraySize = 0;
		RefereeClass[] refereeClassArrayTemp = new RefereeClass[MAX_REFEREES];

		// add only non-null RefereeClass objects to temp array
		// and increment arraySize
		for (RefereeClass ref : refereeClassArray)
			if (ref != null)
			{
				refereeClassArrayTemp[arraySize] = ref;
				arraySize++;
			}

		// create a new RefereeClass array
		RefereeClass[] updatedrefereeClassArray = new RefereeClass[arraySize];
		
		// copy the temp array into the new array
		System.arraycopy(refereeClassArrayTemp, 0, updatedrefereeClassArray, 0, arraySize);
		
		// set array ordering by referee ID and sort
		RefereeClass.setWhichOrder("ID");
		Arrays.sort(updatedrefereeClassArray);
		
		// change pointer of refereeClassArray to the new array
		refereeClassArray = updatedrefereeClassArray;
	}
	
	/**
	 * Adds a referee class to the referee class array
	 * and updates the ordering of the array.
	 * @param r - the referee class being deleted
	 */
	public void addRefereeClassToList(RefereeClass r)
	{
		// if there are already 12 elements in the array
		// throw an exception
		if (elementsInArray >= MAX_REFEREES)
			throw new StringIndexOutOfBoundsException();
		else // otherwise add RefereeClass to the refereeClassArray
		{
			RefereeClass[] updatedrefereeClassArray = new RefereeClass[elementsInArray + 1];
			System.arraycopy(refereeClassArray, 0, updatedrefereeClassArray, 0, elementsInArray);
			
			refereeClassArray = updatedrefereeClassArray;
			
			refereeClassArray[elementsInArray] = r;
			
			RefereeClass.setWhichOrder("ID");
			Arrays.sort(refereeClassArray);
			
			elementsInArray++;
		}
	}
	
	/**
	 * Gets the position in the referee class array of the
	 * RefereeClass with name equal to the name parameter.
	 * @param name - the name corresponding to a referee class object
	 * @return the position in the referee class array of referee class object
	 */
	public int getPositionInRefereeClassArray(String name)
	{
		int positionInList = 0;
		
		for (int i = 0; i < elementsInArray; i++)
			if (refereeClassArray[i] != null && refereeClassArray[i].getRefereeName().equals(name))
				positionInList = i;
		
		return positionInList;
	}
	
	/*
	 * Getters and setters
	 */
	public RefereeClass[] getrefereeClassArray()
	{
		return refereeClassArray;
	}
	
	public RefereeClass getRefereeClassAtX(int x)
	{
		return refereeClassArray[x];
	}

	public int getelementsInArray()
	{
		return elementsInArray;
	}
}
