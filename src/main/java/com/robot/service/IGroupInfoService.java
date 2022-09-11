package com.robot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.robot.entity.GroupInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yu
 * @since 2022-09-11
 */
public interface IGroupInfoService extends IService<GroupInfo> {
    /**
     * 获取当前群对应的控制器类
     *
     * @param groupId
     * @return
     */
    GroupInfo getGroupById(String groupId);
}
