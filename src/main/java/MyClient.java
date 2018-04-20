import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollDomainSocketChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.unix.DomainSocketAddress;
import java.util.Scanner;

public class MyClient {

    public static void main(String[] args) {
        //sock file path for communication between server and client
        String sockPath = "/tmp/echo.socket";

        //client initiation
        Bootstrap bootstrapClient = new Bootstrap();
        EventLoopGroup clientEventLoop = new EpollEventLoopGroup();

        bootstrapClient.group(clientEventLoop)
                .channel(EpollDomainSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                             @Override
                             protected void initChannel(final Channel channel) throws Exception {
                                 channel.pipeline().addLast(
                                         new ChannelInboundHandlerAdapter() {

                                             @Override
                                             public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
                                                 System.out.println("Client Side Channel Registered");
                                             }

                                             @Override
                                             public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
                                                 final ByteBuf buff = (ByteBuf) msg;
                                                 try {

                                                     byte[] bytes = new byte[buff.readableBytes()];
                                                     buff.getBytes(0, bytes);
                                                     System.out.println(new String(bytes) + ", changed to UPPER CASE");
                                                 } finally {
                                                     buff.release();
                                                 }
                                                 ctx.close();
                                             }

                                             @Override
                                             public void channelActive(final ChannelHandlerContext ctx) throws Exception {

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
                                                         //future.channel().parent().close();
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

        try {
            //read message from .sock file
            final ChannelFuture clientFuture = bootstrapClient.connect(new DomainSocketAddress(sockPath)).sync();

            //close socket synchronization
            clientFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientEventLoop.shutdownGracefully();
        }
    }
}
