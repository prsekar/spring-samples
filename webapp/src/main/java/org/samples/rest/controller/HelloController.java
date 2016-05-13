package org.samples.rest.controller;

import org.samples.rest.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Controller
public class HelloController {

    @Autowired
    DataService dataService;
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String helloApi() {
        return "Hello World!!!";
    }

    @RequestMapping(value = "/hello1")
    @ResponseBody
    public String asyncHello() throws ExecutionException, InterruptedException {
        return dataService.asyncDataAccess().get();
    }
}
