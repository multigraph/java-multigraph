import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.multigraph.*;

public class Main {


    private static void setAxisOrientations(Graph graph) {
        for (HorizontalAxis haxis : graph.getHorizontalaxis()) {
            haxis.setOrientation(AxisOrientation.HORIZONTAL);
        }
        for (VerticalAxis haxis : graph.getVerticalaxis()) {
            haxis.setOrientation(AxisOrientation.VERTICAL);
        }
        for (Graph childgraph : graph.getGraph()) {
            setAxisOrientations(childgraph);
        }
    }

    public static void main(String[] args) {
        try {
            JAXBContext    jc = JAXBContext.newInstance("org.multigraph");
            Unmarshaller    u = jc.createUnmarshaller();
            JAXBElement mugle = (JAXBElement) u.unmarshal( new FileInputStream("multigraph.xml"));
            Graph         graph = (Graph) mugle.getValue();

            setAxisOrientations(graph);

            if (graph.getGraph().size() > 0) {
                graph = graph.getGraph().get(0);
            }

            System.out.printf("horizontal axes:\n");
            for (Axis axis : graph.getHorizontalaxis()) {
                System.out.printf("   id='%s'  min='%3d'   max='%3d'   orientation='%s'\n",
                                  axis.getId(), axis.getMin(), axis.getMax(), axis.getOrientation());
            }
            System.out.printf("vertical axes:\n");
            for (Axis axis : graph.getVerticalaxis()) {
                System.out.printf("   id='%s'  min='%3d'   max='%3d'   orientation='%s'\n",
                                  axis.getId(), axis.getMin(), axis.getMax(), axis.getOrientation());
            }

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
