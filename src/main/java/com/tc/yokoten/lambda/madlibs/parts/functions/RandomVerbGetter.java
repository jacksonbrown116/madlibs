package com.tc.yokoten.lambda.madlibs.parts.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class RandomVerbGetter implements RequestHandler<Map<String,Object>, String> {

    @Override
    public String handleRequest(Map<String,Object> s, Context context) {
        int total= Arrays.asList(RandomVerbGetter.Verb.values()).size();
        System.out.println(total);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int indexOfVerb = -1;
        while(indexOfVerb < 0){
            indexOfVerb = rand.nextInt() % total;
        }

        return RandomVerbGetter.Verb.values()[indexOfVerb].getVal();

    }

    public enum Verb {
        Walk("walk"),
        Jump("jump"),
        Run("run");

        private String val;

        Verb(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}