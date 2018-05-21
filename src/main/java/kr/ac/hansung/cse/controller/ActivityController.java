package kr.ac.hansung.cse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.model.Activity;
import kr.ac.hansung.cse.model.Emergency;
import kr.ac.hansung.cse.service.ActivityService;
import kr.ac.hansung.cse.service.EmergencyService;
import kr.ac.hansung.cse.service.UserService;

@Controller
public class ActivityController {

	@Autowired
	ActivityService activityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	EmergencyService emergencyService;
	
	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public String getActivities(@PathVariable String id, Model model) {
		
		Activity activity;
		activity = activityService.getActivity(id);
		model.addAttribute("activity", activity);
		
		return "activity";
	}
	
	@RequestMapping(value = "/emergency/{id}", method = RequestMethod.GET)
	public String getEmergencies(@PathVariable String id, Model model) {
		
		List<Emergency> emergency = emergencyService.viewEmergency(id);
		String name = userService.getName(id);
		
		List<String> sliptime = new ArrayList<String>();
		List<String> sostime = new ArrayList<String>();
		List<String> thefttime = new ArrayList<String>();
		for(int i=0; i<emergency.size(); i++) {
			if(emergency.get(i).getCount_slip()==1) {
				sliptime.add(emergency.get(i).getDatetime());
			}
			if(emergency.get(i).getSos()==1) {
				sostime.add(emergency.get(i).getDatetime());
			}
			if(emergency.get(i).getTheft()==1) {
				thefttime.add(emergency.get(i).getDatetime());
			}
		}
		String st = sliptime.get(sliptime.size()-1);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sliptime", sliptime.get(sliptime.size()-1));
		map.put("sostime", sostime.get(sostime.size()-1));
		map.put("thefttime", thefttime.get(thefttime.size()-1));
		
		model.addAllAttributes(map);
		//model.addAttribute("emergency", emergency);
		
		return "emergency";
	}
	
	@RequestMapping(value="/emergency/slip/{id}")
	public String getSlip(@PathVariable String id, Model model) {
		List<Emergency> slip  = emergencyService.getSlip(id, "count_slip");
		String name = userService.getName(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("slip", slip);
		map.put("name", name);
		
		model.addAllAttributes(map);
		
		return "slip";
		
	}
	
	@RequestMapping(value="/emergency/sos/{id}")
	public String getSos(@PathVariable String id, Model model) {
		List<Emergency> sos = emergencyService.getSos(id, "sos");
		String name = userService.getName(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sos", sos);
		map.put("name", name);
		
		model.addAllAttributes(map);
		
		return "sos";
	}
	
	@RequestMapping(value="/emergency/theft/{id}")
	public String getTheft(@PathVariable String id, Model model) {
		List<Emergency> theft = emergencyService.getTheft(id, "theft");
		String name = userService.getName(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("theft", theft);
		map.put("name", name);
		
		model.addAllAttributes(map);
		return "theft";
	}
}
