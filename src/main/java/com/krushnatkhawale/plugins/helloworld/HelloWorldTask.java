package com.krushnatkhawale.plugins.helloworld;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class HelloWorldTask extends DefaultTask {

    @TaskAction
    public void helloWorldAction(){

        System.out.println("\n  HelloWorldTask: HELLO WORLD!");

    }
}