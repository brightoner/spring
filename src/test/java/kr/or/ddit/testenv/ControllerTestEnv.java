package kr.or.ddit.testenv;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/application-context.xml",
						"classpath:kr/or/ddit/config/spring/root-context.xml",
						"classpath:kr/or/ddit/config/spring/application-datasource-dev.xml",
						"classpath:kr/or/ddit/config/spring/application-transaction.xml"})
@WebAppConfiguration
public class ControllerTestEnv {
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	//@Autowired 는 타입이 같은것을 검색!! 단 하나만 존재해야한다
	//	ex) IuserDao 를 UserDao 한개만 implement하면 가능하지만, UserDao2도 존재하면 사용X
	//여러개일때는 어노테이션 을 더 추가해야함
	@Autowired
	protected WebApplicationContext ctx;	//spring container
	//브라우저에서 요청과 응답을 의미하는 객체
	protected MockMvc mockMvc;			//dispatcher servlet
	
	;
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.setContinueOnError(false);
		rdp.addScript(new ClassPathResource("kr/or/ddit/testenv/dbInit.sql"));
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Ignore
	@Test
	public void dummy() {};
	

}
