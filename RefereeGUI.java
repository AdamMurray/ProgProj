import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;

/**
 * Defines a GUI for handling user input and displaying information
 * currently contained in RefereesProgram.
 * @author Adam John Campbell Murray
 *
 */
@SuppressWarnings("serial")
public class RefereeGUI extends JFrame implements ActionListener
{
	/* Instance variables */
	// GUI component instance variables
	private JPanel north, center, south;
	private JTextArea refereeDisplay;
	private JLabel refLabel;
	private JComboBox<String> refComboBox;
	private JButton updateRefButton, deleteRefButton,
	addRefButton, barChartButton,
	matchAllocationsButton, saveExitButton;
	// Object instance variables
	private RefereeProgram refereeProgram;
	private RefDisplayFrame refDisplayFrame;
	private AddRefFrame addRefFrame;
	private MatchFrame matchFrame;
	private MatchList matchList;

	/* Class constants */
	// text file constants
	private final String refereesInFile = "RefereesIn.txt";
	private final String refereesOutFile = "RefereesOut.txt";
	private final String matchAllocationsFile = "MatchAllocs.txt";
	// GUI constants
	/** The width of the frame */
	private final int GUI_WIDTH = 520;

	/** The height of the frame */
	private final int GUI_HEIGHT = 700;

	/** The on-screen horizontal position of the frame */
	private final int GUI_X_POSITION = 200;

	/** The on-screen vertical position of the frame */
	private final int GUI_Y_POSITION = 100;

	// text area constants
	/** The font size of the text area */
	private final int TEXT_AREA_FONT = 14;

	/** The number of rows in the text area */
	private final int MAIN_TEXT_AREA_ROWS = 14;

	/** The number of columns in the text area */
	private final int MAIN_TEXT_AREA_COLS = 60;

	/**
	 * Constructor for RefereeGUI class.
	 */
	public RefereeGUI()
	{
		// set close operation, title, size, position on screen,
		// and make the frame non-resizable
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("Referee Assignment");
		setSize(GUI_WIDTH, GUI_HEIGHT);
		setLocation(GUI_X_POSITION, GUI_Y_POSITION);
		setResizable(false);

		// initialise referee class array from RefereesIn.txt
		initReferees();

		// add components to GUI
		layoutNorth();
		layoutCenter();
		layoutSouth();

		// update the referee display
		updateRefDisplay();
	}

