package test.io.github.wonyoungpark.ams.service.impl; 

import io.github.wonyoungpark.ams.AmsApplication;
import io.github.wonyoungpark.ams.domain.User;
import io.github.wonyoungpark.ams.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;

/** 
* UserServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>7월 15, 2017</pre> 
* @version 1.0 
*/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AmsApplication.class)
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    /**
    *
    * Method: getUserByUsername(String name)
    *
    */
    @Test
    public void testGetUserByUsername() throws Exception {
        String username = "admin";

        User user = userService.findByUsername(username);

        System.out.println(user);

        Assert.assertThat(user.getPassword(), is("1234"));
    }
}
