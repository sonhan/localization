package eu.tsp.sal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GenericSensor extends JLabel implements MouseListener{
    
    protected String    name;
    protected int       layer;
    protected int       room;
    protected double    error; // failure rate [0-1]
    protected int       x, y;
    
    public GenericSensor(String name, int layer, int room, double error, int x, int y) {
        this.name = name;
        this.layer = layer;
        this.error = error;
        this.x = x;
        this.y = y;
        
        if (layer == 1) this.setIcon(HomeGatewaySimulation.yellowIcon);
        else if (layer == 2) this.setIcon(HomeGatewaySimulation.greenIcon);
        else if (layer == 3) this.setIcon(HomeGatewaySimulation.redIcon);
        else this.setIcon(HomeGatewaySimulation.greyIcon);
        
        this.setBounds(0, 0, this.getIcon().getIconWidth(), this.getIcon().getIconHeight());
        this.setLocation(x, y);
        
        this.addMouseListener(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.setIcon(HomeGatewaySimulation.greyIcon);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (layer == 1) this.setIcon(HomeGatewaySimulation.yellowIcon);
        else if (layer == 2) this.setIcon(HomeGatewaySimulation.greenIcon);
        else if (layer == 3) this.setIcon(HomeGatewaySimulation.redIcon);
    }
    
}
