package edu.afeka.controller;

import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Iterator;
import edu.afeka.document.Document;
import edu.afeka.oracle.Oracle;
import edu.afeka.histogram.Histogram;
import edu.afeka.report.Report;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		if (args.length != 1) {
			Usage();
			System.exit(1);
		}

		Document doc = new Document();
		doc.open(args[0]);
		Oracle oracle = new Oracle();
		oracle.load(Main.class.getResource("/resources/prepositions.txt").getFile());
		Histogram histogram = new Histogram();


		String word; // holds the next word to process or null if all words were processed
		boolean prefix; // true when the oracle has a non empty prefix

		word = next_word(doc);
		prefix = false;

		while (word != null) {

				while (word != null && !prefix) {
						oracle.start();
						prefix = oracle.extend_prefix(word);
						word = next_word(doc);
				}

				// word == null or prefix

				while (word != null && prefix) {
						prefix = oracle.extend_prefix(word);
						word = next_word(doc);
				}

				// word == null or !prefix

				String[] phrase = oracle.getPhrase();
				if (phrase.length != 0)
					histogram.count_phrase(phrase);
		}

		histogram.sort_counts();
		Report report = new Report();

		report.print_header(System.out);

		Iterator<Map.Entry<Integer, String> > p = histogram.iterator();
		while(p.hasNext()) {
			Map.Entry<Integer, String> e = p.next();
			report.print_count(System.out, e.getValue(), e.getKey());
		}

		report.print_footer(System.out);

		doc.close();
	}

	private static String next_word(Document doc) {
		if (doc.has_more_words()) {
			doc.next_word();
			return doc.get_word();
		}
		else
			return null;
	}

	public static void Usage() {
		System.out.println("Usage: wstat [file]");
	}
}
