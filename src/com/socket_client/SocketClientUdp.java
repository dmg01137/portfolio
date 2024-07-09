package com.socket_client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SocketClientUdp {

    public static void main(String[] args) {
        // 서버의 주소와 포트 번호 설정
        String serverAddress = "192.168.1.35";  // 서버 IP 주소
        int serverPort = 9090;                  // 서버 포트 번호

        try {
            // DatagramSocket 생성 
            DatagramSocket clientSocket = new DatagramSocket();

            // 서버에 데이터 전송
            String buffer = "안녕 서버야! 나는 클라이언트야";
                        
            byte[] sendData = buffer.getBytes();

            // 서버의 주소 정보 설정
            InetAddress serverInetAddress = InetAddress.getByName(serverAddress);

            // DatagramPacket 생성 (데이터를 담아서 서버로 전송)
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverInetAddress, serverPort);

            // 데이터 전송
            clientSocket.send(sendPacket);
            System.out.println("메세지: " + buffer);

            // 서버로부터 데이터 수신을 위한 DatagramPacket 생성
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // 데이터 수신
            clientSocket.receive(receivePacket);
            
            // 수신된 데이터를 문자열로 변환
            String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("서버로부터 수신된 데이터: " + receivedMessage);

            // DatagramSocket 닫기
            clientSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
