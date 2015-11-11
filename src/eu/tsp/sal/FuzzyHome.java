package eu.tsp.sal;
import java.util.Arrays;
import java.util.Random;

public class FuzzyHome {
    
    public static final String BASE = "E:/workspaces/github/localization/";
    public static final int UCSIZE = 9;
    public static final int N = 100;
    public static final double STEP = 0.05;
    public static final double LIMIT = .3; // limit of error 30%
    
    Part[] parts = new Part[8];
    double mAs[] = new double[8];
    
    public FuzzyHome() {
    	
    	//for (int i = 0; i <= 5; i++) experiment_fault_tolerance(i); // Before use: comment System.out.println(Arrays.toString(mAs));
    	//for (int i = 1; i <= 10; i++) experiment_size(i); // Before use: comment System.out.println(Arrays.toString(mAs));
    	//experiment_mA(.2); // Before use: uncomment System.out.println(Arrays.toString(mAs));
    	//for (int i = 0; i < 100; i++) experiment_mA_distribution(.1); // Before use: uncomment System.out.println(Arrays.toString(mAs));
    	
    	for (double error = STEP; error <= LIMIT; error += STEP) 
    		experiment_layer(error, 1); // // Before use: comment System.out.println(Arrays.toString(mAs));
    }
    
    // show detection success rate for 3 layers of PART (part) at SENSOR ERROR (error)
    private void experiment_layer(double error, int part) {
    	initializeFault(error, 0);
    	
    	int count = 0;
    	for (int i = 0; i < 100; i++)
        	if (setUserLocation(part, 1)) count++;
    	System.out.println(count);
    	
    	count = 0;
    	for (int i = 0; i < 100; i++)
        	if (setUserLocation(part, 2)) count++;
    	System.out.println(count);
    	
    	count = 0;
    	for (int i = 0; i < 100; i++)
        	if (setUserLocation(part, 3)) count++;
    	System.out.println(count);
    	
    	System.out.println();
    }
    
    // show values of mA() at a particular SENSOR ERROR
    private void experiment_mA_distribution(double error) {
    	initializeFault(error, 0);
    	setUserLocation(3, 1);
    }
    
    // show all the values of mA at a particular SENSOR ERROR
    private void experiment_mA(double error) {
    	initializeFault(error, 0);
    	runUseCase();
    }
    private void experiment_size(int size) {
    	for (double error = STEP; error <= LIMIT; error += STEP) {
    		int success = 0;
	    	for (int i = 0; i < N; i++) {
	    		initializeSize(error, size);
	    		success += runUseCase();
	    	}
	    	System.out.println((double)success/UCSIZE);
    	}
    }
    
    private void experiment_fault_tolerance(int fault) {
    	double step = 0.05;
    	for (double error = step; error <= LIMIT; error += step) {
    		int success = 0;
	    	for (int i = 0; i < N; i++) {
	    		initializeFault(error, fault);
	    		success += runUseCase();
	    	}
	    	System.out.println((double)success/UCSIZE);
    	}
	    	
    }
    
    // error: sensor error
    // size: number of sensors in each layer (total = 3*size)
    public void initializeSize(double error, int size) {
    	for (int i = 1; i < parts.length; i++)
            parts[i] = new Part(1-error, size);
    }
    
    // error: sensor error
    // fault: number of malfunctioning sensors, randomly fail
    public void initializeFault(double error, int fault) {

    	double r[][] = {{0, 0, 0},
    			{.8, .8, .8},
    			{.8, .8, .8},
    			{.8, .8, .8},
    			{.8, .8, .8},
    			{.8, .8, .8},
    			{.8, .8, .8},
    			{.8, .8, .8}};
    	int s[][] = {{0,0,0},
    			{7, 2, 4},
    			{7, 2, 6},
    			{7, 2, 6},
    			{7, 2, 4},
    			{7, 2, 2},
    			{7, 1, 4},
    			{7, 2, 0}
    	};
    	
    	for (int i = 1; i < r.length; i++)
    		for (int j = 0; j < 3; j++)
    			r[i][j] = 1 - error;

    	if (fault > 0) {
    		Random rand = new Random();
    		for (int i = 0; i < fault; i++) {
    			int k = rand.nextInt(7) + 1;
    			s[k][0]--;
    		}
    	}
    	
    	for (int i = 1; i < parts.length; i++)
            parts[i] = new Part(r[i], s[i]);
    	
    	
    }
    
    // return number of successful detection
    public int runUseCase() {

//    	System.out.println("STARTING USECASE");
  
    	int count = 0;
    	if (setUserLocation(3, 1)) count++;
    	if (setUserLocation(3, 2)) count++;
    	if (setUserLocation(3, 3)) count++;
    	if (setUserLocation(7, 1)) count++;
    	if (setUserLocation(2, 1)) count++;
    	if (setUserLocation(7, 1)) count++;
    	if (setUserLocation(1, 1)) count++;
    	if (setUserLocation(1, 2)) count++;
    	if (setUserLocation(1, 3)) count++;
    	
//    	System.out.println(count);
//        setUserLocation(3, 2);
//        setUserLocation(3, 3);
//        setUserLocation(7, 1);
//        setUserLocation(2, 1);
//        setUserLocation(7, 1);
//        setUserLocation(1, 1);
//        setUserLocation(1, 2);
//        setUserLocation(1, 3);
    	return count;
    }
    
    public boolean setUserLocation(int part, int layer) {
        for (int i = 1; i < parts.length; i++) {
            if (i == part) {
                parts[i].setLocation(true, layer);
            } else {
            	parts[i].setLocation(false, layer);
            }
        }
        
        for (int i = 1; i < parts.length; i++) mAs[i] = parts[i].mA();
        int partWithMaxValue = maxIndex(mAs);
        
        //System.out.println("User moves to Part " + part + ", Layer " + layer);
        //System.out.print("Home gateway: Part ");
        //System.out.print(partWithMaxValue);
        //System.out.println(", mA() = ");
        //System.out.println(Arrays.toString(mAs).substring(6, Arrays.toString(mAs).length()-1));
        
        // return whether the location system detected is the real location
        // true if correct
        return (partWithMaxValue == part);
    }
    
    public int maxIndex(double a[]) {
    	double max = a[1];
    	int max_index = 1;
    	
    	for (int i = 1; i < a.length; i++) {
    		if (a[i] > max) {
    			max = a[i];
    			max_index = i;
    		}
    	}
    	
    	return max_index;
    }

    public void initFixRate() {
    	double r[] = {.8, .8, .8};
    	int s[] = {7, 2, 2};
    	
    	for (int i = 1; i < parts.length; i++)
            parts[i] = new Part(r, s);
        
    }
    
    public void initFromfile() {
    	for (int i = 1; i < parts.length; i++)
            parts[i] = new Part(BASE + "part" + i + ".txt");
    }
    
    public static void main(String[] args) {
       new FuzzyHome();      
    }

}
