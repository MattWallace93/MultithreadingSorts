import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;


/**
 * @author Burt, Moran, Wallace, Lowry
 * 
 * This program seeks to sort several copies of an array of 
 * user input length using five sorting algorithms: bubble, insertion,
 * merge, quick, and radix sort. Each sort will be run simultaneously on
 * their own respective threads. Progress bars will display each sort's
 * progress. Each sort's time to complete will be written to a file named
 * "output.dat".
 * 
 * The View class contains the GUI elements and components necessary for
 * displaying and updating progress bars for each sort thread.
 */
@SuppressWarnings("serial")
public class View extends JFrame
{
	private JPanel panel;
	private JLabel bubbleL, insertionL, mergeL, quickL, radixL;
	private JProgressBar bubblePB, insertionPB, mergePB, quickPB, radixPB;
	private int bProgress;
	private int iProgress;
	private int mProgress;
	private int qProgress;
	private int rProgress;
	private static final int DELAY = 10;
	
	/**
	 * Constructor a new View object.
	 */
	public View()
	{
		setGUI();
		final Multithreading m = new Multithreading(this);	
		
		/**
		 * Anonymous Timer class for updating progress bars.
		 * @param DELAY  the system delay for time in milliseconds 
		 * @param ActionListener  the ActionListener object
		 */
		Timer Timer = new Timer(DELAY, new ActionListener()
		{
			/**
			 * Implemented method for ActionListener
			 * @param event  the event occuring
			 */
			public void actionPerformed(ActionEvent event)
			{
				bProgress = m.getbProgress();
				iProgress = m.getiProgress();
				mProgress = m.getmProgress();
				qProgress = m.getqProgress();
				rProgress = m.getrProgress();
				bubblePB.setValue(bProgress);
				insertionPB.setValue(iProgress);
				mergePB.setValue(mProgress);
				quickPB.setValue(qProgress);
				radixPB.setValue(rProgress);
			}	
		});
		Timer.start();
	}
	
	/**
	 * Private helper method to set up the graphical
	 * components.
	 */
	private void setGUI()
	{
		bubblePB = setProgressBar(bubblePB);
		insertionPB = setProgressBar(insertionPB);
		mergePB = setProgressBar(mergePB);
		quickPB = setProgressBar(quickPB);
		radixPB = setProgressBar(radixPB);
		
		setLabels();
		setPanel();
		setLabels();
		setFrame();
	}
	
	/**
	 * Private helper method used to set up the progress
	 * bar components.
	 */
	private JProgressBar setProgressBar(JProgressBar PB)
	{
		PB = new JProgressBar(0, 100);
		PB.setMinimum(0);
		PB.setMaximum(100);
		PB.setStringPainted(true);
		return PB;
	}
	
	/**
	 * Private helper method used to set up the panel.
	 */
	private void setPanel()
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));
		
		panel.add(bubbleL);
		panel.add(bubblePB);
		
		panel.add(insertionL);
		panel.add(insertionPB);
		
		panel.add(mergeL);
		panel.add(mergePB);
		
		panel.add(quickL);
		panel.add(quickPB);
		
		panel.add(radixL);
		panel.add(radixPB);
	}
	
	/**
	 * Private helper method used to set up the label
	 * components.
	 */
	private void setLabels()
	{
		bubbleL = new JLabel("Bubble Sort");
		insertionL = new JLabel("Insertion Sort");
		mergeL = new JLabel("Merge Sort");
		quickL = new JLabel("Quick Sort");
		radixL = new JLabel("Radix Sort");
	}
	
	/**
	* Private helper method used to set up the frame.
	*/
	private void setFrame()
	{
		this.setName("Project 3");
		this.setLayout(new BorderLayout());
		this.setSize(300, 300);
		this.setLocation(650, 100);
		this.setVisible(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Method to enable frame visibility once the 
	 * array size is chosen.
	 */
	public void setVisible()
	{
		this.setVisible(true);
	}
	
	/**
	 * Main method that begins the program by
	 * creating a reference to the View class.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		@SuppressWarnings("unused")
		View v = new View();
	}
}