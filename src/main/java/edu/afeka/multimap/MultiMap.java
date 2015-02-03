/* A multimap (modeling a mathematical relation) that sorts its entries
in descending order */

package edu.afeka.multimap;

import java.lang.Comparable;
import java.util.Map;
import java.util.AbstractMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Comparator;

public class MultiMap<Key extends Comparable<Key>,Value extends Comparable<Value> > {

	private SortedSet<Map.Entry<Key, Value> > elems;

	public MultiMap() {
		elems = new TreeSet<Map.Entry<Key, Value> >(new Comparator<Map.Entry<Key , Value> >() {
			public int compare(Map.Entry<Key, Value> y, Map.Entry<Key, Value> x) {
				int low = x.getValue().compareTo(y.getValue());
				int hig = x.getKey().compareTo(y.getKey());
				if (hig == 0) return low; else return hig;
			} 
		});
	}

	public void clear() {
		elems.clear();
	}

	public void add(Key key, Value value) {
		elems.add(new AbstractMap.SimpleEntry<Key,Value>(key,value));
	}
	public Iterator<Map.Entry<Key,Value> > iterator() {
		return elems.iterator();
	}

	public String toString() {
		return elems.toString();
	}
}
