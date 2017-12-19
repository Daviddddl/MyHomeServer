package service;


import UDPServer.DataSend;
import Util.FileUtil;

import java.io.IOException;
import java.net.SocketException;

public class SwitchControl {

    static String host = "192.168.43.50";
    static int port = 8889;

    public SwitchControl(){

    }

    public static String ControlWinOpen() throws SocketException {
        new DataSend("open the windows\n",host,port);
        return "Opening the windows!\n";
    }

    public static String ControlLampOpen() throws SocketException {
        new DataSend("open the lamp\n",host,port);
        return "Opening the lamp!\n";
    }

    public static String ControlLampClose() throws SocketException {
        new DataSend("close the lamp\n",host,port);
        return "Closing the lamp!\n";
    }

    public static String ControlTempHum() throws IOException {
        new DataSend("send the data\n",host,port);
        return new TempHumService().TempHumAnalyz();
    }

    public static String ControlWinClose() throws IOException {
        new DataSend("close the windows\n",host,port);
        return "Closing the windows!\n";
    }

    /*public static void main(String[] args) throws IOException {
        new SwitchControl().ControlTempHum();
    }*/
}
