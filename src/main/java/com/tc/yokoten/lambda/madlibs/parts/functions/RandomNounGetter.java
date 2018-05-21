package com.tc.yokoten.lambda.madlibs.parts.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.tc.yokoten.lambda.madlibs.response.ApiGatewayResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class RandomNounGetter implements RequestHandler<Map<String,Object>, String> {

    @Override
    public String handleRequest(Map<String,Object> s, Context context) {
        int total= Arrays.asList(Noun.values()).size();
        System.out.println(total);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int indexOfNoun = -1;
        while(indexOfNoun < 0){
            indexOfNoun = rand.nextInt() % total;
        }

        return Noun.values()[indexOfNoun].getVal();

    }

    public enum Noun {
        Person("person"),
        Place("thing"),
        Idea("idea");

        private String val;

        Noun(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}
