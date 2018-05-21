package com.tc.yokoten.lambda.madlibs.parts.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class RandomAdverbGetter implements RequestHandler<Map<String,Object>, String> {

    @Override
    public String handleRequest(Map<String,Object> s, Context context) {
        int total= Arrays.asList(RandomAdverbGetter.Adverb.values()).size();
        System.out.println(total);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int index = -1;
        while(index < 0){
            index = rand.nextInt() % total;
        }

        return RandomAdverbGetter.Adverb.values()[index].getVal();

    }

    public enum Adverb {
        Quickly("quickly"),
        Very("very"),
        Quietly("quietly");

        private String val;

        Adverb(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}
