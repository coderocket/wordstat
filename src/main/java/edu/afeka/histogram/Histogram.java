
package edu.afeka.histogram;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.io.PrintStream;
import edu.afeka.multimap.MultiMap;

public class Histogram {
	private Map<String, Integer> phrase_counts;
	private MultiMap<Integer, String> sorted_counts;
	public Histogram() { 
		phrase_counts = new HashMap<String, Integer>();
		sorted_counts = new MultiMap<Integer, String>();
	}

	public void count_phrase(String[] phrase) {
		String s = "";
		for(String w : phrase) {
			s += w + " ";
		}
		Integer count = phrase_counts.get(s);
		if (count == null)
			count = 0;
		phrase_counts.put(s, count+1);
	}

	public void sort_counts() {
		sorted_counts.clear();
		for(Map.Entry<String, Integer> e : phrase_counts.entrySet()) {
			sorted_counts.add(e.getValue(), e.getKey());
		}
	}

	public Iterator<Map.Entry<Integer, String> > iterator() {
		return sorted_counts.iterator();
	}

	public void dump(PrintStream out) {
		out.println("phrase_counts=" + phrase_counts);	
		out.println("sorted_counts=" + sorted_counts);	
	}
}
