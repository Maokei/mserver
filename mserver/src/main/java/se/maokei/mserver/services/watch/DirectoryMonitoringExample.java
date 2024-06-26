package se.maokei.mserver.services.watch;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DirectoryMonitoringExample {
  private static final Logger LOGGER = LoggerFactory.getLogger(DirectoryMonitoringExample.class);

  private static final int POLL_INTERVAL = 500;

  public static void main(String[] args) throws Exception {
    System.out.println("Watching folder: " + System.getProperty("user.home"));
    //FileAlterationObserver observer = new FileAlterationObserver(System.getProperty("user.home"));
    FileAlterationObserver observer = new FileAlterationObserver("/home/maokei/wangi");
    FileAlterationMonitor monitor = new FileAlterationMonitor(POLL_INTERVAL);
    FileAlterationListener listener = new FileAlterationListenerAdaptor() {
      @Override
      public void onFileCreate(File file) {
        System.out.println("created " + file.getName());
        LOGGER.debug("File: " + file.getName() + " created");
      }

      @Override
      public void onFileDelete(File file) {
        LOGGER.debug("File: " + file.getName() + " deleted");
      }

      @Override
      public void onFileChange(File file) {
        LOGGER.debug("File: " + file.getName() + " changed");
      }
    };
    observer.addListener(listener);
    monitor.addObserver(observer);
    monitor.start();
  }
}