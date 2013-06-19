import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

/**
 * Defines a frame for display/updating
 * of match information
 * @author Adam John Campbell Murray 
 *
 */
@SuppressWarnings("serial")
public class MatchFrame extends JFrame implements ActionListener
{
	/* Instance variables */
	// GUI instance variables
	private JPanel north, center, south;
	private JComboBox<Integer> weekNumberComboBox;
	private JRadioButton northRadioButton, centralRadioButton, southRadioButton;
	private JRadioButton juniorRadioButton, seniorRadioButton;
	private JButton assignRefereesButton;
	private JTextArea assignedRefereesTextArea;
	// Object instance variables
	private Match match; // match being created each time
	private MatchList matchList; // match list being added to
	private RefereeGUI refereeGUI; // needs to be updated with match allocation info
	private RefereeProgram refereeProgram;
	private RefereeClass allocatedReferee1; // first referee allocated to match
	private RefereeClass allocatedReferee2; // second referee allocated to match

	private String mainArea; // area match being held in
	private String otherArea1; // adjacent area to match area
	private String otherArea2; // other adjacent or non adjacent area
	private String assignmentLevel;

	private RefereeClass [] arrayFirstOrdering; // RefereeClass array after first ordering
	private RefereeClass [] arraySecondOrdering; // RefereeClass array after second ordering

	/* Class constants */
	/** The width of the frame */
	private final int GUI_WIDTH = 320;

	/** The height of the frame */
	private final int GUI_HEIGHT = 460;

	/** The on-screen horizontal position of the frame */
	private final int GUI_X_POSITION = 400;

	/** The on-screen vertical position of the frame */
	private final int GUI_Y_POSITION = 50;

	// Text area constants
	/** The font size of the text area */
	private final int TEXT_AREA_FONT_SIZE = 12;

	/** The number of rows in the text area */
	private final int MAIN_TEXT_AREA_ROWS = 9;

	/** The number of columns in the text area */
	private final int MAIN_TEXT_AREA_COLS = 40;

	/**
	 * Constructor for MatchFrame which takes a RefereeProgram object,
	 * a RefereeGUI object, and a MatchList object as parameters
	 * @param rProg - the RefereeProgram object being used
	 * @param rGUI - the main GUI to be updated
	 * @param mList - the MatchList object being used
	 */
	public MatchFrame(RefereeProgram rProg, RefereeGUI rGUI, MatchList mList)
	{
		// initialise instance variables
		refereeProgram = rProg;
		refereeGUI = rGUI;
		matchList = mList;

		// set default frame properties
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Referee Allocation");
		setSize(GUI_WIDTH, GUI_HEIGHT);
		setLocation(GUI_X_POSITION, GUI_Y_POSITION);

		// layout GUI components
		layoutNorth();
		layoutCenter();
		layoutSouth();
		updateMatchAllocationDisplay();
	}

	/**
	 * Adds the components to the GUI which
	 * are in the NORTH area of the JFrame.
	 */
	public void layoutNorth()
	{
		// create north, set properties
		north = new JPanel();
		north.setLayout(new GridLayout(1, 2));
		north.setBorder(new TitledBorder(new EtchedBorder(), "Choose match week"));
		north.setBackground(Color.LIGHT_GRAY);

		// create label for combo box, add to north
		JLabel weekNumberComboBoxLabel = new JLabel("Select week number:");
		north.add(weekNumberComboBoxLabel);

		// create combo box for match weeks
		weekNumberComboBox = new JComboBox<Integer>();
		// populate it with week numbers
		for (int i = 1; i < 53; i++)
			weekNumberComboBox.addItem(i);
		// add combo box to north
		north.add(weekNumberComboBox);

		// add north to the frame
		add(north, BorderLayout.NORTH);
	}

