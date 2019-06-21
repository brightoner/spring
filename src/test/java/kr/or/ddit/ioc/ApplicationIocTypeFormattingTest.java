package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {

	@Resource(name="formattingVo")
	private FormattingVo formattingVo;
	
	
	@Test
	public void formattingConversionServiceTest() {
		/***Given***/

		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy");

		/***Then***/
		assertEquals("2019-06-21", sdf.format(formattingVo.getReg_dt()));
		assertEquals("06-21-2019", sdf2.format(formattingVo.getMod_dt()));
		assertEquals(6000, formattingVo.getNumber()); 	// "6,000" --> 6000 (문자열을 숫자로 변환<쉼표조심!!>)
		
	}

}
