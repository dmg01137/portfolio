package com.example.demo.service;

import org.springframework.stereotype.Component;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Component
public class UDPClient {

    private static final String SERVER_IP = "192.168.1.35"; // UDP 서버의 IP 주소
    private static final int SERVER_PORT = 9090; // UDP 서버의 포트 번호

    private InetAddress serverAddress;
    private int serverPort;
    private DatagramSocket socket;

    public UDPClient() {
        try {
            this.serverAddress = InetAddress.getByName(SERVER_IP);
            this.serverPort = SERVER_PORT;
            this.socket = new DatagramSocket();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendBehaviorChangeMessage() {
        sendMessage("behavior_change");
    }

    public void sendPatternChangeMessage() {
        sendMessage("pattern_change");
    }

    private void sendMessage(String message) {
        try {
            byte[] sendData = message.getBytes();
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
