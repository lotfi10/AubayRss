package com.aubayrss.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lotfi Fetteni on 08/11/2017.
 */

public class NewsStore {
    private static List<Article> newsArticles = new ArrayList<>();

    public static List<Article> getNewsArticles() {
        return newsArticles;
    }

    public static void setNewsArticles(List<Article> newsArticles) {
        NewsStore.newsArticles = newsArticles;
    }
}
