package kr.or.ddit.config.spring;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class ApplicationTransaction {

	@Resource(name = "datasource")
	private DataSource datasource;
	
	/*
	<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<property name="dataSource" ref="datasource"/>
	</bean>
	*/
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dstm = new DataSourceTransactionManager();
		dstm.setDataSource(datasource);
		return dstm;
	}
	
	/*
	<aop:config>
		<aop:advisor advice-ref="txAdvice" pointcut="execution(* kr.or.ddit..service.*.*(..))"/>
	</aop:config>
	*/
	// (board, user,...)Service 에서 @Transactional 을 붙여 준다 
	
	
	
}






