package tsp_coursework1_cst3170_m00736217;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;


public class main {
	
	public static long t = 0;
	
	public static int distance(int p1, int p2, int q1, int q2) {
		//   distance
		return (int) Math.sqrt((p1-q1)^2 + (p2-q2)^2);
	}
	
	public static void startTimer() {
		t = System.nanoTime();
	}
	
	public static void stopTimer() {
		t = System.nanoTime() - t;
	}
	
	public static void printTimer() {
		long ts = TimeUnit.SECONDS.convert(t, TimeUnit.NANOSECONDS);
		System.out.println(String.format("Timer: %s s", ts));
	}
	
	public static File[] ls(String dir) 
	{
		File dir_ls = new File(dir);
	    return (File[]) dir_ls.listFiles();
	}
	
	 public static String cat(File file_local) 
	 {
		 String data = "";
		 try {
			 Scanner file_reader = new Scanner(file_local);
			 String read_data;
			 while (file_reader.hasNextLine()) {
				 read_data =  file_reader.nextLine();
				 read_data = read_data.replace("null", "");
				 read_data = read_data.replaceAll("\\r|\\n|\\t|", "").replaceAll("^ +| +$|( )+", "$1").strip() + "\n";
				 data += read_data;
		     }
			 file_reader.close();
		  	} catch (FileNotFoundException e) {
		  		System.err.println(String.format("[x] Error: File not found %s", file_local));
		  		e.printStackTrace();
		  	}
		 return data;
	 }
	 // 1 1 5 5
	 // 16 + 16 = 32;  root(32) < 6
	public static double euclideanDistance(int x1, int y1, int x2, int y2)
	{
			return Math.sqrt((Math.pow(x2-x1, 2))+(Math.pow(y2-y1, 2)));
	}
	 
	 public static HashMap<String, HashMap<String, Integer>> parse(ArrayList<String> training_data){
		 HashMap<String, HashMap<String, Integer>> training_sets = new HashMap<String, HashMap<String, Integer>>();
		 ArrayList<String> _data_set = new ArrayList<String>();
		 
		 for (String data_set:training_data)
		 {
			 HashMap<String, Integer> set = new HashMap<String, Integer>();
			 data_set = data_set.strip();
			 
			 int i = 0;
			 Stack<Double> distances = new Stack<Double>(); 
			 for (String city:data_set.split("\n"))
			 { 
				 i++;
				 for (int ii = i; ii < data_set.split("\n").length; ii++)
				 {
					 //System.out.println(ii);
					 //System.out.print(data_set.split("\n")[ii-1].split(" ")[2]);
					 distances.push(euclideanDistance(
							 Integer.parseInt(city.split(" ")[1]),
							 Integer.parseInt(city.split(" ")[2]),
							 Integer.parseInt(data_set.split("\n")[ii].split(" ")[1]), 
							 Integer.parseInt(data_set.split("\n")[ii].split(" ")[2])
							 ));
				 }
				
			 }
			 System.out.println(distances); 
//			 for (String city:data_set.split("\n")) {
//				 String[] cs;
//				 cs = city.split(" ");
//				 for (String n:cs) {
//					 set.push(Integer.valueOf(n));
//				 }
//			 }
//			 training_sets.add(set);
		 }
		 
//		for (Stack<Integer> s:training_sets) {
//			System.out.println(s);
//		}
		 return training_sets;
	 }
	 
	@SuppressWarnings("null")
	public static void main(String[] args) throws InterruptedException {
	    File[]	training_sets_locals= ls("data");
	    ArrayList<String> training_sets = new ArrayList<String>();
	    HashMap<String, HashMap<String, Integer>> training_data = new HashMap<String, HashMap<String, Integer>>();
			
		for (File set_local:training_sets_locals){
			training_sets.add(cat(set_local));
		}
		
		training_data = parse(training_sets);
//		
//		for (Stack<Integer> data_set:training_data) {
//			startTimer();
//
//			System.out.println(TSP.solve(data_set));
//			
//			stopTimer();
//			printTimer();
//		}
	}

}
