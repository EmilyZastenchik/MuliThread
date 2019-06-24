
public class AdderThread extends Thread {
	
	private int[] array;
	private int start, end;
	Result answer;
	
	// Constructor:
	// takes a reference to the array being added
	// the start index this thread should look at
	// the end index this thread should look at
	AdderThread (int[] a, int s, int e, Result r) {
		array = a; start = s; end = e; answer = r;
	}
	
	public void run() {
		System.out.println(this.getName()+ " Range "+ "Start Time "+ System.currentTimeMillis());
		int summation = 0;
		for (int i = start; i <= end; i++) {
			summation += array[i];
		}
		answer.setSum(summation);	
		System.out.println(this.getName()+ " Range "+ "End Time "+ System.currentTimeMillis());
	}
	
	
}