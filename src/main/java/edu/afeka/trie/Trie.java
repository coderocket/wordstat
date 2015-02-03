
package edu.afeka.trie; 

import java.io.PrintStream;

public class Trie {

	public class Iterator {
		Node n;
		public Iterator(Node n) { this.n = n ; }
		public Node node() { return n; }	
		public String word() { return n.word; }
	}

	private class Node {
		public String word;
		public Node sibling;
		public Node child;
		
		Node(String word, Node sibling, Node child) {
			this.word = word;
			this.sibling = sibling;
			this.child = child;	
		}
	}

	private static String EOP = "#";

	private Node root;

	public Trie() { 
		root = new Node(null, null, null);
	}

	public void insert(String[] words) { 
		assert root != null;

		String[] phrase = addEOP(words);
		int[] indexResult = new int[1];
		Node p =  findLongestPrefix(phrase, indexResult); 
		int k = indexResult[0];

		while(k != phrase.length) {
			p.child = new Node(phrase[k], p.child, null);
			p = p.child;
			k = k + 1;
		}
	}

	public boolean member(String[] words) {

		String[] phrase = addEOP(words);
		int[] indexResult = new int[1];
		findLongestPrefix(phrase, indexResult); 
		return indexResult[0] == phrase.length;
	}

	private Node findLongestPrefix(String[] phrase, int[] indexResult) {
		assert root != null;

		Node p = root;
		int k = 0;
		Node q = findSeq(p,phrase,k);

		while (k != phrase.length && q != null) {
			p = q;
			k = k + 1;
			q = findSeq(p, phrase, k);
		}

		indexResult[0] = k;
		return p;
	}

	private String[] addEOP(String [] seq) {
		String[] seqEOP = new String[seq.length+1];
		int i;
		for (i = 0; i < seq.length; i++) {
			seqEOP[i] = seq[i];
		}
		seqEOP[i] = EOP;
		return seqEOP;
	}
	
	private Node findSeq(Node p, String[] seq, int k) {
		assert p != null;
		if (k == seq.length) return null;
		return find(p, seq[k]);
	}

	private Node find(Node n, String w) {
		assert n != null;
		Node p = n.child;
		while (p != null && !p.word.equals(w))
			p = p.sibling;
		return p;
	}

	public void dump(PrintStream out) {
		assert root != null;
		
		out.println("digraph trie {");

		Node p = root.child;
		while (p != null) {
			dumpNode(p, out);
			p = p.sibling;
		}

		out.println("}");
	}

	private void dumpNode(Node n, PrintStream out) {
		assert n != null;

		out.println(System.identityHashCode(n) + "[label=\"" + n.word + "\"];");

        Node p = n.child;
        while (p != null) {
			out.println(System.identityHashCode(n) + " -> " + System.identityHashCode(p) + ";");
            dumpNode(p, out);
            p = p.sibling;
        }
	}

	/* Phrase iteration interface. 

	*/

	public Iterator start() {

		assert root != null;

		return new Iterator(root);
	}

	public Iterator next(Iterator current, String word) {

		assert current != null;
		return new Iterator(find(current.node(), word));
	}

	// returns true iff the iterator points to a complete phrase
	// that is, the node that the iterator points to contains 'EOP'
	// as one of its children.

	public boolean complete(Iterator p) {

		return find(p.node(), EOP) != null; 
	}
}
