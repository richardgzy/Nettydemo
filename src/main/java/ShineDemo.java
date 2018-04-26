import io.netty.channel.*;

public class ShineDemo {
    public static void main(String[] args){
        final String sockPath = "/tmp/echo2.socket";
        start(sockPath);
    }

    static public void start(String sockPath){
        ShineServerDemo ssd = new ShineServerDemo(sockPath);
//        ShineClientDemo scd = new ShineClientDemo(sockPath);

        ssd.run();
//        scd.run();

        try {

            final ChannelFuture serverFuture = ssd.getBootstrap().bind().sync();
            //read message from .sock file
//            final ChannelFuture clientFuture = scd.getBootstrapClient().connect(new DomainSocketAddress(sockPath)).sync();

            //close socket synchronization
//            clientFuture.channel().closeFuture().sync();
            serverFuture.channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //shut down server and client
            ssd.shutDown();
//            scd.shutDown();
        }
    }
}
