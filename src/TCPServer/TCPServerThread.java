package TCPServer;

import Main.DoorService;
import Util.FileUtil;
import service.SwitchControl;
import service.TempHumService;

import java.io.*;
import java.net.Socket;

/*
 * 服务器线程处理类
 */
public class TCPServerThread extends Thread {

    // 和本线程相关的Socket
    public static Socket socket = null;
    InputStream is=null;
    InputStreamReader isr=null;
    BufferedReader br=null;
    OutputStream os=null;
    public static DataOutputStream pw=null;

    public TCPServerThread(Socket socket) throws IOException {
        this.socket = socket;
        os = socket.getOutputStream();
        pw = new DataOutputStream(os);
        is = socket.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
    }

    //线程执行的操作，响应客户端的请求
    public void run(){
        FileUtil.clearFile("TCPRecvData.txt");
        try {
            //获取输入流，并读取客户端信息

            boolean win = true;
            boolean lamp = true;
            String info;
            String data = "";
            while((info=br.readLine())!=null){//循环读取客户端的信息

                data += info+"\n";
                System.out.println("我是服务器，客户端说："+info);

                if(info.trim().contains("refresh")){

                    SwitchControl.ControlTempHum();
                    pw.writeUTF(new TempHumService().TempHumAnalyz());
                }

                if(info.trim().contains("Win")){
                    if(win){
                        pw.writeUTF(SwitchControl.ControlWinClose());
                        System.out.println("Windows closing..." + "win state: " + win);
                        win = false;
                    }else{
                        pw.writeUTF(SwitchControl.ControlWinOpen());
                        System.out.println("Windows opening..."+ "win state: " + win);
                        win = true;
                    }
                }

                if(info.trim().contains("Lamp")){
                    if(lamp){
                        pw.writeUTF(SwitchControl.ControlLampClose());
                        System.out.println("Lamp closing ..."+ "lamp state: " + lamp);
                        lamp = false;
                    }else {
                        pw.writeUTF(SwitchControl.ControlLampOpen());
                        System.out.println("Lamp opening..."+ "lamp state: " + lamp);
                        lamp = true;
                    }
                }

            }

            socket.shutdownInput();//关闭输入流

            //获取输出流，响应客户端的请求
            //pw.write("连接成功！欢迎您，接收到的消息是："+data);

            System.out.println("Receive Data:" + data+"==============");
            FileUtil.writeFile(data,"TCPRecvData.txt");
            FileUtil.writeFile("\n----------------------\n"+data, "TCPRecvDataBack.txt");
            pw.flush();//调用flush()方法将缓冲输出


        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            //关闭资源
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}