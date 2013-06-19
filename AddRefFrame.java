import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class to define a window in which to add a new referee
 * to the referee list
 * @author Adam John Campbell Murray
 *
 */
@SuppressWarnings("serial")
public class AddRefFrame extends JFrame implements ActionListener
{
	/* Instance variables */
	// GUI instance variables
	private JPanel north, south;
	private JTextField firstNameField, lastNameField;
	private JButton addRefButton;
	private JRadioButton njbButton, ijbButton;
	private JRadioButton oneButton, twoButton, threeButton, fourButton;
	private JRadioButton northButton, centralButton, southButton;
	private JCheckBox northCheckBox, centralCheckBox, southCheckBox;
	// Object instance variables
	private RefereeProgram refereeProgram;
	private RefereeGUI refereeGUI;

	/* Class constants */
	// GUI constants
	/** The width of the frame */
	private final int GUI_WIDTH = 250;
	
	/** The height of the frame */
	private final int GUI_HEIGHT = 350;
	
	/** The on-screen horizontal position of the frame */
	private final int GUI_X_POSITION = 400;
	
	/** The on-screen vertical position of the frame */
	private final int GUI_Y_POSITION = 200;

	/**
	 * Constructor for AddRefFrame.
	 * @param rProg - RefereeProgram class to be used
	 * @param rGUI - the main GUI
	 */
	public AddRefFrame(RefereeProgram rProg, RefereeGUI rGUI)
	{
		// initialise instance variabless
		refereeProgram = rProg;
		refereeGUI = rGUI;

		// set default properties of JFrame
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Add New Referee");
		setSize(GUI_WIDTH, GUI_HEIGHT);
		setLocation(GUI_X_POSITION, GUI_Y_POSITION);

		// layout GUI components
		layoutNorth();
		layoutSouth();
	}

	/**
	 * Adds GUI components to the NORTH section of the JFrame.
	 */
	public void layoutNorth()
	{
		// create north JPanel, set layout and border
		north = new JPanel();
		north.setLayout(new GridLayout(2, 2));
		north.setBorder(new TitledBorder(new EtchedBorder(), "New Referee's Name"));
		north.setBackground(Color.LIGHT_GRAY);

		// create label for first name text field, add to north
		JLabel firstNameLabel = new JLabel("Enter first name:");
		north.add(firstNameLabel);

		// create text field for first name, add to north
		firstNameField = new JTextField();
		north.add(firstNameField);

		// create label for last name text field, add to north
		JLabel lastNameLabel = new JLabel("Enter last name:");
		north.add(lastNameLabel);

		// create text field for last name, add to north
		lastNameField = new JTextField();
		north.add(lastNameField);

		// add north to JFrame
		add(north, BorderLayout.NORTH);
	}

