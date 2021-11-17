package gui;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import process.GUIRefresher;
import process.MyBlock;

public class GameFrame {
	public static JFrame frame;
	public static GamePanel gamePanel;
	public static GUIRefresher guiRefresher;
	public static Vector<MyBlock> myblocks=new Vector<MyBlock>();
	public GameFrame() {
		frame=new JFrame("BouncyBall");
		gamePanel=new GamePanel();
		frame.getContentPane().add(gamePanel);
		frame.setResizable(false);
		frame.setSize(500, 300);
		frame.setLocation(0,0);
		frame.addMouseMotionListener(new MyMouseListener());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

