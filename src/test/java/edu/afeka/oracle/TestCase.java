package edu.afeka.oracle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class TestCase {

@Test
public void WordNotInOracleShouldFailToExtend() {
    String[][] prepositions = { { "in" }, {"in", "place", "of"}, {"in", "point", "of"}, {"in", "spite", "of"}, {"with"}, {"with", "a", "view", "to" }, { "with", "respect", "to" }, {"in"}, {"in", "to"}, {"in", "addition", "to"} };
	Oracle oracle = new Oracle();
	oracle.addPhrases(prepositions);
	oracle.start();
	assertEquals(false, oracle.extend_prefix("who"));
}

@Test
public void WordInOracleShouldExtend() {
    String[][] prepositions = { { "in" }, {"in", "place", "of"}, {"in", "point", "of"}, {"in", "spite", "of"}, {"with"}, {"with", "a", "view", "to" }, { "with", "respect", "to" }, {"in"}, {"in", "to"}, {"in", "addition", "to"} };
	Oracle oracle = new Oracle();
	oracle.addPhrases(prepositions);
	oracle.start();
	assertEquals(true, oracle.extend_prefix("with"));
	assertEquals(true, oracle.extend_prefix("a"));
	assertEquals(true, oracle.extend_prefix("view"));
}

@Test
public void CompletePhrase() {
    String[][] prepositions = { { "in" }, {"in", "place", "of"}, {"in", "point", "of"}, {"in", "spite", "of"}, {"with"}, {"with", "a", "view", "to" }, { "with", "respect", "to" }, {"in"}, {"in", "to"}, {"in", "addition", "to"} };
	Oracle oracle = new Oracle();
	oracle.addPhrases(prepositions);
	oracle.start();
	assertEquals(true, oracle.extend_prefix("with"));
	assertEquals(true, oracle.getPhrase().length == 1);
	assertEquals(true, oracle.getPhrase()[0].equals("with"));
	assertEquals(true, oracle.extend_prefix("respect"));
	assertEquals(true, oracle.getPhrase().length == 1);
	assertEquals(true, oracle.getPhrase()[0].equals("with"));
	assertEquals(true, oracle.extend_prefix("to"));
	assertEquals(true, oracle.getPhrase().length == 3);
	assertEquals(true, oracle.getPhrase()[0].equals("with"));
	assertEquals(true, oracle.getPhrase()[1].equals("respect"));
	assertEquals(true, oracle.getPhrase()[2].equals("to"));
}

@Test
public void LoadPrepositionsFromResourceFile() throws IOException {
	Oracle oracle = new Oracle();
	oracle.load(getClass().getResource("/resources/prepositions.txt").getFile());
	oracle.dump(System.out);
	assertTrue(true);
}
}
