package com.socket_client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketClientUdp {
    
    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        
        try {
            // 서버의 IP 주소와 포트 번호 설정
            InetAddress serverAddress = InetAddress.getByName("192.168.1.35");  // 서버의 IP 주소
            int serverPort = 9090;  // 서버의 포트 번호
            
            // UDP 소켓 생성
            clientSocket = new DatagramSocket();
            
            // 전송할 메시지 준비
            String message = "Hello,I'm jiwon";
            byte[] sendData = message.getBytes();
            
            // UDP 패킷 생성
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            
            // 서버로 패킷 전송
            clientSocket.send(sendPacket);
            
            System.out.println("보낸 메세지: " + message);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // UDP 소켓 닫기
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        }
    }
}
