package springbootnetty.demo.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * 初始化netty服务器
 */
public class ServerChannelInitializer extends ChannelInitializer {


    @Override
    protected void initChannel(Channel channel) throws Exception {
        //添加编解码
       // channel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
       // channel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        channel.pipeline().addLast(new NettyServerHandler());
    }
}
