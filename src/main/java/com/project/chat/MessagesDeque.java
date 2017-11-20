package com.project.chat;

import com.project.beans.Message;
import com.project.beans.XmlSocketBox;

import java.util.concurrent.ConcurrentLinkedDeque;

public class MessagesDeque {
    public static ConcurrentLinkedDeque<Message> messagesDeque = new ConcurrentLinkedDeque();

    public static String dequeToXmlString(){
        checkSize();
        return XmlSocketBox.instanse.generateStringFromXml(Util.instanse.dequeToDocument(messagesDeque));
    }

    private static void checkSize(){
        if(messagesDeque.size() > 200){
            for(int i = 0; i < 10; i++){
                messagesDeque.removeLast();
            }
        }
    }

}
