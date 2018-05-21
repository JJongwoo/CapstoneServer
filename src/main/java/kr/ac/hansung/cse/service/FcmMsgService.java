package kr.ac.hansung.cse.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FcmMsgService {

	public void sendPush(String msg/*, MobileTokenVO vo*/)
			throws Exception {

		/*List<MobileTokenVO> tokenList = fcmService.loadFCMInfoList(vo);
		
		String token = tokenList.get(count).getDEVICE_ID();*/

		final String apiKey = "AAAAuAye5qg:APA91bEYUsK7pM--1J7u1GMP-Oi6oY80QbnMahRCGywE5VNob2jH9TxBjDSbX08hEO-3pneSAODXauAS2ss7v32KNi2eeR3HfgYADWE2Kuz4DZJkaZhvuEwJVXoFyWWvbUntiE3n2uJP";
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key=" + apiKey);
		conn.setDoOutput(true);

		//String userId = (String) request.getSession().getAttribute("ssUserId");

		// 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
		//String input = "{\"notification\" : {\"title\" : \"Hello GrandMa!\", \"body\" : \"" + msg + " \"}, \"to\":\"/topics/ALL\"}";

		JSONObject root = new JSONObject();
        JSONObject notification = new JSONObject();
        notification.put("body", msg);
        notification.put("title", "Hello GrandMa!");
        root.put("data", notification);
        root.put("to", "/topics/ALL");
        root.put("click_action", "OPEN_ACTIVITY"); // click_action 추가!
        String input = root.toString();
		
		// 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다 위에 둘중에 한개 골라서 날려주자
		//String input = "{\"notification\" : {\"title\" : \" 여기다 제목넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\" 여기가 받을 사람 토큰  \"}";

		OutputStream os = conn.getOutputStream();

		// 서버에서 날려서 한글 깨지는 사람은 아래처럼 UTF-8로 인코딩해서 날려주자
		os.write(input.getBytes("UTF-8"));
		os.flush();
		os.close();

		int responseCode = conn.getResponseCode();
		//System.out.println("\nSending 'POST' request to URL : " + url);
		//System.out.println("Post parameters : " + input);
		//System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		// print result
		System.out.println(response.toString());

	}
	
}
