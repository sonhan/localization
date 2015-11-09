package eu.tsp.sal;

import javax.swing.JLabel;

public class PeriodicSensor extends GenericSensor implements Runnable {
    private int freq; // inteval after each task

    private PeriodicSensor(String name, int layer, int room, double error, int x, int y, int freq) {
        super(name, layer, room, error, x, y);
        this.freq = freq;
    }
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(freq);
            double ran = Math.random();
            
            if (ran > error) {
                // action
            }
                
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
    }

}
