
package edu.afeka.oracle; 

import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import edu.afeka.trie.Trie;

public class Oracle {

	List<String> current_phrase;
	List<String> working_prefix;
	Trie.Iterator p;
	Trie trie;

	/* invariant : 
		
		path[trie,p] = working_prefix
		current_phrase is a prefix of working_prefix 
	*/

	public Oracle() {
		trie = new Trie();
		current_phrase = new ArrayList<String>();
		working_prefix = new ArrayList<String>();
		p = trie.start();
	}

	public void dump(PrintStream out) {
		trie.dump(out);
		out.print("\nworking_prefix= ");
		for(String s : working_prefix) {
			out.print(s + " ");
		}
		out.print("\ncurrent_phrase= ");	
		for(String s : current_phrase) {
			out.print(s + " ");
		}
		out.print("\n");
	}

	public void addPhrases(String[][] phrases) {
	    int i;
    	for (i = 0; i < phrases.length; i++) {
       	 trie.insert(phrases[i]);
	    }   
	}

	public void start() {
		p = trie.start();
		current_phrase.clear();
		working_prefix.clear();
	}

	public boolean extend_prefix(String word) {
		p = trie.next(p, word);
 
		if (p.node() == null)
			return false;

		working_prefix.add(word);

		if (trie.complete(p)) {
			current_phrase.clear(); 
			current_phrase.addAll(working_prefix);
		}
		return true;
	}

	public String[] getPhrase() {
		return current_phrase.toArray(new String[0]);
	}

	public void load(String path) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
		String line = reader.readLine();
		while (line != null) {
			trie.insert(line.split("\\s+"));
			line = reader.readLine();
		}
	}
}
