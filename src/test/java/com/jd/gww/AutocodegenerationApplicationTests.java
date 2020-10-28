package com.jd.gww;


import com.jd.gww.model.Users;
import com.jd.gww.service.IUsersService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class AutocodegenerationApplicationTests {
    @Autowired
    IUsersService iUsersService;

    @Test

    void contextLoads() {
        List<Users> users = iUsersService.selectByAll();
        users.forEach(user->{
            System.out.println(user.toString());
        });
    }

}
