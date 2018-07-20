package com.nowcoder.wenda.async;

import java.util.List;

/**
 * @author silent 1183611386@qq.com
 * @date 2018/7/20 14:38
 */
public interface EventHandler {
    void doHandler(EventModel model);
    List<EventType> getSupportTypes();
}
