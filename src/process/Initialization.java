package process;

import gui.GameFrame;

public class Initialization {
	BallRunning ballRunning;
	public Initialization() {
		System.out.println("Game Initialization");
		GameFrame.gamePanel.ballx=250;
		GameFrame.gamePanel.bally=200;
		System.out.println("Generate Blocks...");
		for(int x=33;x<480;x=x+25){
			for(int y=15;y<100;y=y+25){
				GameFrame.myblocks.addElement(new MyBlock(x, y));
			}
		}
		ballRunning=new BallRunning();
	}
}
