package com.project.chat;

import com.project.beans.Message;
import com.project.beans.XmlSocketBox;
import org.w3c.dom.Document;

import java.util.concurrent.ConcurrentLinkedDeque;

public class Util {
    public static Util instanse = new Util();

    public Document dequeToDocument(ConcurrentLinkedDeque<Message> messagesDeque){
        Document xmlSocketBox = XmlSocketBox.instanse.newDocument();
        xmlSocketBox = XmlSocketBox.instanse.createSocketBoxForManyMessages(xmlSocketBox);
        int k = 0;
        for (Message i: messagesDeque) {
            XmlSocketBox.instanse.appendMessage(xmlSocketBox);
            XmlSocketBox.instanse.setMessage(xmlSocketBox, i, k);
            k++;
        }
        return xmlSocketBox;
    }

}
