package com.lumata.e4o.system.performance;

public class RunPerformanceTest {

	public static void main(String args[]) {
	
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		PXMLRPC hi = new PXMLRPC(Thread.MAX_PRIORITY);
			
		PXMLRPC lo = new PXMLRPC(Thread.MAX_PRIORITY);
	
		lo.startThread();
		
		hi.startThread();
		
		long startTime = System.currentTimeMillis();
		
		try {
		
			Thread.sleep(8000);
		
		} catch (Exception e){}
		
		lo.stopThread();
		hi.stopThread();
		
		System.out.println(lo.getClick()+" vs. "+hi.getClick());
		long diffTime = System.currentTimeMillis() - startTime;  
        int decSeconds = (int)(diffTime % 1000 / 100);  
        int seconds = (int)(diffTime / 1000 % 60);  
        int minutes = (int)(diffTime / 60000 % 60);  
        int hours = (int)(diffTime / 3600000); 
	
        System.out.println( "dec second:" + decSeconds );
        System.out.println( "second:" + seconds );
        System.out.println( "minutes:" + minutes );
        System.out.println( "hours:" + hours );
        String s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
        System.out.println( s );
        
	}
	
}
