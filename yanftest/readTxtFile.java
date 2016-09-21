package cn.les.yanftest;
import net.sf.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
/**
 * Created by 严峰 on 2016/7/5 0005.
 */
public class readTxtFile {
    public static int nm=0;
    public static String[] peIds={"char pe_id_hum_sensor[]=\"","char pe_id_temp_sensor[]=\"","char pe_id_vibra_sensor[]=\""
                                  ,"char pe_id_speed_sensor[]=\"","char pe_id_air_hum_sensor[]=\""
                                  ,"char pe_id_air_temp_sensor[]=\"","char pe_id_food_rfid_sensor[]=\""
                                  ,"char pe_id_food_temp_sensor[]=\"","char pe_id_hos_hum_sensor[]=\"","char pe_id_hos_temp_sensor[]=\""};
    public static String[] upKeys={"char upKeyHumSensor[]=\"","char upKeyTempSensor[]=\"","char upKeyVibraSensor[]=\""
                                    ,"char upKeySpeedSensor[]=\"","char upKeyAirHumSensor[]=\"","char upKeyAirTempSensor[]=\""
                                    ,"char upKeyFoodRFIDSensor[]=\"","char upKeyFoodTempSensor[]=\"","char upKeyHosHumSensor[]=\"","char upKeyHosTempSensor[]=\""};
    public static String[] downKeys={"char downKeyHumSensor[]=\"","char downKeyTempSensor[]= \"","char downKeyVibraSensor[]= \""
                                    ,"char downKeySpeedSensor[]= \"","char downKeyAirHumSensor[]= \""
                                    ,"char downKeyAirTempSensor[]= \"","char downKeyFoodRFIDSensor[]= \""
                                    ,"char downKeyFoodTempSensor[]= \"","char downKeyHosHumSensor[]= \"","char downKeyHosTempSensor[]= \""};
    public static String[] baseKeys={"char basicKeyHumSensor[]=\"","char basicKeyTempSensor[]=\"","char basicKeyVibraSensor[]=\""
                                    ,"char basicKeySpeedSensor[]=\"","char basicKeyAirHumSensor[]=\"","char basicKeyAirTempSensor[]=\""
                                    ,"char basicKeyFoodRFIDSensor[]=\"","char basicKeyFoodTempSensor[]=\"","char basicKeyHosHumSensor[]=\""
                                    ,"char basicKeyHosTempSensor[]=\""};
    public static void readTxtFile1(String filePath,int n){
    try {
        String encoding="UTF-8";
        File file=new File(filePath);
        if(file.isFile() && file.exists()){ //判断文件是否存在
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file),encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while((lineTxt = bufferedReader.readLine()) != null){
                lineTxt=lineTxt.substring(1);
                JSONObject jo= JSONObject.fromObject(lineTxt);
                JSONObject jo1=JSONObject.fromObject(jo.getJSONObject("pe"));
                System.out.println("//"+filePath);
                System.out.println(peIds[n]+jo1.getString("deviceID")+"\\0\";");
                System.out.println(upKeys[n]+jo1.getString("upKey")+"\\0\";");
                System.out.println(downKeys[n]+jo1.getString("downKey")+"\\0\";");
                System.out.println(baseKeys[n]+jo1.getString("baseKey")+"\\0\";");
                nm++;
//                System.out.println(lineTxt);
            }
            read.close();
        }else{
            System.out.println();
            System.out.println("找不到指定的文件");
        }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
        e.printStackTrace();
    }

}

    public static void main(String argv[]){
        String filePath = "C:\\Users\\Administrator\\Desktop\\模拟设备\\航空运输港温度传感器\\航空运输港温度传感器";
//      "res/";
        nm=0;
        //System.out.println("\"");
        for (int i=0;i<10;i++){
            int n=i+ 49;
            String filePath1;
            if(n<10) {
                filePath1 = filePath + "0000" + n + ".txt";
            }else{
                filePath1 = filePath + "000" + n + ".txt";
            }
                //System.out.println(filePath1);
            readTxtFile1(filePath1,i);
        }
        System.out.println();
        System.out.println(nm);
    }
}