	/**
	 * Adds the components to the GUI which
	 * are in the CENTER area of the JFrame.
	 */
	public void layoutCenter()
	{
		// create center panel, set layout, set background colour
		center = new JPanel();
		center.setLayout(new GridLayout(3, 1));
		center.setBackground(Color.LIGHT_GRAY);

		// create topCenter panel, set border, set background colour
		JPanel topCenter = new JPanel();
		topCenter.setBorder(new TitledBorder(new EtchedBorder(), "Select Area"));
		topCenter.setBackground(Color.LIGHT_GRAY);

		// create radio buttons for selecting match area
		// and set button background colours
		northRadioButton = new JRadioButton("North");
		northRadioButton.setBackground(Color.LIGHT_GRAY);
		centralRadioButton = new JRadioButton("Central");
		centralRadioButton.setBackground(Color.LIGHT_GRAY);
		southRadioButton = new JRadioButton("South");
		southRadioButton.setBackground(Color.LIGHT_GRAY);

		// set initially selected radio button
		northRadioButton.setSelected(true);

		// create button groups and add buttons to it
		ButtonGroup areaGroup = new ButtonGroup();
		areaGroup.add(northRadioButton);
		areaGroup.add(centralRadioButton);
		areaGroup.add(southRadioButton);

		// add buttons to topCenter panel
		topCenter.add(northRadioButton);
		topCenter.add(centralRadioButton);
		topCenter.add(southRadioButton);

		// add topCenter panel to center panel
		center.add(topCenter);

		// create midCenter panal, set border, set background colour
		JPanel midCenter = new JPanel();
		midCenter.setBorder(new TitledBorder(new EtchedBorder(), "Select Level"));
		midCenter.setBackground(Color.LIGHT_GRAY);

		// create radio buttons for selecting match level
		// and set button background colours
		juniorRadioButton = new JRadioButton("Junior");
		juniorRadioButton.setBackground(Color.LIGHT_GRAY);
		seniorRadioButton = new JRadioButton("Senior");
		seniorRadioButton.setBackground(Color.LIGHT_GRAY);

		// set initially selected radio button
		juniorRadioButton.setSelected(true);

		// create button group to hold level buttons
		ButtonGroup levelGroup = new ButtonGroup();
		levelGroup.add(juniorRadioButton);
		levelGroup.add(seniorRadioButton);

		// add level buttons to midCenter panel
		midCenter.add(juniorRadioButton);
		midCenter.add(seniorRadioButton);

		// add midCenter to center panel
		center.add(midCenter);

		// create bottomCenter panel, set border, set background
		JPanel bottomCenter = new JPanel();
		bottomCenter.setBorder(new TitledBorder(new EtchedBorder(), "Assign Referees"));
		bottomCenter.setBackground(Color.LIGHT_GRAY);

		// create button initiate referee assignment,
		// and add actionListener
		assignRefereesButton = new JButton("Assign Referees");
		assignRefereesButton.addActionListener(this);

		// add button to bottomCenter
		bottomCenter.add(assignRefereesButton);

		// add bottomCenter to center panel
		center.add(bottomCenter);

		// add center panel to the CENTER of the JFrame
		add(center, BorderLayout.CENTER);
	}

	/**
	 * Adds the components to the GUI which
	 * are in the CENTER area of the JFrame.
	 */
	public void layoutSouth()
	{
		// create south panel, set border, set background colour
		south = new JPanel();
		south.setBorder(new TitledBorder(new EtchedBorder(), "Referee Suitability List"));
		south.setBackground(Color.LIGHT_GRAY);

		// create text area, set border, set to non-editable,
		// set font, set foreground and background colours
		assignedRefereesTextArea = new JTextArea(MAIN_TEXT_AREA_ROWS, MAIN_TEXT_AREA_COLS);
		assignedRefereesTextArea.setBorder(new EtchedBorder());
		assignedRefereesTextArea.setEditable(false);
		assignedRefereesTextArea.setFont(new Font("Courier", Font.PLAIN, TEXT_AREA_FONT_SIZE));
		assignedRefereesTextArea.setForeground(Color.WHITE);
		assignedRefereesTextArea.setBackground(Color.BLUE);

		// add text area to south panel
		south.add(assignedRefereesTextArea);

		// add south panel to the SOUTH of the JFrame
		add(south, BorderLayout.SOUTH);
	}

	/**
	 * Updates the text area of the frame with
	 * a list of referees and their number of
	 * allocated matches in order of suitability
	 * of refereeing the match being allocated.
	 */
	public void updateMatchAllocationDisplay()
	{
		// clear the text area
		assignedRefereesTextArea.setText("");

		// create and add headings to the text area
		String headings = String.format("%-20s%-20s", "Referee Name", "Allocated Matches");
		assignedRefereesTextArea.append(headings + "\n");

		// create and add a break line to the text area
		String breakLine = "";
		for (int i = 0; i < 40; i++)
			breakLine += "*";

		assignedRefereesTextArea.append(breakLine + "\n");
	}

