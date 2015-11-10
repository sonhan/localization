package eu.tsp.sal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part {
    List<Double>[] layers; // layer[0-3] ~ 1-4
    boolean userPresent = false;
    int userLayer; // 1-4
    
    public Part(String fileName) {
        
        layers = new ArrayList[3];
        for (int i = 0; i < layers.length; i++)
            layers[i] = new ArrayList<Double>();
        
        String line = null;
        String[] vals;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            for (int i = 0; i < layers.length; i++) {
                line = bufferedReader.readLine();
                vals = line.split(" ");
                for (String val : vals)
                    layers[i].add(Double.parseDouble(val));
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
        
        for (int i = 0; i < 3; i++)
            ret += layerVal(layers[i], i+1);
        
        return ret;
    }
    
    private double layerVal(List<Double> list, int layer) {
        int numberActiveSensors = 0;
        for (double rate : list) {
            if (Math.random() > rate) numberActiveSensors++;
        }
        
        double ret = (double) numberActiveSensors/list.size()/layer; 
        if (userPresent) return ret;
        else return 1-ret;
    }
    
    public String toString() {
        return layers[0].toString() + layers[1].toString() + layers[2].toString();
    }
    
    public boolean isUserPresent() {
        return userPresent;
    }

    public void setUserPresent(boolean userPresent) {
        this.userPresent = userPresent;
    }

    public int getUserLayer() {
        return userLayer;
    }

    public void setUserLayer(int userLayer) {
        this.userLayer = userLayer;
    }
}
