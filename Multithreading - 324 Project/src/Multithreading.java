import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.Timer;


/**
 * @author Burt, Moran, Wallace, Lowry
 *
 * The Multithreading class prompts the user for an array size
 * and begins the threads with their appropriate sorts.
 */
public class Multithreading
{
	private Scanner scan;
	private int num;
	private int randNum;
	private boolean pass;
	private boolean bWrite = false;
	private boolean mWrite = false;
	private boolean iWrite = false;
	private boolean qWrite = false;
	private boolean rWrite = false;
	private Random rand;
	private static int[] marr;
	private static int[] iarr;
	private static int[] barr;
	private static int[] qarr;
	private static int[] rarr;
	private int[] deepArr;
	private static final int DELAY = 10;
	private int bProgress, iProgress, mProgress, qProgress, rProgress;
	private View v;
	private String bubTitle = "Bubble sort took: ";
	private String merTitle = "Merge sort took: ";
	private String insTitle = "Insert sort took: ";
	private String quickTitle = "Quick sort took: ";
	private String radixTitle = "Radix sort took: ";
	
	/**
	 * Constructs a new Mulithreading object.
	 * @param ve  the instance of view created upon program start
	 */
	public Multithreading(View ve)
	{
		this.v = ve;
		setClass();
		final BubThread b = new BubThread(barr);
		final MerThread m = new MerThread(marr);
		final inThread i = new inThread(iarr);
		final quickThread q = new quickThread(qarr);
		final radixThread r = new radixThread(rarr);
		b.start();
		m.start();
		i.start();
		q.start();
		r.start();
		
		/**
		 * Anonymous Timer class for periodically obtaining each
		 * thread's progress.
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
				iProgress = i.getProgress();
				bProgress = b.getProgress();
				mProgress = m.getProgress();
				qProgress = q.getProgress();
				rProgress = r.getProgress();
				
				if(bProgress == 100 && bWrite == false)
				{
					try
					{
						WriteToFile(bubTitle, b.getTime());
						bWrite = true;
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				if(rProgress == 100 && rWrite == false)
				{
					try
					{
						WriteToFile(radixTitle, r.getTime());
						rWrite = true;
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				if(mProgress == 100 && mWrite == false)
				{
					try
					{
						WriteToFile(merTitle, m.getTime());
						mWrite = true;
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				if(iProgress == 100 && iWrite == false)
				{
					try
					{
						WriteToFile(insTitle, i.getTime());
						iWrite = true;
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				
				if(qProgress == 100 && qWrite == false)
				{
					try
					{
						WriteToFile(quickTitle, q.getTime());
						qWrite = true;
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}	
		});
		Timer.start();
	}
	
	/**
	 * Private helper method to begin essential
	 * multhithreading methods.
	 */
	private void setClass()
	{
		pass = false;
		scan = new Scanner(System.in);
		rand = new Random();
		this.selectInt();
		this.setArray();
	}
	
	/**
	 * Private method to write a sort's time elapsed
	 * to a file named "output.dat".
	 * @param s  the sort being recorded
	 * @param x  the amount of time in nanoseconds
	 */
	private void WriteToFile(String s, long x)
	{
		try
		{
			FileWriter fw = new FileWriter("output.dat", true);
			fw.write(s + x + " nanoseconds.");
			fw.write(System.getProperty("line.separator"));
			fw.close();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Getter method to obtain bubble sort's progress
	 * @return bProgress  the current progress of bubble
	 */
	public int getbProgress() 
	{
		return bProgress;	
	}
	
	/**
	 * Getter method to obtain merge sort's progress
	 * @return mProgress  the current progress of bubble
	 */
	public int getmProgress() 
	{
		return mProgress;	
	}
	
	/**
	 * Getter method to obtain insertion sort's progress
	 * @return iProgress  the current progress of bubble
	 */
	public int getiProgress() 
	{
		return iProgress;	
	}
	
	/**
	 * Getter method to obtain quick sort's progress
	 * @return qProgress  the current progress of bubble
	 */
	public int getqProgress() 
	{
		return qProgress;	
	}
	
	/**
	 * Getter method to obtain radix sort's progress
	 * @return rProgress  the current progress of bubble
	 */
	public int getrProgress()
	{
		return rProgress;
	}
	
	/**
	 * Private method to prompt the user to input a number
	 * as the length of the randomized array.
	 */
	private void selectInt()
	{
		System.out.print("Please select an integer between 1,000 and 10,000,000: ");

		while(!pass)
		{
			try
			{
				num = scan.nextInt();
				
				if(num < 1000 || num > 10000000)
				{
					System.out.println("The integer must be between 1,000 and 10,000,000.");
				}
				
				else 
					pass = true;
			}
			
			catch(InputMismatchException e)
			{
				System.out.println("Not a number, please try again.");
				scan.next();
			}
		}
		
		v.setVisible();
	}
	
	/**
	 * Private method to assign the user's number for 
	 * array length to five deep copies for each sorting
	 * algorithm.
	 */
	private void setArray()
	{
		marr = new int[num];
		iarr = new int[num];
		barr = new int[num];
		qarr = new int[num];
		rarr = new int[num];
		deepArr = new int[num];
		
		for(int i = 0; i < deepArr.length; i++)
		{
			randNum = rand.nextInt();
			marr[i] = randNum;
			iarr[i] = randNum;
			barr[i] = randNum;
			qarr[i] = randNum;
			rarr[i] = randNum;
			deepArr[i] = randNum;
		}
	}
}