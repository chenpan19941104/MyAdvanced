package com.study.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author cp
 * @create 2019-12-26 15:08
 */
public class JMSProducer_topic {
    public static final String DEFAULT_BROKER_URL = "tcp://192.168.190.130:61616";
    public static final String TOPIC_NAME="mytopic";
    public static void main(String[] args) throws JMSException {
        //1 先通过ActiveMQConnectionFactory获得mq工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
        //2 获得连接connection
        Connection connection = activeMQConnectionFactory.createConnection();
        //3 启动连接准备建立会话
        connection.start();
        //3 通过connection获得Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //3.1 第一个参数叫事务，默认用false
        //3.2 第二个参数叫签收，默认自动签收
        //4 通过session创建目的地
        Topic topic = session.createTopic(TOPIC_NAME);
        //5 通过session创建消息生产者
        MessageProducer producer = session.createProducer(topic);

        for (int i = 0; i <3 ; i++) {
            //6 编写发送的消息（提问卡msg）
            TextMessage textMessage = session.createTextMessage("topic msg---" + i);
            //7 messageProducer开始发送消息到MQ
            producer.send(textMessage);
        }
        //8.释放各种资源
        producer.close();
        session.close();
        connection.close();

        System.out.println("send ok ~~~");
    }
}
