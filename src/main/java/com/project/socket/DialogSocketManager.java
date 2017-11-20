package com.project.socket;

import com.project.beans.Dialog;
import com.project.beans.Message;
import com.project.beans.XmlSocketBox;
import com.project.chat.DialogsMap;
import org.w3c.dom.Document;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/room")

public class DialogSocketManager{

    @OnOpen
    public void open(Session session) {
        if(getDialog(session).getUser1().getSession() == null){
            getDialog(session).getUser1().setSession(session);
        } else {
            getDialog(session).getUser2().setSession(session);
        }
    }

    @OnClose
    public void close(Session session){
        closeQuietly(session);
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        Document doc = XmlSocketBox.instanse.parseXmlFromString(message);

        if(session.getRequestParameterMap().get("whos").get(0).equals("onewhocalls")){
            getDialog(session).getMessages().addFirst(new Message(getDialog(session).getUser1().getLogin(),doc.getElementsByTagName("message").item(0).getAttributes().getNamedItem("textofthemessage").getNodeValue()));
        }
        if(session.getRequestParameterMap().get("whos").get(0).equals("thecallee")){
            getDialog(session).getMessages().addFirst(new Message(getDialog(session).getUser2().getLogin(),doc.getElementsByTagName("message").item(0).getAttributes().getNamedItem("textofthemessage").getNodeValue()));
        }

        try {
            getDialog(session).getUser1().getSession().getBasicRemote().sendText(getDialog(session).dequeToXmlString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            getDialog(session).getUser2().getSession().getBasicRemote().sendText(getDialog(session).dequeToXmlString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Dialog getDialog(Session session){
        return DialogsMap.dialogsUserMap.get(session.getRequestParameterMap().get("user1").get(0) + session.getRequestParameterMap().get("user2").get(0));
    }

    public void closeQuietly(Session session){
        try {
            getDialog(session).getMessages().addFirst(new Message("SERVER", "Your interlocutor has already left the dialog :("));
        } catch (Exception e) {
            //NOP
        }
        try {
            getDialog(session).getUser1().getSession().getBasicRemote().sendText(getDialog(session).dequeToXmlString());
        } catch (Exception e) {
            //NOP
        }
        try {
            getDialog(session).getUser2().getSession().getBasicRemote().sendText(getDialog(session).dequeToXmlString());
        } catch (Exception e) {
            //NOP
        }
        DialogsMap.dialogsUserMap.remove(session.getRequestParameterMap().get("user1").get(0) + session.getRequestParameterMap().get("user2").get(0));
    }

}