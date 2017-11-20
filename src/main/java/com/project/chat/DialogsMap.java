package com.project.chat;

import com.project.beans.Dialog;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DialogsMap {
    public static Map<String, Dialog> dialogsUserMap = new ConcurrentHashMap();
}
