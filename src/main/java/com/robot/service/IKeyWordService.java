package com.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robot.entity.KeyWord;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yu
 * @since 2022-09-11
 */
public interface IKeyWordService extends IService<KeyWord> {
    /**
     * 关键词回复
     *
     * @param keyWord
     * @return
     */
    KeyWord autoReplyLike(String keyWord);

    KeyWord autoReplyEq(String keyWord);

    /**
     * 自定义回复
     *
     * @param msg
     * @return
     */
    String addAutoReply(Object msg);
}
