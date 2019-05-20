package eg.edu.alexu.csd.oop.game.ui;

import java.io.File;
import java.sql.SQLException;
import java.util.Properties;

import eg.edu.alexu.csd.oop.jdbc.cs40.concrete_classes.Connection;
import eg.edu.alexu.csd.oop.jdbc.cs40.concrete_classes.Driver;
import eg.edu.alexu.csd.oop.jdbc.cs40.concrete_classes.Statment;

public class ConnectionDB {
	private Driver dr = new Driver();
	private Connection conn;
	private String url = "jdbc:xmldb://localhost";
	private Statment stat;
	
	@SuppressWarnings("unused")
	public Statment connect() {
		Properties prop  = new Properties();
		File dbDir = new File("gameDB");
        prop.put("path", dbDir.getAbsoluteFile());
		try {
			conn = (Connection) dr.connect(url, prop);
			stat = (Statment) conn.createStatement();
		} catch (SQLException e) {
			//e.printStackTrace();
			System.out.println("Failed");
		}
		return stat;
	}

}
