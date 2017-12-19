package service;

import Util.FileUtil;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Pattern;

public class TempHumService {

    String msg = null;
    String data = null;
    float temp;
    float humidity;
    float gqa;
    float gqd;
    float air;
    float aird;
    String filepath = "TCPRecvDataBack.txt";
    int tempcount = 0;
    int humiditycount = 0;
    int gqacount = 0;
    int gqdcount = 0;
    int aircount = 0;
    int airdcount = 0;

    public TempHumService(){}

    public String TempHumAnalyz() throws IOException {

        msg = FileUtil.readFile(filepath);

        msg = FileUtil.replaceBlankLine(msg);

        String[] msgsplit = msg.split("\r\n");

        for(String s : msgsplit){

            String[] msgsplit2 = s.split(",");

            for(String s2 : msgsplit2){

                if(s2.contains("Humidity")){
                    humiditycount++;
                    humidity = Float.valueOf(s2.replaceAll("[a-zA-Z]*:\b*",""));
                }

                if(s2.contains("Temperature")){
                    tempcount++;
                    temp = Float.valueOf(s2.replaceAll("[a-zA-Z]*:\b*",""));
                }

                if(s2.contains("GQa")){
                    gqacount++;
                    gqa = Float.valueOf(s2.replaceAll("[a-zA-Z]*:\b*",""));
                }

                if(s2.contains("GQd")){
                    gqdcount++;
                    gqd = Float.valueOf(s2.replaceAll("[a-zA-Z]*:\b*",""));
                }

                if(s2.contains("AIR")){
                    aircount++;
                    air = Float.valueOf(s2.replaceAll("[a-zA-Z]*:\b*",""));
                }

                if(s2.contains("AIRD")){
                    airdcount++;
                    aird = Float.valueOf(s2.replaceAll("[a-zA-Z]*:\b*",""));
                }

            }
        }


        System.out.println(humidity+"---"+temp+"---"+gqa+"---"+air);
        /*humidity /= humiditycount;
        temp /= tempcount;
        gqa /= gqacount;
        gqd /= gqacount;
        air /= aircount;
        aird /= airdcount;*/

        /*temp = 0;
        humidity = 1;
        gqa = 2;
        gqd = 3;
        air = 4;
        aird = 5;*/

        //data = "temp:"+temp+";"+"hum:"+humidity+";"+"GQa:" + gqa +";" + "GQd:" + gqd + ";" + "AIR:" + air + "AIRD:" + aird + "\n";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("temp",String.valueOf(temp));
        jsonObject.put("hum",String.valueOf(humidity));
        jsonObject.put("GQa",String.valueOf(gqa));
        jsonObject.put("GQd",String.valueOf(gqd));
        jsonObject.put("AIR",String.valueOf(air));
        jsonObject.put("AIRD",String.valueOf(aird));

        String data = jsonObject.toString();

        System.out.println(data);

        FileUtil.writeFile(data+"\n","TCPDataAnalyz.txt");

        return data+"\n";

    }

    public static void main(String[] args) throws IOException {

        new TempHumService().TempHumAnalyz();
    }
}
