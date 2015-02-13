<!DOCTYPE html>
<html>

	<head>
		<style  type="text/css">
			body {
				background-color: black;
			}
			table, th, td, span {
   				border: 1px solid black;
			}
			table {
				background-color: white;
				border-collapse: collapse;
				border-spacing: 3px;				
			}
			th, td { 
    			padding: 5px;
			}			
			.test-success {
				background-color: green;
				color: white;
			}
			.test-failure {
				background-color: red;
				color: white;
			}
			.test-skip {
				background-color: gray;
				color: white;
			}
			.font {
				font-family: "Trebuchet MS", Helvetica, sans-serif;
			}
			.font-center {
				text-align:center;
			}
			.hover {
			    position:relative;
			    top:50px;			    			    			    
			}
			.tooltip {
			  	top:-10px;			  				  	
			  	background-color:black;
			  	color:white;
			  	border-radius:5px;
			  	opacity:0;
			  	position:absolute;
			  	-webkit-transition: opacity 0.5s;
			  	-moz-transition:  opacity 0.5s;
			  	-ms-transition: opacity 0.5s;
			  	-o-transition:  opacity 0.5s;
			  	transition:  opacity 0.5s;			  				  	
			}
			.hover:hover .tooltip {
				opacity:1;
				left:-500px;
				z-index: 1000;
			}  
		</style>
	</head>

	<body>
		<table style="margin:auto;" class="font">
			<thead>
			 	<tr>
			    	<th colspan="6" style="font-size: 20px;">${project} - Regression Suite ( ${release} )</th>				     
			  	</tr>
			</thead>
			<thead>
				<tr><th colspan="6">Start Date ( ${testSuiteStartDate} ) - End Date ( ${testSuiteEndDate} ) - Execution Time ( ${testSuiteExecutionTime} )</th></tr>
			  	<tr><th colspan="6">Total ( ${total} ) - Success ( ${success} ) - Failure ( ${failure} ) - Skip ( ${skip} )</th></tr>
			</thead>
			<thead>
			  	<tr>
			  		<th colspan="2">Class Name</th>
			     	<th>Method Name</th>
			     	<th>Start Date</th>
			     	<th>Execution Time</th>
			     	<th>Status</th>
			  	</tr>
			</thead>
			<tbody>
				<#list testSuite as testCase>
					<tr>
						<td class="font-center">${testCase_index + 1}</td>
						<td>${testCase.testClassName}</td>
						<td>${testCase.testMethodName}</td>
						<td>${testCase.testStartDate}</td>
						<td>${testCase.testExecutionTime} ms</td>
						<td class="hover <#if testCase.testStatus == 'SUCCESS'>test-success<#elseif testCase.testStatus == 'FAILURE'>test-failure<#else>test-skip</#if>">${testCase.testStatus}<span class="tooltip">${testCase.testStackTrace}</span></td>
					</tr>
				</#list>
			</tbody>
			<tbody>
			</tbody>
			<tbody>
			</tbody>
		</table>
	</body>
</html>











