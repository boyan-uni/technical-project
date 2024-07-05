package com.boyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boyan.pojo.Headline;
import com.boyan.service.HeadlineService;
import com.boyan.mapper.HeadlineMapper;
import org.springframework.stereotype.Service;

/**
* @author boyan
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-07-03 15:39:24
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

}




