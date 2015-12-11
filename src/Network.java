import java.util.*;

public class Network<E> {

    private ArrayList<Node<E>> nodes;
    private HashMap<E, Node<E>> nodeMap;

    public Network(List<E> nodeValues) {
        nodes = new ArrayList<>(nodeValues.size());

        for (E value : nodeValues) {
            Node<E> node = new Node<>(value);
            nodes.add(node);
            nodeMap.put(value, node);
        }
    }

    public void addConnection(E A, E B, int weight) {
        Node<E> nodeA = nodeMap.get(A);
        Node<E> nodeB = nodeMap.get(B); // Lägga till exception?

        nodeA.addAdjacent(nodeB, weight);
        //nodeB.addAdjacent(nodeA, weight);
    }

    public Iterator<Node<E>> getNodes() {
        return nodes.iterator();
    }

    public static class Node<E> {

        public final E value;
        private final int hash;
        private HashSet<Pair<Node<E>, Integer>> adjacents;

        public Node(E value) {
            this.value = value;
            this.hash = value.hashCode();
        }

        public void addAdjacent(Node<E> node, Integer weight) {
            adjacents.add(new Pair<>(node, weight));
        }

        public Iterator<Pair<Node<E>, Integer>> getAdjacents() {
            return adjacents.iterator();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return hashCode() == node.hashCode() && value == node.value;
        }

        @Override
        public int hashCode() {
            return hash;
        }
    }
}

