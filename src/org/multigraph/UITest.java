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
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.InputStream;

public class UITest extends JPanel
                        implements ActionListener, KeyListener {
    protected JButton reloadButton;
    
    public static final int WIDTH  = 800;
    public static final int HEIGHT = 600;
    //public static final String MUGLFILE = "oceanheatcontent.xml";
    public static final String MUGLFILE = "graph2.xml";
    //public static final String MUGLFILE = "graph.xml";
    
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
			long t0 = System.currentTimeMillis();
            multigraph.render(new GraphicsContext((Graphics2D)g));
            long t1 = System.currentTimeMillis();
            System.out.printf("multigraph rendered in %1dms\n", t1-t0);
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

        width = WIDTH;
        height = HEIGHT;
        
        canvas = new MyCanvas();
        canvas.setSize(width, height);
        canvas.setFocusable(true);
        canvas.addKeyListener(this);
        
        reload();
        
        //Add Components to this container, using the default FlowLayout.
        add(reloadButton, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        if ("reload".equals(e.getActionCommand())) {
        	reload();
        	canvas.repaint();
        }
    }
    
    private void reload() {
    	try {
    		long t0 = System.currentTimeMillis();
    		InputStream inputStream = new FileInputStream(MUGLFILE);
    		Multigraph multigraph = new Multigraph(inputStream, width, height);
    		inputStream.close();
    		canvas.setMultigraph(multigraph);
    		long t1 = System.currentTimeMillis();
    		System.out.printf("MUGL loaded in %1dms\n", t1-t0);
    	} catch (Exception e) {
    		System.out.println(e);
    		e.printStackTrace();
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

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// the following is a ctrl-r:
		if (e.getKeyChar()=='') {
			reload();
			canvas.repaint();
		}
		// TODO Auto-generated method stub
		
	}
}
