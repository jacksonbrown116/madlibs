package com.tc.yokoten.lambda.madlibs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tc.yokoten.lambda.madlibs.response.ApiGatewayResponse;

public class MadLibAdder implements RequestHandler<Map<String,Object>, ApiGatewayResponse> {

    private static final String jdbcDatabaseUrl = "jdbc:postgresql://tcmadlibs.chpcwfrdxrr1.us-east-2.rds.amazonaws.com:5432/YokotenMadLibs";
    private static final String user = "YokotenUser";
    private static final String password = "YokotenTime";
    private static final Gson gson = new Gson();


    //insert into madlib (id,story) values ('1234321','Some good story');

    public ApiGatewayResponse handleRequest(Map<String, Object> input, Context context) {

        Connection dbConnection = null;
        Statement statement = null;
        try {
            dbConnection = DriverManager.getConnection(jdbcDatabaseUrl, user, password);
            statement = dbConnection.createStatement();


            JsonObject inputJsonObject = gson.fromJson(gson.toJson(input), JsonObject.class);
            JsonObject bodyObject = gson.fromJson(inputJsonObject.get("body").getAsString(), JsonObject.class);

            String id = bodyObject.get("id").getAsString();
            String story = bodyObject.get("story").getAsString();


            statement.execute(String.format("insert into madlib (id,story) values ('%s','%s')", id, story.replaceAll("'", "")));

            String responseBody = "responseBody";
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            return ApiGatewayResponse.builder()
                    .setStatusCode(201)
                    .setObjectBody("Created!")
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


}
