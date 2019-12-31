package com.study.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author cp
 * @create 2019-12-25 22:16
 */
public class JMSProducer {
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
        //关闭事务且自动签收。
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4.1 是否开启事务
        //4.2 签收模式
        //5 获得目的地，此例是队列
        Queue queue = session.createQueue(QUEUE_NAME);
        //6 获得消息生产者,生产什么内容？生产出来放在哪里？
        MessageProducer producer = session.createProducer(queue);
        //默认为持久化数据
        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //7 生产message内容
        for (int i = 0; i < 3; i++) {
            TextMessage textMessage = session.createTextMessage("msg----"+i);
            producer.send(textMessage);
        }
        //8 释放各种连接和资源
        producer.close();
        //session.commit();
        session.close();
        connection.close();

        System.out.println("msg send ok~~~");
    }
}
