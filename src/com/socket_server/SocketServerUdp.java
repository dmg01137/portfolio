package com.socket_server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketServerUdp {
    
    public static void main(String[] args) {

        DatagramSocket serverSocket = null;
        
        try {
            // UDP 소켓 생성 & 바인딩 
            serverSocket = new DatagramSocket(9090);
            System.out.println("UDP 서버가 시작되었습니다.");
            
            while (true) {
                // 수신용 버퍼 준비
                byte[] buffer = new byte[1024];
                
                // UDP 패킷 수신 대기
                DatagramPacket Packet = new DatagramPacket(buffer, buffer.length);
                serverSocket.receive(Packet);  // 클라이언트로부터 데이터를 수신
                
                // 수신된 메세지 처리
                byte[] receivedData = Packet.getData();
                int length = Packet.getLength();
                String client = new String(receivedData, 0, length);
                
                // 수신된 메시지 출력
                System.out.println("클라이언트 : " + client);
                
                // 클라이언트에게 보낼 메시지
                String Message = "반가워";
                byte[] responseData = Message.getBytes();
                
                // 클라이언트의 IP 주소와 포트 번호 가져오기
                InetAddress clientAddress = Packet.getAddress();
                int clientPort = Packet.getPort();
                
                // 클라이언트에게 응답 보내기
                DatagramPacket sendPacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // UDP 소켓 닫기
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        }
    }
}
