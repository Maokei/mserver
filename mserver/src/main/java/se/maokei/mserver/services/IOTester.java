package se.maokei.mserver.services;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
public class IOTester {
  public static void main(String[] args) {
    try {
      usingFileAlterationMonitor();
    } catch(IOException e) {
      System.out.println(e.getMessage());
    }
  }
  public static void usingFileAlterationMonitor() throws IOException {
    //get the file object
    File inputFile = FileUtils.getFile("input.txt");
    String absolutePath = inputFile.getAbsolutePath();
    String parent = absolutePath.substring(0,absolutePath.indexOf("input.txt"));
    File parentDirectory = FileUtils.getFile(parent);
    FileAlterationObserver observer = new
            FileAlterationObserver(parentDirectory);
    observer.addListener(new FileAlterationListenerAdaptor(){
      @Override
      public void onDirectoryCreate(File file) {
        System.out.println("Folder created: " + file.getName());
      }
      @Override
      public void onDirectoryDelete(File file) {
        System.out.println("Folder deleted: " + file.getName());
      }
      @Override
      public void onFileCreate(File file) {
        System.out.println("File created: " + file.getName());
      }
      @Override
      public void onFileDelete(File file) {
        System.out.println("File deleted: " + file.getName());
      }
    });
    //create a monitor to check changes after every 500 ms
    FileAlterationMonitor monitor = new FileAlterationMonitor(500, observer);
    try {
      monitor.start();
      //create a new directory
      File newFolder = new File("test");
      File newFile = new File("test1");
      newFolder.mkdirs();
      Thread.sleep(1000);
      newFile.createNewFile();
      Thread.sleep(1000);
      FileDeleteStrategy.NORMAL.delete(newFolder);
      Thread.sleep(1000);
      FileDeleteStrategy.NORMAL.delete(newFile);
      Thread.sleep(1000);
      monitor.stop(10000);
    } catch(IOException e) {
      System.out.println(e.getMessage());
    } catch(InterruptedException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}