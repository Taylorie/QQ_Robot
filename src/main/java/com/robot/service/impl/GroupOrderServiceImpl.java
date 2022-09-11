package com.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robot.entity.GroupOrder;
import com.robot.mapper.GroupOrderMapper;
import com.robot.service.IGroupOrderService;
import com.robot.utils.MethodUtils;
import love.forte.simbot.api.message.MessageContent;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yu
 * @since 2022-09-11
 */
@Service
public class GroupOrderServiceImpl extends ServiceImpl<GroupOrderMapper, GroupOrder> implements IGroupOrderService {

    @Override
    public void orderIniti(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender) throws Exception {
        String text = groupMsg.getText();
        String[] split = text.split("\\^");
        if (text.length() < 2) {
            failOrder(groupMsg, msgBuilder, setter, sender, "指令不得为空");
            return;
        }
        LambdaQueryWrapper<GroupOrder> wrapper = new LambdaQueryWrapper<GroupOrder>();
        wrapper.eq(GroupOrder::getOrderName, split[1]);
        GroupOrder order = getOne(wrapper);
        if (order == null) {
            failOrder(groupMsg, msgBuilder, setter, sender, "指令不存在请检查后重试");
            return;
        }

        Object reflect = MethodUtils.reflectOrder(split, order.getClassPath(), order.getOrderName());

        MessageContent msg = msgBuilder
                // at当事人
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text(reflect.toString())
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
    }


    @Override
    public void failOrder(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender, String failReason) {
        MessageContent msg = msgBuilder
                // at当事人
                .at(groupMsg.getAccountInfo().getAccountCode())
                .text(failReason)
                .build();
        sender.sendGroupMsg(groupMsg.getGroupInfo(), msg);
    }
}
