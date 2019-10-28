package com.mhc.springboot.base;

import com.mhc.springboot.Application;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ：menghui.cao, menghui.cao@leyantech.com
 * @date ：2019-10-28 17:39
 * 测试的基类
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BaseTest {
}
