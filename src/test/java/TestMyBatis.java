import com.demo.entity.User;
import com.demo.service.IUserService;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import org.junit.Test;

/**
 * Created by baolei on 2016/3/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-mybatis.xml"})
public class TestMyBatis {
    private Logger logger = LoggerFactory.getLogger(Test.class);

    @Resource
    private IUserService userService;

    @Test
    public void test(){
        User user = userService.selectByPrimaryKey(1L);
        logger.info("*************id=1 user="+user.getId()+","+user.getUsername()+","
            +user.getPassword());
    }
}
