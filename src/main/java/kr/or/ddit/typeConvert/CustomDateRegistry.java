package kr.or.ddit.typeConvert;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;

//날자 타입 변환
public class CustomDateRegistry implements PropertyEditorRegistrar{
	
	private static final Logger logger = LoggerFactory.getLogger(CustomDateRegistry.class);

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		
		registry.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
		
		logger.debug("customEditorConfigurer");

		
	}

}
