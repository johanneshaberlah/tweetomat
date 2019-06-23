package com.github.johanneshaberlah.tweetomat.twitter.client;

public interface TwitterClient {

  TwitterClient connect();

  TwitterService twitterService();
}
