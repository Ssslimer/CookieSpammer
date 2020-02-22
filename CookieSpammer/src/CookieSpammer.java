package src;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.lwjgl.glfw.GLFW;

public class CookieSpammer
{	  	
	private static ClientThread clientThread;
	
	public static void main(String[] args)
	{
		init();
	    loop();
	    stop();
	}
	
	private static void init()
	{
		try
		{
			clientThread = new ClientThread();
		}
		catch(Exception e)
		{
			System.err.println("Could not create a client thread");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		Logger.getLogger(GlobalScreen.class.getPackage().getName()).setLevel(Level.OFF);

		Handler[] handlers = Logger.getLogger("").getHandlers();
		for(int i = 0; i < handlers.length; i++)
		{
			handlers[i].setLevel(Level.OFF);
		}
		
		try
		{
			GlobalScreen.registerNativeHook();
		}
		catch(NativeHookException ex)
		{
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}
		
		GlobalScreen.addNativeKeyListener(clientThread);
		DisplayManager.createDisplay();
	}
	
	private static void loop()
	{
	    while(!GLFW.glfwWindowShouldClose(DisplayManager.getWindow()))
	    {
	    	ClientThread.renderUpdate();
	    }
	}
	
	private static void stop()
	{
        DisplayManager.closeDisplay();
        System.exit(0);
	}
}