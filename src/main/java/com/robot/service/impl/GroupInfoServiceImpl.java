package com.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.robot.entity.GroupInfo;
import com.robot.mapper.GroupInfoMapper;
import com.robot.service.IGroupInfoService;
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
public class GroupInfoServiceImpl extends ServiceImpl<GroupInfoMapper, GroupInfo> implements IGroupInfoService {

    @Override
    public GroupInfo getGroupById(String groupId) {
        LambdaQueryWrapper<GroupInfo> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GroupInfo::getGroupCode, groupId);
        return getOne(wrapper);
    }
}
