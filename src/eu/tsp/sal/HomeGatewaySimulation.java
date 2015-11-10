package eu.tsp.sal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class HomeGatewaySimulation extends JFrame {
    
    public static final ImageIcon yellowIcon = createImageIcon("/res/yellow.png");
    public static final ImageIcon greenIcon = createImageIcon("/res/green.png");
    public static final ImageIcon redIcon = createImageIcon("/res/red.png");
    public static final ImageIcon greyIcon = createImageIcon("/res/grey.png");
    
    private GenericSensor sensors[] = {    
            new GenericSensor("Contact Sensor", 1, 1, 0.2, 250, 50),
            new GenericSensor("Contact Sensor", 2, 1, 0.2, 250, 100),
            new GenericSensor("Contact Sensor", 3, 1, 0.2, 250, 150)
                                   };
    
    public HomeGatewaySimulation() {
        super("Home Gateway Simulation");
        this.setContentPane(new JLabel(createImageIcon("/res/plan.png")));
        
        JPanel list = new JPanel();
        list.add(new JLabel(sensors[0].getName()));
        list.setBounds(0, 0, 200, 500);
        list.setLocation(10, 60);
        this.add(list);
        //this.add(new JScrollPane(list));
        
        JLabel info = new JLabel("Information");
        //info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setForeground(Color.RED);
        info.setBounds(0, 0, 500, 50);
        info.setLocation(500, 0);
        this.add(info);
        
  
        for (GenericSensor sensor : sensors)
            this.add(sensor);
                
        this.pack();
        this.setResizable(false);
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
