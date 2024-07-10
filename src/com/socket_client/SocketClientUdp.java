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
            String message = "Hello, I'm jiwon";
            byte[] sendData = message.getBytes();
            
            // UDP 패킷 생성 및 서버로 전송
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
            clientSocket.send(sendPacket);
            System.out.println("보낸 메세지: " + message);
            
            // 서버로부터의 응답을 받기 위한 패킷 준비
            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            
            // 서버에서 패킷 수신
            clientSocket.receive(receivePacket);
            
            // 수신된 데이터 추출 및 출력
            byte[] receivedData = receivePacket.getData();
            int length = receivePacket.getLength();
            String serverMessage = new String(receivedData, 0, length);
            System.out.println("서버: " + serverMessage);
            
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
