package com.ezen.www.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableScheduling
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ezen.www.repository"})
@Configuration
public class RootConfig {

	@Autowired //bean으로 인지 / 객체 생성해서 쓸 수 있게
	ApplicationContext applicationContext;
	
	@Bean
	public DataSource dataSoruce() {
		HikariConfig hikariConfig = new HikariConfig();
		//log4jdbc -> DB의 로그를 찍을 수 있는 드라이버로 설정 변경
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/springtest");
		hikariConfig.setUsername("springUser");
		hikariConfig.setPassword("mysql");
		//여기까지는 기본 설정
		
		//여기서부터 hikari 추가 설정(필수)
		hikariConfig.setMaximumPoolSize(5); //최대 커넥션 개수
		hikariConfig.setMinimumIdle(5); //최소 유휴 커넥션 개수(Max와 같은 갯수로 설정)
		
		hikariConfig.setConnectionTestQuery("SELECT now()"); //test 쿼리문
		hikariConfig.setPoolName("springHikariCP");
		
		//여기서부터 hikari 추가 설정(선택)
		//cachePrepStmts : cache 사용 여부 설정
		hikariConfig.addDataSourceProperty("dataSource.cachePrepStmts", "true");
		//mysql 드라이버가 한 연결 당 cache 사이즈 : 기본값 250~500 권장
		hikariConfig.addDataSourceProperty("dataSource.prepStmtsCacheSize", "250");
		//한 connection 당 캐싱할 preparedStatement의 개수 지정옵션 : default 250
		hikariConfig.addDataSourceProperty("dataSource.prepStmtsCacheSqlLimit", "true"); //true : 기본값 설정(256)
		//mysql 서버에서 최신 이슈가 있을 경우 지원을 받을 것인지 설정
		hikariConfig.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
		
		HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
		
		return hikariDataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlFactoryBean = new SqlSessionFactoryBean();
		sqlFactoryBean.setDataSource(dataSoruce());
		sqlFactoryBean.setMapperLocations(
				applicationContext.getResources("classpath:/mappers/*.xml"));
		
		//DB : _ 언더바를 이용한 스네이크 표기법이 기본
		//java : 카멜 표기법이 기본
		//DB에서 file_name 을 쓰면 -> java에서 fileName 으로 인지할 수 있게 함
		//별칭 설정
		/*
		 * sqlFactoryBean.setConfigLocation(
		 * applicationContext.getResources("classpath:/MybatisConfig.xml"));
		 */
		
		sqlFactoryBean.setConfigLocation(
				applicationContext.getResource("classpath:/mybatisConfig.xml"));
		
		return sqlFactoryBean.getObject();
	}
	
	//트랜젝션 매니저 설정
	@Bean
	public DataSourceTransactionManager transcationManager() {
		return new DataSourceTransactionManager(dataSoruce());
	}
	
}
