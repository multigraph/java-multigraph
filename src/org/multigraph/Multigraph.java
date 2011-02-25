package org.multigraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

public class Multigraph {
	
	//private Mugl state;
	private org.multigraph.jaxb.Graph state;
    private int width;
    private int height;
    
    private ArrayList<Graph> graphs;
    
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
    
	public static org.multigraph.jaxb.Graph loadMugl(String filename) throws Exception {
        JAXBContext    jc = JAXBContext.newInstance("org.multigraph.jaxb");
        Unmarshaller    u = jc.createUnmarshaller();
        JAXBElement mugle = (JAXBElement) u.unmarshal( new FileInputStream("graph.xml"));
        org.multigraph.jaxb.Graph         graph = (org.multigraph.jaxb.Graph) mugle.getValue();
        setAxisOrientations(graph);
        return graph;
	}    
	
	public Multigraph(String muglFilename, String muglDefaultsFilename, int width, int height) throws Exception {
		org.multigraph.jaxb.Graph mugl = loadMugl(muglFilename);
		init(mugl, width, height);
	}
	
	public Multigraph(org.multigraph.jaxb.Graph mugl, int width, int height) {
		init(mugl, width, height);
	}

	private void init(org.multigraph.jaxb.Graph mugl, int width, int height) {		
		this.state = mugl;
        this.width = width;
        this.height = height;
        
        this.graphs = new ArrayList<Graph>();
        if (state.isSetGraph()) {
        	for (org.multigraph.jaxb.Graph graphState : state.getGraph()) {
        		this.graphs.add(new Graph(graphState, width, height));
        	}
        } else {
    		this.graphs.add(new Graph(state, width, height));
        }
	}
	
	
	
	public void render(GraphicsContext g) {
		for (Graph graph : graphs) {
			graph.render(g);
		}
	}

}
