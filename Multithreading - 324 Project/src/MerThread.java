/**
 * @author Burt, Moran, Wallace, Lowry
 *
 * The MerThread class sorts its copy of the
 * array using the merge sort algorithm.
 */
public class MerThread extends Thread
{
	public int mProgress;
	public int x;
	public int counter;
	private int[] data;
	@SuppressWarnings("unused")
	private long merDifference;
	private long merStartTime = System.nanoTime();
	private long merStopTime;
	
	/**
	 * Constructs a new MerThread object.
	 * @param data  merge sort's copy of the array
	 */
	public MerThread(int[] data)
	{
		mProgress = 1;
		this.data = data;
		x = data.length/50;
		counter = 0;
	}
	
	/**
	 * Calls the merge sort algorithm and
	 * obtains the time at which the sort finishes.
	 */
	public void run ()
	{
		data = mergeSort(data, 0, data.length - 1);
		merStopTime = System.nanoTime();
	}
		
    /**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
     *
     * @param data the array to be sorted
     * @param min  the index of the first element 
     * @param max  the index of the last element
     */
	private int[] mergeSort(int[] data, int min, int max)
	{
		if (min < max)
		{
			int mid = (min + max) / 2;
			mergeSort(data, min, mid);
			mergeSort(data, mid+1, max);
			
			data = merge(data, min, mid, max);
		}
		
		counter++;
		if (counter == x)
		{
			mProgress ++;
			counter = 0;
		}
		
		return data;
	}
	
	/**
     * Merges two sorted subarrays of the specified array.
     *
     * @param data the array to be sorted
     * @param first the beginning index of the first subarray 
     * @param mid the ending index fo the first subarray
     * @param last the ending index of the second subarray
     */
	private int[] merge(int[] data, int first, int mid, int last)
	{
		//T[] temp = (T[])(new Comparable[data.length]);
		int[] temp = new int[data.length];
		
		int first1 = first, last1 = mid;  // endpoints of first subarray
		int first2 = mid+1, last2 = last;  // endpoints of second subarray
		int index = first1;  // next index open in temp array
		
		//  Copy smaller item from each subarray into temp until one
		//  of the subarrays is exhausted
		while (first1 <= last1 && first2 <= last2)
		{
			if (data[first1] < data[first2])
			{
				temp[index] = data[first1];
				first1++;
			}
			else
			{
				temp[index] = data[first2];
				first2++;
			}
			index++;
		}
		
		//  Copy remaining elements from first subarray, if any
		while (first1 <= last1)
		{
			temp[index] = data[first1];
			first1++;
			index++;
		}
		
		//  Copy remaining elements from second subarray, if any
		while (first2 <= last2)
		{
			temp[index] = data[first2];
			first2++;
			index++;
		}
		
		//  Copy merged data into original array
		for (index = first; index <= last; index++)
			data[index] = temp[index];
		
		return data;
    }	
	
	/**
	 * Getter method for the merge sort's progress.
	 * @return rProgress  the progress of the merge sort
	 */
	public int getProgress() 
	{
		return mProgress;	
	}
	
	/**
	* Gets the difference between the time that the sort begins
	  and when it ends.
	* @return merDifference  the total sorting time
	*/
	public long getTime()
	{
		return merDifference = merStopTime - merStartTime;
	}
}
