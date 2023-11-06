package org.example.design.iterator;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 14:28
 **/
public interface ChannelCollection {
    void addChannel(Channel c);

    void removeChannel(Channel c);

    ChannelIterator iterator(ChannelTypeEnum type);

}
