package com.seshu.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConversion {
	String jsonFile = "C:\\Projects\\Seshu\\WhiteBoard\\input\\json\\resp.json";
	public static void main(String args[]) {
			
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("receiptkey", "0000002235");
		map.put("storerkey", "TRC");
		map.put("expectedqty", "336");
		map.put("receivedqty", "775");		
		map.put("infokey", "0002384938");
		JsonHelper helper = new JsonHelper();
		String json = helper.getJson(map);
		//System.out.println(json);
		
		//CarDTO dto = new CarDTO();
		
		//System.out.println(new JsonConversion().getJsonFromDTO(dto));
		//TestDTO test = new JsonConversion().getDTOFromJson();
		//System.out.println(test.getResult().getIdToken());
		
		String sampleDate = "2019-10-31 13:52:52.000";
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			  
		try {
			Date date = format.parse(sampleDate);
			System.out.println(date);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public String getJsonFromDTO(TestDTO dto) {
		String jsonString = "";
		try {
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(dto);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return jsonString;
	}
	
	/*
	 * public TestDTO getDTOFromJson() {
	 * 
	 * TestDTO dto = new TestDTO(); try{ String jsonContent =
	 * returnFileContent(jsonFile); ObjectMapper mapper = new ObjectMapper();
	 * StringReader reader = new StringReader(jsonContent); dto =
	 * (TestDTO)mapper.readValue(reader, TestDTO.class); } catch(Exception e){
	 * e.printStackTrace(); } return dto; }
	 */
	
	public String returnFileContent(String filePath)
	{
		StringBuffer sb = new StringBuffer();
		try{
			FileReader fileReader = new FileReader(filePath);		
			BufferedReader br = new BufferedReader(fileReader);
			String line = br.readLine();
			while(line!=null)
			{
				sb.append(line);
				line = br.readLine();
			}
			if(br != null){
				br.close();
			}
		}catch(Exception e){
			
		}
		return sb.toString();		
	}
}