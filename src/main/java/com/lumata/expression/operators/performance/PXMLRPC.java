package com.lumata.expression.operators.performance;

public class PXMLRPC implements Runnable {

	private int click = 0;
	private Thread t;
	private boolean running = true;
	
	public PXMLRPC(int p) {
	
		t = new Thread(this);
		t.setPriority(p);
	}
	
	public int getClick(){
		
		return click;
	
	}
	
	public void run() {
	
		while (running) {
			click++;
		}

	}
	
	public void stopThread() {
		
		running = false;
	
	}
	
	public void startThread() {
	
		t.start();

	}
	
}
