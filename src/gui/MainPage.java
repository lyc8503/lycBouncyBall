package gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainPage {
	public static MyPanel panel;
	public static JFrame frame;
	public MainPage() {
		frame=new JFrame("lycBouncyBall");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel=new MyPanel();
		frame.getContentPane().add(panel);
		frame.addKeyListener(new MyActionListener());
		frame.setLocation(0, 0);
		frame.setSize(200, 255);
		frame.setResizable(false);
		frame.setVisible(true);
		System.out.println("Main Page Ready!");
	}
}

class MyPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	public static int selected=1;
	public MyPanel() {
		super();
	}
	public void paint(Graphics g){
		g.setFont(new Font("Gill Sans MT Condensed", Font.BOLD, 30));
		g.drawString("lycBouncyBall", 25, 50);
		g.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
		if(selected==1){
			g.drawString("[Start Game]", 40, 100);
		}else{
			g.drawString("Start Game", 45, 100);
		}
		if(selected==2){
			g.drawString("[Help]", 63, 130);
		}else{
			g.drawString("Help", 68, 130);
		}
		if(selected==3){
			g.drawString("[About]", 58, 160);
		}else{
			g.drawString("About", 63, 160);
		}
		if(selected==4){
			g.drawString("[Exit]", 66, 190);
		}else{
			g.drawString("Exit", 71, 190);
		}
		g.setFont(new Font("Yu Gothic UI Semilight",2, 13));
		g.drawString("Beta0.1  Made By lyc8503", 3, 220);
	}
}

class MyActionListener implements KeyListener{
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 38:
//			System.out.println("UP");
			MyPanel.selected--;
			if(MyPanel.selected<1){
				MyPanel.selected=1;
			}
			MainPage.frame.update(MainPage.frame.getGraphics());
			break;
		case 40:
//			System.out.println("DOWN");
			MyPanel.selected++;
			if(MyPanel.selected>4){
				MyPanel.selected=4;
			}
			MainPage.frame.update(MainPage.frame.getGraphics());
			break;
		case 10:
//			System.out.println("ENTER");
			switch (MyPanel.selected) {
			case 1:
				System.out.println("GAME START!");
				new GameFrame();
				MainPage.frame.setVisible(false);
				break;
			case 2:
				System.out.println("HelpMenu");
				JDialog dialog=new JDialog(MainPage.frame,true);
				dialog.getContentPane().add(new JPanel(){
					private static final long serialVersionUID = 1L;
					public void paint(Graphics g){
						g.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 15));
						g.drawString("Game Control:", 10, 20);
						g.drawString("A. Use Mouse to Control in game", 10, 50);
						g.drawString("B. Use UP and DOWN and ENTER to Control in menu", 10, 80);
						g.drawString("That's all. Have Fun!", 10, 110);
						g.drawString("[Hit any key to close this dialog]", 10, 140);
					}
				});
				dialog.setLocation(0, 0);
				dialog.setSize(450,200);
				dialog.addKeyListener(new KeyListener() {
					public void keyTyped(KeyEvent arg0) {
						dialog.dispose();
					}
					public void keyReleased(KeyEvent arg0) {
					}
					public void keyPressed(KeyEvent arg0) {
					}
				});
				dialog.setVisible(true);
				break;
			case 3:
				JOptionPane.showMessageDialog(MainPage.frame,"µ¯µ¯Çò Beta 1.0\n×÷Õß:lyc8503\nBug·´À¡:lyc8503@gmail.com");
				break;
			case 4:
				System.exit(0);
				break;
			default:
				break;
			}
		default:
			break;
		}
	}
	public void keyReleased(KeyEvent e) {	
	}
	public void keyTyped(KeyEvent e) {
	}
}