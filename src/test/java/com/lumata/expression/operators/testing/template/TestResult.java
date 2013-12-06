package com.lumata.expression.operators.testing.template;

public class TestResult {

	private static StringBuilder result = new StringBuilder();
	
	public enum ResultType { PASSED, FAILS }
	
	public static void update( String subject, ResultType resultType, String note ) {
		
		result.append( "\t\t<tr>" )
				.append( "<td>" ).append( subject ).append( "</td>" )
				.append( "<td>" ).append( resultType ).append( "</td>" )
				.append( "<td>" ).append( note ).append( "</td>" )
				.append( "</tr>\n" );		
				
	}
	
	public static String build() {
		
		
		
		
		return result.toString();
	}
	
}
