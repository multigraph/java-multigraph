package org.multigraph;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class MG_ObjectFactory extends org.multigraph.jaxb.ObjectFactory {

	@Override
	public org.multigraph.jaxb.Muglatrix createMuglatrix() {
		return new org.multigraph.Muglatrix();
	}
	

}
