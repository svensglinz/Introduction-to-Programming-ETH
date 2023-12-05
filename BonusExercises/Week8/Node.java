
public class Node {

	int label;
	LinkedNodeList graph;
	Node[] neighbors;

	// (node has label and information about the graph in which it is contained
	public Node(int label, LinkedNodeList graph) {
		this.label = label;
		this.graph = graph;
	}

	Node[] getNeighbors() {

		// get number of neighbors
		int nNodes = graph.size;
		int nNeighbors = (int) (Math.log((double) nNodes) / Math.log(2));

		// find number of neighbors with bitwise comparison
		int nNodesFound = 0;
		Node[] neighbors = new Node[nNeighbors];

		// get XOR binary number between i and nNeighbors
		// loop over all possible neighbours
		for (int i = 0; i < (1 << nNeighbors); i++) {
			int count = 0;
			int diff = i ^ label;
			// count how many bits are equal to 1 in diff
			for (int j = 0; j < nNeighbors; j++) {
				if (((1 << j) & diff) != 0) {
					count++;
				}
			}
			// if number contains only one 1, add it as neighbor
			if (count == 1) {
				Node neighborNode = graph.get(i);
				neighbors[nNodesFound++] = neighborNode;
			}
		}
		return neighbors;
	}
}
