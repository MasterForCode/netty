package top.soliloquize.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author wb
 * @date 2019/3/27
 */
public class TestServer {
    public static void main(String[] args) throws Exception {
        // 接受连接
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        // 处理连接，也可以用boosGroup处理(不建议)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 启动服务端服务
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // boosGroup接受请求，并将请求转给workerGroup
            serverBootstrap.group(boosGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 自定义请求处理器(针对workerGroup)
                    .childHandler(new TestServerInitializer());
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
