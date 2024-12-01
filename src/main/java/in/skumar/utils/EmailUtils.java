package in.skumar.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

  @Autowired
  private JavaMailSender javaMailSender;
	
	public boolean senEmail(String subject, String body,String to) {
		
		
		
		
		return true;
	}

}
