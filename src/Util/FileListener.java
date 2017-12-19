
package Util;

import Main.DoorService;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import org.apache.log4j.Logger;

import java.io.File;

/**
 *
 * 文件变化监听器
 *
 * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 *
 */

public class FileListener extends FileAlterationListenerAdaptor {

    private Logger log = Logger.getLogger(FileListener.class);

    @Override
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        System.out.println("onStart()");
        super.onStart(observer);

    }

    @Override
    public void onDirectoryCreate(File directory) {
        // TODO Auto-generated method stub
        System.out.println("onDirectoryCreate()");
        log.info("[新建]:" + directory.getAbsolutePath());
    }


    @Override
    public void onDirectoryChange(File directory) {
        // TODO Auto-generated method stub
        System.out.println("onDirectoryChange()");
        log.info("[修改]:" + directory.getAbsolutePath());
    }

    @Override
    public void onDirectoryDelete(File directory) {
        // TODO Auto-generated method stub
        System.out.println("onDirectoryDelete()");
        log.info("[删除]:" + directory.getAbsolutePath());
    }

    @Override
    public void onFileCreate(File file) {
        // TODO Auto-generated method stub
        System.out.println("onFileCreate()");
        log.info("[新建]:" + file.getAbsolutePath());
    }

    @Override
    public void onFileChange(File file) {
        // TODO Auto-generated method stub
        System.out.println("onFileChange()");
        log.info("[修改]:" + file.getAbsolutePath());

        //文件变化，开始运行门禁系统
        DoorService.DoorAccept();
    }

    @Override
    public void onFileDelete(File file) {
        // TODO Auto-generated method stub
        System.out.println("onFileDelete()");
        log.info("[删除]:" + file.getAbsolutePath());
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        System.out.println("onStop()");
        super.onStop(observer);
    }
}

