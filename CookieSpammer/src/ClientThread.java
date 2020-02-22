package src;

import java.awt.Robot;
import java.awt.event.InputEvent;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class ClientThread implements NativeKeyListener
{
	private static boolean isRunning;
	private static Robot robot;

	private static final int KEY_F1 = 59;
	private static final int CLICKS_PER_SECOND = 100; 

	public ClientThread() throws Exception
	{
		robot = new Robot();
	}

	public static void renderUpdate()
	{
		DisplayManager.updateDisplay();

		if(isRunning)
		{
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
			
			try{Thread.sleep(1000 / CLICKS_PER_SECOND);}
			catch (InterruptedException e) {}
		}
		else
		{
			try{Thread.sleep(30);}
			catch (InterruptedException e) {}
		}
	}	

	public void nativeKeyPressed(NativeKeyEvent keyEvent)
	{
		if(keyEvent.getKeyCode() == KEY_F1) isRunning = !isRunning;
	}

	public void nativeKeyReleased(NativeKeyEvent e) {}

	public void nativeKeyTyped(NativeKeyEvent e) {}

}