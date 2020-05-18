package fr.iMath.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * JavaFX: Load a website in a canvas
 */
public class WebController implements Initializable {

	@FXML
	public WebView webView;

	/**
	 * Load a web page from a URL
	 * @param url website to show
	 */
	public void showWeb(String url) {

		// Get WebEngine via WebView
		WebEngine webEngine = webView.getEngine();

		// Load page
		webEngine.load(url);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { }
}
