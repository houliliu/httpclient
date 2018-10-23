package com.chf;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        long start=System.currentTimeMillis();
        System.out.println( "Hello World!" );
        String url="http://mirrors.163.com/centos/7.5.1804/isos/x86_64/CentOS-7-x86_64-NetInstall-1804.torrent";
        String localFilePath="G:/downLoad-test/";
       // HttpHelper.executeDownloadFile(HttpHelper.createHttpClient(),url,localFilePath,"UTF-8",true);

        CounterPoolExecutor executor = new CounterPoolExecutor(200, 200, 300,
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 200; i++) {
            executor.execute(new ThreadDownload(url,i,localFilePath+i+".torrent"));
        }
        executor.shutdown();
        long end=System.currentTimeMillis();
        System.out.println( "end----------- 花费时间："+(end-start) );
    }
}
