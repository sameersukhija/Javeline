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
				cursor: pointer;
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
		</style>
		<script>
			function changeText( id ) {
				var result_style = document.getElementById( id ).style;
				if( result_style.display == 'none' ) { result_style.display = 'table-row'; }
				else { result_style.display = 'none'; }
			}
		</script>  
	</head>

	<body>
		<table style="margin:auto; width:80%;" class="font">
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
			     	<th style="width:180px;">Start Date</th>
			     	<th style="width:120px;">Execution Time</th>
			     	<th style="width:80px;">Status</th>
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
						<td <#if testCase.testStatus == 'FAILURE'>onclick="changeText('failure_${testCase_index + 1}')" </#if>class="<#if testCase.testStatus == 'SUCCESS'>test-success<#elseif testCase.testStatus == 'FAILURE'>test-failure<#else>test-skip</#if>">${testCase.testStatus}</td>
					</tr>
					<tr id="failure_${testCase_index + 1}" style="display: none;">
			  			<td colspan="6" style="font-size: 12px;">${testCase.testStackTrace}</td>				     
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











