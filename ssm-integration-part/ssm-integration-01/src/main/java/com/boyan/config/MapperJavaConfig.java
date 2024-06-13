package com.boyan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 持久层配置类：连接池、SqlSessionFactory、Mapper 代理对象
 * TODO. ⚠️问题：如果将 dataSource 和 mybatis 的组件配置在一起，触发 @Value 不生效的问题
 *         原因：MyBatis的组件优先加载，@Value 还没有获取
 *         解决：分开配置，写到不同的类中即可！-》拆开 -》全部缓存完毕后再相互引用！
 */
@Configuration
public class MapperJavaConfig {

    /**
     * 配置SqlSessionFactory - 加入 IoC 容器
     * MyBatis -> SqlSessionFactoryBean[IoC] -> getObject() -> sqlSessionFactory
     *
     * 方式1 - 外部指定 mybatis-config.xml 配置文件【MyBatis的配置，除了连接池、Mapper指定⬇️】
     * 方式2 - 不保留外部配置文件，全部 MyBatis 的属性都在代码中设置⬇️
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 方式1
        // 指定配置文件等信息、指定数据库连接池对象、指定 mybatis-config.xml 文件位置
        // Resource resource = new ClassPathResource("mybatis-config.xml");
        // qlSessionFactoryBean.setConfigLocation(resource);

        // 方式2
        // 把 方式1 中保留的 mybatis-config.xml 配置文件中的配置信息，全部设置到 SqlSessionFactoryBean 中
        /**
         *     <settings>
         *         <!-- 开启resultMap自动映射 -->
         *         <setting name="autoMappingBehavior" value="FULL"/>
         *         <!-- 开启驼峰式映射-->
         *         <setting name="mapUnderscoreToCamelCase" value="true"/>
         *         <!-- 开启logback日志输出-->
         *         <setting name="logImpl" value="STDOUT_LOGGING"/>
         *     </settings>
         *
         *     <typeAliases>
         *         <!-- 给实体类起别名 : 该package下所有实体类自动起小写别名，可以直接用 eg.User.java -> user-->
         *         <package name="com.boyan.pojo"/>
         *     </typeAliases>
         *
         *     <plugins>
         *         <plugin interceptor="com.github.pagehelper.PageInterceptor">
         *             <!--
         *                 helperDialect：分页插件会自动检测当前的数据库链接，自动选择合适的分页方式。
         *                 你可以配置helperDialect属性来指定分页插件使用哪种方言。配置时，可以使用下面的缩写值：
         *                 oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
         *                 （完整内容看 PageAutoDialect） 特别注意：使用 SqlServer2012 数据库时，
         *                 https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md#%E5%A6%82%E4%BD%95%E9%85%8D%E7%BD%AE%E6%95%B0%E6%8D%AE%E5%BA%93%E6%96%B9%E8%A8%80
         *              -->
         *             <property name="helperDialect" value="mysql"/>
         *         </plugin>
         *     </plugins>
         */
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(Slf4jImpl.class);
        configuration.setAutoMappingBehavior(org.apache.ibatis.session.AutoMappingBehavior.FULL);

        // 存储 setting 的配置文件
        sqlSessionFactoryBean.setConfiguration(configuration);
        // 别名设置
        sqlSessionFactoryBean.setTypeAliasesPackage("com.boyan.pojo");
        // 插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.addPlugins(pageInterceptor);

        return sqlSessionFactoryBean;
    }

    /**
     * Mapper 代理对象加入到 IoC 容器中，在这里指定 mapper 路径
     * TODO.⚠️：mapper路径
     *           - main/java/com.boyan.mapper
     *           - resources/com.boyan.mapper
     *          两者需要完全一致！！！不然无法识别！！！
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper 代理对象兑现的 factoryBean -> 指定一个包 -> mapper 接口 -> sqlSessionFactory -> sqlSession -> mapper 代理对象 -> IoC
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.boyan.mapper");
        return mapperScannerConfigurer;
    }

}
