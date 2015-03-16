package com.lumata.e4o.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class CustomReport extends TestNGReportListener { 

		private static final SimpleDateFormat dateFormatter = new SimpleDateFormat(" MMM d 'at' hh:mm a");

        private String reportFileName = "Test Report";
        private PrintWriter m_out;
        private int m_row;
        private Integer m_testIndex;
        private int m_methodIndex;
        private Scanner scanner;

        @Override
        public void generateReport( List<XmlSuite> xml, List<ISuite> suites, String outdir ) {
//                try {
//                        //m_out = createWriter( outdir );
                	System.out.println( "### custom report ###");
//                }
//                catch ( IOException e ) {
//                        return;
//                }
                //startHtml(m_out);
                //generateSuiteSummaryReport(suites);
                //generateMethodSummaryReport(suites);
                //generateMethodDetailReport(suites);
                //endHtml(m_out);
                //m_out.flush();
                	//m_out.close();
        }
        
}