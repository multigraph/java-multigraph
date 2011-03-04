//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2.3-3- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.03.03 at 09:48:11 AM EST 
//


package org.multigraph.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Graph complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Graph">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;group ref="{}GraphContent"/>
 *         &lt;element name="graph" type="{}Graph" maxOccurs="unbounded"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Graph", propOrder = {
    "window",
    "background",
    "plotarea",
    "horizontalaxis",
    "verticalaxis",
    "plot",
    "data",
    "graph"
})
public class Graph {

    protected Window window;
    protected Background background;
    protected Plotarea plotarea;
    protected List<HorizontalAxis> horizontalaxis;
    protected List<VerticalAxis> verticalaxis;
    protected List<Plot> plot;
    protected List<Data> data;
    protected List<Graph> graph;

    /**
     * Gets the value of the window property.
     * 
     * @return
     *     possible object is
     *     {@link Window }
     *     
     */
    public Window getWindow() {
        return window;
    }

    /**
     * Sets the value of the window property.
     * 
     * @param value
     *     allowed object is
     *     {@link Window }
     *     
     */
    public void setWindow(Window value) {
        this.window = value;
    }

    public boolean isSetWindow() {
        return (this.window!= null);
    }

    /**
     * Gets the value of the background property.
     * 
     * @return
     *     possible object is
     *     {@link Background }
     *     
     */
    public Background getBackground() {
        return background;
    }

    /**
     * Sets the value of the background property.
     * 
     * @param value
     *     allowed object is
     *     {@link Background }
     *     
     */
    public void setBackground(Background value) {
        this.background = value;
    }

    public boolean isSetBackground() {
        return (this.background!= null);
    }

    /**
     * Gets the value of the plotarea property.
     * 
     * @return
     *     possible object is
     *     {@link Plotarea }
     *     
     */
    public Plotarea getPlotarea() {
        return plotarea;
    }

    /**
     * Sets the value of the plotarea property.
     * 
     * @param value
     *     allowed object is
     *     {@link Plotarea }
     *     
     */
    public void setPlotarea(Plotarea value) {
        this.plotarea = value;
    }

    public boolean isSetPlotarea() {
        return (this.plotarea!= null);
    }

    /**
     * Gets the value of the horizontalaxis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the horizontalaxis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHorizontalaxis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HorizontalAxis }
     * 
     * 
     */
    public List<HorizontalAxis> getHorizontalaxis() {
        if (horizontalaxis == null) {
            horizontalaxis = new ArrayList<HorizontalAxis>();
        }
        return this.horizontalaxis;
    }

    public boolean isSetHorizontalaxis() {
        return ((this.horizontalaxis!= null)&&(!this.horizontalaxis.isEmpty()));
    }

    public void unsetHorizontalaxis() {
        this.horizontalaxis = null;
    }

    /**
     * Gets the value of the verticalaxis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verticalaxis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerticalaxis().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerticalAxis }
     * 
     * 
     */
    public List<VerticalAxis> getVerticalaxis() {
        if (verticalaxis == null) {
            verticalaxis = new ArrayList<VerticalAxis>();
        }
        return this.verticalaxis;
    }

    public boolean isSetVerticalaxis() {
        return ((this.verticalaxis!= null)&&(!this.verticalaxis.isEmpty()));
    }

    public void unsetVerticalaxis() {
        this.verticalaxis = null;
    }

    /**
     * Gets the value of the plot property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plot property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlot().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Plot }
     * 
     * 
     */
    public List<Plot> getPlot() {
        if (plot == null) {
            plot = new ArrayList<Plot>();
        }
        return this.plot;
    }

    public boolean isSetPlot() {
        return ((this.plot!= null)&&(!this.plot.isEmpty()));
    }

    public void unsetPlot() {
        this.plot = null;
    }

    /**
     * Gets the value of the data property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the data property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Data }
     * 
     * 
     */
    public List<Data> getData() {
        if (data == null) {
            data = new ArrayList<Data>();
        }
        return this.data;
    }

    public boolean isSetData() {
        return ((this.data!= null)&&(!this.data.isEmpty()));
    }

    public void unsetData() {
        this.data = null;
    }

    /**
     * Gets the value of the graph property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the graph property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGraph().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Graph }
     * 
     * 
     */
    public List<Graph> getGraph() {
        if (graph == null) {
            graph = new ArrayList<Graph>();
        }
        return this.graph;
    }

    public boolean isSetGraph() {
        return ((this.graph!= null)&&(!this.graph.isEmpty()));
    }

    public void unsetGraph() {
        this.graph = null;
    }

}
