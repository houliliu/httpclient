package com.chf;

import java.io.IOException;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author 温柔一刀
 * @create 2018-10-23 22:24
 **/
public class ThreadDownload   implements Runnable{

    private int num;

    private String url;

    private String localFilePath;

    public ThreadDownload(String url,int num, String localFilePath) {
        this.num = num;
        this.url = url;
        this.localFilePath = localFilePath;
    }
    @Override
    public void run() {
        try {
            HttpHelper.executeDownloadFile(HttpHelper.createHttpClient(),url,localFilePath,"UTF-8",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
