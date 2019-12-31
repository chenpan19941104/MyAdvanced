package com.study.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;

/**
 * @author cp
 * @create 2019-12-25 22:37
 */
public class JMSConsumer {
    public static final String DEFAULT_BROKER_URL = "tcp://192.168.190.130:61616";
    public static final String QUEUE_NAME="myqueue";
    public static void main(String[] args) throws JMSException {
        //1 获得ActiveMQConnectionFactory
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
        //2 由ActiveMQConnectionFactory获得Connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //3 启动连接准备建立会话
        connection.start();
        //4 获得Session，两个参数先用默认
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        //4.1 是否开启事务
        //4.2 签收模式
        //5 获得目的地，此例是队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //6 获得消息消费者,消费什么内容？从哪里消费？
        MessageConsumer consumer = session.createConsumer(queue);

          /*
        异步非阻塞方式(监听器onMessage())
        订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener listener)注册一个消息监听器，
        当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法。*/
          consumer.setMessageListener(message-> {
             if(message!=null && message instanceof  TextMessage){
                 TextMessage textMessage = (TextMessage) message;
                 try {
                     System.out.println("message consumer****"+textMessage.getText());
                    //textMessage.acknowledge();
                 } catch (JMSException e) {
                     e.printStackTrace();
                 }
             }
          });
        //暂停毫秒
       try { TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) { e.printStackTrace(); }
        session.commit();
    }
}
