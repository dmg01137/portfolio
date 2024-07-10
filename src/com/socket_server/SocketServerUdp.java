package com.socket_server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class SocketServerUdp {
    
    public static void main(String[] args) {

    	//초기화 
    	DatagramSocket serverSocket = null;
        
        try {
            // UDP 소켓 생성 & 바인딩 
            serverSocket = new DatagramSocket(9090);
            System.out.println("UDP 서버가 시작되었습니다.");
            
            while (true) {
                // 수신용 버퍼 준비
                byte[] Buffer = new byte[1024];
                
                // UDP 패킷 수신 대기
                DatagramPacket receivePacket = new DatagramPacket(Buffer, Buffer.length);
                serverSocket.receive(receivePacket);  // 클라이언트로부터 데이터를 수신
                
                // 수신된 메세지 처리
                byte[] receivedData = receivePacket.getData();
                int length = receivePacket.getLength();
                String test1 = new String(receivedData, 0, length);
                
                // 수신된 메시지 출력
                System.out.println("Client message : " + test1);
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
