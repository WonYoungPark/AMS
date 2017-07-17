package io.github.wonyoungpark.ams;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)

// 스프링부트로 컨텍스트 로드
@SpringBootTest(classes = AmsApplication.class)
@WebAppConfiguration
public class AmsApplicationTests {

	@Test
	public void contextLoads() { // 컨텍스트 로드 테스트
	}

}
