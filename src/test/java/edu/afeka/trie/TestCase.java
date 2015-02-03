package edu.afeka.trie;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class TestCase {

@Test
public void AddThenMemberShouldReturnTrue() { 
	String[] phrase = { "with" };
	Trie trie = new Trie();
	trie.insert(phrase);
	assertEquals(true, trie.member(phrase));
}

@Test
public void EmptyTrieShouldReturnFalse() {
	String[] phrase = { "with" };
	Trie trie = new Trie();
	assertEquals(false, trie.member(phrase));
}

@Test
public void AddTwoPhrasesThenMemberShouldReturnTrue() {
	String[] phrase0 = { "with" };
	String[] phrase1 = { "house" };
	Trie trie = new Trie();
	trie.insert(phrase0);
	trie.insert(phrase1);
	assertEquals(true, trie.member(phrase0));
	assertEquals(true, trie.member(phrase1));
}

@Test
public void AddTwoPhrasesWithCommonPrefixThenMemberShouldReturnTrue() {
	String[] phrase0 = { "with" };
	String[] phrase1 = { "with", "blue" };
	Trie trie = new Trie();
	trie.insert(phrase0);
	trie.insert(phrase1);
	assertEquals(true, trie.member(phrase0));
	assertEquals(true, trie.member(phrase1));
}

@Test
public void AddPhraseWithTwoWordsThenMemberShouldReturnFalse() {
	String[] phrase0 = { "with", "more" };
	String[] phrase1 = { "with" };
	Trie trie = new Trie();
	trie.insert(phrase0);
	assertEquals(false, trie.member(phrase1));
}

@Test
public void DumpTrieWithMultiplePhrases() {
	String[][] prepositions = { { "in" }, {"in", "place", "of"}, {"in", "point", "of"}, {"in", "spite", "of"}, {"with"}, {"with", "a", "view", "to" }, { "with", "respect", "to" }, {"in"}, {"in", "to"}, {"in", "addition", "to"} };
	int i;
	Trie trie = new Trie();
	for (i = 0; i < prepositions.length; i++) {
		trie.insert(prepositions[i]);
	}
	trie.dump(System.out);
	}	

@Test
public void TraversalShouldEqualPhrase() {
	String[][] prepositions = { { "in" }, {"in", "place", "of"}, {"in", "point", "of"}, {"in", "spite", "of"}, {"with"}, {"with", "a", "view", "to" }, { "with", "respect", "to" }, {"in"}, {"in", "to"}, {"in", "addition", "to"} };
		
	int i;
	Trie trie = new Trie();
	for (i = 0; i < prepositions.length; i++) {
		trie.insert(prepositions[i]);
	}

	String[] phrase = { "in", "place", "of" };
	
	Trie.Iterator p = trie.start();
	i = 0;
	while (i < phrase.length && p != null) { 
		p = trie.next(p, phrase[i]);
		assertEquals(true, p.word().equals(phrase[i]));
		i++;
	}	
	assertEquals(true, i == phrase.length);
	assertEquals(true, trie.complete(p));
	}
}
