package process;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import gui.GameFrame;
import start.GameStart;

public class GameOver {
	@SuppressWarnings("deprecation")
	public static void succeed(){
		GameFrame.gamePanel.thread.stop();
		BallRunning.thread.stop();
		GameFrame.frame.dispose();
		JFrame frame=new JFrame("Success");
		frame.getContentPane().add(new JPanel(){
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g){
				g.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
				g.drawString("Congratulations!You Won!", 10, 20);
				g.drawString("lycBouncyBall Made By lyc8503", 10, 45);
				g.drawString("[Hit Any Key to Back to Menu]", 10, 70);
			}
		});
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				frame.dispose();
				GameOver.reset();
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		frame.setVisible(true);
		frame.setSize(300,130);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("deprecation")
	public static void fail(){
		GameFrame.gamePanel.thread.stop();
		BallRunning.thread.stop();
		GameFrame.frame.dispose();
		JFrame frame=new JFrame("Failure");
		frame.getContentPane().add(new JPanel(){
			private static final long serialVersionUID = 1L;
			public void paint(Graphics g){
				g.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
				g.drawString("You Failed The Game!", 10, 20);
				g.drawString("Blocks Left:"+GameFrame.myblocks.size(), 10, 45);
				g.drawString("lycBouncyBall Made By lyc8503", 10, 70);
				g.drawString("[Hit Any Key to Back to Menu]", 10, 95);
			}
		});
		frame.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {
				frame.dispose();
				GameOver.reset();
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyPressed(KeyEvent arg0) {
			}
		});
		frame.setVisible(true);
		frame.setSize(300,150);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	@SuppressWarnings("deprecation")
	public static void reset(){
		System.out.println("Restarting...");
		BallRunning.thread.stop();
		BallRunning.thread=null;
		GameFrame.guiRefresher.Stopflag = true;
		GameFrame.guiRefresher=null;
		
		GameFrame.gamePanel.thread.stop();
		GameFrame.gamePanel=null;
		BallRunning.direction=BallRunning.RIGHT;
		BallRunning.ballslope=-1.0;
		GameFrame.myblocks=null;
		GameFrame.myblocks=new Vector<MyBlock>();
		new Thread(new Runnable() {
			public void run() {
				GameStart.main(new String[0]);
			}
		}).start();;
	}
}
