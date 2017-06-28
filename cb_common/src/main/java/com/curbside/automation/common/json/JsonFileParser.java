package com.curbside.automation.common.json;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kumar.nipun on 6/23/2017.
 */
public class JsonFileParser {
  public JSONObject extractJsonFromFile(String filePath) {
    JSONObject jsonObject = null;
    
    filePath= filePath.replace("%20" , " ");
    String jsonContents;
	try {
		jsonContents = FileUtils.readFileToString(new File(filePath));
		jsonObject = new JSONObject(jsonContents);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    return jsonObject;
  }
}
