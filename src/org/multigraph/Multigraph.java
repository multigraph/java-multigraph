package org.multigraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

/*
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;
*/

public class Multigraph {
	
	private org.multigraph.jaxb.Graph mState;
    private int mWidth;
    public int getWidth() { return mWidth; }
    private int mHeight;
    public int getHeight() { return mHeight; }
    
    private ArrayList<Graph> mGraphs;
    public ArrayList<Graph> getGraphs() { return mGraphs; }
    
    private static void setAxisOrientations(org.multigraph.jaxb.Graph graph) {
        for (org.multigraph.jaxb.HorizontalAxis haxis : graph.getHorizontalaxis()) {
            haxis.setOrientation(AxisOrientation.HORIZONTAL);
        }
        for (org.multigraph.jaxb.VerticalAxis vaxis : graph.getVerticalaxis()) {
            vaxis.setOrientation(AxisOrientation.VERTICAL);
        }
        for (org.multigraph.jaxb.Graph childgraph : graph.getGraph()) {
            setAxisOrientations(childgraph);
        }
    }    
    
	private static org.multigraph.jaxb.Graph loadMugl(InputStream inputStream) throws Exception {
        JAXBContext    jc = JAXBContext.newInstance("org.multigraph.jaxb");
        Unmarshaller    u = jc.createUnmarshaller();
        //u.setProperty("com.sun.xml.internal.bind.ObjectFactory",new org.multigraph.ObjectFactory());
        JAXBElement mugle = (JAXBElement) u.unmarshal( inputStream );
        org.multigraph.jaxb.Graph         graph = (org.multigraph.jaxb.Graph) mugle.getValue();
        setAxisOrientations(graph);
        return graph;
	}    
	
	public Multigraph(InputStream inputStream, int width, int height) throws Exception {
		org.multigraph.jaxb.Graph mugl = loadMugl(inputStream);
		init(mugl, width, height);
	}
	
	private void init(org.multigraph.jaxb.Graph mugl, int width, int height) throws DataTypeException {		
		this.mState = mugl;
        this.mWidth = width;
        this.mHeight = height;
        
        this.mGraphs = new ArrayList<Graph>();
        if (mState.isSetGraph()) {
        	for (org.multigraph.jaxb.Graph graphState : mState.getGraph()) {
        		this.mGraphs.add(new Graph(graphState, width, height));
        	}
        } else {
    		this.mGraphs.add(new Graph(mState, width, height));
        }
	}
	
	
	
	public void render(GraphicsContext g) {
		for (Graph graph : mGraphs) {
			graph.render(g);
		}
	}

}
