package com.newworld.king.bird.eventbus.event;

/**
 * Created by jldaii on 2017/7/7.
 */

//1 创建粘性事件类
public class StickyEvent {
   public String msg;

    public StickyEvent(String msg) {
        this.msg = msg;
    }
}
