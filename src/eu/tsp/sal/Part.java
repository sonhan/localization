package eu.tsp.sal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part {
    List<Double>[] rates; // layer[0-3] ~ use only 1-3
    boolean userPresent = false;
    int layerInteractingWithUser; // 1-3
    
    public final double[] PRIORITY = {0, .5, .33, .17};
    
    public Part(double r, int s) {
        rates = new ArrayList[4];
        for (int i = 1; i < rates.length; i++) {
            rates[i] = new ArrayList<Double>();
            for (int j = 0; j < s; j++)
            	rates[i].add(r);
        }
    }
    
    public Part(double[] r, int[] s) {
        rates = new ArrayList[4];
        for (int i = 1; i < rates.length; i++) {
            rates[i] = new ArrayList<Double>();
            for (int j = 0; j < s[i-1]; j++)
            	rates[i].add(r[i-1]);
        }
    }
    
    public Part(String fileName) {
        rates = new ArrayList[4];
        for (int i = 1; i < rates.length; i++)
            rates[i] = new ArrayList<Double>();
        
        String line = null;
        String[] vals;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            for (int i = 1; i < rates.length; i++) {
                line = bufferedReader.readLine();
                vals = line.split(" ");
                for (String val : vals)
                    rates[i].add(Double.parseDouble(val));
            }

            // Always close files.
            bufferedReader.close();         
        }
        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
    
    public double mA() {
        double ret = 0;
        int numberOfSensorsDetectingUser;
        // For each layer
        for (int i = 1; i < rates.length; i++) {
        	int numberOfAccurateSensor = numberOfAccurateSensorAtLayer(i);
        	if (userPresent)//if (userPresent && i == layerInteractingWithUser)
            	numberOfSensorsDetectingUser = numberOfAccurateSensor;
            else numberOfSensorsDetectingUser = rates[i].size() - numberOfAccurateSensor;
        	if (rates[i].size() != 0) ret += PRIORITY[i] * numberOfSensorsDetectingUser/rates[i].size();
        	
        	//System.out.print("[" +  numberOfAccurateSensor + ", " + numberOfSensorsDetectingUser +  "/" + rates[i].size() + "]");
        }
        //System.out.println();
        return ret;
    }
    
    public int numberOfAccurateSensorAtLayer(int i) {
        int n = 0;
        for (double rate : rates[i]) {
            if (Math.random() <= rate) n++;
        }
        return n;
    }
    
    public String toString() {
        return rates[0].toString() + rates[1].toString() + rates[2].toString();
    }
  
    public void setLocation(boolean userPresent, int userLayer) {
    	this.userPresent = userPresent;
    	this.layerInteractingWithUser = userLayer;
    }
    
    // Test
    public static void main(String[] args) {
    	Part part = new Part(FuzzyHome.BASE + "part1.txt");
    	
    	part.setLocation(true, 1);
    	
    	System.out.println("mA(): " + part.mA());
  
    	part.setLocation(false, 1);
    	
    	System.out.println("mA(): " + part.mA());
  
    }
}
