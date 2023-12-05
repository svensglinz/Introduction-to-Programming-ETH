
public class Graphs {

	public static Node cube(int n) {

		// create node List
		LinkedNodeList nodeList = new LinkedNodeList();

		// append nodes with unique label to nodeList
		for (int i = 0; i < (1 << n); i++) {
			Node node = new Node(i, nodeList);
			nodeList.addLast(node);
		}

		// return the first node
		Node out = nodeList.get(0);
		return out;
	}

	// Eine Hilfsmethod zum printen von Graphen.
	// Ueberschreiben Sie Node.toString() um einen schoeneren Output zu bekommen.
	public static void printGraph(Node node) {
		LinkedNodeList visited = new LinkedNodeList();
		printGraphRecursive(node, visited);
	}

	public static void printGraphRecursive(Node node, LinkedNodeList visited) {
		visited.addFirst(node);

		Node[] nexts = node.getNeighbors();

		String[] names = new String[nexts.length];
		for (int i = 0; i < nexts.length; i += 1) {
			names[i] = nexts[i].toString();
		}

		System.out.println(node + " -> " + String.join(",", names));

		for (Node next : node.getNeighbors()) {
			if (!visited.contains(next)) {
				printGraphRecursive(next, visited);
			}
		}
	}
}
