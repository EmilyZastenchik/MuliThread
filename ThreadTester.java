// ThreadTester - contains main method
// bigArray holds a bunch of numbers that need to be added up
// create one or more AdderThread threads to divide up the task
// -- each thread adds up elements in one section of the array, and
// -- returns its answer in a Result object
// main collects and adds the answers from each thread to get the final sum

// In class on 10/9 we got only as far as creating one thread to do all the math

import java.util.Random;

public class ThreadTester {

	static final int RANDMAX = 1000;
	static final int SIZE = 10000;
	static final int NUMTHREADS = 10;
	
	public static void main(String[] args) {
		
		// make an array full of datas
		int[] bigArray = new int[SIZE];
		arrayFiller(bigArray);
		
		
		// make an object to hold the thread's answer
		Result [] subtotal = new Result[NUMTHREADS];
		for(int i = 0 ; i < NUMTHREADS; i++){
			subtotal[i] = new Result();

		}

		// create a thread
		Thread[] threadlist = new Thread[NUMTHREADS];
		int INTERVAL = SIZE / NUMTHREADS;
		for(int i = 0 ; i < NUMTHREADS; i++){
		threadlist[i] = new Thread 
			(new AdderThread(bigArray, i*INTERVAL, (i+1)*INTERVAL, subtotal[i]));
		threadlist[i].start();
		}
		// wait for thread to finish
		try {
			for(int i = 0 ; i < NUMTHREADS; i++){
				threadlist[i].join();
			}
		} catch (InterruptedException ie) { }
		
		// calculate and print result
		long finalanswer = 0;
		for(int i = 0 ; i < NUMTHREADS; i++){
			finalanswer += subtotal[i].getSum();
		}
		System.out.println("Sum: " + finalanswer);
		System.out.println("Average: " + finalanswer/SIZE);
	}

	// fill the array with random numbers between 0 and RANDMAX
	static void arrayFiller (int[] thearray) {
		Random rng = new Random();
		for (int i = 0; i < thearray.length; i++) {
			thearray[i] = rng.nextInt(RANDMAX);
		}
	}
}