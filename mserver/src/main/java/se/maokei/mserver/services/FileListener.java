package se.maokei.mserver.services;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class FileListener extends FileAlterationListenerAdaptor {
  @Override
  public void onDirectoryCreate(File directory) {
    System.out.println("crap");
  }

  public static void main(String []args) throws Exception {
    // Monitoring catalogue
    String rootDir = "/home/maokei/wangi";
    // Polling interval 5 seconds
    long interval = TimeUnit.SECONDS.toMillis(1);
    // Create filters
    IOFileFilter directories = FileFilterUtils.and(
            FileFilterUtils.directoryFileFilter(),
            HiddenFileFilter.VISIBLE);
    IOFileFilter files    = FileFilterUtils.and(
            FileFilterUtils.fileFileFilter(),
            FileFilterUtils.suffixFileFilter(".txt"));
    IOFileFilter filter = FileFilterUtils.or(directories, files);
    // Use filters
    FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
    // Do not use filters
    //FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
    observer.addListener(new FileListener());
    // Create a File Change Listener
    FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
    // Start monitoring
    monitor.start();
  }
}
