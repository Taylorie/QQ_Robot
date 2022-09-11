package com.robot.listener;


import com.robot.entity.GroupInfo;
import com.robot.service.IGroupInfoService;
import com.robot.utils.MethodUtils;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhang.yu
 * @date 2022-09-09 11:07:12
 * @description 群消息监听器 , 不负责具体逻辑负责转发
 */
@Service
public class GroupMsgListener {

    private static final Logger LOG = LoggerFactory.getLogger(GroupMsgListener.class);


    private final MessageContentBuilderFactory messageContentBuilderFactory;


    @Autowired
    public GroupMsgListener(MessageContentBuilderFactory messageContentBuilderFactory) {
        this.messageContentBuilderFactory = messageContentBuilderFactory;
    }

    @Autowired
    IGroupInfoService groupInfoService;

    @OnGroup
    public void reply(GroupMsg groupMsg, Sender sender, Setter setter) throws Exception {
        //消息构造器
        MessageContentBuilder msgBuilder = messageContentBuilderFactory.getMessageContentBuilder();

        GroupInfo group = groupInfoService.getGroupById(groupMsg.getGroupInfo().getGroupCode());
        if (group == null) return;
        MethodUtils.reflect(groupMsg, msgBuilder, setter, sender, group.getClassPath(), "entrance");
    }


}
