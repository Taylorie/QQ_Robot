package com.robot.robot.controller;

import com.robot.robot.controller.base.BaseController;
import com.robot.service.IKeyWordService;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @author zhang.yu
 * @date 2022-09-11 15:51:38
 * @description
 */
@Component(value = "robottest")
public class RobotTest extends BaseController {
    @Autowired
    IKeyWordService keyWordService;

    @Override
    public void entrance(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) {
        boolean sensitive = sensitive(groupMsg, msgBuilder, setter, sender);
        if (sensitive) return;
        autoReply(groupMsg, msgBuilder, setter, sender);
    }

    @Override
    public void autoReply(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) {
        if (groupMsg.getText().length()>13&&"^addAutoReply".equals(groupMsg.getText().substring(0, 13))) {
            addAutoReply(groupMsg, msgBuilder, setter, sender);
        } else {
            super.autoReply(groupMsg, msgBuilder, setter, sender);
        }
    }

    public void addAutoReply(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) {
        String reply = keyWordService.addAutoReplay(groupMsg.getText());
        MessageContent msg = msgBuilder
                // at当事人
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text(reply)
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
    }
}
