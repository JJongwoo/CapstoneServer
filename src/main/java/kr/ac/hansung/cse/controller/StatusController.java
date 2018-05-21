package kr.ac.hansung.cse.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hansung.cse.service.SensorDataService;

@Controller
public class StatusController {

	@Autowired
	SensorDataService sensorDataService = new SensorDataService();

	@RequestMapping(value = "/savestatus2", method = RequestMethod.POST, produces = { "application/json" })
	public /* Map<String, Object> */String makerepo(@RequestBody Map<String, Object> info) {

		// System.out.println(info);

		sensorDataService.saveData(info.get("dir1Value").toString(), info.get("dir2Value").toString(),
				info.get("id").toString(), info.get("sona1Value").toString(), info.get("sona2Value").toString(),
				info.get("buttonValue").toString(), info.get("shock1Value").toString());

		return "empty";

	}

	@RequestMapping(value = "/savestatus", method = RequestMethod.GET)
	public String makerepo() {

		// sensorDataService.saveData("31", "50", "jwkim");

		return "home";
	}

	@RequestMapping(value = "/empty")
	public String empty() {

		return "empty";
	}

}
