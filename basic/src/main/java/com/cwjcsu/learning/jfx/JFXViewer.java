package com.cwjcsu.learning.jfx;
/*$Id: $
 * author                     date    comment
 * cwjcsu@gmail.com  2015年11月30日  Created
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
public class JFXViewer extends JFrame {

	JFXPanel javafxPanel;
	WebView webComponent;
	JPanel mainPanel;

	JTextField urlField;
	JButton goButton;

	int containerHeight = 625;
	int containerWidth = 825;

	int viewHeight = 600;
	int viewWidth = 800;

	int W = 1920;
	int H = 1080;
	double zoomValue = 2.0;

	public JFXViewer() {
		super();
		javafxPanel = new JFXPanel();
		initSwingComponents();

		loadJavaFXScene();
	}

	public static double getScale() {
		return javafx.stage.Screen.getPrimary().getDpi() / 96.0;
	}

	private void calculateSize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth();
		int height = gd.getDisplayMode().getHeight();
		containerHeight = (int) (containerHeight * zoomValue + 0.5);
		viewWidth = (int) ((viewWidth * 1.0 / W) * width);

		viewHeight = (int) ((viewHeight * 1.0 / H) * height);
		containerWidth = (int) ((containerWidth * 1.0 / W) * width);

		log("Screnn size [" + width + "," + height + "],window scale to [" + containerWidth + "," + containerHeight
				+ "],view scale to [" + viewWidth + "," + viewHeight + "]");
	}

	private void log(Object msg) {
		System.out.println(msg);
	}

	/**
	 * Instantiate the Swing compoents to be used
	 */
	private void initSwingComponents() {
		calculateSize();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(javafxPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel urlPanel = new JPanel(new FlowLayout());
		urlField = new JTextField();
		urlField.setColumns(50);
		urlPanel.add(urlField);
		goButton = new JButton("加载");
		goButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setDefaultSize();
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

	public void setDefaultSize() {

		Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
		Object[] keys = keySet.toArray(new Object[keySet.size()]);

		for (Object key : keys) {

			if (key != null && key.toString().toLowerCase().contains("font")) {
				System.out.println(key);
				Font font = UIManager.getDefaults().getFont(key);
				if (font != null) {
					font = font.deriveFont((float) (font.getSize() * zoomValue));
					UIManager.put(key, font);
				}
			}
		}

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
				Scene scene = new Scene(borderPane, viewHeight, viewWidth);
				privateInfo();
				log("zoomValue:" + getScale());
				webComponent.setScaleX(getScale());
				webComponent.setScaleY(getScale());
				javafxPanel.setScene(scene);
			}

		});
	}
	private void privateInfo() {
		log(javafx.stage.Screen.getPrimary());
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFXViewer mainFrame = new JFXViewer();
				mainFrame.setVisible(true);
			}
		});

	}
}
