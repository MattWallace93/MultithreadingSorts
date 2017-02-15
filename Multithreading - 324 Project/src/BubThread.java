/**
 * @author Burt, Moran, Wallace, Lowry
 *
 * The BubThread class sorts its copy of the 
 * array using the bubble sort algorithm.
 */
public class BubThread extends Thread
{
	private int[] array;
	private int counter;
	private int bProgress;
	private int x;
	@SuppressWarnings("unused")
	private long bubbleDifference;
	private long bubbleStartTime = System.nanoTime();
	private long bubbleStopTime;
	
	/**
	 * Constructs a new BubThread object.
	 * @param array  bubble sort thread's copy of the array
	 */
	   BubThread (int[] array)
	   {
		   this.array = array;
		   x = array.length/100;
		   counter = 0;
		   bProgress = 1;
	   }
	   
	   /**
	    * Calls the bubble sort algorithm and obtains
	    * the time at which the sort finishes.
	    */
	   public void run()
	   {
		   bubbleSort();
		   bubbleStopTime = System.nanoTime();
	   }
	   
	   /**
	    * Sorts the array using the bubble sort 
	    * algorithm.
	    */
		private void bubbleSort()
		{
			for (int i = array.length-1; i >= 1;i--)
			   {
				   counter++;
				   if (counter == x)
				   {
					   bProgress ++;
					   counter = 0;
				   }
				   
				   for (int j = 0; j < i;j++)
				   {
					   if(array[j] > array[j+1])
					   {
						   int temp = array[j];
						   array[j] = array[j+1];
						   array[j+1] = temp;
					   }
				   }
			   }
		}
		
		/**
		 * Getter method for the bubble sort's progress.
		 * @return rProgress  the progress of the bubble sort
		 */
		public int getProgress() 
		{
			return bProgress;	
		}
		
		/**
		 * Gets the difference between the time that the sort begins
		   and when it ends.
		 * @return bubbleDifference  the total sorting time
		 */
		public long getTime()
		{
			return bubbleDifference = bubbleStopTime - bubbleStartTime;
		}
}