package com.tc.yokoten.lambda.madlibs.parts.invocation;

import com.amazonaws.services.lambda.invoke.LambdaFunction;

import java.util.Map;


public interface PartsOfSpeechService {

    @LambdaFunction(functionName="GetRandomNoun")
    String getRandomNoun(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomVerb")
    String getRandomVerb(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomAdverb")
    String getRandomAdverb(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomAdjective")
    String getRandomAdjective(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomPreposition")
    String getRandomPreposition(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomPronoun")
    String getRandomPronoun(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomConjunction")
    String getRandomConjunction(Map<String,Object> input);

    @LambdaFunction(functionName="GetRandomArticle")
    String getRandomArticle(Map<String,Object> input);
}

