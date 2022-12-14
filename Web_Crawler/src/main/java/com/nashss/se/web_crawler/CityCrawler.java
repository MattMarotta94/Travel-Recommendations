package com.nashss.se.web_crawler;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.aws.dynamodb.DynamoDbClientProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CityCrawler {

    public static void main(String[] args) throws IOException {
        CreateJsonObject jsonObj =  new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        String cityName;
        String countryName;

        List<DestinationModel> cityList = new ArrayList<>();
        String serializedDestinations = "";

        Document doc = Jsoup
                .connect("https://www.holidify.com/pages/best-urban-city-experience-1534.html")
                .get();

        Elements cityNameList = doc.getElementsByTag("h2");

        for(Element element : cityNameList) {
            if(element.html().contains(". ")) {
                int startIndex = element.html().indexOf(". ");
                int cityEndIndex = element.html().indexOf(" (");
                int countryEndIndex = element.html().indexOf(")");

                cityName = element.html().substring(startIndex+1, cityEndIndex);
                countryName = element.html().substring(cityEndIndex+2, countryEndIndex);
                String locationName = null;

                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                DestinationModel cityDestination = new DestinationModel(cityName, countryName, null, "city", uuidAsString);
                cityList.add(cityDestination);
            }
        }

        serializedDestinations = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cityList);
        jsonObj.writeJsonToFile("CityDestinations", serializedDestinations);

        List<DestinationModel> deserializedDestObjects = objectMapper.readValue(serializedDestinations, new TypeReference<List<DestinationModel>>(){});

//        for(DestinationModel dest : deserializedDestObjects) {
//        jsonObj.writeJsonToFile("cityDestinations", serializedDestinations);

        //List<DestinationModel> deserializedDestObjects = objectMapper.readValue(serializedDestinations, new TypeReference<List<DestinationModel>>(){});

        for(DestinationModel desty : deserializedDestObjects) {
            DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
            mapper.save(desty);
        }
    }

}
