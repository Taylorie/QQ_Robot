package com.robot.listener;


import com.robot.entity.GroupInfo;
import com.robot.robot.controller.base.BaseController;
import com.robot.service.IGroupInfoService;
import com.robot.utils.SpringContextUtils;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.MessageContentBuilderFactory;
import love.forte.simbot.api.message.containers.AccountInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    public void reply(GroupMsg groupMsg, Sender sender, Setter setter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {

        //消息内容
        String text = groupMsg.getText();

        //获取发信人
        AccountInfo accountInfo = groupMsg.getAccountInfo();

        //发信人 QQ 号
        String qqCode = accountInfo.getAccountCode();

        //消息构造器
        MessageContentBuilder msgBuilder = messageContentBuilderFactory.getMessageContentBuilder();

        GroupInfo group = groupInfoService.getGroupById(groupMsg.getGroupInfo().getGroupCode());
        if (group == null) return;
        Class<?> aClass = Class.forName(group.getClassPath());
        // 获取bean,注意这里用实现类的接口强转去获得目标bean的代理对象，才能成功执行下面的反射方法
        Class<?> cls  = SpringContextUtils.getBean(aClass.getSimpleName().toLowerCase()).getClass();
// 获取方法，这个selectAll实际上是父类的方法
//        Method method = ReflectionUtils.findMethod(obj.getClass(), "entrance", GroupMsg.class, MessageContentBuilder.class, Setter.class, Sender.class);
//// 反射执行方法
//        Object objRe = ReflectionUtils.invokeMethod(method, groupMsg, msgBuilder, setter, sender);


        Method entrance = cls.getMethod("entrance", GroupMsg.class, MessageContentBuilder.class, Setter.class, Sender.class);
        entrance.invoke(SpringContextUtils.getBean(aClass.getSimpleName().toLowerCase()), groupMsg, msgBuilder, setter, sender);
//        MessageContent msg = null;
//        KeyWord keyWord = keyWordService.autoReply(text);
//        if (keyWord != null) {
//            msg = msgBuilder
//                    // at当事人
//                    .at(accountInfo)
//                    // tips 通过 \n 换行
//                    .text(keyWord.getReplyWord())
//                    .build();
//            GroupInfo groupInfo = groupMsg.getGroupInfo();
//            sender.sendGroupMsg(groupInfo, msg);
//        }
    }


}
