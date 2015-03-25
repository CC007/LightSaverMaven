package com.github.cc007.lightsaver.datacontroller;

import com.github.cc007.lightsaver.message.Message;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.com.github.cc007.lightsaver.message.rabbitmq.RMQMessageProtocol;

public class ApplianceStateReceiver extends Thread {

	private final static String QUEUE_NAME = "hello";
	
	private RMQMessageProtocol rmqmp;
	
	private Message m;
	
	@Override
	public void run() {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);

			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] Received '" + message + "'");
			}
		} catch (IOException ex) {
			Logger.getLogger(ApplianceStateReceiver.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InterruptedException ex) {
			Logger.getLogger(ApplianceStateReceiver.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ShutdownSignalException ex) {
			Logger.getLogger(ApplianceStateReceiver.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ConsumerCancelledException ex) {
			Logger.getLogger(ApplianceStateReceiver.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
