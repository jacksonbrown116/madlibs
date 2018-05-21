package com.tc.yokoten.lambda.madlibs.parts.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class RandomPrepositionGetter implements RequestHandler<Map<String,Object>, String> {

    @Override
    public String handleRequest(Map<String,Object> s, Context context) {
        int total= Arrays.asList(RandomPrepositionGetter.Preposition.values()).size();
        System.out.println(total);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int index = -1;
        while(index < 0){
            index = rand.nextInt() % total;
        }

        return RandomPrepositionGetter.Preposition.values()[index].getVal();

    }

    public enum Preposition {
        Under("under"),
        Beneath("beneath"),
        Below("below");

        private String val;

        Preposition(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}