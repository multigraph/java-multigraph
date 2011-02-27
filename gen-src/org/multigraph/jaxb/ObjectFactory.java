//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.27 at 11:50:22 AM EST 
//


package org.multigraph.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.multigraph.jaxb package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Mugl_QNAME = new QName("", "mugl");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.multigraph.jaxb
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Graph }
     * 
     */
    public Graph createGraph() {
        return new Graph();
    }

    /**
     * Create an instance of {@link Plotarea }
     * 
     */
    public Plotarea createPlotarea() {
        return new Plotarea();
    }

    /**
     * Create an instance of {@link Variables }
     * 
     */
    public Variables createVariables() {
        return new Variables();
    }

    /**
     * Create an instance of {@link PlotVariable }
     * 
     */
    public PlotVariable createPlotVariable() {
        return new PlotVariable();
    }

    /**
     * Create an instance of {@link Axis }
     * 
     */
    public Axis createAxis() {
        return new Axis();
    }

    /**
     * Create an instance of {@link Variable }
     * 
     */
    public Variable createVariable() {
        return new Variable();
    }

    /**
     * Create an instance of {@link Labels }
     * 
     */
    public Labels createLabels() {
        return new Labels();
    }

    /**
     * Create an instance of {@link Background }
     * 
     */
    public Background createBackground() {
        return new Background();
    }

    /**
     * Create an instance of {@link PlotAxis }
     * 
     */
    public PlotAxis createPlotAxis() {
        return new PlotAxis();
    }

    /**
     * Create an instance of {@link Data }
     * 
     */
    public Data createData() {
        return new Data();
    }

    /**
     * Create an instance of {@link Renderer }
     * 
     */
    public Renderer createRenderer() {
        return new Renderer();
    }

    /**
     * Create an instance of {@link Plot }
     * 
     */
    public Plot createPlot() {
        return new Plot();
    }

    /**
     * Create an instance of {@link Grid }
     * 
     */
    public Grid createGrid() {
        return new Grid();
    }

    /**
     * Create an instance of {@link Label }
     * 
     */
    public Label createLabel() {
        return new Label();
    }

    /**
     * Create an instance of {@link VerticalAxis }
     * 
     */
    public VerticalAxis createVerticalAxis() {
        return new VerticalAxis();
    }

    /**
     * Create an instance of {@link Window }
     * 
     */
    public Window createWindow() {
        return new Window();
    }

    /**
     * Create an instance of {@link RendererOption }
     * 
     */
    public RendererOption createRendererOption() {
        return new RendererOption();
    }

    /**
     * Create an instance of {@link HorizontalAxis }
     * 
     */
    public HorizontalAxis createHorizontalAxis() {
        return new HorizontalAxis();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Graph }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "mugl")
    public JAXBElement<Graph> createMugl(Graph value) {
        return new JAXBElement<Graph>(_Mugl_QNAME, Graph.class, null, value);
    }

}
