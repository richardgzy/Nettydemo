import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerDomainSocketChannel;
import io.netty.channel.unix.DomainSocketAddress;


public class ShineServerDemo {
    private ServerBootstrap bootstrap;
    private EventLoopGroup serverBossEventLoopGroup;
    private EventLoopGroup serverWorkerEventLoopGroup;
    //sock file path for communication
    private String sockPath;

    public ShineServerDemo(String newSockPath){
        //server initiation

        //Why two LoopGroups?
        bootstrap = new ServerBootstrap();
        serverBossEventLoopGroup = new EpollEventLoopGroup();
        serverWorkerEventLoopGroup = new EpollEventLoopGroup();
        this.sockPath = newSockPath;
    }

    public void run() {

        bootstrap.group(serverBossEventLoopGroup, serverWorkerEventLoopGroup)
                .localAddress(new DomainSocketAddress(sockPath))
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
                                                System.out.println("Server Side Channel Activated");
                                            }
                                        }
                                );
                            }
                        }
                );
    }

    public void shutDown(){
        serverBossEventLoopGroup.shutdownGracefully();
        serverWorkerEventLoopGroup.shutdownGracefully();
    }

    public ServerBootstrap getBootstrap() {
        return bootstrap;
    }

    public EventLoopGroup getServerBossEventLoopGroup() {
        return serverBossEventLoopGroup;
    }

    public EventLoopGroup getServerWorkerEventLoopGroup() {
        return serverWorkerEventLoopGroup;
    }

    public String getSockPath() {
        return sockPath;
    }
}
