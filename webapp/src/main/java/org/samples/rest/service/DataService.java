package org.samples.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Service
public class DataService {

    @Autowired
    @Qualifier("dataExecutor")
    ThreadPoolTaskExecutor dataExecutor;
    
    public Future<String> asyncDataAccess() {
        
       Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "hello world";
            }
        };

        return dataExecutor.submitListenable(task);
    }

}
