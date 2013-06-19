import java.awt.*;
import javax.swing.*;

/**
 * Defines a component which displays a bar chart
 * of referee match allocations. Each bar is identified
 * by the corresponding referee's ID, and the number of
 * match allocations is displayed above each bar.
 * @author Adam John Campbell Murray
 *
 */
@SuppressWarnings("serial")
public class BarChartComponent extends JComponent
{
	/* Instance variables */
	// Object instance variables
	private RefereeProgram refereeProgram;

	/**
	 * Constructor for BarChartComponent.
	 * @param rProg - RefereeProgram being used
	 */
	public BarChartComponent(RefereeProgram rProg)
	{
		refereeProgram = rProg;
	}

	public void paintComponent(Graphics g)
	{
		// recover Graphics2D
		Graphics2D g2 = (Graphics2D) g;
		
		// add string to top left hand corner of component
		g2.drawString("(Number of match allocations above each bar)", 10, 20);
		
		// initialise top left hand corner coordinates,
		// and width of the bar chart bars
		int barXCoord = 10;
		int barYCoord = 200;
		int barWidth = 60;

		// add a bar for each referee
		for (int x = 0; x < refereeProgram.getelementsInArray(); x++)
		{
			RefereeClass refAtX = refereeProgram.getRefereeClassAtX(x);

			// set colour of the bar chart outline
			g2.setColor(Color.BLACK);

			// draw outline of bars
			g2.draw(new Rectangle(barXCoord, barYCoord - (refAtX.getAllocatedMatches() * 10),
					barWidth, (refAtX.getAllocatedMatches() * 10)));

			// set colour of the bar chart bars
			g2.setColor(Color.RED);

			// fill bars
			g2.fill(new Rectangle(barXCoord, barYCoord - (refAtX.getAllocatedMatches() * 10),
					barWidth, (refAtX.getAllocatedMatches() * 10)));

			// set colour of the ID identifiers
			g2.setColor(Color.BLACK);

			// draw match allocations about each bar
			g2.drawString(refereeProgram.getRefereeClassAtX(x).getRefereeID(),
					barXCoord + 20, barYCoord + 20);
			
			// draw bar identifiers
			g2.drawString("" + refereeProgram.getRefereeClassAtX(x).getAllocatedMatches(),
					barXCoord + 25, barYCoord - (refAtX.getAllocatedMatches() * 10) - 5);

			// increment x-coord of bars
			barXCoord += 70;
		}
	}
}
