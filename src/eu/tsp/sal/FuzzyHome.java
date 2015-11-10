package eu.tsp.sal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FuzzyHome {
    
    public static final String BASE = "E:/_workspaces/github/localization/";
    Part[] parts = new Part[8];
    
    public FuzzyHome() {
        List<Double> mAs = new ArrayList<Double>();
        
        for (int i = 1; i < parts.length; i++) {
            parts[i] = new Part(BASE + "part" + i + ".txt");
            mAs.add(parts[i].mA());
            
        }
        
        setUserLocation(3, 1); // Part 3, layer 1
        
        System.out.println(mAs);

    }
    
    public void setUserLocation(int part, int layer) {
        for (int i = 1; i < parts.length; i++) {
            if (i == part) {
                parts[i].setUserPresent(true);
                parts[i].setUserLayer(layer);
            }
        }
    }
    
    public static void main(String[] args) {
        
       new FuzzyHome();      
        
    }
    
    
}
