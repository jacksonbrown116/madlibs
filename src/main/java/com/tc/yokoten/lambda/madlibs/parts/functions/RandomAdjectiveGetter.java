package com.tc.yokoten.lambda.madlibs.parts.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class RandomAdjectiveGetter implements RequestHandler<Map<String,Object>, String> {

    @Override
    public String handleRequest(Map<String,Object> s, Context context) {
        int total= Arrays.asList(RandomAdjectiveGetter.Adjective.values()).size();
        System.out.println(total);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int index = -1;
        while(index < 0){
            index = rand.nextInt() % total;
        }

        return RandomAdjectiveGetter.Adjective.values()[index].getVal();

    }

    public enum Adjective {
        Blue("blue"),
        Slow("slow"),
        Difficult("difficult");

        private String val;

        Adjective(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}
