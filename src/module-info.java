module Jdbc {
	requires javafx.controls;
	requires java.sql;
	requires mysql.connector.j;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	
	
	
	opens application to javafx.graphics, javafx.fxml;
}
