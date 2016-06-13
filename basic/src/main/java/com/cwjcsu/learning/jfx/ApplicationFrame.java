/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月30日  Created
 */
package com.cwjcsu.learning.jfx;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

/**
 * 
 * @author atlas
 *
 */
public class ApplicationFrame extends JFrame {

	JFXPanel javafxPanel;
	WebView webComponent;
	JPanel mainPanel;

	JTextField urlField;
	JButton goButton;

	int containerHeight = 625;
	int containerWidth = 825;

	public ApplicationFrame() {
		super();
		javafxPanel = new JFXPanel();

		initSwingComponents();

		loadJavaFXScene();
	}

	/**
	 * Instantiate the Swing compoents to be used
	 */
	private void initSwingComponents() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(javafxPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel urlPanel = new JPanel(new FlowLayout());
		urlField = new JTextField();
		urlField.setColumns(50);
		urlPanel.add(urlField);
		goButton = new JButton("Go");

		/**
		 * Handling the loading of new URL, when the user enters the URL and
		 * clicks on Go button.
		 */
		goButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Platform.runLater(new Runnable() {
					public void run() {
						String url = urlField.getText();
						if (url != null && url.length() > 0) {
							if (!url.startsWith("http://")) {
								url += "http://" + url;
							}
							webComponent.getEngine().load(url);
						}
					}
				});

			}
		});

		urlPanel.add(goButton);
		mainPanel.add(urlPanel, BorderLayout.NORTH);

		this.add(mainPanel);
		// this.setDefaultCloseOperation();
		this.setSize(containerWidth, containerHeight);
	}

	/**
	 * Instantiate the JavaFX Components in the JavaFX Application Thread.
	 */
	private void loadJavaFXScene() {
		Platform.runLater(new Runnable() {
			public void run() {

				BorderPane borderPane = new BorderPane();
				webComponent = new WebView();

				webComponent.getEngine().load("http://127.0.0.1:9999");

				borderPane.setCenter(webComponent);
				Scene scene = new Scene(borderPane, 600, 800);
				javafxPanel.setScene(scene);

			}
		});
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ApplicationFrame mainFrame = new ApplicationFrame();
				mainFrame.setVisible(true);
			}
		});

	}
}
