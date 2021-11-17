package process;

import java.util.Random;

import gui.GameFrame;

public class BallRunning implements Runnable{
	public static Thread thread;
	public static final int LEFT=1;
	public static final int RIGHT=2;
	public static double ballslope=-1.0;//斜率
	public static int direction=RIGHT;
	public BallRunning() {
		thread=new Thread(this);
		thread.start();
	}
	public static void hit(){
		if(GameFrame.gamePanel.ballx>490||GameFrame.gamePanel.ballx<10){
			if(direction==RIGHT){//反转方向
				direction=LEFT;
			}else{
				direction=RIGHT;
			}
		}
		if(GameFrame.gamePanel.ballx>490){
			GameFrame.gamePanel.ballx=490;
		}
		if(GameFrame.gamePanel.ballx<10){
			GameFrame.gamePanel.ballx=10;
		}
		if(GameFrame.gamePanel.bally<10){
			GameFrame.gamePanel.bally=10;
		}
		if(GameFrame.gamePanel.bally>250){
			System.out.println("Game Over!");
			new Thread(new Runnable() {
				public void run() {
					GameOver.fail();
				}
			}).start();
			GameFrame.gamePanel.bally=250;
		}
//		System.out.println(direction);
		ballslope=-ballslope;
		System.out.println("Now slope:"+ballslope);
		Random random=new Random();//随机斜率变化
		double deltaslope=random.nextDouble()/10;
		System.out.println("New Delta:"+deltaslope);
		ballslope+=deltaslope;
		if(ballslope<1&&ballslope>0){
			ballslope=1;
		}
		if(ballslope>-1&&ballslope<0){
			ballslope=-1;
		}
	}
	public void run(){
		System.out.println("BallRunning Processing...");
		while(true){
			if(GameFrame.myblocks.size()==0){
				new Thread(new Runnable() {
					public void run() {
						GameOver.succeed();
					}
				}).start();
			}
			if(direction==RIGHT){
				GameFrame.gamePanel.ballx++;
				GameFrame.gamePanel.bally=(int) (GameFrame.gamePanel.bally+ballslope);
			}else{
				GameFrame.gamePanel.ballx--;
				GameFrame.gamePanel.bally=(int) (GameFrame.gamePanel.bally-ballslope);
			}
			if(GameFrame.gamePanel.ballx>490||GameFrame.gamePanel.ballx<10||GameFrame.gamePanel.bally>250||GameFrame.gamePanel.bally<10){
				hit();
			}
			if(GameFrame.gamePanel.bally>225&&GameFrame.gamePanel.bally<250){
				if(GameFrame.gamePanel.padx-50<GameFrame.gamePanel.ballx&&GameFrame.gamePanel.padx+50>GameFrame.gamePanel.ballx){
					System.out.println("Hit pad");
					hit();
				}
			}
			outer:for(MyBlock block:GameFrame.myblocks){
				int XDistance=Math.abs(block.getX()-GameFrame.gamePanel.ballx);
				int YDistance=Math.abs(block.getY()-GameFrame.gamePanel.bally);
				int Distance=(int) Math.sqrt(XDistance*XDistance+YDistance*YDistance);
				if(Distance<20){
					System.out.println("Hit Block:("+block.getX()+","+block.getY()+") Ball:("+GameFrame.gamePanel.ballx+","+GameFrame.gamePanel.bally+")"+" XDistance:"+XDistance+" YDistance:"+YDistance+" Distance:"+Distance);
					System.out.println("Blocks Left:"+GameFrame.myblocks.size());
					hit();
					GameFrame.myblocks.remove(block);
					break outer;
				}
			}
			try {
				Thread.sleep(1000/180);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}