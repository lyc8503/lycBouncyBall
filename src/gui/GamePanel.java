package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import process.GUIRefresher;
import process.Initialization;
import process.MyBlock;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	public Thread thread;
	String text="";
	boolean drawtext=false;
	boolean gamestart=false;
	public int padx=0;
	public int ballx=100;
	public int bally=100;
	public GamePanel() {
		super();
		System.out.println("Counting down...");
		thread=new Thread(this);
		thread.start();
	}
	public void paint(Graphics g2){
		Image image=createImage(500,300);
		Graphics g=image.getGraphics();
		g.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 30));
		if(drawtext){
			g.drawString(text,110 ,135);
		}
		if(gamestart){
			if(padx<49){
				padx=49;
			}
			if(padx>442){
				padx=442;
			}
			g.setColor(Color.CYAN);
			g.drawRect(padx-49,235, 100, 15);
			g.fillRect(padx-49,235, 100, 15);
			g.setColor(Color.GREEN);
			g.drawOval(ballx-10,bally-10,20,20);
			g.fillOval(ballx-10,bally-10,20,20);
			g.setColor(Color.LIGHT_GRAY);
			GameFrame.frame.setTitle("BouncyBall    Blocks Left:"+GameFrame.myblocks.size());
			@SuppressWarnings("unchecked")
			Vector<MyBlock> safecopy=(Vector<MyBlock>) GameFrame.myblocks.clone();
			for(MyBlock block:safecopy){
				g.drawRect(block.getX()-10, block.getY()-10,20,20);
				g.fillRect(block.getX()-10, block.getY()-10,20,20);
			}
		}
		g2.drawImage(image,0,0,null);
	}
	public void run() {
		try {
			drawtext=true;
			text="Game Start in 3...";
			GameFrame.frame.update(GameFrame.frame.getGraphics());
			Thread.sleep(1000);
			text="Game Start in 2...";
			GameFrame.frame.update(GameFrame.frame.getGraphics());
			Thread.sleep(1000);
			text="Game Start in 1...";
			GameFrame.frame.update(GameFrame.frame.getGraphics());
			Thread.sleep(1000);
			drawtext=false;
			gamestart=true;
			new Initialization();
			if(GameFrame.guiRefresher==null){
				GameFrame.guiRefresher=new GUIRefresher(this);
			}
		} catch (Exception e) {
		}
	}
}

class MyMouseListener implements MouseMotionListener{
	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
		GameFrame.gamePanel.padx=e.getX();
	}
}