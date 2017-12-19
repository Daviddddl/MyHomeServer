
package Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ContextFileListener implements ApplicationListener<ContextRefreshedEvent>{


    @Autowired
    private FileMonitor fileMonitor;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try{
            fileMonitor.monitor();
            fileMonitor.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}