package com.github.cc007.lightsaver.message.rabbitmq;

import com.github.cc007.lightsaver.message.Message;
import com.github.cc007.lightsaver.message.ServerStarter;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.github.cc007.lightsaver.message.rabbitmq.RMQMessageProtocol;

public class RMQMessageReceiver extends Thread {

    public static final String QUEUE_NAME = "datacontroller";

    private Message m;

    private RMQMessageProtocol rmqmp;

    public RMQMessageReceiver(RMQMessageProtocol rmqmp) {
        this.rmqmp = rmqmp;
    }

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
                rmqmp.processInput(delivery.getBody(), m);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RMQMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ShutdownSignalException ex) {
            Logger.getLogger(RMQMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ConsumerCancelledException ex) {
            Logger.getLogger(RMQMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RMQMessageReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
