package start;

import gui.MainPage;

//������
public class GameStart {
	public static Thread shutdownHook=new Thread(new Runnable() {//�˳�ʱִ��
		public void run() {
			System.out.println("Stop!");
		}
	});
	public static void main(String args[]){
		System.out.println("Start!");
		new MainPage();
		Runtime.getRuntime().removeShutdownHook(shutdownHook);
		Runtime.getRuntime().addShutdownHook(shutdownHook);
	}
}