	/**
	 * Adds GUI components to the SOUTH section of the JFrame.
	 */
	public void layoutSouth()
	{
		// create south JPanel
		south = new JPanel(new BorderLayout());

		// create topSouth JPanel
		JPanel topSouth = new JPanel();
		topSouth.setLayout(new GridLayout(2, 1));
		topSouth.setBorder(new TitledBorder(new EtchedBorder(), "Qualification"));
		topSouth.setBackground(Color.LIGHT_GRAY);
		
		// add topSouth to south
		south.add(topSouth, BorderLayout.SOUTH);

		// create radio buttons for qualification title
		njbButton = new JRadioButton("NJB");
		njbButton.setBackground(Color.LIGHT_GRAY);
		ijbButton = new JRadioButton("IJB");
		ijbButton.setBackground(Color.LIGHT_GRAY);
		
		// set the initially selected radio button
		njbButton.setSelected(true);

		// create button group for qualification title buttons
		// and add buttons to group
		ButtonGroup jbGroup = new ButtonGroup();
		jbGroup.add(njbButton);
		jbGroup.add(ijbButton);

		// create new JPanel to be added to the upper part of
		// topSouth, and add buttons to panel
		JPanel upperTopSouth = new JPanel();
		upperTopSouth.setBackground(Color.LIGHT_GRAY);
		upperTopSouth.add(njbButton);
		upperTopSouth.add(ijbButton);
		
		// add panel to topSouth
		topSouth.add(upperTopSouth);

		// create radio buttons for qualification level
		oneButton = new JRadioButton("1");
		oneButton.setBackground(Color.LIGHT_GRAY);
		twoButton = new JRadioButton("2");
		twoButton.setBackground(Color.LIGHT_GRAY);
		threeButton = new JRadioButton("3");
		threeButton.setBackground(Color.LIGHT_GRAY);
		fourButton = new JRadioButton("4");
		fourButton.setBackground(Color.LIGHT_GRAY);
		
		// set initially selected radio button
		oneButton.setSelected(true);

		// create button group for qualification level buttons
		// and add to buttons to button group
		ButtonGroup numbersGroup = new ButtonGroup();
		numbersGroup.add(oneButton);
		numbersGroup.add(twoButton);
		numbersGroup.add(threeButton);
		numbersGroup.add(fourButton);

		// create new JPanel to add to the lower part of
		// topSouth, and add buttons to panel
		JPanel lowerTopSouth = new JPanel();
		lowerTopSouth.setBackground(Color.LIGHT_GRAY);
		lowerTopSouth.add(oneButton);
		lowerTopSouth.add(twoButton);
		lowerTopSouth.add(threeButton);
		lowerTopSouth.add(fourButton);
		
		// add panel to topSouth
		topSouth.add(lowerTopSouth);

		// add topSouth to the NORTH section of south
		south.add(topSouth, BorderLayout.NORTH);

		// create centerSouth JPanel, add components to it
		// and set border
		JPanel centerSouth = new JPanel();
		centerSouth.setBorder(new TitledBorder(new EtchedBorder(), "Home Locality"));
		centerSouth.setBackground(Color.LIGHT_GRAY);

		// create radio buttons for the home locality
		northButton = new JRadioButton("North");
		northButton.setBackground(Color.LIGHT_GRAY);
		centralButton = new JRadioButton("Central");
		centralButton.setBackground(Color.LIGHT_GRAY);
		southButton = new JRadioButton("South");
		southButton.setBackground(Color.LIGHT_GRAY);
		
		// set initially selected radio button
		northButton.setSelected(true);

		// create button group for the home locality buttons
		// and add buttons to group
		ButtonGroup homeLocalityGroup = new ButtonGroup();
		homeLocalityGroup.add(northButton);
		homeLocalityGroup.add(centralButton);
		homeLocalityGroup.add(southButton);

		// add buttons to centerSouth panel
		centerSouth.add(northButton);
		centerSouth.add(centralButton);
		centerSouth.add(southButton);

		// add centerSouth panel to the CENTER part of south
		south.add(centerSouth, BorderLayout.CENTER);

		// create bottomSouth JPanel and add components to it
		JPanel bottomSouth = new JPanel();
		bottomSouth.setLayout(new GridLayout(2, 1));
		bottomSouth.setBackground(Color.LIGHT_GRAY);

		// create check boxes for visiting localities
		northCheckBox = new JCheckBox("North");
		northCheckBox.setBackground(Color.LIGHT_GRAY);
		centralCheckBox = new JCheckBox("Central");
		centralCheckBox.setBackground(Color.LIGHT_GRAY);
		southCheckBox = new JCheckBox("South");
		southCheckBox.setBackground(Color.LIGHT_GRAY);

		// create new panel, set border, add check boxes
		JPanel upperBottomSouth = new JPanel();
		upperBottomSouth.setBorder(new TitledBorder(new EtchedBorder(), "Visiting Localities"));
		upperBottomSouth.setBackground(Color.LIGHT_GRAY);
		upperBottomSouth.add(northCheckBox);
		upperBottomSouth.add(centralCheckBox);
		upperBottomSouth.add(southCheckBox);
		
		// add panel to bottomSouth
		bottomSouth.add(upperBottomSouth);

		// create add referee button, add action listener
		addRefButton = new JButton("Add Referee");
		addRefButton.addActionListener(this);

		// create new panel to hold add referee button
		JPanel lowerBottomSouth = new JPanel();
		lowerBottomSouth.setBorder(new TitledBorder(new EtchedBorder(), "Finish Adding Referee"));
		lowerBottomSouth.setBackground(Color.LIGHT_GRAY);
		lowerBottomSouth.add(addRefButton);
		
		// add panel to bottomSouth
		bottomSouth.add(lowerBottomSouth);

		// add bottomSouth to SOUTH part of south
		south.add(bottomSouth, BorderLayout.SOUTH);

		// add south JPanel to JFrame
		add(south, BorderLayout.SOUTH);
	}

