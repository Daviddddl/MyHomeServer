package Util;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 *
 * 文件监控测试
 *
 * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 *
 */

public class FileMonitor {
    private FileAlterationMonitor monitor = null;

    private String filePath;
    private FileAlterationListener alterationListener;
    private long intervalTime = 10000L;

    public FileMonitor(long interval) throws Exception {
        monitor = new FileAlterationMonitor(interval);
    }
    public FileMonitor() throws Exception {
        monitor = new FileAlterationMonitor(intervalTime);
    }

    public void monitor(String path, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));
        monitor.addObserver(observer);
        observer.addListener(listener);
    }
    public void monitor() {
        FileAlterationObserver observer = new FileAlterationObserver(new File(filePath));
        monitor.addObserver(observer);
        observer.addListener(alterationListener);
    }
    public void stop() throws Exception{
        monitor.stop();
    }
    public void start() throws Exception {
        monitor.start();
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FileAlterationListener getAlterationListener() {
        return alterationListener;
    }

    public void setAlterationListener(FileAlterationListener alterationListener) {
        this.alterationListener = alterationListener;
    }
    public long getIntervalTime() {
        return intervalTime;
    }
    public void setIntervalTime(long intervalTime) {
        this.intervalTime = intervalTime;
    }
}
