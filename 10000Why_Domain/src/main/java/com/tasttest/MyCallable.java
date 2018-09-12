package com.tasttest;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    private long waitTime;

    public MyCallable(long waitTime){
        this.waitTime = waitTime;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        return Thread.currentThread().getName()+"sleep:"+waitTime;
    }
}