	/**
	 * Adds referee to the referee list, updates the main display,
	 * and handles any input errors on the part of the user.
	 */
	public void addReferee()
	{
		// declare variables for the new referee's first, last,
		// and full (first + " " + last) names
		String newRefFirstName = "";
		String newRefLastName = "";
		String newRefFullName = "";

		try
		{
			// if either of the first/last name text fields are empty throw an exception
			if (firstNameField.getText().equals("") || lastNameField.getText().equals(""))
				throw new IllegalArgumentException();
			// otherwise, create new RefereeClass object
			else
			{
				// get the new referee's first and last name from text fields;
				// if either of the names begin with a lower case letter
				// then this it corrected to upper case
				newRefFirstName = firstNameField.getText().trim();
				if (newRefFirstName.charAt(0) < 'A' || newRefFirstName.charAt(0) > 'Z')
				{
					newRefFirstName = newRefFirstName.substring(0, 1).toUpperCase()
							+ newRefFirstName.substring(1);
				}
				
				newRefLastName = lastNameField.getText().trim();
				if (newRefLastName.charAt(0) < 'A' || newRefLastName.charAt(0) > 'Z')
				{
					newRefLastName = newRefLastName.substring(0, 1).toUpperCase()
							+ newRefLastName.substring(1);
				}
				
				// instantiate the new referee's full name by the
				// concatenation of the first and last names, with
				// a space between them
				newRefFullName = newRefFirstName + " " + newRefLastName;

				// instantiate the number part of the new referee's ID
				int newRefIDNumber = 1;

				// search through refereeProgram for any referee's with the
				// same initials as the new referee. For each match found,
				// increment the ID number of the new referee by 1
				for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
				{
					RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);

					if (refAtX.getRefereeID().charAt(0) == newRefFirstName.charAt(0)
							&& refAtX.getRefereeID().charAt(1) == newRefLastName.charAt(0))
					{
						newRefIDNumber++;
					}
				}

				// create the ID for the new referee from initials and ID number
				String newRefID = String.format("%c%c%d", newRefFirstName.toUpperCase().charAt(0),
						newRefLastName.toUpperCase().charAt(0), newRefIDNumber);

				// declare new referee's qualification
				String newRefQualification = "";

				// instantiate the new referee's qualification
				// from the selected radio buttons
				if (njbButton.isSelected())
				{
					if (oneButton.isSelected())
						newRefQualification = "NJB1";
					else if (twoButton.isSelected())
						newRefQualification = "NJB2";
					else if (threeButton.isSelected())
						newRefQualification = "NJB3";
					else if (fourButton.isSelected())
						newRefQualification = "NJB4";
				}

				if (ijbButton.isSelected())
				{
					if (oneButton.isSelected())
						newRefQualification = "IJB1";
					else if (twoButton.isSelected())
						newRefQualification = "IJB2";
					else if (threeButton.isSelected())
						newRefQualification = "IJB3";
					else if (fourButton.isSelected())
						newRefQualification = "IJB4";
				}

				// declare new referee's allocated matches
				int newRefAllocatedMatches = 0;

				// declare new referee's home locality
				String newRefHomeLocality = "";

				// instantiate the new referee's home
				// locality from the selected radio buttons
				if (northButton.isSelected())
					newRefHomeLocality = "North";
				else if (centralButton.isSelected())
					newRefHomeLocality = "Central";
				else if (southButton.isSelected())
					newRefHomeLocality = "South";

				// declare new referee's visiting localities
				String newRefVisitingLocalities = "";

				// instantiate the new referee's visiting
				// localities from the selected radio buttons
				if (northCheckBox.isSelected() || northButton.isSelected())
					newRefVisitingLocalities += "Y";
				else
					newRefVisitingLocalities += "N";

				if (centralCheckBox.isSelected() || centralButton.isSelected())
					newRefVisitingLocalities += "Y";
				else
					newRefVisitingLocalities += "N";

				if (southCheckBox.isSelected() || southButton.isSelected())
					newRefVisitingLocalities += "Y";
				else
					newRefVisitingLocalities += "N";

				// create new RefereeClass object from input data
				RefereeClass newRefRefereeClass = new RefereeClass(newRefID, newRefFullName, newRefQualification,
						newRefAllocatedMatches, newRefHomeLocality, newRefVisitingLocalities);

				// add new RefereeClass object to refereeProgram's RefereeClass list
				refereeProgram.addRefereeClassToList(newRefRefereeClass);

				// updates the display in the main GUI
				refereeGUI.updateRefDisplay();
				refereeGUI.updateComboBox();

				// dispose the add referee frame
				this.dispose();
			}
		}
		catch (IllegalArgumentException iax)
		{
			JOptionPane.showMessageDialog(this, "Name fields must not be blank", 
					"Input error", JOptionPane.ERROR_MESSAGE);
		}
		catch (StringIndexOutOfBoundsException six)
		{
			JOptionPane.showMessageDialog(this, "Can't add any more items to list", 
					"Error", JOptionPane.ERROR_MESSAGE);
			this.dispose();
		}
	}

	/**
	 * Handles button presses, etc
	 * @param e - the action event being processed
	 */
	public void actionPerformed(ActionEvent e)
	{
		// handles the add referee button being pressed
		if (e.getSource() == addRefButton)
			addReferee();
	}

}
