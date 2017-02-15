/**
 * @author Burt, Moran, Wallace, Lowry
 * 
 * The inThread class sorts its copy of the 
 * array using the insertion sort algorithm.
 */
public class inThread extends Thread
{
	private int[] data;
	private int counter;
	private int iProgress;
	private int x;
	@SuppressWarnings("unused")
	private long insertDifference;
	private long insertStartTime = System.nanoTime();
	private long insertStopTime;
	
	/**
	 * Consstructs a new inThread object.
	 * @param array  insertion sort thread's copy of the array
	 */
	   inThread (int[] array)
	   {
		   this.data = array;
		   x = array.length/100;
		   counter = 0;
		   iProgress = 1;
	   }
	   
	   /**
	    * Calls the insertion sort algorithm and
	    * obtains the time at which the sort finishes.
	    */
	   public void run ()
	   {
	    	insertionSort();
	    	insertStopTime = System.nanoTime();
	   }
	   
	   /**
	    * Sorts the array using the insertion sort
	    * algorithm.
	    */
	   private void insertionSort()
	   {
		   for(int j = 1; j < data.length; j++)
	    	{
	    		int key = data[j];
	    		int k = j;
	    		counter++;
	    		
	 			if (counter == x){
	 				iProgress ++;
	 				counter = 0;
	 			}
	 			
	    		while(k > 0 && data[k - 1] > key)
	    		{
	    			data[k] = data[k - 1];
	    			k--;
	    		}
	    		
	    		data[k] = key;
	    	}
	   }
	   
	   /**
		 * Getter method for the insertion sort's progress.
		 * @return rProgress  the progress of the insertion sort
		 */
	   public int getProgress() 
	   {
			return iProgress;	
	   }
	   
	   /**
		* Gets the difference between the time that the sort begins
		  and when it ends.
		* @return insertDifference  the total sorting time
	    */
	   public long getTime()
		{
			return insertDifference = insertStopTime - insertStartTime;
		}
}
