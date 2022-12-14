package com.nashss.se.digitalnomad.activity;

import com.nashss.se.digitalnomad.activity.results.GetCategoriesResult;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetPlaylistActivity for the MusicPlaylistService's GetPlaylist API.
 *
 * This API allows the customer to get one of their saved playlists.
 */
public class GetCategoriesActivity {
    private final Logger log = LogManager.getLogger();
    private final CategoryDao categoryDao;

    /**
     * Instantiates a new GetPlaylistActivity object.
     *
     * @param categoryDao PlaylistDao to access the playlist table.
     */
    @Inject
    public GetCategoriesActivity(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @return GetCategoriesResult result object
     */
    public GetCategoriesResult handleRequest() {
        log.info("Inside GetCategoriesResult handleRequest");
        List<Category> categories = categoryDao.getCategories();
        List<String> categoriesList = new ArrayList<>();

        for (Category category : categories) {
            categoriesList.add(category.getCategory());
        }
        return GetCategoriesResult.builder()
                .withCategory(categoriesList)
                .build();
    }
}
