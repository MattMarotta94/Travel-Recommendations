package com.nashss.se.digitalnomad.dynamoDbTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.digitalnomad.dynamoDb.FavoritesDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class FavoritesDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private FavoritesDao favoritesDao;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        favoritesDao = new FavoritesDao(dynamoDBMapper);
    }

    @Test
    void saveFavorites_validSaveFavoritesRequest_returnsSaveFavoritesRequest() {
        //GIVEN
        String userId = "testUser";
        List<String> favoriteDestinations = new ArrayList<>();
        String destination1 = "destination1";
        String destination2 = "destination2";
        favoriteDestinations.add(destination1);
        favoriteDestinations.add(destination2);

        Favorite favorite = new Favorite(userId, favoriteDestinations);


        // WHEN
       Favorite result = favoritesDao.saveFavorites(favorite);

        // THEN
        verify(dynamoDBMapper).save(favorite);
        assertEquals(favorite, result);
    }
}
