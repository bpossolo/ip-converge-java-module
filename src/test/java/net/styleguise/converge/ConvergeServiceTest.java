package net.styleguise.converge;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

public class ConvergeServiceTest {

	private ConvergeService convergeService;
	
	private ObjectMapper om;
	
	@Before
	public void setUp(){
		convergeService = new ConvergeService(new TestableConvergeServiceDelegate());
		om = new ObjectMapper();
	}
	
	@Test
	public void testEncodeCookies() throws ConvergeException {
		
		ConvergeCookie cookie1 = new ConvergeCookie();
		cookie1.setName("person");
		cookie1.setValue("benjamin");
		cookie1.setPath("/path/to/cookie");
		cookie1.setDomain("www.styleguise.net");
		cookie1.setSticky(1);
		
		ConvergeCookie cookie2 = new ConvergeCookie();
		cookie2.setName("person");
		cookie2.setValue("tierney");
		cookie2.setPath("/path/to/cookie");
		cookie2.setDomain("www.barkpark.com");
		cookie2.setSticky(0);
		
		String result = convergeService.encodeCookies(Arrays.asList(cookie1, cookie2));
		
		String expected = 
				"YToyOntpOjA7YTo1OntzOjQ6Im5hbWUiO3M6NjoicGVyc29uIjtzOjU6InZh" +
				"bHVlIjtzOjg6ImJlbmphbWluIjtzOjQ6InBhdGgiO3M6MTU6Ii9wYXRoL3Rv" +
				"L2Nvb2tpZSI7czo2OiJkb21haW4iO3M6MTg6Ind3dy5zdHlsZWd1aXNlLm5l" +
				"dCI7czo2OiJzdGlja3kiO2k6MTt9aToxO2E6NTp7czo0OiJuYW1lIjtzOjY6" +
				"InBlcnNvbiI7czo1OiJ2YWx1ZSI7czo3OiJ0aWVybmV5IjtzOjQ6InBhdGgi" +
				"O3M6MTU6Ii9wYXRoL3RvL2Nvb2tpZSI7czo2OiJkb21haW4iO3M6MTY6Ind3" +
				"dy5iYXJrcGFyay5jb20iO3M6Njoic3RpY2t5IjtpOjA7fX0=";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void testEncodeImportMembersResponse() throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ConvergeMember member1 = new ConvergeMember();
		member1.setEmail("bpossolo@gmail.com");
		member1.setFlag(0);
		member1.setJoinDate(ConvergeUtil.dateToUnixTimestamp(sdf.parse("2012-06-14")));
		member1.setIpAddress("192.168.1.1");
		member1.setUsername("bpossolo");
		member1.setPasswordMd5Hash("x2dasdf2sz");
		member1.setPasswordSalt("abc");
		
		ConvergeMember member3 = new ConvergeMember();
		member3.setEmail("chad@gmail.com");
		member3.setFlag(0);
		member3.setJoinDate(ConvergeUtil.dateToUnixTimestamp(sdf.parse("2010-01-13")));
		member3.setIpAddress("192.168.1.3");
		member3.setUsername("chad");
		member3.setPasswordMd5Hash("xarqidvosw");
		member3.setPasswordSalt("abc");
		
		ImportMembersResponse imr = new ImportMembersResponse();
		imr.addMember(1, member1);
		imr.addMember(3, member3);
		
		String json = om.writeValueAsString(imr);
		String result = convergeService.convertJsonToEncodedPhpArray(json);
		
		String expected = 
			"YToyOntzOjg6ImNvbXBsZXRlIjtpOjA7czo3OiJtZW1iZXJzIjthOjI6" +
			"e2k6MTthOjg6e3M6ODoidXNlcm5hbWUiO3M6ODoiYnBvc3NvbG8iO3M6" +
			"NToiZXh0cmEiO047czo0OiJmbGFnIjtpOjA7czoxMzoiZW1haWxfYWRk" +
			"cmVzcyI7czoxODoiYnBvc3NvbG9AZ21haWwuY29tIjtzOjk6InBhc3Nf" +
			"c2FsdCI7czozOiJhYmMiO3M6ODoicGFzc3dvcmQiO3M6MTA6IngyZGFz" +
			"ZGYyc3oiO3M6MTA6ImlwX2FkZHJlc3MiO3M6MTE6IjE5Mi4xNjguMS4x" +
			"IjtzOjk6ImpvaW5fZGF0ZSI7aToxMzM5NjU3MjAwO31pOjM7YTo4Ontz" +
			"Ojg6InVzZXJuYW1lIjtzOjQ6ImNoYWQiO3M6NToiZXh0cmEiO047czo0" +
			"OiJmbGFnIjtpOjA7czoxMzoiZW1haWxfYWRkcmVzcyI7czoxNDoiY2hh" +
			"ZEBnbWFpbC5jb20iO3M6OToicGFzc19zYWx0IjtzOjM6ImFiYyI7czo4" +
			"OiJwYXNzd29yZCI7czoxMDoieGFycWlkdm9zdyI7czoxMDoiaXBfYWRk" +
			"cmVzcyI7czoxMToiMTkyLjE2OC4xLjMiO3M6OToiam9pbl9kYXRlIjtp" +
			"OjEyNjMzNjk2MDA7fX19";
		
		assertEquals(expected, result);
	}

}
