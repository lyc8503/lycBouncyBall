package process;

import gui.GamePanel;

public class GUIRefresher implements Runnable{
	private GamePanel panel;
	public Thread thread;
	public boolean Stopflag = false;
	public GUIRefresher(GamePanel panel) {
		this.panel = panel;
		thread=new Thread(this);
		thread.start();
	}
	public void run(){
		while(!Stopflag){
			try {
				panel.update(panel.getGraphics());
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
