package edu.afeka.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class TestCase {

@Test
public void EmptyDocumentShouldContainNoWords() throws IOException {
	Document doc = new Document();
	doc.open(getClass().getResource("/resources/empty.txt").getFile());
	assertFalse(doc.has_more_words());
	doc.close();
}

@Test
public void ThreeWordsSeparatedByWhiteSpace() throws IOException {
	Document doc = new Document();
	doc.open(getClass().getResource("/resources/3words.txt").getFile());
	assertTrue(doc.has_more_words());
	doc.next_word();
	assertTrue(doc.get_word().equals("It"));
	assertTrue(doc.has_more_words());
	doc.next_word();
	assertTrue(doc.get_word().equals("was"));
	assertTrue(doc.has_more_words());
	doc.next_word();
	assertTrue(doc.get_word().equals("the"));
	assertFalse(doc.has_more_words());
	doc.close();
}

@Test
public void ThreeWordsSeparatedByPunctuation() throws IOException {
	Document doc = new Document();
	doc.open(getClass().getResource("/resources/3punc.txt").getFile());
	assertTrue(doc.has_more_words());
	doc.next_word();
	assertTrue(doc.get_word().equals("It"));
	assertTrue(doc.has_more_words());
	doc.next_word();
	assertTrue(doc.get_word().equals("was"));
	assertTrue(doc.has_more_words());
	doc.next_word();
	assertTrue(doc.get_word().equals("the"));
	assertFalse(doc.has_more_words());
	doc.close();
}

@Test
public void ScanLargeDocument() throws IOException {
	int word_count = 0;
	Document doc = new Document();
	doc.open(getClass().getResource("/resources/warpeace.txt").getFile());
	while(doc.has_more_words()) {
		doc.next_word();
		word_count++;
	}
	doc.close();
	System.out.println(word_count);
}

}
