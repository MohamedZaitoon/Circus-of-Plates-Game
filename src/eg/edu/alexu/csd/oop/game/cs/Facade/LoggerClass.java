package eg.edu.alexu.csd.oop.game.cs.Facade;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerClass {
	
	private final Logger log;
	public LoggerClass()
	{
		log = Logger.getLogger("MyLog");  
	    FileHandler fh;  
	    try {  
	        // This block configure the logger with handler and formatter  
	        fh = new FileHandler("MyLogFile.log");  
	        log.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
	    log.setUseParentHandlers(false);
	}
	public Logger getLog() {
		return log;
	}
}