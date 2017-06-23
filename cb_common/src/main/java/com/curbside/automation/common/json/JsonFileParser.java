package com.curbside.automation.common.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kumar.nipun on 6/23/2017.
 */
public class JsonFileParser {
  public JSONObject extractJsonFromFile(String filePath) {
    JSONObject jsonObject = null;
    try {
      FileReader reader = new FileReader(filePath.replace("%20" , " "));

      JSONParser jsonParser = new JSONParser();
      jsonObject = (JSONObject) jsonParser.parse(reader);
    } catch (IOException | ParseException ex) {
      ex.printStackTrace();
    }
    return jsonObject;
  }
}
