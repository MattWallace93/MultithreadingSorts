/**
 * @author Burt, Moran, Wallace, Lowry
 *
 * The radixThread class sorts its copy of the array
 * using the radix sort algorithm.
 */
public class radixThread extends Thread
{
	private int[] rArray;
	private int counter;
	private int rProgress;
	private int x;
	private long radixStartTime = System.nanoTime();
	private long radixStopTime;
	@SuppressWarnings("unused")
	private long radixDifference;
	
	/**
	 * Constructs a new radixThread object.
	 * @param rData  the radix sort thread's copy of the array
	 */
	radixThread(int[] rData)
	{
		this.rArray = rData;
		x= rData.length / rData.length;
		counter = 0;
		rProgress = 4;
	}
		
	/**
	 * Calls the radix sort algorithm and
	 * obtains the time at which the sort finishes.
	 */
	public void run()
	{
		radixSort();
		radixStopTime = System.nanoTime();
	}
	
	/**
	 * Sorts an array of ints using the Radix sort algorithm.
	 */
	public void radixSort() 
	{
	    // Loop for every bit in the integers
	    for (int shift = Integer.SIZE - 1; shift > -1; shift--) 
	    {
	    	 counter++;
			 if(counter == x)
			 {
				rProgress = rProgress + 3;
				 counter = 0;
			 }
	    	
	        // An array to store the partially sorted array temporarily
	        int[] tmp = new int[rArray.length];
	        // The number of 0s
	        int j = 0;
	 
	        // Move the 0s to the new array, and the 1s to the old one
	        for (int i = 0; i < rArray.length; i++) 
	        {
	        	//"<<" shifts a bit pattern to the left
	        	//shift "old[i]" to the left by "shift" bits, and check if that's positive
	        	//it will be positive if there are no 1s
	            boolean move = (rArray[i] << shift) >= 0;
	 
	            // If this is the last bit, negative numbers are actually lower
	            if (shift == 0 ? !move : move) 
	            {
	                tmp[j] = rArray[i];
	                j++;
	            } 
	            
	            else 
	            {
	                // It's a 1, so stick it in the old array for now
	                rArray[i - j] = rArray[i];
	            }
	            
	        }
	 
	        // Copy over the 1s from the old array
	        for (int i = j; i < tmp.length; i++) 
	        {
	            tmp[i] = rArray[i - j];
	       
	        }
	 
	        // Switch out the partially sorted array for the original, to sort again
	        rArray = tmp;
	    }
	}
	 
	/**
	 * Getter method for the radix sort's progress.
	 * @return rProgress  the progress of the radix sort
	 */
	 public int getProgress()
	 {
		 return rProgress;
	 }
	 
	 /**
	  * Gets the difference between the time that the sort begins
	  * and when it ends.
	  * @return radixDifference  the total sorting time
	  */
	 public long getTime()
	 {
		 return radixDifference = radixStopTime - radixStartTime;
	 }
}