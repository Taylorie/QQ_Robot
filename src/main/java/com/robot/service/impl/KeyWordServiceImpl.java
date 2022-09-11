package com.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robot.entity.KeyWord;
import com.robot.mapper.KeyWordMapper;
import com.robot.service.IKeyWordService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yu
 * @since 2022-09-11
 */
@Service
public class KeyWordServiceImpl extends ServiceImpl<KeyWordMapper, KeyWord> implements IKeyWordService {

    @Override
    public KeyWord autoReplyLike(String keyWordStr) {
        LambdaQueryWrapper<KeyWord> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(KeyWord::getKeyWord, keyWordStr);
        return getOne(wrapper);
    }

    @Override
    public KeyWord autoReplyEq(String keyWord) {
        LambdaQueryWrapper<KeyWord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KeyWord::getKeyWord, keyWord);
        return getOne(wrapper);
    }

    @Override
    public String addAutoReply(Object msg) {
        String[] split = (String[]) msg;
        if (split.length != 5) {
            return "哎呦!怎么回事格式好像不对耶! -正确格式 ^addAutoReply^[全匹配 A|模糊匹配 L]^[关键字]^[回复词]";
        }
        KeyWord keyWord = new KeyWord();
        keyWord.setKeyWord(split[3]);
        keyWord.setReplyWord(split[4]);
        boolean save = save(keyWord);
        //todo : 测试使用,上线后删除
        if (Objects.equals(split[2], "X")) {
            return "崽种!直视我 !这都能错 !!!";
        }
        if (save) {
            return "新增成功啦!来试试看吧!(美有姬语气)";
        } else {
            return "崽种!直视我 !这都能错 !!!";
        }
    }
}
