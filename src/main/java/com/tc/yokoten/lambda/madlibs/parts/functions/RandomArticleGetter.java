package com.tc.yokoten.lambda.madlibs.parts.functions;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class RandomArticleGetter implements RequestHandler<Map<String,Object>, String> {

    @Override
    public String handleRequest(Map<String,Object> s, Context context) {
        int total= Arrays.asList(RandomArticleGetter.Article.values()).size();
        System.out.println(total);

        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());

        int index = -1;
        while(index < 0){
            index = rand.nextInt() % total;
        }

        return RandomArticleGetter.Article.values()[index].getVal();

    }

    public enum Article {
        A("a"),
        An("an"),
        The("the");

        private String val;

        Article(String val){
            this.val = val;
        }

        public String getVal() {
            return val;
        }
    }
}
