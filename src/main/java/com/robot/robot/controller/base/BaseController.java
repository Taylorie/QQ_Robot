package com.robot.robot.controller.base;

import com.robot.entity.KeyWord;
import com.robot.entity.SensitiveWord;
import com.robot.service.IKeyWordService;
import com.robot.service.ISensitiveWordService;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.MessageGet;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @autcom zhang.yu
 * @date 2022-09-11 15:58:44
 * @description
 */

public class BaseController {
    @Autowired
    ISensitiveWordService sensitiveWordService;

    @Autowired
    IKeyWordService keyWordService;

    public void entrance(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) {
        boolean sensitive = sensitive(groupMsg, msgBuilder, setter, sender);
        if (sensitive) return;
        autoReply(groupMsg, msgBuilder, setter, sender);
    }

    /**
     * 敏感词校验 无法保证
     *
     * @param groupMsg
     * @param msgBuilder
     * @param setter
     * @param sender
     * @return
     */
    public boolean sensitive(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) {
        SensitiveWord sensitiveWord = sensitiveWordService.checkSensiWord(groupMsg.getText());
        if (sensitiveWord != null) {
            MessageGet.MessageFlag<? extends GroupMsg.FlagContent> flag = groupMsg.getFlag();
            flag.getFlag();
            setter.setMsgRecall(flag);
            MessageContent msg = msgBuilder
                    // at当事人
                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text("您已经触发违禁词撤回 - ").text(sensitiveWord.getReplyWord())
                    .build();
            sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
            return true;
        }
        return false;
    }

    public void autoReply(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) {
        KeyWord keyWord = keyWordService.autoReplyLike(groupMsg.getText());
        if (keyWord != null) {
            MessageContent msg = msgBuilder
                    // at当事人
                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text(keyWord.getReplyWord())
                    .build();
            sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
        }
    }
}
