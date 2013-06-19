import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Defines a window in which the details of
 * an individual referee are displayed.
 * @author Adam John Campbell Murray
 *
 */
@SuppressWarnings("serial")
public class RefDisplayFrame extends JFrame implements ActionListener
{
	/* Instance variables */
	// GUI component instance variables
	private JTextArea refDetailsArea;
	private JPanel north, center, south;
	private JButton editButton, updateButton;
	private JRadioButton njbButton, ijbButton,
	oneButton, twoButton, threeButton, fourButton,
	northButton, centralButton, southButton;
	private JCheckBox northCheckBox, centralCheckBox, southCheckBox;
	// Object instance variables
	private RefereeClass refereeClass;
	private RefereeGUI refereeGUI;

	/* Class constants */
	// GUI constants
	/** The width of the frame */
	private final int GUI_WIDTH = 240;

	/** The height of the frame */
	private final int GUI_HEIGHT = 250;

	/** The width of the frame after edit button is pressed */
	private final int GUI_WIDTH_AFTER_EDIT = 240;

	/** The height of the frame after edit button is pressed */
	private final int GUI_HEIGHT_AFTER_EDIT = 500;

	/** The on-screen horizontal position of the frame */
	private final int GUI_X_POSITION = 400;

	/** The on-screen vertical position of the frame */
	private final int GUI_Y_POSITION = 50;

	// text area constants
	/** The font size of the text area */
	private final int TEXT_AREA_FONT_SIZE = 12;

	/** The number of rows in the text area */
	private final int MAIN_TEXT_AREA_ROWS = 8;

	/** The number of columns in the text area */
	private final int MAIN_TEXT_AREA_COLS = 24;

	/**
	 * Constructor for RefDisplayFrame.
	 * @param r - the RefereeClass being updated
	 * @param rGUI - the main GUI
	 */
	public RefDisplayFrame(RefereeClass r, RefereeGUI rGUI)
	{
		// initialise instance variables
		refereeClass = r;
		refereeGUI = rGUI;

		// set default properties of JFrame
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Referee Details");
		setSize(GUI_WIDTH, GUI_HEIGHT);
		setLocation(GUI_X_POSITION, GUI_Y_POSITION);

		// layout GUI components
		layoutComponents();
	}

	/**
	 * Displays the chosen referee details in the text area.
	 */
	public void displayRefDetails()
	{
		// clears the text area
		refDetailsArea.setText("");

		// appends the chosen referee details to the text area
		refDetailsArea.append(refereeClass.individualReportString() + "\n");
	}

	/**
	 * Adds GUI components to the JFrame.
	 */
	public void layoutComponents()
	{
		// create north JPanel, set border, set background colour
		north = new JPanel();
		north.setBorder(new EtchedBorder());
		north.setBackground(Color.BLUE);

		// create text area, set text area properties
		refDetailsArea = new JTextArea(MAIN_TEXT_AREA_ROWS, MAIN_TEXT_AREA_COLS);
		refDetailsArea.setForeground(Color.WHITE);
		refDetailsArea.setBackground(Color.BLUE);
		refDetailsArea.setEditable(false);
		refDetailsArea.setFont(new Font("Courier", Font.PLAIN, TEXT_AREA_FONT_SIZE));
		// add text area to north
		north.add(refDetailsArea);

		// add north to JFrame
		add(north, BorderLayout.NORTH);

		// create center JPanel
		center = new JPanel();
		center.setBackground(Color.LIGHT_GRAY);

		// create edit button, add action listener
		editButton = new JButton("Edit Details");
		editButton.addActionListener(this);
		// add edit button to center
		center.add(editButton);

		// add center to JFrame
		add(center, BorderLayout.CENTER);
	}

