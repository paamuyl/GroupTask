


import java.util.ArrayList;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;



@ServerEndpoint("/mywebsocket/{userid}")
public class MyWebSocketChat {
    //用來存放WebSocket已連接的Socket
    static ArrayList<Session> sessions;
    static ArrayList<String> userids;
    @OnMessage
    public void onMessage(String message,Session session) throws IOException {
    	
    	for (Session s : sessions) {    //對每個連接的Client傳送訊息
            if (s.isOpen()) {
                s.getBasicRemote().sendText(message);
            }
        }
    }
 
    @OnOpen
    public void onOpen(Session session,@PathParam("userid")String userid) {
        //紀錄連接到sessions中
        System.out.println("Client connected");        
        if (sessions == null) {
            sessions = new ArrayList<Session>();
        }
        if (userids == null) {
        	userids = new ArrayList<String>();
        }
        userids.add(userid);
        sessions.add(session);
        System.out.println("Current sessions size: " + sessions.size());
    }
 
    @OnClose
    public void onClose(Session session,@PathParam("userid")String userid) {
        //將連接從sessions中移除
        System.out.println("Connection closed");
        if (sessions == null) {
            sessions = new ArrayList<Session>();
        }
        sessions.remove(session);
        userids.remove(userid);
        System.out.println("Current sessions size: " + sessions.size());
    }
}