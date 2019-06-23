package com.github.johanneshaberlah.tweetomat.twitterservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;

@Configuration
public class TwitterConfigurator {

  @Value("oAuthConsumerKey")
  private String oAuthConsumerKey;

  @Value("oAuthConsumerSecret")
  private String oAuthConsumerSecret;

  @Value("oAuthAccessToken")
  private String oAuthAccessToken;

  @Value("oAuthAccessTokenSecret")
  private String oAuthAccessTokenSecret;

  @Bean
  TwitterConfiguration twitterConfiguration() {
    return TwitterConfiguration.create(
        this.oAuthConsumerKey,
        this.oAuthConsumerSecret,
        this.oAuthAccessToken,
        this.oAuthAccessTokenSecret);
  }

  @Bean
  Twitter twitter() {
    return TwitterConnection.of(this.twitterConfiguration()).establish();
  }
}
