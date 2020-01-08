package gui;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.util.Collection;

import org.w3c.dom.traversal.NodeIterator;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Range;
import utils.StdDraw;

public class Graph_GUI implements Serializable {

	private Graph_Algo gra;
	private graph g;


	public Graph_GUI() {
		gra=new Graph_Algo();
		g=new DGraph();
		StdDraw.setGui(this);
	}

	public Graph_GUI(graph g) {
		this.g = g;	
		gra=new Graph_Algo();
		gra.init(this.g);
		StdDraw.setGui(this);

	}

	public void init(graph gr) {
		this.g = gr;
		this.gra.g = gr;
	}

	public void init(String name) {
		this.gra.init(name);
		this.g=gra.g;
		drawAll();
	}

	public void drawAll() {
		drawCanvas();
		drawEdges();
		drawNodes();

	}

	public void drawCanvas() {

		double minX=0, maxX=0, minY=0, maxY=0;

		Collection<node_data> points = g.getV();
		if(points.isEmpty()) {
			StdDraw.setCanvasSize(600,600);
			StdDraw.setXscale(-150,150);
			StdDraw.setYscale(-150,150);
		}
		else {
			
			minX = points.iterator().next().getLocation().x();
			maxX = points.iterator().next().getLocation().x();

			minY =points.iterator().next().getLocation().y();
			maxY =points.iterator().next().getLocation().y();

			
			for (node_data nodes : points) {
				if(nodes.getLocation().x()>maxX)
					maxX = nodes.getLocation().x();

				if(nodes.getLocation().x()<minX)
					minX = nodes.getLocation().x();

				if(nodes.getLocation().y()>maxY)
					maxY = nodes.getLocation().y();

				if(nodes.getLocation().y()<minY)
					minY = nodes.getLocation().y();
			}

			StdDraw.setCanvasSize((int)(Math.abs(minX)+Math.abs(maxX))+300,(int)(Math.abs(minY)+Math.abs(maxY))+300);
			StdDraw.setXscale(minX-10, maxX+10);
			StdDraw.setYscale(minY-10, maxY+10);
		}
	}

	public void drawNodes() {
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.setPenRadius(0.03);
		Collection<node_data> points = g.getV();
		for (node_data nodes : points) {

			StdDraw.point(nodes.getLocation().x(), nodes.getLocation().y());
			StdDraw.setFont(new Font("Ariel", Font.ROMAN_BASELINE, 20));
			StdDraw.text(nodes.getLocation().x(), nodes.getLocation().y()+5, ""+ nodes.getKey());
		}
	}
	
	public void drawEdges() {

		StdDraw.setPenRadius(0.008);
		Collection<node_data> points = g.getV();
		for(node_data nodes: points) {
			Collection<edge_data> e = g.getE(nodes.getKey());
			for (edge_data edge : e) {
				double x0= nodes.getLocation().x();
				double y0= nodes.getLocation().y();
				double x1= g.getNode(edge.getDest()).getLocation().x();
				double y1= g.getNode(edge.getDest()).getLocation().y();
				StdDraw.setPenRadius(0.005);

				StdDraw.setPenColor(Color.RED);
				StdDraw.line(x0, y0, x1, y1);

				StdDraw.setFont(new Font("Ariel", Font.BOLD, 20));

				StdDraw.setPenColor(Color.CYAN);
				StdDraw.setPenRadius(0.05);
				StdDraw.point((x0+x1*3)/4, (y0+y1*3)/4);

				StdDraw.setPenColor(Color.black);
				StdDraw.text((x0+x1*3)/4, (y0+y1*3)/4, ""+ edge.getWeight());
			}
		}
	}

	
	public Graph_Algo getAlgo() {
		return gra;
	}

	
	public graph getGraph() {
		return g;
	}


}