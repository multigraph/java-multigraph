package org.multigraph;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

public class Multigraph {
	
	private Mugl state;
    private int width;
    private int height;
    
    private ArrayList<Graph> graphs;
    
	public static Mugl loadMugl(String filename, String defaultsFilename) throws Exception {
    	SAXBuilder builder = new SAXBuilder();

    	Document defaults = builder.build(new File(defaultsFilename));
        Element defaultsElement = (Element)XPath.selectSingleNode(defaults, "/mugl");

    	Document doc = builder.build(new File(filename));
        Mugl mugl = Mugl.parse((Element)XPath.selectSingleNode(doc, "/mugl"), defaultsElement);
        return mugl;
	}    
	
	public Multigraph(String muglFilename, String muglDefaultsFilename, int width, int height) throws Exception {
		Mugl mugl = loadMugl(muglFilename, muglDefaultsFilename);
		init(mugl, width, height);
	}
	
	public Multigraph(Mugl mugl, int width, int height) {
		init(mugl, width, height);
	}

	private void init(Mugl mugl, int width, int height) {		
		this.state = mugl;
        this.width = width;
        this.height = height;
        
        this.graphs = new ArrayList<Graph>();
        for (org.multigraph.mugl.Graph graphState : state.getGraphs()) {
        	this.graphs.add(new Graph(graphState, width, height));
        }
	}
	
	
	
	public void render(GraphicsContext g) {
		for (Graph graph : graphs) {
			graph.render(g);
		}
	}

}
