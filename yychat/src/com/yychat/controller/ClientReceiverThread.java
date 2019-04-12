package com.yychat.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychat.view.FriendChat1;
import com.yychat.view.FriendList;

public class ClientReceiverThread extends Thread{
	Socket s;
	
	public ClientReceiverThread(Socket s){
		this.s=s;
	}
	
	public void run(){
		ObjectInputStream ois;
		Message mess;
		while(true){
			try {
				//接收服务器转发过来的Message
				ois = new ObjectInputStream(s.getInputStream());				
				mess=(Message)ois.readObject();//等待Server发送Message,阻塞	
				String chatMessageString=(mess.getSender()+"对"+mess.getReceiver()+"说"+mess.getContent()+"\r\n");
				System.out.println(chatMessageString);
				//希望聊天信息在好友的聊天界面上显示出来，该怎么实现的问题？
				FriendChat1 frinedChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
				frinedChat1.appendJta(chatMessageString);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}	
	}
}
