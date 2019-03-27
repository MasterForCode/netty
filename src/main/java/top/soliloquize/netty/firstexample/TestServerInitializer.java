package top.soliloquize.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author wb
 * @date 2019/3/27
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    /**
     * 连接创建时，执行该方法
     *
     * @param ch socketChannel
     * @throws Exception Exception
     */
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("testHttpServerHandler", new TestHttpServerHandler());
    }
}
