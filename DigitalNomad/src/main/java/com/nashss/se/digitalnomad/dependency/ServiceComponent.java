package com.nashss.se.digitalnomad.dependency;

import com.nashss.se.digitalnomad.activity.GetCategoriesActivity;

import com.nashss.se.digitalnomad.activity.GetDestinationsActivity;

import com.nashss.se.digitalnomad.activity.GetFavoritesActivity;
import com.nashss.se.digitalnomad.activity.SaveFavoritesActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DynamoDBModule.class})
public interface ServiceComponent {
    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */
    GetCategoriesActivity provideGetCategoriesActivity();

    /**
     * Provides the relevant activity.
     * @return GetPlaylistSongsActivity
     */
    GetDestinationsActivity provideGetDestinationsActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */

    SaveFavoritesActivity provideSaveFavoritesActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */

    GetFavoritesActivity provideGetFavoritesActivity();
}
