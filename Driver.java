import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

//The Driver for HW3 of COS350
//Used to get user input and display the results of the algorithm on the data
public class Driver {
	
	//List of a list of integers, holding the successful runs
	static ArrayList<ArrayList<Integer>> results = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args){
		
		//Holds the pole sizes that the user inputs
		ArrayList<Integer> poles = new ArrayList<Integer>();
		
		getInput(poles); //Get User entered Values
		
		long startTime = System.currentTimeMillis(); //Thestarttime of Algorithm
		
		sortAscending(poles); //Sort list ascending
		checkLists(poles); //Check for successful lists of poles
		
		//Display Results
		System.out.println("r = " + results.size());
		printListofLists(results);
		
		//Calculates and Displays runtime
		long endtime = System.currentTimeMillis(); //The endtime of program
		long totaltime = endtime - startTime;
		System.out.println("TotalTime:(milli-seconds) " + totaltime);
		//testCases(); //Only Run to check testCases (*Long Running Times*)
	}
	
	//Used to generate the poles for the problem
	public static void getInput(ArrayList<Integer> p){
		p.clear();
		boolean done = false;
		Scanner console = new Scanner(System.in);
		while(!done){
			System.out.println("Enter Pole Values: (Press 0 to end)");
			int response = console.nextInt();
			if (response == 0){ //Exit Case
				done = true;
			} else { //Adds the response to list of poles
				p.add(response);
			}
		}
		console.close();
	}
	
	//Checks for successful combinations of pole sizes
	public static boolean checkLists(ArrayList<Integer> p){
		results.clear(); //clears the list of results
		int iterate = 0; //# of times outer loop will iterate
		if(p.size()<3){
			return false;
		} else {
			iterate = p.size()-2;
		}
		
		//3 Nested Loops check for each location within the tested triple
		//where (a,b,c), where a,b,c are indexes within poles
		for (int a=0;a<iterate;a++){
			for(int b=(a+1);b<p.size();b++){
				for(int c=(b+1);c<p.size();c++){
					checkTrueList(p.get(a),p.get(b),p.get(c));
				}
			}
		}
		return true;
	}
	
	//Checks the slope of the specified integers, where
	//rgSlope = slope(a,b), and gbSlope = slope(b,c)
	//if rgSlope == gbSlope, return true, else false
	/*public static boolean checkTrue(ArrayList<Integer> p){
		float rgSlope, gbSlope;
		rgSlope = getSlope(p.get(0),p.get(1));
		gbSlope = getSlope(p.get(1),p.get(2));
		if (rgSlope == gbSlope){
			return true;
		} else
			return false;
		
	}*/
	
	//Checks the slope of the specified integers, where
	//rgSlope = slope(a,b), and gbSlope = slope(b,c)
	//if rgSlope == gbSlope, return true, else false
	public static boolean checkTrueList(int a, int b, int c){
		float rgSlope, gbSlope;
		rgSlope = getSlope(a,b);
		gbSlope = getSlope(b,c);
		if ((rgSlope == gbSlope) && rgSlope >= 0){
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(a);
			temp.add(b);
			temp.add(c);
			if (!results.contains(temp)){
				results.add(temp);
			} else {
		
			}
			return true;
		} else
			return false;
		
	}
	
	//Prints the specified list to std.out
	public static void printList(ArrayList<Integer> p){
		for (int i=0;i<p.size();i++){
			System.out.println(p.get(i));
		}
	}
	
	//Prints the specified list of lists
	public static void printListofLists(ArrayList<ArrayList<Integer>> r){
		for (int i=0;i<r.size();i++){
			System.out.print("[");
			ArrayList<Integer> temp = r.get(i);
			for (int j=0;j<temp.size();j++){
				if (j == (temp.size()-1)){
					System.out.print(temp.get(j));
				} else {
					System.out.print(temp.get(j) + ", ");
				}
			}
			System.out.println("]");
		}
	}
	
	//Returns the slope between the two points
	public static int getSlope(int a, int b){
		return (b-a);
	}
	
	//Sort the list ascending
	public static void sortAscending(ArrayList<Integer> p){
		Collections.sort(p);
	}
	
	//Conducts test on specified # of inputs
	public static long test(int n){
		ArrayList<Integer> testPoles = new ArrayList<Integer>(); //local test variable
		System.out.println(n + " Starting...");
		long avgTime = 0;
		
		//Checks Each and Every case 3 times to generate statistics
		for(int i=0;i<3;i++){
			testPoles.clear(); //Clear test list
			
			//Generate test list
			for(int j=0;j<n;j++){
				testPoles.add(j);
			}
			
			long startTime = System.currentTimeMillis();
			sortAscending(testPoles);
			checkLists(testPoles);
			long endTime = System.currentTimeMillis();
			avgTime = avgTime + (endTime - startTime);
		}
		
		long tresults = avgTime/3;
		System.out.println("Done...");
		return tresults;
		//System.out.println("results: " + tresults);
	}
	
	//Collects running time for different n values
	public static void testCases(){
		long res50, res100, res200, res300, res400, res500;
		//Test n = 50
		res50 = test(50);
		//Test n = 100
		res100 = test(100);
		//Test n = 200
		res200 = test(200);
		//Test n = 300
		res300 = test(300);
		//Test n = 400...
		res400 = test(400);
		//Test n = 500
		res500 = test(500);
		System.out.println("n = 50: " + res50);
		System.out.println("n = 100: " + res100);
		System.out.println("n = 200: " + res200);
		System.out.println("n = 300: " + res300);
		System.out.println("n = 400: " + res400);
		System.out.println("n = 500: " + res500);
	}
}

