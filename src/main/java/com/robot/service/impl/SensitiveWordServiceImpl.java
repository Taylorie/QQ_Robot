package com.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robot.entity.SensitiveWord;
import com.robot.mapper.SensitiveWordMapper;
import com.robot.service.ISensitiveWordService;
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
public class SensitiveWordServiceImpl extends ServiceImpl<SensitiveWordMapper, SensitiveWord> implements ISensitiveWordService {

    @Override
    public SensitiveWord checkSensiWord(String sensiWord) {
        LambdaQueryWrapper<SensitiveWord> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(SensitiveWord::getKeyWord, sensiWord);
        SensitiveWord one = getOne(wrapper);
        return one;
    }
}
