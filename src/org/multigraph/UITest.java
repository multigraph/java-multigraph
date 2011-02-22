package org.multigraph;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UITest extends JPanel
                        implements ActionListener {
    protected JButton reloadButton;
    
    private MyCanvas canvas;
    private int width, height;
    private GraphicsContext g;
    
    private static class MyCanvas extends Canvas {
    	
        //    	private Image image;
        //    	
        //    	public void setImage(Image image) {
        //    		this.image = image;
        //    	}

        private Multigraph multigraph;
        public void setMultigraph(Multigraph multigraph) {
            this.multigraph = multigraph;
        }

		@Override
		public void paint(Graphics g) {
            multigraph.render(new GraphicsContext((Graphics2D)g));
			//g.drawImage(image, 0, 0, Color.GREEN, null);
		}
    	
    }

    public UITest() throws Exception {
    	
    	setLayout(new BorderLayout());
    	
    	reloadButton = new JButton("Reload");
        reloadButton.setVerticalTextPosition(AbstractButton.CENTER);
        reloadButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
        reloadButton.setMnemonic(KeyEvent.VK_D);
        reloadButton.setActionCommand("reload");

        reloadButton.addActionListener(this);

        reloadButton.setToolTipText("Click this button to reload the MUGL file.");

        width = 500;
        height = 500;
        
        canvas = new MyCanvas();
        canvas.setSize(width, height);
        
        reload();
        
        //Add Components to this container, using the default FlowLayout.
        add(reloadButton, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if ("reload".equals(e.getActionCommand())) {
        	reload();
        	canvas.repaint();
        	System.out.println("you clicked Reload");
        }
    }
    
    private void reload() {
    	try {
    		Multigraph multigraph = new Multigraph("graph.xml", "defaults.xml", width, height);
    		canvas.setMultigraph(multigraph);
    	} catch (Exception e) {
    		System.out.println(e);
    		System.out.println(e.getMessage());
    	}
    }

    /**
     * Create the GUI and show it.  For thread safety, 
     * this method should be invoked from the 
     * event-dispatching thread.
     */
    private static void createAndShowGUI() throws Exception {

        //Create and set up the window.
        JFrame frame = new JFrame("UITest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        UITest newContentPane = new UITest();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	try {
            		createAndShowGUI();
            	} catch (Exception e) {
            		System.err.println(e);
            		System.err.println(e.getMessage());
            	}
            }
        });
    }
}
