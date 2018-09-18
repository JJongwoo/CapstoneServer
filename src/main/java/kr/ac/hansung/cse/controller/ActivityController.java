package kr.ac.hansung.cse.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@RequestMapping(value = "/activity/{id}", method = RequestMethod.GET)
	public String getActivities(@PathVariable String id, Model model) {

		Activity activity;
		activity = activityService.getActivity(id);
		String loct = activity.getLatest_locationtime();
		String toit = activity.getLast_toilettime();
		loct = loct.split("[.]")[0];
		toit = toit.split("[.]")[0];
		activity.setLast_toilettime(toit);
		activity.setLatest_locationtime(loct);
		model.addAttribute("activity", activity);

		return "activity";
	}

	@RequestMapping(value = "/activity/threedays/{id}", method = RequestMethod.GET)
	public String getToiletUseThreeDays(@PathVariable String id, Model model) {

		List<Emergency> toilet = emergencyService.getToiletUse(id, "count_toilet");

		Calendar cal = new GregorianCalendar();

		int one = cal.get(Calendar.DAY_OF_MONTH);
		int one_year = cal.get(Calendar.YEAR);
		int one_month = cal.get(Calendar.MONTH) + 1;
		String one_date = Integer.toString(one_year) + ". " + Integer.toString(one_month) + ". "
				+ Integer.toString(one);

		cal.add(Calendar.DATE, -1);
		int two = cal.get(Calendar.DAY_OF_MONTH);
		int two_year = cal.get(Calendar.YEAR);
		int two_month = cal.get(Calendar.MONTH) + 1;
		String two_date = Integer.toString(two_year) + ". " + Integer.toString(two_month) + ". "
				+ Integer.toString(two);

		cal.add(Calendar.DATE, -1);
		int three = cal.get(Calendar.DAY_OF_MONTH);
		int three_year = cal.get(Calendar.YEAR);
		int three_month = cal.get(Calendar.MONTH) + 1;
		String three_date = Integer.toString(three_year) + ". " + Integer.toString(three_month) + ". "
				+ Integer.toString(three);

		int count_three = 0;
		int count_two = 0;
		int count_one = 0;

		for (int i = 0; i < toilet.size(); i++) {
			if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == three) {
				count_three++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == two) {
				count_two++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == one) {
				count_one++;
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("countthree", count_three);
		map.put("counttwo", count_two);
		map.put("countone", count_one);
		map.put("daythree", three_date);
		map.put("daytwo", two_date);
		map.put("dayone", one_date);

		model.addAllAttributes(map);

		return "toiletThreedays";
	}

	@RequestMapping(value = "/activity/oneweek/{id}", method = RequestMethod.GET)
	public String getToiletUseWeek(@PathVariable String id, Model model) {

		List<Emergency> toilet = emergencyService.getToiletUse(id, "count_toilet");

		Calendar cal = new GregorianCalendar();

		int one = cal.get(Calendar.DAY_OF_MONTH);
		int one_year = cal.get(Calendar.YEAR);
		int one_month = cal.get(Calendar.MONTH) + 1;
		String one_date = Integer.toString(one_year) + ". " + Integer.toString(one_month) + ". "
				+ Integer.toString(one);

		cal.add(Calendar.DATE, -1);
		int two = cal.get(Calendar.DAY_OF_MONTH);
		int two_year = cal.get(Calendar.YEAR);
		int two_month = cal.get(Calendar.MONTH) + 1;
		String two_date = Integer.toString(two_year) + ". " + Integer.toString(two_month) + ". "
				+ Integer.toString(two);

		cal.add(Calendar.DATE, -1);
		int three = cal.get(Calendar.DAY_OF_MONTH);
		int three_year = cal.get(Calendar.YEAR);
		int three_month = cal.get(Calendar.MONTH) + 1;
		String three_date = Integer.toString(three_year) + ". " + Integer.toString(three_month) + ". "
				+ Integer.toString(three);

		cal.add(Calendar.DATE, -1);
		int four = cal.get(Calendar.DAY_OF_MONTH);
		int four_year = cal.get(Calendar.YEAR);
		int four_month = cal.get(Calendar.MONTH) + 1;
		String four_date = Integer.toString(four_year) + ". " + Integer.toString(four_month) + ". "
				+ Integer.toString(four);

		cal.add(Calendar.DATE, -1);
		int five = cal.get(Calendar.DAY_OF_MONTH);
		int five_year = cal.get(Calendar.YEAR);
		int five_month = cal.get(Calendar.MONTH) + 1;
		String five_date = Integer.toString(five_year) + ". " + Integer.toString(five_month) + ". "
				+ Integer.toString(five);

		cal.add(Calendar.DATE, -1);
		int six = cal.get(Calendar.DAY_OF_MONTH);
		int six_year = cal.get(Calendar.YEAR);
		int six_month = cal.get(Calendar.MONTH) + 1;
		String six_date = Integer.toString(six_year) + ". " + Integer.toString(six_month) + ". "
				+ Integer.toString(six);

		cal.add(Calendar.DATE, -1);
		int seven = cal.get(Calendar.DAY_OF_MONTH);
		int seven_year = cal.get(Calendar.YEAR);
		int seven_month = cal.get(Calendar.MONTH) + 1;
		String seven_date = Integer.toString(seven_year) + ". " + Integer.toString(seven_month) + ". "
				+ Integer.toString(seven);

		int count_seven = 0;
		int count_six = 0;
		int count_five = 0;
		int count_four = 0;
		int count_three = 0;
		int count_two = 0;
		int count_one = 0;

		for (int i = 0; i < toilet.size(); i++) {
			if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == seven) {
				count_seven++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == six) {
				count_six++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == five) {
				count_five++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == four) {
				count_four++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == three) {
				count_three++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == two) {
				count_two++;
			} else if (Integer.parseInt(toilet.get(i).getDatetime().split(" ")[0].split("-")[2]) == one) {
				count_one++;
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("countseven", count_seven);
		map.put("countsix", count_six);
		map.put("countfive", count_five);
		map.put("countfour", count_four);
		map.put("countthree", count_three);
		map.put("counttwo", count_two);
		map.put("countone", count_one);
		
		map.put("dayseven", seven_date);
		map.put("daysix", six_date);
		map.put("dayfive", five_date);
		map.put("dayfour", four_date);
		map.put("daythree", three_date);
		map.put("daytwo", two_date);
		map.put("dayone", one_date);

		model.addAllAttributes(map);

		return "toiletOneWeek";
	}

	@RequestMapping(value = "/emergency/{id}", method = RequestMethod.GET)
	public String getEmergencies(@PathVariable String id, Model model) {

		List<Emergency> emergency = emergencyService.viewEmergency(id);
		String name = userService.getName(id);

		List<String> sliptime = new ArrayList<String>();
		List<String> sostime = new ArrayList<String>();
		List<String> thefttime = new ArrayList<String>();
		for (int i = 0; i < emergency.size(); i++) {
			if (emergency.get(i).getCount_slip() == 1) {
				sliptime.add(emergency.get(i).getDatetime().split("[.]")[0]);
			}
			if (emergency.get(i).getSos() == 1) {
				sostime.add(emergency.get(i).getDatetime().split("[.]")[0]);
			}
			if (emergency.get(i).getTheft() == 1) {
				thefttime.add(emergency.get(i).getDatetime().split("[.]")[0]);
			}
		}
		String st = sliptime.get(sliptime.size() - 1);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("sliptime", sliptime.get(sliptime.size() - 1));
		map.put("sostime", sostime.get(sostime.size() - 1));
		map.put("thefttime", thefttime.get(thefttime.size() - 1));

		model.addAllAttributes(map);
		// model.addAttribute("emergency", emergency);

		return "emergency";
	}

	@RequestMapping(value = "/emergency/slip/{id}")
	public String getSlip(@PathVariable String id, Model model) {
		List<Emergency> slip = emergencyService.getSlip(id, "count_slip");
		String name = userService.getName(id);
		List<Emergency> slipweek = new ArrayList<Emergency>();
		String tempday;
		String tempmonth;
		
		for (int i = 0; i < slip.size(); i++) {
			slip.get(i).setDatetime(slip.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();

		int one = cal.get(Calendar.DAY_OF_MONTH);
		int one_year = cal.get(Calendar.YEAR);
		int one_month = cal.get(Calendar.MONTH) + 1;
		
		if(one<10) {
			tempday = "0" + Integer.toString(one);
		}
		else
			tempday = Integer.toString(one);
		if(one_month<10) {
			tempmonth = "0" + Integer.toString(one_month);
		}
		else
			tempmonth = Integer.toString(one_month);
		
		String one_date = Integer.toString(one_year) + "-" + tempmonth + "-"
				+ tempday;
		
		
		cal.add(Calendar.DATE, -1);
		int two = cal.get(Calendar.DAY_OF_MONTH);
		int two_year = cal.get(Calendar.YEAR);
		int two_month = cal.get(Calendar.MONTH) + 1;
		if(two<10) {
			tempday = "0" + Integer.toString(two);
		}
		else
			tempday = Integer.toString(two);
		if(two_month<10) {
			tempmonth = "0" + Integer.toString(two_month);
		}
		else
			tempmonth = Integer.toString(two_month);
		String two_date = Integer.toString(two_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int three = cal.get(Calendar.DAY_OF_MONTH);
		int three_year = cal.get(Calendar.YEAR);
		int three_month = cal.get(Calendar.MONTH) + 1;
		if(three<10) {
			tempday = "0" + Integer.toString(three);
		}
		else
			tempday = Integer.toString(three);
		if(three_month<10) {
			tempmonth = "0" + Integer.toString(three_month);
		}
		else
			tempmonth = Integer.toString(three_month);
		String three_date = Integer.toString(three_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int four = cal.get(Calendar.DAY_OF_MONTH);
		int four_year = cal.get(Calendar.YEAR);
		int four_month = cal.get(Calendar.MONTH) + 1;
		if(four<10) {
			tempday = "0" + Integer.toString(four);
		}
		else
			tempday = Integer.toString(four);
		if(four_month<10) {
			tempmonth = "0" + Integer.toString(four_month);
		}
		else
			tempmonth = Integer.toString(four_month);
		String four_date = Integer.toString(four_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int five = cal.get(Calendar.DAY_OF_MONTH);
		int five_year = cal.get(Calendar.YEAR);
		int five_month = cal.get(Calendar.MONTH) + 1;
		if(five<10) {
			tempday = "0" + Integer.toString(five);
		}
		else
			tempday = Integer.toString(five);
		if(five_month<10) {
			tempmonth = "0" + Integer.toString(five_month);
		}
		else
			tempmonth = Integer.toString(five_month);
		String five_date = Integer.toString(five_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int six = cal.get(Calendar.DAY_OF_MONTH);
		int six_year = cal.get(Calendar.YEAR);
		int six_month = cal.get(Calendar.MONTH) + 1;
		if(six<10) {
			tempday = "0" + Integer.toString(six);
		}
		else
			tempday = Integer.toString(six);
		if(six_month<10) {
			tempmonth = "0" + Integer.toString(six_month);
		}
		else
			tempmonth = Integer.toString(six_month);
		String six_date = Integer.toString(six_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int seven = cal.get(Calendar.DAY_OF_MONTH);
		int seven_year = cal.get(Calendar.YEAR);
		int seven_month = cal.get(Calendar.MONTH) + 1;
		if(seven<10) {
			tempday = "0" + Integer.toString(seven);
		}
		else
			tempday = Integer.toString(seven);
		if(seven_month<10) {
			tempmonth = "0" + Integer.toString(seven_month);
		}
		else
			tempmonth = Integer.toString(seven_month);
		String seven_date = Integer.toString(seven_year) + "-" + tempmonth + "-"
				+ tempday;
		
		for(int i=0;i<slip.size();i++) {
			if(slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(one_date) || slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(two_date) ||
					 slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(three_date) || slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(four_date) ||
					 slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(five_date) || slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(six_date) ||
					 slip.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(seven_date)) {
				slipweek.add(slip.get(i));
			}
		}
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("slip", slipweek);
		map.put("name", name);

		model.addAllAttributes(map);

		return "slip";

	}
	
	@RequestMapping(value="/emergency/slip/thismonth/{id}")
	public String getSlipThisMonth(@PathVariable String id, Model model) {
		List<Emergency> slip = emergencyService.getSlip(id, "count_slip");
		String name = userService.getName(id);
		List<Emergency> slipmonth = new ArrayList<Emergency>();
		String tempmonth;
		
		for (int i = 0; i < slip.size(); i++) {
			slip.get(i).setDatetime(slip.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if(month<10) {
			tempmonth = "0" + Integer.toString(month);
		}
		else
			tempmonth = Integer.toString(month);
		
		for(int i=0; i<slip.size(); i++) {

			if(tempmonth.equals(slip.get(i).getDatetime().split("[.]")[0].split("-")[1])){
				slipmonth.add(slip.get(i));
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("slip", slipmonth);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "slipthismonth";
	}
	
	@RequestMapping(value="/emergency/slip/lastmonth/{id}")
	public String getSlipLastMonth(@PathVariable String id, Model model) {
		List<Emergency> slip = emergencyService.getSlip(id, "count_slip");
		
		String name = userService.getName(id);
		List<Emergency> slipmonth = new ArrayList<Emergency>();
		String tempmonth;
		
		for (int i = 0; i < slip.size(); i++) {
			slip.get(i).setDatetime(slip.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		if(month<10) {
			tempmonth = "0" + Integer.toString(month);
		}
		else
			tempmonth = Integer.toString(month);
		
		for(int i=0; i<slip.size(); i++) {

			if(tempmonth.equals(slip.get(i).getDatetime().split("[.]")[0].split("-")[1])){
				slipmonth.add(slip.get(i));
			}
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("slip", slipmonth);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "sliplastmonth";
	}
	
	@RequestMapping(value="/emergency/slip/thisyear/{id}")
	public String getSlipThisYear(@PathVariable String id, Model model) {
		List<Emergency> slip = emergencyService.getSlip(id, "count_slip");
		
		String name = userService.getName(id);
		List<Emergency> slipyear = new ArrayList<Emergency>();
		String tempyear;
		
		for (int i = 0; i < slip.size(); i++) {
			slip.get(i).setDatetime(slip.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		if(year<10) {
			tempyear = "0" + Integer.toString(year);
		}
		else
			tempyear = Integer.toString(year);
		
		for(int i=0; i<slip.size(); i++) {

			if(tempyear.equals(slip.get(i).getDatetime().split("[.]")[0].split("-")[0])){
				slipyear.add(slip.get(i));
			}
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("slip", slipyear);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "slipthisyear";
	}

	@RequestMapping(value = "/emergency/sos/{id}")
	public String getSos(@PathVariable String id, Model model) {
		List<Emergency> sos = emergencyService.getSos(id, "sos");
		String name = userService.getName(id);
		String tempday;
		String tempmonth;
		List<Emergency> sosweek = new ArrayList<Emergency>();

		for (int i = 0; i < sos.size(); i++) {
			sos.get(i).setDatetime(sos.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();

		int one = cal.get(Calendar.DAY_OF_MONTH);
		int one_year = cal.get(Calendar.YEAR);
		int one_month = cal.get(Calendar.MONTH) + 1;
		
		if(one<10) {
			tempday = "0" + Integer.toString(one);
		}
		else
			tempday = Integer.toString(one);
		if(one_month<10) {
			tempmonth = "0" + Integer.toString(one_month);
		}
		else
			tempmonth = Integer.toString(one_month);
		
		String one_date = Integer.toString(one_year) + "-" + tempmonth + "-"
				+ tempday;
		
		
		cal.add(Calendar.DATE, -1);
		int two = cal.get(Calendar.DAY_OF_MONTH);
		int two_year = cal.get(Calendar.YEAR);
		int two_month = cal.get(Calendar.MONTH) + 1;
		if(two<10) {
			tempday = "0" + Integer.toString(two);
		}
		else
			tempday = Integer.toString(two);
		if(two_month<10) {
			tempmonth = "0" + Integer.toString(two_month);
		}
		else
			tempmonth = Integer.toString(two_month);
		String two_date = Integer.toString(two_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int three = cal.get(Calendar.DAY_OF_MONTH);
		int three_year = cal.get(Calendar.YEAR);
		int three_month = cal.get(Calendar.MONTH) + 1;
		if(three<10) {
			tempday = "0" + Integer.toString(three);
		}
		else
			tempday = Integer.toString(three);
		if(three_month<10) {
			tempmonth = "0" + Integer.toString(three_month);
		}
		else
			tempmonth = Integer.toString(three_month);
		String three_date = Integer.toString(three_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int four = cal.get(Calendar.DAY_OF_MONTH);
		int four_year = cal.get(Calendar.YEAR);
		int four_month = cal.get(Calendar.MONTH) + 1;
		if(four<10) {
			tempday = "0" + Integer.toString(four);
		}
		else
			tempday = Integer.toString(four);
		if(four_month<10) {
			tempmonth = "0" + Integer.toString(four_month);
		}
		else
			tempmonth = Integer.toString(four_month);
		String four_date = Integer.toString(four_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int five = cal.get(Calendar.DAY_OF_MONTH);
		int five_year = cal.get(Calendar.YEAR);
		int five_month = cal.get(Calendar.MONTH) + 1;
		if(five<10) {
			tempday = "0" + Integer.toString(five);
		}
		else
			tempday = Integer.toString(five);
		if(five_month<10) {
			tempmonth = "0" + Integer.toString(five_month);
		}
		else
			tempmonth = Integer.toString(five_month);
		String five_date = Integer.toString(five_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int six = cal.get(Calendar.DAY_OF_MONTH);
		int six_year = cal.get(Calendar.YEAR);
		int six_month = cal.get(Calendar.MONTH) + 1;
		if(six<10) {
			tempday = "0" + Integer.toString(six);
		}
		else
			tempday = Integer.toString(six);
		if(six_month<10) {
			tempmonth = "0" + Integer.toString(six_month);
		}
		else
			tempmonth = Integer.toString(six_month);
		String six_date = Integer.toString(six_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int seven = cal.get(Calendar.DAY_OF_MONTH);
		int seven_year = cal.get(Calendar.YEAR);
		int seven_month = cal.get(Calendar.MONTH) + 1;
		if(seven<10) {
			tempday = "0" + Integer.toString(seven);
		}
		else
			tempday = Integer.toString(seven);
		if(seven_month<10) {
			tempmonth = "0" + Integer.toString(seven_month);
		}
		else
			tempmonth = Integer.toString(seven_month);
		String seven_date = Integer.toString(seven_year) + "-" + tempmonth + "-"
				+ tempday;
		
		for(int i=0;i<sos.size();i++) {
			if(sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(one_date) || sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(two_date) ||
					 sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(three_date) || sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(four_date) ||
					 sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(five_date) || sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(six_date) ||
					 sos.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(seven_date)) {
				sosweek.add(sos.get(i));
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("sos", sosweek);
		map.put("name", name);

		model.addAllAttributes(map);

		return "sos";
	}
	
	@RequestMapping(value="/emergency/sos/thismonth/{id}")
	public String getSosThisMonth(@PathVariable String id, Model model) {
		List<Emergency> sos = emergencyService.getSos(id, "sos");
		String name = userService.getName(id);
		List<Emergency> sosmonth = new ArrayList<Emergency>();
		String tempmonth;
		
		for (int i = 0; i < sos.size(); i++) {
			sos.get(i).setDatetime(sos.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if(month<10) {
			tempmonth = "0" + Integer.toString(month);
		}
		else
			tempmonth = Integer.toString(month);
		
		for(int i=0; i<sos.size(); i++) {

			if(tempmonth.equals(sos.get(i).getDatetime().split("[.]")[0].split("-")[1])){
				sosmonth.add(sos.get(i));
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("sos", sosmonth);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "sosthismonth";
	}
	
	@RequestMapping(value="/emergency/sos/lastmonth/{id}")
	public String getSosLastMonth(@PathVariable String id, Model model) {
		List<Emergency> sos = emergencyService.getSos(id, "sos");
		
		String name = userService.getName(id);
		List<Emergency> sosmonth = new ArrayList<Emergency>();
		String tempmonth;
		
		for (int i = 0; i < sos.size(); i++) {
			sos.get(i).setDatetime(sos.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		if(month<10) {
			tempmonth = "0" + Integer.toString(month);
		}
		else
			tempmonth = Integer.toString(month);
		
		for(int i=0; i<sos.size(); i++) {

			if(tempmonth.equals(sos.get(i).getDatetime().split("[.]")[0].split("-")[1])){
				sosmonth.add(sos.get(i));
			}
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("sos", sosmonth);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "soslastmonth";
	}
	
	@RequestMapping(value="/emergency/sos/thisyear/{id}")
	public String getSosThisYear(@PathVariable String id, Model model) {
		List<Emergency> sos = emergencyService.getSos(id, "sos");
		
		String name = userService.getName(id);
		List<Emergency> sosyear = new ArrayList<Emergency>();
		String tempyear;
		
		for (int i = 0; i < sos.size(); i++) {
			sos.get(i).setDatetime(sos.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		if(year<10) {
			tempyear = "0" + Integer.toString(year);
		}
		else
			tempyear = Integer.toString(year);
		
		for(int i=0; i<sos.size(); i++) {

			if(tempyear.equals(sos.get(i).getDatetime().split("[.]")[0].split("-")[0])){
				sosyear.add(sos.get(i));
			}
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("sos", sosyear);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "sosthisyear";
	}

	@RequestMapping(value = "/emergency/theft/{id}")
	public String getTheft(@PathVariable String id, Model model) {
		List<Emergency> theft = emergencyService.getTheft(id, "theft");
		String name = userService.getName(id);
		List<Emergency> theftweek = new ArrayList<Emergency>();
		String tempday;
		String tempmonth;
		for (int i = 0; i < theft.size(); i++) {
			theft.get(i).setDatetime(theft.get(i).getDatetime().split("[.]")[0]);
		}

		Calendar cal = new GregorianCalendar();

		int one = cal.get(Calendar.DAY_OF_MONTH);
		int one_year = cal.get(Calendar.YEAR);
		int one_month = cal.get(Calendar.MONTH) + 1;
		
		if(one<10) {
			tempday = "0" + Integer.toString(one);
		}
		else
			tempday = Integer.toString(one);
		if(one_month<10) {
			tempmonth = "0" + Integer.toString(one_month);
		}
		else
			tempmonth = Integer.toString(one_month);
		
		String one_date = Integer.toString(one_year) + "-" + tempmonth + "-"
				+ tempday;
		
		
		cal.add(Calendar.DATE, -1);
		int two = cal.get(Calendar.DAY_OF_MONTH);
		int two_year = cal.get(Calendar.YEAR);
		int two_month = cal.get(Calendar.MONTH) + 1;
		if(two<10) {
			tempday = "0" + Integer.toString(two);
		}
		else
			tempday = Integer.toString(two);
		if(two_month<10) {
			tempmonth = "0" + Integer.toString(two_month);
		}
		else
			tempmonth = Integer.toString(two_month);
		String two_date = Integer.toString(two_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int three = cal.get(Calendar.DAY_OF_MONTH);
		int three_year = cal.get(Calendar.YEAR);
		int three_month = cal.get(Calendar.MONTH) + 1;
		if(three<10) {
			tempday = "0" + Integer.toString(three);
		}
		else
			tempday = Integer.toString(three);
		if(three_month<10) {
			tempmonth = "0" + Integer.toString(three_month);
		}
		else
			tempmonth = Integer.toString(three_month);
		String three_date = Integer.toString(three_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int four = cal.get(Calendar.DAY_OF_MONTH);
		int four_year = cal.get(Calendar.YEAR);
		int four_month = cal.get(Calendar.MONTH) + 1;
		if(four<10) {
			tempday = "0" + Integer.toString(four);
		}
		else
			tempday = Integer.toString(four);
		if(four_month<10) {
			tempmonth = "0" + Integer.toString(four_month);
		}
		else
			tempmonth = Integer.toString(four_month);
		String four_date = Integer.toString(four_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int five = cal.get(Calendar.DAY_OF_MONTH);
		int five_year = cal.get(Calendar.YEAR);
		int five_month = cal.get(Calendar.MONTH) + 1;
		if(five<10) {
			tempday = "0" + Integer.toString(five);
		}
		else
			tempday = Integer.toString(five);
		if(five_month<10) {
			tempmonth = "0" + Integer.toString(five_month);
		}
		else
			tempmonth = Integer.toString(five_month);
		String five_date = Integer.toString(five_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int six = cal.get(Calendar.DAY_OF_MONTH);
		int six_year = cal.get(Calendar.YEAR);
		int six_month = cal.get(Calendar.MONTH) + 1;
		if(six<10) {
			tempday = "0" + Integer.toString(six);
		}
		else
			tempday = Integer.toString(six);
		if(six_month<10) {
			tempmonth = "0" + Integer.toString(six_month);
		}
		else
			tempmonth = Integer.toString(six_month);
		String six_date = Integer.toString(six_year) + "-" + tempmonth + "-"
				+ tempday;

		cal.add(Calendar.DATE, -1);
		int seven = cal.get(Calendar.DAY_OF_MONTH);
		int seven_year = cal.get(Calendar.YEAR);
		int seven_month = cal.get(Calendar.MONTH) + 1;
		if(seven<10) {
			tempday = "0" + Integer.toString(seven);
		}
		else
			tempday = Integer.toString(seven);
		if(seven_month<10) {
			tempmonth = "0" + Integer.toString(seven_month);
		}
		else
			tempmonth = Integer.toString(seven_month);
		String seven_date = Integer.toString(seven_year) + "-" + tempmonth + "-"
				+ tempday;
		
		for(int i=0;i<theft.size();i++) {
			if(theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(one_date) || theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(two_date) ||
					 theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(three_date) || theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(four_date) ||
					 theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(five_date) || theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(six_date) ||
					 theft.get(i).getDatetime().split("[.]")[0].split(" ")[0].equals(seven_date)) {
				theftweek.add(theft.get(i));
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("theft", theftweek);
		map.put("name", name);

		model.addAllAttributes(map);
		return "theft";
	}
	
	@RequestMapping(value="/emergency/theft/thismonth/{id}")
	public String getTheftThisMonth(@PathVariable String id, Model model) {
		List<Emergency> theft = emergencyService.getTheft(id, "theft");
		String name = userService.getName(id);
		List<Emergency> theftmonth = new ArrayList<Emergency>();
		String tempmonth;
		
		for (int i = 0; i < theft.size(); i++) {
			theft.get(i).setDatetime(theft.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH) + 1;
		if(month<10) {
			tempmonth = "0" + Integer.toString(month);
		}
		else
			tempmonth = Integer.toString(month);
		
		for(int i=0; i<theft.size(); i++) {

			if(tempmonth.equals(theft.get(i).getDatetime().split("[.]")[0].split("-")[1])){
				theftmonth.add(theft.get(i));
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("theft", theftmonth);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "theftthismonth";
	}
	
	@RequestMapping(value="/emergency/theft/lastmonth/{id}")
	public String getTheftLastMonth(@PathVariable String id, Model model) {
		List<Emergency> theft = emergencyService.getTheft(id, "theft");
		
		String name = userService.getName(id);
		List<Emergency> theftmonth = new ArrayList<Emergency>();
		String tempmonth;
		
		for (int i = 0; i < theft.size(); i++) {
			theft.get(i).setDatetime(theft.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		if(month<10) {
			tempmonth = "0" + Integer.toString(month);
		}
		else
			tempmonth = Integer.toString(month);
		
		for(int i=0; i<theft.size(); i++) {

			if(tempmonth.equals(theft.get(i).getDatetime().split("[.]")[0].split("-")[1])){
				theftmonth.add(theft.get(i));
			}
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("theft", theftmonth);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "theftlastmonth";
	}
	
	@RequestMapping(value="/emergency/theft/thisyear/{id}")
	public String getTheftThisYear(@PathVariable String id, Model model) {
		List<Emergency> theft = emergencyService.getTheft(id, "theft");
		
		String name = userService.getName(id);
		List<Emergency> theftyear = new ArrayList<Emergency>();
		String tempyear;
		
		for (int i = 0; i < theft.size(); i++) {
			theft.get(i).setDatetime(theft.get(i).getDatetime().split("[.]")[0]);
		}
		
		Calendar cal = new GregorianCalendar();
		int year = cal.get(Calendar.YEAR);
		if(year<10) {
			tempyear = "0" + Integer.toString(year);
		}
		else
			tempyear = Integer.toString(year);
		
		for(int i=0; i<theft.size(); i++) {

			if(tempyear.equals(theft.get(i).getDatetime().split("[.]")[0].split("-")[0])){
				theftyear.add(theft.get(i));
			}
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar calmonth = new GregorianCalendar();
		String modelmonth = Integer.toString(calmonth.get(Calendar.MONTH)+1);
		String lastmonth = Integer.toString(calmonth.get(Calendar.MONTH));		
		map.put("month", modelmonth);
		map.put("lastmonth", lastmonth);
		map.put("theft", theftyear);
		map.put("name", name);

		model.addAllAttributes(map);
		
		return "theftthisyear";
	}
}
