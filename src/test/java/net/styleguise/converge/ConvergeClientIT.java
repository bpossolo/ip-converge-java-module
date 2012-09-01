package net.styleguise.converge;

import net.styleguise.converge.AuthenticationResponse.Response;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConvergeClientIT {
	
	private ConvergeClient client;
	
	@Before
	public void setUp() throws Exception {
		String authKey = "3d40d8a29601dc2asfa2da65fawe2hjkx";
		int productId = 1;
		client = new ConvergeClient("http://www.myserver.net/converge", authKey, productId);
	}
	
	@Test
	public void testCheckUsernameAvailable() throws Exception {
		
		assertFalse(client.checkUsernameAvailable("tester"));
		assertTrue(client.checkUsernameAvailable("billybobthornton"));
	}
	
	@Test
	public void testCheckEmailAvailable() throws Exception {
		
		assertFalse(client.checkEmailAvailable("tester@gmail.com"));
		assertTrue(client.checkEmailAvailable("tommy@gun.com"));
	}
	
	@Test
	public void testAuthenticateByUsername() throws Exception {
		
		AuthenticationResponse resp = client.authenticate("tester", AuthType.Username, "somepass");
		assertEquals(Response.Success, resp.getResponse());
		assertEquals("tester@gmail.com", resp.getEmail());
	}
	
	@Test
	public void testAuthenticateByEmail() throws Exception {
		
		AuthenticationResponse resp = client.authenticate("tester@gmail.com", AuthType.Email, "somepass");
		assertEquals(Response.Success, resp.getResponse());
		assertEquals("tester", resp.getUsername());
	}
	
	@Test
	public void testAuthenticateBadPass() throws Exception {
		
		AuthenticationResponse resp = client.authenticate("tester", AuthType.Username, "wrongpass");
		assertEquals(Response.WrongAuth, resp.getResponse());
	}
	
	@Test
	public void testAuthenticateNonExistentUser() throws Exception {
		
		AuthenticationResponse resp = client.authenticate("tommy_gun", AuthType.Username, "fakepass");
		assertEquals(Response.NoUser, resp.getResponse());
	}

}
