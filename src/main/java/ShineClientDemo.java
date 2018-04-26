import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;

import java.util.Scanner;

public class ShineClientDemo {

    Bootstrap bootstrapClient;
    EventLoopGroup clientEventLoop;
    private String sockPath;

    public ShineClientDemo(String newSockPath){
        //init client
        bootstrapClient = new Bootstrap();
        clientEventLoop = new EpollEventLoopGroup();
        this.sockPath = newSockPath;
    }

    public void run(){

        bootstrapClient.group(clientEventLoop)
                .channel(EpollDomainSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                             @Override
                             protected void initChannel(final Channel channel) throws Exception {
                                 channel.pipeline().addLast(
                                         new ChannelInboundHandlerAdapter() {
                                             @Override
                                             public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                                 System.out.println("Client Side Channel Activated");

                                                 Scanner reader = new Scanner(System.in);  // Reading from System.in
                                                 System.out.println("Enter some message: ");
                                                 String userInput = reader.nextLine().trim();

                                                 if (userInput.equals("")){
                                                     userInput = "this is a sample user input";
                                                 }

                                                 final ByteBuf buff = ctx.alloc().buffer();
                                                 buff.writeBytes(userInput.getBytes("UTF-8"));

                                                 ctx.writeAndFlush(buff).addListeners(new ChannelFutureListener() {

                                                     public void operationComplete(ChannelFuture future) {
                                                         future.channel().close();
//                                                         future.channel().parent().close();
                                                     }
                                                 });
                                             }

                                             @Override
                                             public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
                                                 System.out.println("Error occur when reading from Unix domain socket: " + cause.getMessage());
                                                 ctx.close();
                                             }
                                         }
                                 );
                             }
                         }
                );
    }

    public void shutDown(){
        clientEventLoop.shutdownGracefully();
    }

    public Bootstrap getBootstrapClient() {
        return bootstrapClient;
    }

    public EventLoopGroup getClientEventLoop() {
        return clientEventLoop;
    }

    public String getSockPath() {
        return sockPath;
    }
}
