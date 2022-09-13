package com.robot.robot.controller.base;

import com.robot.entity.KeyWord;
import com.robot.service.IKeyWordService;
import com.robot.service.ISensitiveWordService;
import com.robot.utils.trie.GlobalTrie;
import com.robot.utils.trie.Trie;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.assists.Permissions;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.message.events.MessageGet;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    public void entrance(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) throws Exception {
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
        if (GlobalTrie.trie == null) {
            GlobalTrie.trie = new Trie();
            sensitiveWordService.generTrie();
        }
        boolean search = GlobalTrie.trie.search(groupMsg.getText());
        if (search) {
            Permissions permission = groupMsg.getAccountInfo().getPermission();

            String prompt = "您刚才的发言内容包含敏感词,因权限不足无法撤回,请您手动撤回,如果对敏感词有意义请联系群主";
            if (permission == Permissions.MEMBER) {
                MessageGet.MessageFlag<? extends GroupMsg.FlagContent> flag = groupMsg.getFlag();
                flag.getFlag();
                setter.setMsgRecall(flag);
                prompt = "您刚才的发言内容包含敏感词,这边已经为您撤回了,如果对敏感词有意义请联系管群主";
            }

            MessageContent msg = msgBuilder
                    // at当事人
                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text(prompt)
                    .build();
            sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
            return true;
        }
        return false;
    }

    public void autoReply(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) throws Exception {
        KeyWord keyWord = keyWordService.autoReplyEq(groupMsg.getText());
        if (keyWord != null) {
            MessageContent msg = msgBuilder
                    // at当事人
//                    .at(groupMsg.getAccountInfo().getAccountCode())
                    .text(keyWord.getReplyWord())
                    .build();
            sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
        }
    }
}
