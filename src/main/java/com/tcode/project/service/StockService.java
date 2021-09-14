package com.tcode.project.service;

import com.tcode.project.model.response.StockListResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class StockService {

    @Value("${alphaVantage.key}")
    private String apiKey;
    @Value("${publicDay.key}")
    private String dayKey;

    public List<StockListResponse>  getStock(String keyword) throws IOException {
        List<StockListResponse> result = new ArrayList<>();
        List<Map<String, String>> params2 =  readJsonFromUrl("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="
            +keyword
            +"&apikey="+apiKey);
        for(Map<String, String> data : params2){
            StockListResponse response = StockListResponse.builder()
                .highPrice(data.get("stockHighValue"))
                .lowPrice(data.get("stockLowValue"))
                .closePrice(data.get("stockCloseValue"))
                .openPrice(data.get("stockOpenValue"))
                .volumeData(data.get("stockVolme"))
                .stockDate(data.get("stockDate"))
                .build();
            result.add(response);
        }
        return result;

    }

  public static List<Map<String, String>> readJsonFromUrl(String url)
      throws IOException {
    List<Map<String, String>> params = new ArrayList<Map<String, String>>();
    InputStream is = new URL(url).openStream();
    try {
      List<String> dateList = new ArrayList<String>();
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = "";
      String line = "";

      while ((line = rd.readLine()) != null) {
        jsonText += line;
      }
      JSONObject json = new JSONObject(jsonText);
      JSONObject timeSeriesJson = json.getJSONObject("Time Series (Daily)");
      dateList = cal();
      for(int i=0;i<dateList.size();i++) {

          Map<String, String> model = new HashMap<String, String>();
          if(timeSeriesJson.isNull(dateList.get(i))){
              //미국 공휴일
                System.out.println(dateList.get(i));
          }else{
              JSONObject dailyObject = timeSeriesJson.getJSONObject(dateList.get(i));
              model.put("stockDate",dateList.get(i));/*날짜정보*/
              model.put("stockOpenValue",dailyObject.getString("1. open"));
              model.put("stockHighValue",dailyObject.getString("2. high"));
              model.put("stockLowValue",dailyObject.getString("3. low"));
              model.put("stockCloseValue",dailyObject.getString("4. close"));
              model.put("stockVolme",dailyObject.getString("5. volume"));
              params.add(model);
          }

      }

    } catch (JSONException | ParseException e) {
      System.out.println(e.getMessage());
    } finally {
      is.close();
    }

      return params;
    }

    public static List<String> cal() throws ParseException {
        List<String> dateList = new ArrayList<String>();
        SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        Date time = new Date();

        start.setTime(time);
        start.add(Calendar.DATE,-1);

        end.setTime(time);
        end.add(Calendar.DATE,-31);

        String time1 = format1.format(start.getTime());
        String time2 = format1.format(end.getTime());

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(format1.parse(time1));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(format1.parse(time2));
        while(cal1.after(cal2)){
            int day = cal1.get(Calendar.DAY_OF_WEEK);
            if ((day != Calendar.SATURDAY) && (day != Calendar.SUNDAY)){
                dateList.add(format1.format(cal1.getTime()));
            }
            cal1.add(Calendar.DATE, -1);
        }
        return dateList;
    }
}
