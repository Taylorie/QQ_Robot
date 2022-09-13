package com.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robot.entity.SensitiveWord;
import com.robot.mapper.SensitiveWordMapper;
import com.robot.service.ISensitiveWordService;
import com.robot.utils.trie.GlobalTrie;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Boolean addSensi(List<String> str) {
        for (String s : str) {
            SensitiveWord sensitiveWord = new SensitiveWord();
            sensitiveWord.setKeyWord(s);
            sensitiveWord.setReplyWord("您已触发违禁词,已经为您撤回该内容,请注意发言,如对违禁词有异议请联系管理员");
            save(sensitiveWord);
        }
        return true;
    }

    @Override
    public Boolean generTrie() {
        List<SensitiveWord> list = list();
        for (SensitiveWord sensitiveWord : list) {
            GlobalTrie.trie.insert(sensitiveWord.getKeyWord());
        }
        return true;
    }
}
