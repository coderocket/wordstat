package edu.afeka.histogram;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.Map;
import java.util.Iterator;
import java.io.IOException;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class TestCase {

@Test
public void EmptyHistogramHasNoWords() throws IOException {
	Histogram histogram = new Histogram();
	histogram.sort_counts();
	Iterator<Map.Entry<Integer, String> > p = histogram.iterator();
	assertFalse(p.hasNext());
}

@Test
public void HistogramWithTwoPhrasesAppearingOnce() throws IOException {
	String[] phrase0 = { "according", "to" };
	String[] phrase1 = { "ahead", "of" };

	Histogram histogram = new Histogram();
	histogram.count_phrase(phrase0);
	histogram.count_phrase(phrase1);
	histogram.sort_counts();

	Iterator<Map.Entry<Integer, String> > p = histogram.iterator();
	assertTrue(p.hasNext());
	Map.Entry<Integer, String> e0 = p.next();
	assertTrue(e0.getKey() == 1);
	assertTrue(p.hasNext());
	Map.Entry<Integer, String> e1 = p.next();
	assertTrue(e1.getKey() == 1);
	assertFalse(p.hasNext());
}

@Test
public void HistogramWithOnePhraseAppearingTwice() throws IOException {
	String[] phrase0 = { "according", "to" };

	Histogram histogram = new Histogram();
	histogram.count_phrase(phrase0);
	histogram.count_phrase(phrase0);
	histogram.sort_counts();

	Iterator<Map.Entry<Integer, String> > p = histogram.iterator();
	assertTrue(p.hasNext());
	Map.Entry<Integer, String> e0 = p.next();
	assertTrue(e0.getKey() == 2);
	assertFalse(p.hasNext());
}

@Test
public void HistogramSortedByFrequency() {

	String[] phrase0 = { "according", "to" };
	String[] phrase1 = { "ahead", "of" };

	Histogram histogram = new Histogram();
	histogram.count_phrase(phrase0);
	histogram.count_phrase(phrase0);
	histogram.count_phrase(phrase1);
	histogram.sort_counts();

	histogram.dump(System.out);

	Iterator<Map.Entry<Integer, String> > p = histogram.iterator();
	assertTrue(p.hasNext());
	Map.Entry<Integer, String> e0 = p.next();
	assertTrue(e0.getKey() == 2);
	assertTrue(e0.getValue().equals("according to ")); 
	assertTrue(p.hasNext());
	Map.Entry<Integer, String> e1 = p.next();
	assertTrue(e1.getKey() == 1);
	assertTrue(e1.getValue().equals("ahead of ")); 
	assertFalse(p.hasNext());
}
}

