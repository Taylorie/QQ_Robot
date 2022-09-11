package com.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robot.entity.GroupOrder;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yu
 * @since 2022-09-11
 */
public interface IGroupOrderService extends IService<GroupOrder> {
    /**
     * 判断是否为指令 , 转发执行指令
     *
     * @param groupMsg
     * @param msgBuilder
     * @param setter
     * @param sender
     */
    void orderIniti(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) throws Exception;

    /**
     * 指令失败
     *
     * @param groupMsg
     * @param msgBuilder
     * @param setter
     * @param sender
     */
    void failOrder(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender,String failReason);
}
