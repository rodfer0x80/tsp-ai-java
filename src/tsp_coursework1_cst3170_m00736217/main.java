package tsp_coursework1_cst3170_m00736217;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
	
	public static File[] ls(String dir) {
		File dir_ls = new File(dir);
	    return (File[]) dir_ls.listFiles();
	}
	
	 public static String cat(File file_local) {
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
	 
	 public static ArrayList<Stack<Integer>> parse(ArrayList<String> training_data){
		 ArrayList<Stack<Integer>> training_sets = new ArrayList<Stack<Integer>>();
		 
		 
		 for (String data_set:training_data) {
			 Stack<Integer> set = new Stack<Integer>();
			 data_set = data_set.strip();
			 for (String city:data_set.split("\n")) {
				 String[] cs;
				 cs = city.split(" ");
				 for (String n:cs) {
					 set.push(Integer.valueOf(n));
				 }
			 }
			 training_sets.add(set);
		 }
		for (Stack<Integer> s:training_sets) {
			System.out.println(s);
		}
		 return training_sets;
	 }
	 
	@SuppressWarnings("null")
	public static void main(String[] args) throws InterruptedException {
	    File[]	training_sets_locals= ls("data");
	    ArrayList<String> training_sets = new ArrayList<String>();
	    ArrayList<Stack<Integer>> training_data = new ArrayList<Stack<Integer>>();
			
		for (File set_local:training_sets_locals){
			training_sets.add(cat(set_local));
		}
		
		training_data = parse(training_sets);
		
		for (Stack<Integer> data_set:training_data) {
			startTimer();

			System.out.println(TSP.solve(data_set));
			
			stopTimer();
			printTimer();
		}
	}

}
