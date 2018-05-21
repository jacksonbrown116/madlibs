package com.tc.yokoten.lambda.madlibs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tc.yokoten.lambda.madlibs.parts.invocation.PartsOfSpeechService;
import com.tc.yokoten.lambda.madlibs.response.ApiGatewayResponse;

public class MadLibGetter implements RequestHandler<Map<String,Object>, ApiGatewayResponse> {

    private static final String jdbcDatabaseUrl = "jdbc:postgresql://tcmadlibs.chpcwfrdxrr1.us-east-2.rds.amazonaws.com:5432/YokotenMadLibs";
    private static final String user = "YokotenUser";
    private static final String password = "YokotenTime";
    private static final Gson gson = new Gson();

    private PartsOfSpeechService service;


    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        Connection dbConnection = null;
        Statement statement = null;
        try {

           service = LambdaInvokerFactory
                    .builder()
                    .lambdaClient(AWSLambdaAsyncClientBuilder.defaultClient())
                    .build(PartsOfSpeechService.class);

            dbConnection = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
            statement = dbConnection.createStatement();

            String id = gson.fromJson(gson.toJson(input), JsonObject.class).get("pathParameters").getAsJsonObject().get("madLibId").getAsString();


            ResultSet rs = statement.executeQuery(String.format("select * from madlib where id = '%s'", id));
            rs.next();
            String story =  rs.getString(2);

            story = fillInMadLib(story);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            return ApiGatewayResponse.builder()
                    .setStatusCode(200)
                    .setObjectBody(story)
                    .setHeaders(headers)
                    .build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ApiGatewayResponse.builder()
                    .setStatusCode(500)
                    .setObjectBody("Something went wrong!")
                    .build();

        }
    }

    private String fillInMadLib(String story){

        while(story.contains("%noun%")){
            story = story.replace("%noun%", service.getRandomNoun(null));
        }

        while(story.contains("%verb%")){
            story = story.replace("%verb%", service.getRandomVerb(null));
        }

        while(story.contains("%adverb%")){
            story = story.replace("%adverb%", service.getRandomAdverb(null));
        }

        while(story.contains("%adjective%")){
            story = story.replace("%adjective%", service.getRandomAdjective(null));
        }

        while(story.contains("%preposition%")){
            story = story.replace("%preposition%", service.getRandomPreposition(null));
        }

        while(story.contains("%conjunction%")){
            story = story.replace("%conjunction%", service.getRandomConjunction(null));
        }

        while(story.contains("%article%")){
            story = story.replace("%article%", service.getRandomArticle(null));
        }

        while(story.contains("%pronoun%")){
            story = story.replace("%pronoun%", service.getRandomPronoun(null));
        }

        return story;

    }
}
