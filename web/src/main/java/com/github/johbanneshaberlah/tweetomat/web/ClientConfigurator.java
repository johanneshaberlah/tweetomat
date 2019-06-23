package com.github.johbanneshaberlah.tweetomat.web;

import com.github.johanneshaberlah.tweetomat.client.PartyClient;
import com.github.johanneshaberlah.tweetomat.client.GrpcPartyClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class ClientConfigurator {

  @Value("${grpc.service.host}")
  private String host;

  @Value("${grpc.service.port}")
  private int port;

  @Bean
  PartyClient client() {
    return GrpcPartyClient.of(InetSocketAddress.createUnresolved(this.host, this.port));
  }
}
