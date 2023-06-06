package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import org.json.JSONObject;

import jakarta.servlet.http.HttpServletRequest;

public class JSONHelper {

    public String readRequestData(HttpServletRequest req) throws IOException {
        StringBuilder requestData = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            requestData.append(line);
        }
        return requestData.toString();
    }

    public JSONObject parseRequestData(String requestData) {
        return new JSONObject(requestData);
    }

    public String extractField(JSONObject jsonData, String fieldName) {
        return jsonData.optString(fieldName);
    }

}
