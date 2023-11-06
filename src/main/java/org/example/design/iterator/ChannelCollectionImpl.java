package org.example.design.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/4/11 0011 14:55
 **/
public class ChannelCollectionImpl implements ChannelCollection {
    private List<Channel> channels;

    public ChannelCollectionImpl() {
        channels = new ArrayList<>();
    }

    @Override
    public void addChannel(Channel c) {
        this.channels.add(c);
    }

    @Override
    public void removeChannel(Channel c) {
        this.channels.remove(c);
    }

    @Override
    public ChannelIterator iterator(ChannelTypeEnum type) {
        return new ChannelIteratorImpl(type, channels);
    }

    private class ChannelIteratorImpl implements ChannelIterator {
        private ChannelTypeEnum type;
        private List<Channel> channels;
        private int position;

        public ChannelIteratorImpl(ChannelTypeEnum type, List<Channel> channels) {
            this.type = type;
            this.channels = channels;
        }

        @Override
        public boolean hasNext() {
            while (position < channels.size()) {
                Channel c = channels.get(position);
                if (c.getTYPE().equals(type) || type.equals(ChannelTypeEnum.ALL))
                    return true;
                else
                    position++;
            }
            return false;
        }

        @Override
        public Channel next() {
            Channel c = channels.get(position);
            position++;
            return c;
        }
    }
}
