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
	
	public static void header() {
		/*
		<!DOCTYPE html>
		<html lang="en">
			
			<head>
			
				<!--  meta charset="UTF-8"/ -->
				<meta http-equiv="Content-Type" content="text/html; charset=windows-1252" />
				<meta http-equiv="X-UA-Compatible" content="chrome=1;">
				
				<title>Fusion</title>
				<link rel="shortcut icon" href="ui/resources/images/icons/icon-puzzle1.png" />
			   	   
			    <link rel="stylesheet" href="ui/lib/extjs/resources/css/ext-all-neptune.css"/>
			    <script src="ui/lib/extjs/ext-all-debug.js"></script>
			    
			    <script src="ui/lib/jquery/jquery-2.0.0.min.js"></script>
			
				<link rel="stylesheet" href="ui/resources/css/fusion.css"/>
			    <script type="text/javascript" src="ui/app.js"></script>
		    
			</head>
			
			<body>
			</body>

		</html>
		*/
	}
	
	public static String build() {
		
		
		
		
		return result.toString();
	}
	
}
