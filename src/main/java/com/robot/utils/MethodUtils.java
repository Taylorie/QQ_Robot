package com.robot.utils;

import love.forte.simbot.api.message.MessageContentBuilder;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import love.forte.simbot.api.sender.Setter;

import java.lang.reflect.Method;

/**
 * @author zhang.yu
 * @date 2022-09-11 21:27:39
 * @description
 */
public class MethodUtils {
    public static Object reflect(GroupMsg groupMsg, MessageContentBuilder msgBuilder, Setter setter, Sender sender, String classPath, String methodName) throws Exception {
        Class<?> aClass = Class.forName(classPath);
        Object bean = SpringContextUtils.getBean(aClass.getSimpleName());
        Class<?> cls = bean.getClass();
        Method entrance = cls.getMethod(methodName, GroupMsg.class, MessageContentBuilder.class, Setter.class, Sender.class);
        Object invoke = entrance.invoke(bean, groupMsg, msgBuilder, setter, sender);
        return invoke;
    }

    public static Object reflectOrder(String[] msg, String classPath, String methodName) throws Exception {
        Class<?> aClass = Class.forName(classPath);
        Object bean = SpringContextUtils.getBean(aClass.getSimpleName());
        Class<?> cls = bean.getClass();
        Method entrance = cls.getMethod(methodName, Object.class);
        Object invoke = entrance.invoke(bean, (Object) msg);
        return invoke;
    }
}
