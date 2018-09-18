package kr.ac.hansung.cse.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.ac.hansung.cse.dao.EmergencyDao;
import kr.ac.hansung.cse.dao.SensorDataDao;
import kr.ac.hansung.cse.model.Activity;
import kr.ac.hansung.cse.model.Emergency;
import kr.ac.hansung.cse.model.Status;



@Service
public class ActivityService {

	@Autowired
	SensorDataDao sensordataDao;
	
	@Autowired
	EmergencyDao emergencyDao;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public Activity getActivity(String id) {

		Date currentTime = new Date();
		String date = dateFormat.format(currentTime);
		int count_toilet=0;
		
		Status status = sensordataDao.getCurrnetLocation(id);
		String last_time = emergencyDao.getLastToilet(id);
		List<Emergency> emergency = emergencyDao.getEmergencyObjectByStatus(id, "count_toilet");
		
		for(int i=0; i < emergency.size(); i++) {
			String[] datetime = emergency.get(i).getDatetime().split(" ");
			if(datetime[0].equals(date))
				count_toilet++;
		}
		
		Activity activity = new Activity();
		
		activity.setLocation(status.getLocation());
		activity.setLatest_locationtime(status.getDatetime());
		activity.setCount_toilet(count_toilet);
		activity.setLast_toilettime(last_time);
		
		return activity;
		
		
	}

	
	
	
	
}