	/**
	 * Adds new GUI components to the JFrame when the
	 * edit button is pressed.
	 */
	public void processEdit()
	{
		// set new size to accommodate additional components
		setSize(GUI_WIDTH_AFTER_EDIT, GUI_HEIGHT_AFTER_EDIT);

		// create south JPanel
		south = new JPanel(new BorderLayout());
		south.setBackground(Color.LIGHT_GRAY);

		// create topSouth JPanel and add components to it
		JPanel topSouth = new JPanel();
		topSouth.setBackground(Color.LIGHT_GRAY);
		topSouth.setLayout(new GridLayout(2, 1));
		topSouth.setBorder(new TitledBorder(new EtchedBorder(), "Qualification"));

		// create radio buttons for qualification title
		njbButton = new JRadioButton("NJB");
		njbButton.setBackground(Color.LIGHT_GRAY);
		ijbButton = new JRadioButton("IJB");
		ijbButton.setBackground(Color.LIGHT_GRAY);
		// set the initially selected radio button
		// based on the qualification of the selected referee
		if (refereeClass.getQualification().charAt(0) == 'N')
			njbButton.setSelected(true);
		else
			ijbButton.setSelected(true);

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

		// set initially selected radio button based on the
		// qualification level of the selected referee
		if (refereeClass.getQualification().charAt(3) == '1')
			oneButton.setSelected(true);
		else if (refereeClass.getQualification().charAt(3) == '2')
			twoButton.setSelected(true);
		else if (refereeClass.getQualification().charAt(3) == '3')
			threeButton.setSelected(true);
		else if (refereeClass.getQualification().charAt(3) == '4')
			fourButton.setSelected(true);

		// create button group for qualification level buttons
		// and add buttons to button group
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
		centerSouth.setBackground(Color.LIGHT_GRAY);
		centerSouth.setBorder(new TitledBorder(new EtchedBorder(), "Home Locality"));

		// create radio buttons for the home locality
		northButton = new JRadioButton("North");
		northButton.setBackground(Color.LIGHT_GRAY);
		centralButton = new JRadioButton("Central");
		centralButton.setBackground(Color.LIGHT_GRAY);
		southButton = new JRadioButton("South");
		southButton.setBackground(Color.LIGHT_GRAY);

		// set initially selected radio button based
		// on the home locality of the selected referee
		if (refereeClass.getHomeLocality().equals("North"))
			northButton.setSelected(true);
		else if (refereeClass.getHomeLocality().equals("Central"))
			centralButton.setSelected(true);
		else if(refereeClass.getHomeLocality().equals("South"))
			southButton.setSelected(true);

		// create button group for home locality buttons
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
		bottomSouth.setBackground(Color.LIGHT_GRAY);
		bottomSouth.setLayout(new GridLayout(2, 1));

		// create check boxes for visiting localities
		northCheckBox = new JCheckBox("North");
		northCheckBox.setBackground(Color.LIGHT_GRAY);
		centralCheckBox = new JCheckBox("Central");
		centralCheckBox.setBackground(Color.LIGHT_GRAY);
		southCheckBox = new JCheckBox("South");
		southCheckBox.setBackground(Color.LIGHT_GRAY);

		// handles the home locality having to be in the visiting localities
		visitingLocalitiesCheck();

		// create new panel, set border, add check boxes
		JPanel upperBottomSouth = new JPanel();
		upperBottomSouth.setBackground(Color.LIGHT_GRAY);
		upperBottomSouth.setBorder(new TitledBorder(new EtchedBorder(), "Visiting Localities"));
		upperBottomSouth.add(northCheckBox);
		upperBottomSouth.add(centralCheckBox);
		upperBottomSouth.add(southCheckBox);
		// add panel to bottomSouth
		bottomSouth.add(upperBottomSouth);

		// create new panel to hold update button
		JPanel lowerBottomSouth = new JPanel();
		lowerBottomSouth.setBackground(Color.LIGHT_GRAY);
		lowerBottomSouth.setBorder(new TitledBorder(new EtchedBorder(), "Update Details"));

		// create update button, add action listener
		updateButton = new JButton("Update Details");
		updateButton.addActionListener(this);

		lowerBottomSouth.add(updateButton);

		// add panel to bottomSouth
		bottomSouth.add(lowerBottomSouth);

		// add bottomSouth to SOUTH part of south
		south.add(bottomSouth, BorderLayout.SOUTH);

		// add south JPanel to JFrame
		add(south, BorderLayout.SOUTH);

		// disable editButton once pressed
		editButton.setEnabled(false);
	}

