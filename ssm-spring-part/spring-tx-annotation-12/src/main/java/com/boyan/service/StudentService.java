package com.boyan.service;

import com.boyan.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * 添加事务：@Transactional
     * 位置：方法 / 类 上
     *  - 方法：当前方法有事务
     *  -  类 ：当前类下的所有方法都有事务
     *  ⚠️ 如果类和方法上都设置了 @Transactional 注解，方法上的会完全覆盖类上的 @Transactional 注解
     *
     *  1. 只读模式
     *     @Transactional(readOnly = true), 默认 false
     *     只读模式可以提升查询事务的效率！推荐事务中只有查询代码，使用只读模式！
     *     适用：平时一般在类上加 @Transactional，在查询只读的方法上加 @Transactional(readOnly = true)覆盖设定，提高效率；
     *
     *  2. 超时时间
     *     @Transactional(timeout = 3), 默认 -1 (永远不超时), 单位：秒
     *     超时回滚，释放资源，释放异常
     *
     *  3. 指定异常回滚 > 指定异常不回滚
     *     默认情况下，指定发生运行时异常，事务才会回滚：
     *      - rollbackFor = 指定哪些异常才会回滚,默认是 RuntimeException and Error 异常方可回滚!
     *                      eg. @Transactional(rollbackFor = Exception.class) 指定 Exception 异常来控制所有异常都回滚!
     *      - noRollbackFor = 回滚异常范围内，指定哪些异常不会回滚。默认没有指定，如果指定,应该在rollbackFor的范围内!
     *        eg. @Transactional(rollbackFor = Exception.class,noRollbackFor = FileNotFoundException.class)
     *        ⚠️其中 Exception.class 范围 应大于 FileNotFoundException.class！
     *
     *  4. 隔离级别
     *      @Transactional(isolation = Isolation.READ_COMMITTED)
     *      isolation = 设置事务的隔离级别, mysql默认是repeatable read
     *      1 - READ_UNCOMMITED, 读未提交（容易脏读，一般不推荐）
     *      2 - READ_COMMITED, 读已提交（*最推荐设置第二个级别）
     *      3 - REPEATABLE_READ, 可重复读（mysql默认的隔离级别）
     *      4 - SERIALIZABLE, 可串行化（最高的隔离级别，完全禁止并发）
     *      ⚠️隔离级别越高，隔离性越强，性能越弱!
     *
     *  5. 传播特性
     *      @Transactional(propagation = Propagation.REQUIRES_NEW)
     *      在被调用的子方法中设置传播行为，代表如何处理调用的事务！ 是加入，还是新事务等！
     *      - REQUIRED, 默认值：如果当前没有事务，就创建一个事务，如果有事务，就加入到当前事务中！
     *      - REQUIRES_NEW, 总是创建一个新的事务，如果当前有事务，就挂起当前事务，创建新的事务，然后执行方法，最后恢复挂起的事务！
     *      ⚠️推荐使用默认值，因为既然调用了，最终应该是同一个事务。
     */

    /**
     * 事务：正常执行方法
     * 预期执行结果: 正常更新数据
     */
    @Transactional
    public void changeInfo1(){
        studentDao.updateAgeById(100,1);
        System.out.println("-----------");
        studentDao.updateNameById("test1",1); // 正常执行版本
    }

    /**
     * 事务：执行失败回滚方法
     * 预期执行结果：执行失败完全回滚，step1执行后回滚，最后结果不变
     */
    @Transactional
    public void changeInfo2() {
        studentDao.updateAgeById(88,1);				  // step1
        int i = 1/0;                                              // 一定报错的语句
        System.out.println("-----------");
        studentDao.updateNameById("test2",1);		   	  // step2
    }

    /**
     * 事务：超时回滚
     * 预期执行结果：执行失败完全回滚，step1执行后回滚，最后结果不变
     */
    @Transactional(timeout = 3)
    public void changeInfo3() {
        studentDao.updateAgeById(88,1);				  // step1
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------");
        studentDao.updateNameById("test3",1);		   	  // step2
    }

    /**
     * 事务：事务异常指定问题 + 隔离级别2st
     * 预期执行结果：step1完成执行，step2没有完成执行，事务不会回滚，因为这个异常不在rollbackFor的默认范围内！
     */
    @Transactional(rollbackFor = Exception.class,noRollbackFor = FileNotFoundException.class, isolation= Isolation.READ_COMMITTED)
    public void changeInfo4() throws FileNotFoundException {
        studentDao.updateAgeById(88,1);                         // step1
        //主动抛出一个检查异常,测试! 发现不会回滚,因为不在rollbackFor的默认范围内!
        new FileInputStream("xxxx");
        studentDao.updateNameById("test4",1);                  // step2
    }

    /**
     * 声明两个独立修改数据库的事务业务方法
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeAge(){
        studentDao.updateAgeById(99,1);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void changeName(){
        studentDao.updateNameById("test5",1);
        int i = 1/0;
    }
}
