package kr.ac.hansung.cse.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.EmergencyDao;
import kr.ac.hansung.cse.dao.SensorDataDao;
import kr.ac.hansung.cse.dao.UserDao;
import kr.ac.hansung.cse.model.Emergency;
import kr.ac.hansung.cse.model.Status;

@Service
public class SensorDataService {

	@Autowired
	private SensorDataDao sensorDataDao;

	@Autowired
	private EmergencyService emergencyService;

	@Autowired
	private FcmMsgService fcmmsgService;

	@Autowired
	private UserDao userDao;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	private SimpleDateFormat millFormat = new SimpleDateFormat("ss.S");

	private int isTheft = 1;
	private int location_count = 0;
	private int sos_count = 0;
	private int bath_count = 0;
	private int theft_count = 0;
	private int shock_count = 0;
	private int lonely_count = 50;

	private int bath_time_first;
	private int bath_time_second;
	private int bath_time_last;
	private int bath_quantity_sample;
	
	private String time_duplicate = "a";

	private Emergency emergency;

	private String[] toilet_first;
	private String[] toilet_second;
	private String[] toilet_last;

	public void saveData(String pir1, String pir2, String id, String sona1, String sona2, String btn, String shock) {

		Date currentTime = new Date();
		String date = dateFormat.format(currentTime);
		String time = timeFormat.format(currentTime);
		String mill = millFormat.format(currentTime);
		char millsec = mill.charAt(3);
		String datetime = date + " " + time + "." + millsec;
		if(time_duplicate.equals(datetime)) {
			return;
		}
		time_duplicate = datetime;
		String name = userDao.getName(id);
		isTheft = 1;

		List<Status> status = sensorDataDao.getStatus(20);
		List<Emergency> emergencies = emergencyService.getEmergency(3);  

		// 실내 외출 판정
		String location = status.get(0).getLocation();
		if (location.equals("외출")) {
			if ((Integer.parseInt(status.get(6).getSona_living()) < 15) && location_count <= 0) {
				if ((status.get(0).getPir_living().equals("1")) || (status.get(1).getPir_living().equals("1"))
						|| (status.get(2).getPir_living().equals("1")) || (status.get(3).getPir_living().equals("1"))
						|| (status.get(4).getPir_living().equals("1"))|| (status.get(5).getPir_living().equals("1"))) {
					System.out.println("들어왔습니당.");
					location = "실내";
					location_count = 7;
					
				}
			}

			for (int i = 0; i < 5; i++) {
				if (Integer.parseInt(status.get(i).getSona_living()) < 15 || Integer.parseInt(sona1) < 15)
					isTheft = 0;
			}

			// 외출상태에서 집 내부에 침입 감지
			if (isTheft == 1 && theft_count <= 0) {
				if (pir1.equals("1") || pir2.equals("1")) {
					// 도둑이얌
					theft_count = 10;
					System.out.println("도둑");
					emergency = new Emergency();
					emergency.setEmergency_id(id);
					emergency.setDatetime(datetime);
					emergency.setTheft(1);
					emergency.setCount_slip(0);
					emergency.setCount_toilet(0);
					emergency.setSos(0);
					emergencyService.addEmergency(emergency);
					try {
						fcmmsgService.sendPush(name + "님의 집에 외부침입이 감지 되었습니다. 지금 바로 연락하세요!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		} else if (location.equals("실내")) {
			if ((Integer.parseInt(status.get(6).getSona_living()) < 15) && location_count <= 0) {
				if ((status.get(0).getPir_living().equals("0") && status.get(0).getPir_bath().equals("0"))
						|| (status.get(1).getPir_living().equals("0") && status.get(1).getPir_bath().equals("0"))
						|| (status.get(2).getPir_living().equals("0") && status.get(2).getPir_bath().equals("0"))
						|| (status.get(3).getPir_living().equals("0") && status.get(3).getPir_bath().equals("0"))
						|| (status.get(4).getPir_living().equals("0") && status.get(4).getPir_bath().equals("0"))
						|| (status.get(5).getPir_living().equals("0") && status.get(4).getPir_bath().equals("0"))) {
					//if (location_count <= 0) {
						System.out.println("외출");
						location = "외출";
						location_count = 7;
					//}
				}
			}
			// 화장실 이용 급증
			int l = emergencyService.getEmergencyByStatus("count_toilet");
			if (l >= 3) {
				
				toilet_first = emergencies.get(2).getDatetime().split(" ")[1].split(":");
				toilet_second = emergencies.get(1).getDatetime().split(" ")[1].split(":");
				toilet_last = emergencies.get(0).getDatetime().split(" ")[1].split(":");
				// System.out.println(toilet_first[2]);
				// System.out.println(toilet_last[2]);
				bath_time_first = Integer.parseInt(toilet_first[0]) * 3600 + Integer.parseInt(toilet_first[1]) * 60
						+ Integer.parseInt(toilet_first[2].split("[.]")[0]);
				bath_time_second = Integer.parseInt(toilet_second[0]) * 3600 + Integer.parseInt(toilet_second[1])*60
						+ Integer.parseInt(toilet_second[2].split("[.]")[0]);
				bath_time_last = Integer.parseInt(toilet_last[0]) * 3600 + Integer.parseInt(toilet_last[1]) * 60
						+ Integer.parseInt(toilet_last[2].split("[.]")[0]);
				if (bath_quantity_sample != bath_time_first && bath_quantity_sample != bath_time_second
						&& bath_quantity_sample != bath_time_last) {
					bath_quantity_sample = bath_time_last;
					// System.out.println(bath_time_first);
					// System.out.println(bath_time_last);
					//System.out.println(bath_time_last - bath_time_first);

					if (bath_time_last - bath_time_first <= 120) {
						System.out.println("화장실 이용이 잦습니다.");
						try {
							fcmmsgService.sendPush(name + "님의 화장실 이용이 잦습니다. 연락을 통해 확인하세요.");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

			// 낙상감지
			if (Integer.parseInt(shock) > 12 && shock_count <= 0) {
				if (status.get(0).getPir_living().equals("1") || status.get(1).getPir_living().equals("1")
						|| status.get(2).getPir_living().equals("1")) {
					System.out.println("거실에서 낙상감지");
					emergency = new Emergency();
					emergency.setEmergency_id(id);
					emergency.setDatetime(datetime);
					emergency.setTheft(0);
					emergency.setCount_slip(1);
					emergency.setCount_toilet(0);
					emergency.setSos(0);
					emergencyService.addEmergency(emergency);
					try {
						fcmmsgService.sendPush(name + "님의 낙상이 거실에서 감지 되었습니다. 연락을통해 확인하세요.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					shock_count = 5;
				} else if (status.get(0).getPir_bath().equals("1") || status.get(1).getPir_bath().equals("1")
						|| status.get(2).getPir_bath().equals("1")) {
					System.out.println("화장실에서 낙상감지");
					emergency = new Emergency();
					emergency.setEmergency_id(id);
					emergency.setDatetime(datetime);
					emergency.setTheft(0);
					emergency.setCount_slip(1);
					emergency.setCount_toilet(0);
					emergency.setSos(0);
					emergencyService.addEmergency(emergency);
					try {
						fcmmsgService.sendPush(name + "님의 낙상이 화장실에서 감지 되었습니다. 연락을통해 확인하세요.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					shock_count = 5;
				}
			}
			// 화장실 초음파가 줄어들고 화장실 적외선이 1이고. 거실 적외선 0이면 체크.
			if (Integer.parseInt(sona2) < 12 && pir1.equals("0")) {
				bath_count++;
				System.out.println(bath_count);
			}
			if (Integer.parseInt(sona2) >= 12 && pir1.equals("1") && pir2.equals("0")) {
				if (bath_count <= 2) {// 몇 초 이하는 화장실 이용 체크 안할지 정해야함.
					bath_count = 0;
				} else if (bath_count > 2) { // 몇 초 이상 화장실에 앉아 있을 경우 화장실을 이용했는지 정해야함.
					// DB 상의 화장실 이용횟수 증가
					System.out.println("화장실 이용");
					emergency = new Emergency();
					emergency.setEmergency_id(id);
					emergency.setDatetime(datetime);
					emergency.setTheft(0);
					emergency.setCount_slip(0);
					emergency.setCount_toilet(1);
					emergency.setSos(0);
					emergencyService.addEmergency(emergency);
					bath_count = 0;

					// emergencyService.getEmergencyByStatus("count_bath");
				}
			}

			// SOS버튼 눌렀을때 푸쉬알림
			if (btn.equals("1")) {
				if (sos_count <= 0) {
					System.out.println("도와주세요");
					sos_count = 2;
					emergency.setEmergency_id(id);
					emergency.setDatetime(datetime);
					emergency.setTheft(0);
					emergency.setCount_slip(0);
					emergency.setCount_toilet(0);
					emergency.setSos(1);
					emergencyService.addEmergency(emergency);
					try {
						fcmmsgService.sendPush(name + "님이 SOS요청을 하셨습니다. 지금 바로 연락하세요!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			// 고독사 의심
			for (int i = 0; i < status.size(); i++) {
				if (Integer.parseInt(status.get(i).getPir_living()) != 0
						|| Integer.parseInt(status.get(i).getPir_bath()) != 0) {
					break;
				}

				if (i == 19 && lonely_count <= 0) {
					System.out.println("고독사 의심");
					try {
						fcmmsgService.sendPush(name + "님의 움직임이 감지되지 않습니다. 전화로 확인해보세요.");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lonely_count = 20;
				}
			}

		} else
			location = "외출";
		
		sensorDataDao.saveData(pir1, pir2, datetime, id, sona1, sona2, btn, location, shock);
		sos_count = sos_count - 1;
		location_count = location_count - 1;
		theft_count -= 1;
		shock_count -= 1;
		lonely_count -= 1;

	}
}
