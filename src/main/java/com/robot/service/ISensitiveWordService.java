package com.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robot.entity.SensitiveWord;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yu
 * @since 2022-09-11
 */
public interface ISensitiveWordService extends IService<SensitiveWord> {
    /**
     * 敏感词检验
     *
     * @param sensiWord
     * @return
     */
    SensitiveWord checkSensiWord(String sensiWord);
}
