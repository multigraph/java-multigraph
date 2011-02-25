package org.multigraph;

public class Muglatrix extends org.multigraph.jaxb.Muglatrix {
	
	public Muglatrix() {
		super();
		System.out.printf("org.multigraph.Mulatrix constructor called\n");
	}
	
	public void muglate() {
		System.out.printf("muglate: %1d", this.getFoo() + this.getBar());
	}

}
