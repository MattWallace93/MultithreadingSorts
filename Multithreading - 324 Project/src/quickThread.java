/**
 * @author Burt, Moran, Wallace, Lowry
 * 
 * The quickThread class sorts its copy of
 * the array using the quick sort algorithm.
 */
public class quickThread extends Thread
{
	private int[] qdata;
	private int counter;
	private int qProgress;
	private int x;
	private int nElems;
	private long quickStartTime = System.nanoTime();
	private long quickStopTime;
	@SuppressWarnings("unused")
	private long quickDifference;
	   
	/**
	 * Constructs a new quickThread object.
	 * @param array  quick sort's copy of the array
	 */
	quickThread (int[] array)
	{
		   this.qdata = array;
		   x = array.length/200;
		   qProgress = 1;
	}
	   
	/**
	 * Calls the quick sort algorithm and
	 * obtains the time at which the sort finishes.
	 */
	   public void run()
	   {
		   nElems = qdata.length;
		   recQuickSort(0, nElems - 1);
		   qProgress = 100;
		   quickStopTime = System.nanoTime();
	   }
	   
	   /**
	    * Recursively sorts the array using either
	    * the insertion sort algorithm, the median
	    * of three algorithm, or choosing a partition
	    * element.
	    * @param left  the leftmost index of the array
	    * @param right  the rightmost index of the array
	    */
	   public void recQuickSort(int left, int right)
	   {	
		   counter++;
		   if (counter == x)
		   {
			   qProgress ++;
			   counter = 0;
		   }
		   int size = right - left + 1;
		   
		   if(size < 10)
			   insertionSort(left, right);
		   
		   else
		   {
			   int median = medianof3(left, right);
			   int partition = partitionIt(left, right, median);
			   recQuickSort(left, partition - 1);
			   recQuickSort(partition - 1, right);
		   }
	   }
	   
	   /**
	    * Chooses a partition element based on
	    * the median of three algorithm.
	    * @param left  the leftmost index of the array
	    * @param right  the rightmost index of the array
	    * @return  the median of left, center, and right elements
	    */
	   public int medianof3(int left, int right)
	   {
		   int center = (left + right) / 2;
		   
		   if(qdata[left] > qdata[center])
			   swap(left, center);
		   
		   if(qdata[left] > qdata[right])
			   swap(left, right);
		   
		   if(qdata[center] > qdata[right])
			   swap(center, right);
		   
		   swap(center, right - 1);
		   
		   return qdata[right - 1];
	   }
	   
	   /**
	    * Swaps two elements in the array.
	    * @param dex1  the first element to be swapped
	    * @param dex2  the second element to be swapped
	    */
	   public void swap(int dex1, int dex2)
	   {
		   int temp = qdata[dex1];
		   qdata[dex1] = qdata[dex2];
		   qdata[dex2] = temp;
	   }
	   
	   /**
	    * Partitions the array by selecting a pivot
	    * value.
	    * @param left  the leftmost index of the array
	    * @param right  the rightmost index of the array
	    * @param pivot  the pivot value to partition
	    * @return leftPtr  the partition element    
	    */
	   public int partitionIt(int left, int right, int pivot)
	   {
		   
		   int leftPtr = left;
		   int rightPtr = right - 1;
		   while(true)
		   {
			   while(qdata[++leftPtr] < pivot)
				   ;
			   while(qdata[--rightPtr] > pivot)
				   ;
			   
			   if(leftPtr >= rightPtr)
				   break;
			   
			   else
				   swap(leftPtr, rightPtr);
		   }
		   
		   swap(leftPtr, right - 1);
		   return leftPtr;
	   }
	   
	   /**
	    * Sorts data using the insertion sort
	    * algorithm.
	    * @param left  the leftmost index of the array
	    * @param right  the rightmost index of the array
	    */
	   public void insertionSort(int left, int right)
	   {
		   int in, out;
		   for(out = left + 1; out <= right; out++)
		   {
			   int temp = qdata[out];
			   in = out;
			   
			   while(in > left && qdata[in - 1] >= temp)
			   {
				   qdata[in] = qdata[in - 1];
				   in--;
			   }
			   
			   qdata[in] = temp;
		   }
	   }
	   
	   /**
	    * Getter method for the quick sort's progress.
	    * @return rProgress  the progress of the quick sort
	 	*/
	    public int getProgress()
	    {
		    return qProgress;	
	    }
	   
	  /**
	   * Gets the difference between the time that the sort begins
	     and when it ends.
	   * @return quickDifference  the total sorting time
	   */
	   public long getTime()
	   {
		   return quickDifference = quickStopTime - quickStartTime;
	   }
}
