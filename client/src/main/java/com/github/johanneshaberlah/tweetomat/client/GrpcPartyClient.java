package com.github.johanneshaberlah.tweetomat.client;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannel;
import io.grpc.internal.DnsNameResolverProvider;
import io.grpc.netty.NettyChannelBuilder;

import java.net.InetSocketAddress;

public class GrpcPartyClient implements PartyClient {
  private final InetSocketAddress inetSocketAddress;

  private ManagedChannel managedChannel;
  private GrpcPartyService partyService;

  private GrpcPartyClient(InetSocketAddress inetSocketAddress) {
    this.inetSocketAddress = inetSocketAddress;
  }

  @Override
  public GrpcPartyClient connect() {
    this.managedChannel =
        NettyChannelBuilder.forAddress(
                this.inetSocketAddress.getHostName(), this.inetSocketAddress.getPort())
            .usePlaintext()
            .keepAliveWithoutCalls(true)
            .nameResolverFactory(DnsNameResolverProvider.asFactory())
            .build();
    this.partyService = GrpcPartyService.of(this.managedChannel);
    return this;
  }

  public InetSocketAddress inetSocketAddress() {
    return inetSocketAddress;
  }

  public ManagedChannel managedChannel() {
    return managedChannel;
  }

  public GrpcPartyService partyService() {
    return partyService;
  }

  public static GrpcPartyClient of(InetSocketAddress inetSocketAddress) {
    Preconditions.checkNotNull(inetSocketAddress);
    return new GrpcPartyClient(inetSocketAddress);
  }
}
