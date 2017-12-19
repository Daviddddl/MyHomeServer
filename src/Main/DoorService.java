package Main;

import TCPServer.TCPServerThread;
import UDPServer.DataSend;
import Util.FileUtil;

import java.io.IOException;

public class DoorService implements Runnable{

    public DoorService(){}

//    static String host = "192.168.43.184";
//    static int port = 8889;

    public static String acceptList = "";
    private static String filePath = "D:\\Documents\\课件\\I0T\\MyHomeServer\\";
    private static String exePath = "D:\\Documents\\课件\\I0T\\RFIDFiles\\Release\\";

    @Override
    public void run() {

        /**
         * 运行RFID应用程序
         */
        new Thread(){
            @Override
            public void run() {
                Process proc = null;
                try {
                    proc = Runtime.getRuntime().exec(exePath+"Demo.exe");
                    proc.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public static void DoorAccept(){

        /**
         * 开始读取RFID记录的文件
         */
        try {
            acceptList = FileUtil.readFile(filePath+"Record.txt");
            FileUtil.writeFile(acceptList,filePath+"RecordBack.txt");

            //E2 00 40 80 67 18 00 50 12 80 96 A2  ---用户1
            //E2 00 41 07 73 06 01 47 27 50 07 4B  ---用户2
            //00 11 22 33 44 55 66 77 88 99 AA BB  ---用户3
            //E2 00 40 80 67 18 01 68 12 70 99 C1  ---陌生人1
            //E2 00 40 80 67 18 00 98 13 60 8E 12  ---陌生人2

            acceptList = acceptList.replaceAll("E2 00 40 80 67 18 00 50 12 80 96 A2","用户1");
            acceptList = acceptList.replaceAll("E2 00 41 07 73 06 01 47 27 50 07 4B","用户2");
            acceptList = acceptList.replaceAll("00 11 22 33 44 55 66 77 88 99 AA BB","用户3");
            acceptList = acceptList.replaceAll("E2 00 40 80 67 18 01 68 12 70 99 C1","陌生人1");
            acceptList = acceptList.replaceAll("E2 00 40 80 67 18 00 98 13 60 8E 12","陌生人2");

            if(!acceptList.trim().equals("")){
                System.out.println("\n来的人是" + acceptList.trim() + "\n");

                //此处开始将判断出的人的信息发送给前端
                //new DataSend(acceptList.trim(),host,port);
            }


            FileUtil.clearFile(filePath+"Record.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
