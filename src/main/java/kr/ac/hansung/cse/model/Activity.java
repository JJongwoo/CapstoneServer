package kr.ac.hansung.cse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Activity {

	private String location;
	private String latest_locationtime;
	
	private int count_toilet;
	private String last_toilettime;
	
}
