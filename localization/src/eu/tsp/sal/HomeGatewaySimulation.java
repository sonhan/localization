package eu.tsp.sal;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HomeGatewaySimulation extends JFrame {
    
    public static final ImageIcon yellowIcon = createImageIcon("/res/yellow.png");
    public static final ImageIcon greenIcon = createImageIcon("/res/green.png");
    public static final ImageIcon redIcon = createImageIcon("/res/red.png");
    public static final ImageIcon greyIcon = createImageIcon("/res/grey.png");
    
    private GenericSensor sensors[] = {    
            new GenericSensor("Contact Sensor", 1, 1, 0.2, 50, 50),
            new GenericSensor("Contact Sensor", 2, 1, 0.2, 100, 100),
            new GenericSensor("Contact Sensor", 3, 1, 0.2, 150, 150)
                                   };
    
    public HomeGatewaySimulation() {
        super("Home Gateway Simulation");
        this.setContentPane(new JLabel(createImageIcon("/res/plan.png")));
        
        JPanel list = new JPanel();
        list.add(new JLabel(sensors[0].getName()));
        list.setBounds(0, 0, 200, 500);
        list.setLocation(100, 200);
        this.add(list);
        
        JLabel info = new JLabel("Information");
        //info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setBounds(0, 0, 200, 50);
        info.setLocation(100, 150);
        this.add(info);
        
  
        for (GenericSensor sensor : sensors)
            this.add(sensor);
                
        this.pack();
        //this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        initiateSensors();
    }
    
    private void initiateSensors() {
        
    }
    
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = HomeGatewaySimulation.class.getResource(path);
        ImageIcon icon;
        if (imgURL != null) {
            icon = new ImageIcon(imgURL);
        } else {
            icon = new ImageIcon(path); 
        }
        
        if (icon.getIconHeight() > 0) return icon; // if an image icon created
        else return null;
    }
    
    public static void main(String[] args) {
        new HomeGatewaySimulation();
    }
}