	/**
	 * Handles the assignment of two referees to a certain match.
	 * If no two suitable referees are available an error is displayed
	 * to the user. If two suitable referees are found, their names
	 * are displayed to the user.
	 */
	public void assignReferees()
	{
		try
		{
			// get the week from combo box, then parse to an integer
			int assignmentWeek = (Integer) weekNumberComboBox.getSelectedItem();

			// get the match area from selected radio button
			if (northRadioButton.isSelected())
			{
				mainArea = "North";
				otherArea1 = "Central";
				otherArea2 = "South";
			}
			else if (centralRadioButton.isSelected())
			{
				mainArea = "Central";
				otherArea1 = "North";
				otherArea2 = "South";
			}
			else if (southRadioButton.isSelected())
			{
				mainArea = "South";
				otherArea1 = "Central";
				otherArea2 = "North";
			}

			// get the match level from selected radio button
			if (juniorRadioButton.isSelected())
				assignmentLevel = "Junior";
			else
				assignmentLevel = "Senior";

			// order the arrays
			firstArrayOrdering();
			secondArrayOrdering();

			// reset allocated referees to null (so checks can be carried out)
			allocatedReferee1 = null;
			allocatedReferee2 = null;

			finalCheck();

			// update the match allocations of the allocated referees
			allocatedReferee1.setAllocatedMatches(allocatedReferee1.getAllocatedMatches() + 1);
			allocatedReferee2.setAllocatedMatches(allocatedReferee2.getAllocatedMatches() + 1);

			// create match object
			match = new Match(assignmentWeek, mainArea, assignmentLevel,
					allocatedReferee1.getRefereeName(), allocatedReferee2.getRefereeName());

			// update the text area
			updateMatchAllocationDisplay();

			// list of referees in order of suitability added to text area
			for (int x = 0; x < arraySecondOrdering.length; x++)
			{
				assignedRefereesTextArea.append(
						arraySecondOrdering[x].matchAllocationsReportString() + "\n");
			}

			// display allocated referees to the user
			JOptionPane.showMessageDialog(this, "Allocated Referee 1: " + allocatedReferee1.getRefereeName(), 
					"Match Allocation", JOptionPane.INFORMATION_MESSAGE);

			JOptionPane.showMessageDialog(this, "Allocated Referee 2: " + allocatedReferee2.getRefereeName(), 
					"Match Allocation", JOptionPane.INFORMATION_MESSAGE);

			// add match to match list
			matchList.addMatchToList(match);

			// update main GUI
			refereeGUI.updateRefDisplay();

			// remove selected week from the combo box
			weekNumberComboBox.removeItem(weekNumberComboBox.getSelectedItem());
		}
		catch (IllegalArgumentException iax)
		{
			updateMatchAllocationDisplay();

			JOptionPane.showMessageDialog(this, "No Two Suitable Referees", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void levelCheck()
	{
		boolean found1 = false;
		int i = 0;

		while(!found1 && i < arraySecondOrdering.length)
		{
			if (arraySecondOrdering[i].getQualification().charAt(3) > '1')
			{
				allocatedReferee1 = arraySecondOrdering[i];
				found1 = true;
			}
			i++;
		}

		boolean found2 = false;
		int j = 0;

		while(!found2 && j < arraySecondOrdering.length)
		{
			if (arraySecondOrdering[j] != allocatedReferee1
					&& arraySecondOrdering[j].getQualification().charAt(3) > '1')
			{
				allocatedReferee2 = arraySecondOrdering[j];
				found2 = true;
			}
			j++;
		}

		if (allocatedReferee1 == null || allocatedReferee2 == null)
			throw new IllegalArgumentException();
	}

	public void finalCheck()
	{
		int visLocalIndex = 0;
		if (mainArea.equals("North"))
			visLocalIndex = 0;
		else if (mainArea.equals("Central"))
			visLocalIndex = 1;
		else if (mainArea.equals("South"))
			visLocalIndex = 2;

		char levelIndex = '0';
		if (assignmentLevel.equals("Junior"))
			levelIndex = '0';
		else if (assignmentLevel.equals("Senior"))
			levelIndex = '1';

		boolean found1 = false;
		int i = 0;

		while(!found1 && i < arraySecondOrdering.length)
		{
			if (arraySecondOrdering[i].getVisitingLocalities().charAt(visLocalIndex) == 'Y'
					&& arraySecondOrdering[i].getQualification().charAt(3) > levelIndex)
			{
				allocatedReferee1 = arraySecondOrdering[i];
				found1 = true;
			}
			i++;
		}

		boolean found2 = false;
		int j = 0;

		while(!found2 && j < arraySecondOrdering.length)
		{
			if (arraySecondOrdering[j] != allocatedReferee1
					&& arraySecondOrdering[j].getVisitingLocalities().charAt(visLocalIndex) == 'Y'
					&& arraySecondOrdering[j].getQualification().charAt(3) > levelIndex)
			{
				allocatedReferee2 = arraySecondOrdering[j];
				found2 = true;
			}
			j++;
		}

		if (allocatedReferee1 == null || allocatedReferee2 == null)
			throw new IllegalArgumentException();
	}

	public void firstArrayOrdering()
	{
		// set whichOrder in order to sort the RefereeClass array
		// by match allocations
		RefereeClass.setWhichOrder("matches");
		Arrays.sort(refereeProgram.getrefereeClassArray());

		// create new array
		arrayFirstOrdering = new RefereeClass[refereeProgram.getelementsInArray()];

		// count to hold the amount of array entries already added
		int newArrayElementsAdded = 0;

		// add RefereeClass objects which are in the main area
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
		{
			RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);

			if (refAtX.getHomeLocality().equals(mainArea))
			{
				arrayFirstOrdering[newArrayElementsAdded] = refAtX;
				newArrayElementsAdded++;
			}
		}

		// add RefereeClass objects which are in the adjacent area
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
		{
			RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);

			if (refAtX.getHomeLocality().equals(otherArea1))
			{
				arrayFirstOrdering[newArrayElementsAdded] = refAtX;
				newArrayElementsAdded++;
			}
		}

		// add RefereeClass objects which are in the non-adjacent or
		// second adjacent area
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
		{
			RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);

			if (refAtX.getHomeLocality().equals(otherArea2))
			{
				arrayFirstOrdering[newArrayElementsAdded] = refAtX;
				newArrayElementsAdded++;
			}
		}
	}

