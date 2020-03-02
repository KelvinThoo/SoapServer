package com.example.soapserver;

import javax.annotation.PostConstruct;

import com.example.owners.Owner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class TestRepository {
	private static final Map<String, Owner> owners = new HashMap<>();

    
	@PostConstruct
	public void initData() {
		Owner drake = new Owner();
		drake.setRefNo("123");
		drake.setMsg("Hello, There!");
        drake.setMsgCode("ksl1234@qwwq");
        drake.setSubmissionStatus("success");

		owners.put(drake.getRefNo(), drake);

		Owner kevin = new Owner();
		kevin.setRefNo("456");
		kevin.setMsg("Bye, There!");
        kevin.setMsgCode("ksl1234@q2113wwq");
        kevin.setSubmissionStatus("fail");

		owners.put(kevin.getRefNo(), kevin);

		Owner jeremy = new Owner();
		jeremy.setRefNo("789");
		jeremy.setMsg("Ohaiyo, There!");
        jeremy.setMsgCode("ksl12d1234@qwwq");
        jeremy.setSubmissionStatus("success");

		owners.put(jeremy.getRefNo(), jeremy);
	}

	public Owner findOwner(String name) {
		Assert.notNull(name, "The country's name must not be null");
		return owners.get(name);
	}
}