	/**
	 * Initialises the array of RefereeClass objects held in
	 * RefereeProgram array using data from RefereesIn.txt.
	 * The array is ordered lexicographically by referee ID.
	 */
	public void initReferees()
	{
		// create file reader and referee program object
		FileReader refereesFileReader = null;
		refereeProgram = new RefereeProgram();

		try
		{
			try
			{
				// initialise file reader using RefereesIn.txt
				refereesFileReader = new FileReader(refereesInFile);
				// create new Scanner using file reader
				Scanner in = new Scanner(refereesFileReader);

				// while there are more lines in the file to check
				while (in.hasNextLine())
				{
					// create RefereeClass for each line in RefereesIn.txt
					RefereeClass refereeClass = new RefereeClass(in.nextLine());
					//update the referee program object
					refereeProgram.refereeClassArrayBuilder(refereeClass);
				}

				// close Scanner
				in.close();
			}
			finally
			{
				// close file reader if not null
				if (refereesFileReader != null) refereesFileReader.close();
			}
		}
		catch (IOException iox)
		{
			JOptionPane.showMessageDialog(this, "File cannot be opened or does not exist", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adds the components to the GUI which
	 * are in the NORTH area of the JFrame.
	 */
	public void layoutNorth()
	{
		// create north JPanel, set border, set background colour
		north = new JPanel();
		north.setBorder(new TitledBorder(new EtchedBorder(), "Referees Overview"));
		north.setBackground(Color.LIGHT_GRAY);

		// create text area, set text area properties, and add to north
		refereeDisplay = new JTextArea(MAIN_TEXT_AREA_ROWS, MAIN_TEXT_AREA_COLS);
		refereeDisplay.setEditable(false);
		refereeDisplay.setFont(new Font("Courier", Font.PLAIN, TEXT_AREA_FONT));
		refereeDisplay.setForeground(Color.WHITE);
		refereeDisplay.setBackground(Color.BLUE);
		refereeDisplay.setBorder(new EtchedBorder());

		north.add(refereeDisplay);

		// add north to JFrame
		add(north, BorderLayout.NORTH);
	}

	/**
	 * Adds the components to the GUI which
	 * are in the CENTER area of the JFrame.
	 */
	public void layoutCenter()
	{
		// create center JPanel, set border, set background colour
		center = new JPanel();
		center.setBorder(new TitledBorder(new EtchedBorder(), "View/Update Referee Details"));
		center.setBackground(Color.LIGHT_GRAY);

		// create containedCenter JPanel, set layout, set background colour
		JPanel containedCenter = new JPanel(new GridLayout(2, 2));
		containedCenter.setBackground(Color.LIGHT_GRAY);

		// add containedCenter to center
		center.add(containedCenter);

		// create JPanel to hold refPanel, set background colour
		JPanel refLabelPanel = new JPanel();
		refLabelPanel.setBackground(Color.LIGHT_GRAY);

		// create refLabel, set foreground colour
		refLabel = new JLabel("Select referee --> ");
		refLabel.setForeground(Color.DARK_GRAY);

		// add refLabel to refLabelPanel
		refLabelPanel.add(refLabel);

		// add refLabelPanel to containedCenter
		containedCenter.add(refLabelPanel);

		// create panel to hold referee name combo box, set background colour
		JPanel refComboBoxPanel = new JPanel();
		refComboBoxPanel.setBackground(Color.LIGHT_GRAY);

		// create referee combo box
		refComboBox = new JComboBox<String>();

		// populate referee combo box with referee names
		updateComboBox();

		// add referee combo box to panel
		refComboBoxPanel.add(refComboBox);

		// add panel to containedCenter
		containedCenter.add(refComboBoxPanel);

		// create panel to hold update referee button, set background colour
		JPanel updateRefButtonPanel = new JPanel();
		updateRefButtonPanel.setBackground(Color.LIGHT_GRAY);

		// create update referee button, add action listener
		updateRefButton = new JButton("Display/Update Details");
		updateRefButton.addActionListener(this);

		// add button to panel
		updateRefButtonPanel.add(updateRefButton);

		// add panel to containedCenter
		containedCenter.add(updateRefButtonPanel);

		// create panel to hold delete referee button, set background colour
		JPanel deleteRefButtonPanel = new JPanel();
		deleteRefButtonPanel.setBackground(Color.LIGHT_GRAY);

		// create button, add action listener
		deleteRefButton = new JButton("Delete Referee");
		deleteRefButton.addActionListener(this);

		// add button to panel
		deleteRefButtonPanel.add(deleteRefButton);

		// add panel to containedCenter
		containedCenter.add(deleteRefButtonPanel);

		// add center to JFrame
		add(center, BorderLayout.CENTER);
	}

	/**
	 * Adds the components to the GUI which
	 * are in the SOUTH area of the JFrame.
	 */
	public void layoutSouth()
	{
		// create south JPanel, set background colour
		south = new JPanel(new GridLayout(4, 1));
		south.setBackground(Color.LIGHT_GRAY);

		// create panel to hold addRefButton, set background,
		// and set border
		JPanel addRefButtonPanel = new JPanel();
		addRefButtonPanel.setBackground(Color.LIGHT_GRAY);
		addRefButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Add New Referee Details"));

		// create addRefButton, add actionListener,
		// and add to addRefButtonPanel
		addRefButton = new JButton("Add new referee");
		addRefButton.addActionListener(this);
		addRefButtonPanel.add(addRefButton);

		// add addRefButtonPanel to south panel
		south.add(addRefButtonPanel);

		// create panel to hold barChartButton,
		// set background colour, set border
		JPanel barChartButtonPanel = new JPanel();
		barChartButtonPanel.setBackground(Color.LIGHT_GRAY);
		barChartButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Allocations Bar Chart"));

		// create barChartButton, add actionListener,
		// and add to barChartButtonPanel
		barChartButton = new JButton("Display Bar Chart");
		barChartButton.addActionListener(this);
		barChartButtonPanel.add(barChartButton);

		// add barChartButtonPanel to south panel
		south.add(barChartButtonPanel);

		// create panel to hold matchAllocationsButton,
		// set background colour, set border
		JPanel matchAllocationsButtonPanel = new JPanel();
		matchAllocationsButtonPanel.setBackground(Color.LIGHT_GRAY);
		matchAllocationsButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Match Allocations"));

		// create matchAllocationsButton, add actionListener,
		// and add to matchAllocationsButtonPanel
		matchAllocationsButton = new JButton("Allocate Matches");
		matchAllocationsButton.addActionListener(this);
		matchAllocationsButtonPanel.add(matchAllocationsButton);

		// add matchAllocationsButtonPanel to south panel
		south.add(matchAllocationsButtonPanel);

		// create panel to hold saveExitButtonPanel,
		// set background colour, set border
		JPanel saveExitButtonPanel = new JPanel();
		saveExitButtonPanel.setBackground(Color.LIGHT_GRAY);
		saveExitButtonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Save & Exit"));

		// create saveExitButton, set foreground colour,
		// add actionListener, and add to saveExitButtonPanel
		saveExitButton = new JButton("Save & Exit");
		saveExitButton.setForeground(Color.RED);
		saveExitButton.addActionListener(this);
		saveExitButtonPanel.add(saveExitButton);

		// add saveExitButtonPanel to south panel
		south.add(saveExitButtonPanel);

		// add south panel to JFrame
		add(south, BorderLayout.SOUTH);
	}

	/**
	 * Handles updating of the referee name combo box when
	 * the program is started, or when a referee is
	 * added/removed from the referee list.
	 */
	public void updateComboBox()
	{
		// first, remove all items previously in combo box
		refComboBox.removeAllItems();

		// then, populate the combo box with the names of referees
		// stored in the RefereeClass list of RefereeProgram
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
			refComboBox.addItem(refereeProgram.getRefereeClassAtX(x).getRefereeName());
	}

	/**
	 * Handles updating of the referee list in the main display.
	 */
	public void updateRefDisplay()
	{
		// first clear the main display
		clearMainDisplay();

		// create headings for the display
		String headings = String.format("%-5s%-20s%-6s%-4s%-10s%-4s",
				"ID", "Name", "Qual.", "AM", "HL", "VL");
		refereeDisplay.append(headings + "\n");

		// create break line between headings and main information
		for (int i = 0; i < MAIN_TEXT_AREA_COLS; i++)
			refereeDisplay.append("*");
		refereeDisplay.append("\n");

		// make sure that main display is always only sorted by ID
		RefereeClass.setWhichOrder("ID");
		Arrays.sort(refereeProgram.getrefereeClassArray());

		// create the list entries for the display
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
			refereeDisplay.append(
					refereeProgram.getRefereeClassAtX(x).guiReportString() + "\n");
	}

	/**
	 * Creates a new window in which to update details of a
	 * referee chosen from the referee name combo box.
	 */
	public void updateRefDetails()
	{
		// get the selected name from combo box and store as a string
		String refNameSearch = (String) refComboBox.getSelectedItem();

		// search through refereeProgram for the refereeClass which
		// matches the name stored from the combo box. When found,
		// create a new window to display and update the referee details.
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
		{
			RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);

			if (refAtX.getRefereeName().equals(refNameSearch))
			{
				refDisplayFrame = new RefDisplayFrame(refAtX, this);
				refDisplayFrame.displayRefDetails();
				refDisplayFrame.setVisible(true);
			}
		}
	}

	/**
	 * Processes deletion of a referee from the referee class list,
	 * updates the display in the main GUI, and deletes the selected
	 * referee's name from the referee name combo box.
	 */
	public void processDeleteReferee()
	{
		// get the selected name from combo box and store as a string
		String refToDelete = (String) refComboBox.getSelectedItem();

		// search through refereeProgram for the refereeClass which
		// matches the name stored from the combo box. When found,
		// call relevant method to delete refereeClass from list
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
		{
			RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);
			if (refAtX.getRefereeName().equals(refToDelete))
				refereeProgram.deleteRefereeClassFromList(refAtX);
		}

		// update the main display
		updateRefDisplay();

		// remove the selected name from the combo box
		refComboBox.removeItem(refToDelete);
	}

	/**
	 * Creates a new window to facilitate the addition of
	 * a new referee to the RefereeClass list of RefereeProgram.
	 */
	public void processAddReferee()
	{
		// create a new window in which to add the new referee
		addRefFrame = new AddRefFrame(refereeProgram, this);
		addRefFrame.setVisible(true);
	}

	/**
	 * Creates a bar chart of referee match allocations
	 * ordered by referee ID.
	 */
	public void createBarChart()
	{
		// create a new JFrame for the bar chart
		JFrame barChartFrame = new JFrame();

		// set the width of the frame
		int barChartFrameWidth = refereeProgram.getelementsInArray() * 75;

		// set frame properties
		barChartFrame.setTitle("Match Allocations Bar Chart");
		barChartFrame.setSize(barChartFrameWidth, 280);
		barChartFrame.setLocation(300, 300);
		barChartFrame.setDefaultCloseOperation(HIDE_ON_CLOSE);
		barChartFrame.setResizable(false);

		// create a new BarChartComponent and add to the frame
		BarChartComponent barChart = new BarChartComponent(refereeProgram);
		barChartFrame.add(barChart);

		// set the frame to visible
		barChartFrame.setVisible(true);
	}

	/**
	 * Creates a new window to facilitate the allocations of
	 * referees to JavaBall matches.
	 */
	public void processRefereeAllocation()
	{
		// instantiate matchList
		matchList = new MatchList();

		// create a new window in which to allocate the match
		matchFrame = new MatchFrame(refereeProgram, this, matchList);
		matchFrame.setVisible(true);
	}

	/**
	 * Handles the closing of the program.
	 */
	public void processSaveAndExit()
	{
		// write to the RefereesOut.txt file
		writeRefereesOutFile();

		// write to the MatchAllocs.txt file
		if (matchList != null)
			writeMatchAllocationsFile(matchList);

		System.exit(0);
	}

	/**
	 * Writes the updated RefereeClass list contained within
	 * RefereeProgram to a text file.
	 */
	public void writeRefereesOutFile()
	{
		// create file writer object
		FileWriter refereesOutWriter = null;

		try
		{
			try
			{
				// instantiate file writer object
				refereesOutWriter = new FileWriter(refereesOutFile);

				// array length for use as iterator limit
				int arrayLength = refereeProgram.getrefereeClassArray().length;
				// loop over fitnessProgramObject
				for (int x = 0; x < arrayLength; x++)
				{
					RefereeClass refereeClassAtX = refereeProgram.getRefereeClassAtX(x);

					// if fitness class at x is not null
					if (refereeClassAtX != null)
					{
						refereesOutWriter.write(String.format("%s %s %s %d %s %s%n",
								refereeClassAtX.getRefereeID(), refereeClassAtX.getRefereeName(),
								refereeClassAtX.getQualification(), refereeClassAtX.getAllocatedMatches(),
								refereeClassAtX.getHomeLocality(), refereeClassAtX.getVisitingLocalities()));
					}
				}
			}
			finally
			{
				// close file writer
				if (refereesOutWriter != null) refereesOutWriter.close();
			}
		}
		catch (IOException iox)
		{
			JOptionPane.showMessageDialog(null, "File could not be created", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void writeMatchAllocationsFile(MatchList mList)
	{
		// create file writer object
		FileWriter matchAllocationsWriter = null;

		try
		{
			try
			{
				// instantiate file writer object
				matchAllocationsWriter = new FileWriter(matchAllocationsFile);

				matchAllocationsWriter.write(String.format("%-10s%-14s%-14s%-30s%n", "Week No.",
						"Match Level", "Match Area", "Assigned Referees"));

				for (int i = 0; i < 68; i++)
					matchAllocationsWriter.write("*");

				matchAllocationsWriter.write("\n");

				// array length for use as iterator limit
				int arrayLength = mList.getElementsInArray();
				System.out.println(arrayLength);
				// loop over fitnessProgramObject
				for (int x = 0; x < arrayLength; x++)
				{
					Match matchAtX = mList.getMatchArrayAtX(x);

					// if fitness class at x is not null
					if (matchAtX != null)
					{
						matchAllocationsWriter.write(String.format("%-10s%-14s%-14s%-15s & %-15s%n",
								matchAtX.getMatchWeekNumber(), matchAtX.getMatchLevel(), matchAtX.getMatchArea(),
								matchAtX.getAssignedReferee1(), matchAtX.getAssignedReferee2()));
					}
				}
			}
			finally
			{
				// close file writer
				if (matchAllocationsWriter != null) matchAllocationsWriter.close();
			}
		}
		catch (IOException iox)
		{
			JOptionPane.showMessageDialog(null, "File could not be created", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Clears the referee list display.
	 */
	public void clearMainDisplay()
	{
		// set the display to the empty string
		refereeDisplay.setText("");
	}

	/**
	 * Handles button presses, etc.
	 * @param e - the action event being handled
	 */
	public void actionPerformed(ActionEvent e)
	{
		// handles pressing the update button
		if (e.getSource() == updateRefButton)
			updateRefDetails();

		// handles pressing the delete button
		if (e.getSource() == deleteRefButton)
			processDeleteReferee();

		// handles pressing the add button
		if (e.getSource() == addRefButton)
			processAddReferee();

		// handles pressing the bar chart button
		if (e.getSource() == barChartButton)
			createBarChart();

		// handles pressing the match allocations button
		if (e.getSource() == matchAllocationsButton)
			processRefereeAllocation();

		// handles pressing the save and exit button
		if (e.getSource() == saveExitButton)
			processSaveAndExit();
	}
}
