package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import com.yychat.model.Message;

public class ServerReceiverThread extends Thread{
	Socket s;
	HashMap hmSocket;
	
	public ServerReceiverThread(Socket s,HashMap hmSocket){
		this.s=s;
		this.hmSocket=hmSocket;
	}
		
	public void run(){
		ObjectInputStream ois;
		while(true){
			try {
			//接收Message信息
			ois=new ObjectInputStream(s.getInputStream());
			Message mess=(Message)ois.readObject();
			System.out.println("等待用户发送聊天信息");
			System.out.println(mess.getSender()+"对"+mess.getReceiver()+"说:"+mess.getContent());
			//转发Message信息
			if(mess.getMessageType().equals(Message.message_Common)){
				Socket s1=(Socket)hmSocket.get(mess.getReceiver());		 		
				ObjectOutputStream oos=new ObjectOutputStream(s1.getOutputStream());
				oos.writeObject(mess);//转发Message
				System.out.println("服务器转发了信息"+mess.getSender()+"对"+mess.getReceiver()+"说:"+mess.getContent());
			}
		} catch (IOException | ClassNotFoundException e) {		
			e.printStackTrace();
		}
		}
		
	}
}