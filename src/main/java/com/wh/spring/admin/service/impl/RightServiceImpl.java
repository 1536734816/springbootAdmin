package com.wh.spring.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wh.spring.admin.entity.Right;
import com.wh.spring.admin.mapper.RightMapper;
import com.wh.spring.admin.service.IRightService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author Mr.W
 * @since 2020-08-06
 */
@Service
public class RightServiceImpl extends ServiceImpl<RightMapper, Right> implements IRightService {

}
