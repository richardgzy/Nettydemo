import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerDomainSocketChannel;
import io.netty.channel.unix.DomainSocketAddress;
import java.util.Scanner;

public class ShineDemo {
    public static void main(String[] args) throws Exception {
        //sock file path for communication
        String serverSockPath = "/tmp/echo2.socket";

        //server initiation
        final ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup serverBossEventLoopGroup = new EpollEventLoopGroup();
        EventLoopGroup serverWorkerEventLoopGroup = new EpollEventLoopGroup();

        bootstrap.group(serverBossEventLoopGroup, serverWorkerEventLoopGroup)
                .localAddress(new DomainSocketAddress(serverSockPath))
                .channel(EpollServerDomainSocketChannel.class)
                .childHandler(
                        new ChannelInitializer<Channel>() {
                            @Override
                            protected void initChannel(final Channel channel) {
                                channel.pipeline().addLast(
                                        new SimpleChannelInboundHandler() {
                                            @Override
                                            public void channelRegistered(final ChannelHandlerContext ctx) {
                                                System.out.println("Channel Registered and Waiting for message....");
                                            }

                                            @Override
                                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

                                                final ByteBuf buff = (ByteBuf) msg;
                                                try {
                                                    byte[] bytes = new byte[buff.readableBytes()];
                                                    buff.getBytes(0, bytes);
                                                    System.out.println("Message received: " + new String(bytes));
                                                } finally {
                                                    buff.release();
                                                }
                                                ctx.close();
                                            }

                                            @Override
                                            protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                                                final ByteBuf buff = (ByteBuf) msg;
                                                try {
                                                    byte[] bytes = new byte[buff.readableBytes()];
                                                    buff.getBytes(0, bytes);
                                                    System.out.println("Message received: " + new String(bytes));
                                                } finally {
                                                    buff.release();
                                                }
                                                ctx.close();
                                            }

                                            @Override
                                            public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
                                                System.out.println("Error occur when reading from Unix domain socket: " + cause.getMessage());
                                                ctx.close();
                                            }

                                            @Override
                                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                super.channelActive(ctx);
                                            }
                                        }
                                );
                            }
                        }
                );
        final ChannelFuture serverFuture = bootstrap.bind().sync();

        //close communication
        serverFuture.channel().closeFuture().sync();
        serverBossEventLoopGroup.shutdownGracefully();
        serverWorkerEventLoopGroup.shutdownGracefully();
    }

}
