package Tests;

	import static org.junit.jupiter.api.Assertions.*;


	import java.util.Collection;

	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

	import dataStructure.DGraph;
	import dataStructure.Edge;
	import dataStructure.edge_data;
	import dataStructure.graph;
	import dataStructure.Node;
	import utils.Point3D;
	
	public class DGraphTest {
		private DGraph createGraph() {
			DGraph g = new DGraph();
			g.addNode(new Node(1, new Point3D(50,0), 0, "", 0));
			g.addNode(new Node(2, new Point3D(0,50), 0, "", 0));
			g.addNode(new Node(3, new Point3D(0,0), 0, "", 0));
			g.addNode(new Node(4, new Point3D(30,60), 0, "", 0));

			return g;
		}

		@Test
		void testAddNode() {
			DGraph d = createGraph();
			int preSize = d.nodeSize();
			d.addNode(new Node(5, new Point3D(8,52), 0, "", 0));
			int newSize = d.nodeSize();
			assertTrue(preSize+1 == newSize);	}

		@Test
		void testConnect() {
			DGraph d = createGraph();
			int preSize = d.edgeSize();
			d.connect(1, 2, 1);
			int newSize = d.edgeSize();
			assertTrue(preSize+1 == newSize);	}

		@Test
		void testRemoveNode() {
			DGraph d = createGraph();
			int preSize = d.nodeSize();
			d.removeNode(1);
			int newSize = d.nodeSize();
			assertTrue(preSize-1 == newSize);	}

		@Test
		void testRemoveEdge() {
			DGraph d = createGraph();
			int preSize = d.edgeSize();
			d.connect(1, 2, 1);
			d.removeEdge(1, 2);
			int newSize = d.edgeSize();
			assertTrue(preSize == newSize);	}

		@Test
		void testNodeSize() {
			DGraph temp = new DGraph();
			temp.addNode(new Node(1, new Point3D(1,1), 0, "", 0));
			temp.addNode(new Node(2, new Point3D(2,2), 0, "", 0));
			temp.addNode(new Node(3, new Point3D(3,3), 0, "", 0));
			int size = temp.nodeSize();
			assertTrue(size == 3);
		}

		@Test
		void testEdgeSize() {
			DGraph d = createGraph();
			d.connect(1, 2, 1);
			d.connect(2, 1, 1);
			d.connect(3, 2, 1);
			int size = d.edgeSize();
			assertTrue(size == 3);
		}
	}