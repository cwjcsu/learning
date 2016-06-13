package com.cwjcsu.learning.network.multicast;

import java.awt.Checkbox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * 
 * @author atlas
 * @date 2012-9-28
 */
public class UDPMulticastClient {
	JFrame frame;
	JPanel panel;
	JTextField field1, field2, field3, field4, field5, field6, field7, field8;
	JTextArea area;
	JScrollPane pane;
	JLabel label;
	JButton button;
	JList list;
	Checkbox check1, check2, check3, check4;

	public static void main(String[] args) {
		UDPMulticastClient u = new UDPMulticastClient();
	}

	public UDPMulticastClient() {
		frame = new JFrame("UDP Broadcast Client");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		panel = new JPanel();
		panel.setLayout(null);
		label = new JLabel("Destination IP");
		label.setBounds(80, 5, 100, 30);
		panel.add(label);
		check1 = new Checkbox();
		check1.setBounds(5, 40, 20, 20);
		panel.add(check1);
		label = new JLabel("Client 1 :");
		label.setBounds(25, 35, 60, 30);
		panel.add(label);
		field1 = new JTextField(20);
		field1.setBounds(80, 35, 120, 20);
		panel.add(field1);
		label = new JLabel("Destination Port");
		label.setBounds(255, 5, 100, 30);
		panel.add(label);
		field2 = new JTextField(10);
		field2.setBounds(255, 35, 100, 20);
		panel.add(field2);
		check2 = new Checkbox();
		check2.setBounds(5, 70, 20, 20);
		panel.add(check2);
		label = new JLabel("Client 2 :");
		label.setBounds(25, 65, 60, 30);
		panel.add(label);
		field3 = new JTextField(20);
		field3.setBounds(80, 65, 120, 20);
		panel.add(field3);
		field4 = new JTextField(10);
		field4.setBounds(255, 65, 100, 20);
		panel.add(field4);
		check3 = new Checkbox();
		check3.setBounds(5, 100, 20, 20);
		panel.add(check3);
		label = new JLabel("Client 3 :");
		label.setBounds(25, 95, 60, 30);
		panel.add(label);
		field5 = new JTextField(20);
		field5.setBounds(80, 95, 120, 20);
		panel.add(field5);
		field6 = new JTextField(10);
		field6.setBounds(255, 95, 100, 20);
		panel.add(field6);
		check4 = new Checkbox();
		check4.setBounds(5, 130, 20, 20);
		panel.add(check4);
		label = new JLabel("Client 4 :");
		label.setBounds(25, 125, 60, 30);
		panel.add(label);
		field7 = new JTextField(20);
		field7.setBounds(80, 125, 120, 20);
		panel.add(field7);
		field8 = new JTextField(10);
		field8.setBounds(255, 125, 100, 20);
		panel.add(field8);
		label = new JLabel("Message:");
		label.setBounds(10, 160, 80, 30);
		panel.add(label);
		area = new JTextArea();
		pane = new JScrollPane(area);
		pane.setBounds(10, 190, 300, 200);
		panel.add(pane);
		button = new JButton("Send");
		button.setBounds(235, 410, 75, 30);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SendRequest();
			}
		});
		panel.add(button);
		frame.add(panel);
		frame.setSize(400, 500);
		frame.setVisible(true);
	}

	public class SendRequest {
		SendRequest() {
			try {
				boolean b = true;
				if (check1.getState() == b) {
					String dip = field1.getText();
					InetAddress address = InetAddress.getByName(dip);
					MulticastSocket socket = new MulticastSocket();
					socket.joinGroup(address);
					String port = field2.getText();
					int pnum = Integer.parseInt(port);
					String mess = area.getText();
					byte message[] = mess.getBytes();
					DatagramPacket packet = new DatagramPacket

					(message, message.length, address, pnum);
					socket.send(packet);
					area.setText("");
					// For Received message
					DatagramPacket packet1 = new DatagramPacket

					(message, message.length);
					socket.receive(packet1);
					String recmessage = new String(packet1.getData());
					area.append("Received from server: " + recmessage);
					socket.close();
				}
				if (check2.getState() == b) {
					String dip = field3.getText();
					InetAddress address = InetAddress.getByName(dip);
					MulticastSocket socket = new MulticastSocket();
					socket.joinGroup(address);
					String port = field4.getText();
					int pnum = Integer.parseInt(port);
					String mess = area.getText();
					byte message[] = mess.getBytes();
					DatagramPacket packet = new DatagramPacket(message,
							message.length, address, pnum);
					socket.send(packet);
					area.setText("");
					// For Received message
					DatagramPacket packet1 = new DatagramPacket(message,
							message.length);
					socket.receive(packet1);
					String recmessage = new String(packet1.getData());
					area.append("Received from server: " + recmessage);
					socket.close();
				}
				if (check3.getState() == b) {
					String dip = field5.getText();
					InetAddress address = InetAddress.getByName(dip);
					MulticastSocket socket = new MulticastSocket();
					socket.joinGroup(address);
					String port = field6.getText();
					int pnum = Integer.parseInt(port);
					String mess = area.getText();
					byte message[] = mess.getBytes();
					DatagramPacket packet = new DatagramPacket

					(message, message.length, address, pnum);
					socket.send(packet);
					area.setText("");
					// For Received message
					DatagramPacket packet1 = new DatagramPacket(message,
							message.length);
					socket.receive(packet1);
					String recmessage = new String(packet1.getData());
					area.append("Received from server: " + recmessage);
					socket.close();
				}
				if (check4.getState() == b) {
					String dip = field7.getText();
					InetAddress address = InetAddress.getByName(dip);
					MulticastSocket socket = new MulticastSocket();
					socket.joinGroup(address);
					String port = field8.getText();
					int pnum = Integer.parseInt(port);
					String mess = area.getText();
					byte message[] = mess.getBytes();
					DatagramPacket packet = new DatagramPacket

					(message, message.length, address, pnum);
					socket.send(packet);
					area.setText("");
					// For Received message
					DatagramPacket packet1 = new DatagramPacket(message,
							message.length);
					socket.receive(packet1);
					String recmessage = new String(packet1.getData());
					area.append("Received from server: " + recmessage);
					socket.close();
				}
			} catch (IOException io) {
			}
		}
	}
}
