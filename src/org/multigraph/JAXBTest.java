package org.multigraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBTest {

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

    public static void main(String[] args) {
        try {
            JAXBContext    jc = JAXBContext.newInstance("org.multigraph.jaxb");
            Unmarshaller    u = jc.createUnmarshaller();
            u.setProperty("com.sun.xml.internal.bind.ObjectFactory",new org.multigraph.MG_ObjectFactory());
            //u.setProperty("coctory",new org.multigraph.MG_ObjectFactory());
            JAXBElement mugle = (JAXBElement) u.unmarshal( new FileInputStream("graph.xml"));
            org.multigraph.jaxb.Graph         graph = (org.multigraph.jaxb.Graph) mugle.getValue();

            setAxisOrientations(graph);

            if (graph.getGraph().size() > 0) {
                graph = graph.getGraph().get(0);
            }

            System.out.printf("horizontal axes:\n");
            for (org.multigraph.jaxb.Axis axis : graph.getHorizontalaxis()) {
                System.out.printf("   id='%s'  min='%s'   max='%s'   orientation='%s'\n",
                                  axis.getId(), axis.getMin(), axis.getMax(), axis.getOrientation());
            }
            System.out.printf("vertical axes:\n");
            for (org.multigraph.jaxb.Axis axis : graph.getVerticalaxis()) {
                System.out.printf("   id='%s'  min='%s'   max='%s'   orientation='%s'\n",
                                  axis.getId(), axis.getMin(), axis.getMax(), axis.getOrientation());
            }
            
            Muglatrix muglatrix = (Muglatrix)(graph.getMuglatrix());
            muglatrix.muglate();

            Marshaller      m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(mugle, System.out);
        } catch (JAXBException je) {
            je.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
