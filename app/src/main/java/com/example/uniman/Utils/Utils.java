package com.example.uniman.Utils;

import com.example.uniman.Model.Student;
import com.example.uniman.Model.Teacher;
import com.example.uniman.Model.User;

public class Utils {
    public static User user = new User();
    public static Teacher teacher = new Teacher();
    public static final String BASE = "http://192.168.43.216:7070/unimain/";
    // chat
    public static String RECEIVEDI = "2";//
    public static final String key_send = "send_id";// nguoi gui
    public static final String key_received = "received";// nguoi nhan
    public static final String key_message = "message";//noi dung
    public static final String key_date = "date";// thoi gian
    public static final String key_path = "chat";
}
