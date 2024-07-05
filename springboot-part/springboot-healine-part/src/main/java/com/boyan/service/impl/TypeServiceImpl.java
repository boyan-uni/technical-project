package com.boyan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boyan.pojo.Type;
import com.boyan.service.TypeService;
import com.boyan.mapper.TypeMapper;
import org.springframework.stereotype.Service;

/**
* @author boyan
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-07-03 15:39:24
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




