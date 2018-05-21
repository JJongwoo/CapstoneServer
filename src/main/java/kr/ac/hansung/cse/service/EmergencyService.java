package kr.ac.hansung.cse.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.EmergencyDao;
import kr.ac.hansung.cse.model.Emergency;

@Service
public class EmergencyService {

	@Autowired
	private EmergencyDao emergencyDao;

	public void addEmergency(Emergency emergency) {

		emergencyDao.addEmergency(emergency);
	}

	public List<Emergency> viewEmergency(String id) {
		
		List<Emergency> emergency = emergencyDao.viewEmergency(id);
		for(Iterator<Emergency> it = emergency.iterator(); it.hasNext();) {
			Emergency value = it.next();
			
			if(value.getCount_slip()==0 && value.getSos()==0 && value.getTheft()==0) {
				it.remove();
			}
		}
		
		return emergency;
	}

	public int getEmergencyByStatus(String status) {
		return emergencyDao.getEmergencyByStatus(status);
		
	}

	public List<Emergency> getEmergency(int i) {
		
		return emergencyDao.getEmergency(i);
	}

	public List<Emergency> getSlip(String id, String status) {

		return emergencyDao.getEmergencyObjectByStatus(id, status);
	}

	public List<Emergency> getSos(String id, String status) {

		return emergencyDao.getEmergencyObjectByStatus(id, status);
	}
	
	public List<Emergency> getTheft(String id, String status) {

		return emergencyDao.getEmergencyObjectByStatus(id, status);
	}


	
}