	public void secondArrayOrdering()
	{
		// create the new array
		arraySecondOrdering = new RefereeClass[refereeProgram.getelementsInArray()];

		// count to hold the number of array entries already added
		int newArrayElementsAdded = 0;

		int visitingLocalitiesCheck = 0;
		if (mainArea.equals("North"))
			visitingLocalitiesCheck = 0;
		else if (mainArea.equals("Central"))
			visitingLocalitiesCheck = 1;
		else if (mainArea.equals("South"))
			visitingLocalitiesCheck = 2;

		// add elements which have home locality in main area
		for (int x = 0; x < arrayFirstOrdering.length; x++)
		{
			if (arrayFirstOrdering[x].getHomeLocality().equals(mainArea))
			{
				arraySecondOrdering[newArrayElementsAdded] = arrayFirstOrdering[x];
				newArrayElementsAdded++;
			}			
		}

		for (int x = 0; x < arrayFirstOrdering.length; x++)
		{
			if (arrayFirstOrdering[x].getHomeLocality().equals(otherArea1)
					&& arrayFirstOrdering[x].getVisitingLocalities().charAt(visitingLocalitiesCheck) == 'Y')
			{
				arraySecondOrdering[newArrayElementsAdded] = arrayFirstOrdering[x];
				newArrayElementsAdded++;
			}
		}

		for (int x = 0; x < arrayFirstOrdering.length; x++)
		{
			if (arrayFirstOrdering[x].getHomeLocality().equals(otherArea2)
					&& arrayFirstOrdering[x].getVisitingLocalities().charAt(visitingLocalitiesCheck) == 'Y')
			{
				arraySecondOrdering[newArrayElementsAdded] = arrayFirstOrdering[x];
				newArrayElementsAdded++;
			}		
		}

		for (int x = 0; x < arrayFirstOrdering.length; x++)
		{
			if (arrayFirstOrdering[x].getVisitingLocalities().charAt(visitingLocalitiesCheck) == 'N')
			{
				arraySecondOrdering[newArrayElementsAdded] = arrayFirstOrdering[x];
				newArrayElementsAdded++;
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		// handles assign referees button being pressed
		if (e.getSource() == assignRefereesButton)
			assignReferees();
	}
}
