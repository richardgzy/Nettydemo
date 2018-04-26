import io.netty.channel.ChannelFuture;
import io.netty.channel.unix.DomainSocketAddress;

public class Main2 {

    public static void main(String[] args){
        final String sockPath = "/tmp/echo2.socket";
        start(sockPath);
    }

    static public void start(String sockPath){

        ShineClientDemo scd = new ShineClientDemo(sockPath);
        scd.run();

        try {

            //read message from .sock file
            final ChannelFuture clientFuture = scd.getBootstrapClient().connect(new DomainSocketAddress(sockPath)).sync();

            //close socket synchronization
            clientFuture.channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //shut down client
            scd.shutDown();
        }
    }
}
