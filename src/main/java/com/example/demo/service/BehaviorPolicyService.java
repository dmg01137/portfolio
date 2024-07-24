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

import com.example.demo.dao.detection.BehaviorPolicyDao;
import com.example.demo.dto.BehaviorPolicyDto;

@Service
public class BehaviorPolicyService 
{
	private final BehaviorPolicyDao behaviorPolicyDao;

	public BehaviorPolicyService(BehaviorPolicyDao behaviorPolicyDao) 
	{
		this.behaviorPolicyDao = behaviorPolicyDao;
	}
	
	
	// 행동 정책 조회
	public List<BehaviorPolicyDto> listBehaviorPolicy() 
	{
		return behaviorPolicyDao.listBehaviorPolicy();
	}

	// 정책 마지막 번호 가져오기
    public int getLastDetectionNumber() 
    {
        return behaviorPolicyDao.getLastDetectionNumber();
    }
	
	// 행동 정책 추가
	@Transactional
	public int addBehaviorPolicy(BehaviorPolicyDto behaviorPolicyDto) 
	{
		behaviorPolicyDao.addBehaviorPolicy(behaviorPolicyDto);
        return behaviorPolicyDto.getDetection_number(); // 현재 detection_number + 1 반환
	}

	// 정책번호로 찾아서 수정하기
    public BehaviorPolicyDto findByDetectionNumber(int detection_number) 
    {
        return behaviorPolicyDao.findByDetectionNumber(detection_number);
    }
	
	// 행동 정책 수정
	@Transactional
	public int updateBehaviorPolicy(BehaviorPolicyDto behaviorPolicyDto) 
	{
		return behaviorPolicyDao.updateBehaviorPolicy(behaviorPolicyDto);
	}
	
	// 행동 정책 활성화/비활성화
	@Transactional
	public int clickBehaviorPolicy(int detection_number) 
	{
		return behaviorPolicyDao.clickBehaviorPolicy(detection_number);
	}
	
	
	// 행동 정책 추가, 수정, 활성화/비활성화 udp 신호
	public int udpBehaviorPolicy() 
	{
		try (DatagramSocket datagramSocket = new DatagramSocket()) 
		{
			String serverAddress = "192.168.1.26";
			String message = "behavior_change";
			
			DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(serverAddress), 9090);
			datagramSocket.send(datagramPacket);
			return 1;
		} 
		catch (SocketException e) 
		{
			e.printStackTrace();
		}
		catch (UnknownHostException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return 0;
	}

	// 검색 조건에 따른 행동 정책 조회
    public List<BehaviorPolicyDto> searchBehaviorPolicy(Integer detectionNumber, String sToIp, String sIp, String dIp, String sToPort, Integer sPort,
    		Integer dPort, LocalDateTime createDtFrom, LocalDateTime createDtTo, LocalDateTime modifyDtFrom, LocalDateTime modifyDtTo, Integer actionType, String policyName, String policyInfo, 
    		Integer baseCnt, Integer baseTime, String pattern1, String pattern2, String pattern3, Integer dangerous) {
        return behaviorPolicyDao.searchBehaviorPolicy(detectionNumber, sToIp, sIp, dIp, sToPort, sPort, dPort, createDtFrom, createDtTo, modifyDtFrom, modifyDtTo, actionType, policyName, 
        		policyInfo, baseCnt, baseTime, pattern1, pattern2, pattern3, dangerous);
    }
	
}