	/**
	 * Handles the updating of a referee's details.
	 */
	public void processUpdate()
	{
		// update qualification based on the radio buttons
		// which are selected
		if (njbButton.isSelected())
		{
			if (oneButton.isSelected())
				refereeClass.setQualification("NJB1");
			else if (twoButton.isSelected())
				refereeClass.setQualification("NJB2");
			else if (threeButton.isSelected())
				refereeClass.setQualification("NJB3");
			else if (fourButton.isSelected())
				refereeClass.setQualification("NJB4");
		}
		if (ijbButton.isSelected())
		{
			if (oneButton.isSelected())
				refereeClass.setQualification("IJB1");
			else if (twoButton.isSelected())
				refereeClass.setQualification("IJB2");
			else if (threeButton.isSelected())
				refereeClass.setQualification("IJB3");
			else if (fourButton.isSelected())
				refereeClass.setQualification("IJB4");
		}

		// update home locality based on the radio
		// buttons which are selected
		if (northButton.isSelected())
			refereeClass.setHomeLocality("North");
		else if (centralButton.isSelected())
			refereeClass.setHomeLocality("Central");
		else if (southButton.isSelected())
			refereeClass.setHomeLocality("South");

		// update visiting localities
		String newVisitingLocalities = "";
		if (northCheckBox.isSelected() || northButton.isSelected())
			newVisitingLocalities += "Y";
		else
			newVisitingLocalities += "N";

		if (centralCheckBox.isSelected() || centralButton.isSelected())
			newVisitingLocalities += "Y";
		else
			newVisitingLocalities += "N";

		if (southCheckBox.isSelected() || southButton.isSelected())
			newVisitingLocalities += "Y";
		else
			newVisitingLocalities += "N";

		refereeClass.setVisitingLocalities(newVisitingLocalities);

		// update displays
		visitingLocalitiesCheck();
		displayRefDetails();
		refereeGUI.updateRefDisplay();
	}

	/**
	 * Ensures that if a referee's home locality is changed,
	 * the corresponding visiting locality is also updated.
	 * i.e. if the new home locality is 'North', the referee
	 * must have North as a visiting locality.
	 */
	public void visitingLocalitiesCheck()
	{
		// handles the home locality being 'North'
		if (refereeClass.getHomeLocality().equals("North"))
		{
			// set the north check box to be selected,
			// and un-select the other check boxes
			northCheckBox.setSelected(true);
			centralCheckBox.setSelected(false);
			southCheckBox.setSelected(false);
		}
		// handles the home locality being 'Central'
		if (refereeClass.getHomeLocality().equals("Central"))
		{
			// set the central check box to be selected,
			// and un-select the other check boxes
			centralCheckBox.setSelected(true);
			northCheckBox.setSelected(false);
			southCheckBox.setSelected(false);
		}
		// handles the home locality being 'South'
		if (refereeClass.getHomeLocality().equals("South"))
		{
			// set the south check box to be selected,
			// and un-select the other check boxes
			southCheckBox.setSelected(true);
			northCheckBox.setSelected(false);
			centralCheckBox.setSelected(false);
		}
	}

	/**
	 * Handles button presses, etc.
	 * @param e - the action event being processed
	 */
	public void actionPerformed(ActionEvent e)
	{
		// handles the edit button being pressed
		if (e.getSource() == editButton)
			processEdit();

		// handles the update button being pressed
		if (e.getSource() == updateButton)
			processUpdate();
	}
}
