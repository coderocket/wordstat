
package edu.afeka.report;

import java.io.PrintStream;
import java.util.Map;

public class Report {
	String header; 
	public Report() {
		header = "Phrase      \t\tOccurrence";
	}
	public void print_header(PrintStream out) {
		out.println(header);
		for(int i =0; i < 32; i++) 
			out.print("-");
		out.println("");
	}
	public void print_count(PrintStream out, String phrase, int count) {
		out.format("%-16s\t%d\n", phrase, count);
	}
	public void print_footer(PrintStream out) {
		for(int i =0; i < 32; i++) 
			out.print("-");
		out.println("");
	} 
}
