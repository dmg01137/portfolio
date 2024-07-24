package com.example.demo.service;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.detection.PatternPolicyDao;
import com.example.demo.dto.PatternPolicyDto;

@Service
public class PatternPolicyService 
{
    private final PatternPolicyDao patternPolicyDao;

    public PatternPolicyService(PatternPolicyDao patternPolicyDao) 
    {
        this.patternPolicyDao = patternPolicyDao;
    }

    
    
    // 패턴 정책 조회
    public List<PatternPolicyDto> listPatternPolicy() 
    {
        return patternPolicyDao.listPatternPolicy();
    }

    // 정책 마지막 번호 가져오기
    public int getLastDetectionNumber() 
    {
        return patternPolicyDao.getLastDetectionNumber();
    }

    // 패턴 정책 추가
    @Transactional
    public int addPatternPolicy(PatternPolicyDto patternPolicyDto) 
    {
        patternPolicyDao.addPatternPolicy(patternPolicyDto);
        return patternPolicyDto.getDetection_number(); // 현재 detection_number + 1 반환
    }

    // 정책번호로 찾아서 수정하기
    public PatternPolicyDto findByDetectionNumber(int detectionNumber) 
    {
        return patternPolicyDao.findByDetectionNumber(detectionNumber);
    }

    // 패턴 정책 수정
    @Transactional
    public int updatePatternPolicy(PatternPolicyDto patternPolicyDto) 
    {
        return patternPolicyDao.updatePatternPolicy(patternPolicyDto);
    }

    // 패턴 정책 활성화/비활성화
    @Transactional
    public int clickPatternPolicy(int detectionNumber) 
    {
        return patternPolicyDao.clickPatternPolicy(detectionNumber);
    }

    // 패턴 정책 추가, 수정, 활성화/비활성화 udp 신호
    @Transactional
    public int udpPatternPolicy() {
        try (DatagramSocket datagramSocket = new DatagramSocket()) 
        {
            String serverAddress = "192.168.1.26";
            String message = "pattern_change";

            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), 9090);
            datagramSocket.send(datagramPacket);
            return 1;
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 검색 조건에 따른 패턴 정책 조회
    public List<PatternPolicyDto> searchPatternPolicy(Integer detectionNumber, String sToIp, String sIp, String dIp, String sToPort, Integer sPort,
    		Integer dPort, LocalDateTime createDtFrom, LocalDateTime createDtTo, LocalDateTime modifyDtFrom, LocalDateTime modifyDtTo, Integer actionType, String policyName, String policyInfo, String pattern1, 
    		String pattern2, String pattern3, Integer dangerous) 
    {
        return patternPolicyDao.searchPatternPolicy(detectionNumber, sToIp, sIp, dIp, sToPort, sPort, dPort, createDtFrom, createDtTo, modifyDtFrom, modifyDtTo, actionType, policyName, 
        		policyInfo, pattern1, pattern2, pattern3, dangerous);
    }
}

