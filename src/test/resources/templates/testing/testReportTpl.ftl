<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        
		<style  type="text/css">
			body {
				background-color: black;
			}
			table, th, tr, td, span {
   				border: 1px solid black;
			}
			table {
				background-color: white;
				border-collapse: collapse;
				border-spacing: 3px;				
			}
			th, tr, td { 
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
			.fs-18 {
				font-size: 18px;
			}
			.fs-20 {
				font-size: 20px;
			}
			.font-center {
				text-align:center;
			}
			.status-label {
				font-size: 18px; 
				margin-left: 20px; 
				padding: 3px 10px;
				-webkit-border-radius: 5px;
				-moz-border-radius: 5px;
				border-radius: 5px;
			}
			.no-border {
				border-style:none;
			}
			.status-unstable {
				background-color: #FC7F26; 
				color: white;
			}
			.status-stable {
				background-color: green; 
				color: white;
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
			    	<th colspan="6" class="fs-20"><span class="no-border">${project} - Regression Suite ( ${release} )</span><span class="fs-18 no-border status-label <#if testSuiteStatus == 'STABLE'>status-stable<#else>status-unstable</#if>">${testSuiteStatus}</span></th>				     
			  	</tr>
			</thead>
			<thead>
				<tr><th colspan="6">Environment ( ${testEnvironment} ) - Platform ( ${testPlatform} ) - Browser ( ${testBrowser} )</th></tr>
			  	<#if (testJenkinsExecution)!false><tr><th colspan="6">Job ( ${testJenkinsJobName} ) - Build ( <a href="${testJenkinsBuildLink}">${testJenkinsBuildNumber}</a> ) - Job Execution Time ( ${testJenkinsExecutionTime} ) </th></tr></#if>			  	
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
						<td <#if testCase.testStatus == 'FAILURE' && !testCase.testSuiteNotification>onclick="changeText('failure_${testCase_index + 1}')" </#if>class="<#if testCase.testStatus == 'SUCCESS'>test-success<#elseif testCase.testStatus == 'FAILURE'>test-failure<#else>test-skip</#if>">${testCase.testStatus}</td>
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











