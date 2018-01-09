package com.tf.service;

import com.baomidou.mybatisplus.service.IService;
import com.tf.commons.result.PageInfo;
import com.tf.model.SysLog;

/**
 *
 * SysLog 表数据服务层接口
 *
 */
public interface ISysLogService extends IService<SysLog> {

    void selectDataGrid(PageInfo pageInfo);

}