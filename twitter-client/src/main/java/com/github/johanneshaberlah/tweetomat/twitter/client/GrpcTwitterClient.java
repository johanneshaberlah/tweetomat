package com.github.johanneshaberlah.tweetomat.twitter.client;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannel;
import io.grpc.internal.DnsNameResolverProvider;
import io.grpc.netty.NettyChannelBuilder;

import java.net.InetSocketAddress;

public class GrpcTwitterClient implements TwitterClient {
  private final InetSocketAddress inetSocketAddress;

  private ManagedChannel managedChannel;
  private TwitterService twitterService;

  private GrpcTwitterClient(InetSocketAddress inetSocketAddress) {
    this.inetSocketAddress = inetSocketAddress;
  }

  @Override
  public TwitterClient connect() {
    this.managedChannel =
        NettyChannelBuilder.forAddress(
                this.inetSocketAddress.getHostName(), this.inetSocketAddress.getPort())
            .usePlaintext()
            .keepAliveWithoutCalls(true)
            .nameResolverFactory(DnsNameResolverProvider.asFactory())
            .build();
    this.twitterService = GrpcTwitterService.of(this.managedChannel);
    return this;
  }

  @Override
  public TwitterService twitterService() {
    return this.twitterService;
  }

  public InetSocketAddress inetSocketAddress() {
    return inetSocketAddress;
  }

  public ManagedChannel managedChannel() {
    return managedChannel;
  }
}
