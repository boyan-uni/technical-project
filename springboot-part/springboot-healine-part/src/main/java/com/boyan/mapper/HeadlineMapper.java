package com.boyan.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boyan.pojo.Headline;
import com.boyan.pojo.dto.HeadlineDTO;
import com.boyan.pojo.dto.HeadlineDetailDTO;
import com.boyan.pojo.vo.PortalVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

/**
* @author boyan
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-07-03 15:39:24
* @Entity com.boyan.pojo.Headline
*/

public interface HeadlineMapper extends BaseMapper<Headline> {

    // 自定义分页查询方法
    IPage<HeadlineDTO> selectMyPage(Page<HeadlineDTO> page, @Param("portalVo") PortalVo portalVo);

    // 头条详情页属性多表查询
    HeadlineDetailDTO selectHeadlineDetail(Integer hid);
}

