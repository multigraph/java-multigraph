package org.multigraph;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;
import org.jdom.input.SAXBuilder;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.multigraph.Mugl;

public class Doit {
	
	public static Mugl load(String filename, String defaultsFilename) throws Exception {
    	SAXBuilder builder = new SAXBuilder();

    	Document defaults = builder.build(new File(defaultsFilename));
        Element defaultsElement = (Element)XPath.selectSingleNode(defaults, "/mugl");

    	Document doc = builder.build(new File(filename));
        Mugl mugl = Mugl.parse((Element)XPath.selectSingleNode(doc, "/mugl"), defaultsElement);
        return mugl;
	}

    public static void main(String[] args) throws Exception {
    	
    	Mugl mugl = load("graph.xml", "defaults.xml");

    	XMLOutputter outputter = new XMLOutputter();

        outputter.setFormat(Format.getPrettyFormat());

        try {
          outputter.output(mugl.build(true), System.out);       
        }
        catch (IOException ee) {
          System.err.println(ee);
        }
    	

    	/*
        XMLConfig xmlConfig = new XMLConfig("graph.xml");

        Node muglNode  = xmlConfig.getNode("/mugl");

        Axis haxis = Axis.parse( xmlConfig, xmlConfig.getNode("/mugl/horizontalaxis") );
        System.out.print(haxis.markup());

        Axis vaxis = Axis.parse( xmlConfig, xmlConfig.getNode("/mugl/horizontalaxis") );
        System.out.print(vaxis.markup());
        */

    }

}
