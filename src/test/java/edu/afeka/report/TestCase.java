package edu.afeka.report;

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
		public void EmptyReport() throws IOException {
			Report report = new Report();
			report.print_header(System.out);
			report.print_footer(System.out);
			assertTrue(true);
		}
		@Test
		public void ReportWithThreeEntries() throws IOException {
			Report report = new Report();
			report.print_header(System.out);
			report.print_count(System.out, "with ", 15);
			report.print_count(System.out, "because of ", 5);
			report.print_count(System.out, "according to ", 14);
			report.print_footer(System.out);
			assertTrue(true);
		}
}

