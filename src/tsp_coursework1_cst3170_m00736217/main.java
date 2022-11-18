package tsp_coursework1_cst3170_m00736217;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


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
		System.out.println(String.format("Timer: %s ns", t));
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
	 
	 public static ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> parse(ArrayList<String> training_sets){
		 ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> training_data = new ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>>();
		 ArrayList<HashMap<Integer, ArrayList<Integer>>> cities = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
		 HashMap<Integer, ArrayList<Integer>> city = new HashMap<Integer, ArrayList<Integer>>();
		 int n = 0;
		 ArrayList<Integer> coords = new ArrayList<Integer>(); 
		 for (String data_set:training_sets) {
			 data_set = data_set.strip();
			 cities = new ArrayList<HashMap<Integer, ArrayList<Integer>>>();
			 for (String _city:data_set.split("\n")) {
				 String[] cs = _city.split(" ");
				 for (int i = 0; i < cs.length; i += 3) {
					 city = new HashMap<Integer, ArrayList<Integer>>();
					 coords = new ArrayList<Integer>();
					 n = Integer.parseInt(cs[i]);
					 try {
						 coords.add(Integer.parseInt(cs[i+1]));
						 coords.add(Integer.parseInt(cs[i+2]));
						 city.put(n, coords); 
						 cities.add(city);
					 } catch (ArrayIndexOutOfBoundsException e) {
						 break;
					 }
				 }
			 }
			 training_data.add(cities);
		 }
		 return training_data;
	 }
	 
	@SuppressWarnings("null")
	public static void main(String[] args) {
	    File[]	training_sets_locals= ls("data");
	    ArrayList<String> training_sets = new ArrayList<String>();
	    ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>> training_data = new ArrayList<ArrayList<HashMap<Integer, ArrayList<Integer>>>>();
		TSP tsp = new TSP();
			
		for (File set_local:training_sets_locals){
			training_sets.add(cat(set_local));
		}
		training_data = parse(training_sets);
		for (ArrayList<HashMap<Integer, ArrayList<Integer>>>data_set:training_data) {
			startTimer();
			stopTimer();
			System.out.println(tsp.solve(data_set));
			printTimer();
		}
	}

}
