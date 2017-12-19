package Main;

import TCPServer.TCPServer;
import Util.FileListener;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainService implements Runnable{

    public MainService(){}

    public static void main(String[] args) throws Exception {
        new Thread(new MainService()).start();
        new Thread(new DoorService()).start();


        // 监控目录
        String rootDir = "D:\\Documents\\课件\\I0T\\MyHomeServer\\";
        // 轮询间隔 5 秒
        long interval = TimeUnit.SECONDS.toMillis(5);
        // 创建一个文件观察器用于处理文件的格式
        FileAlterationObserver _observer = new FileAlterationObserver(
                rootDir, FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter(".txt")), //过滤文件格式
                null);
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);

        observer.addListener(new FileListener()); //设置文件变化监听器
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
    }



    @Override
    public void run() {
        new TCPServer();
    }
}
