package com.boyan.pojo.vo;

import lombok.Data;

/**
 * 首页模块 - 按标题关键字分页查询
 */
@Data
public class PortalVo {

    // 搜索标题关键字
    private String keyWords;
    // 搜索的新闻类型
    private int type;
    // 页码数
    private int pageNum;
    // 页大小
    private int pageSize;

}
