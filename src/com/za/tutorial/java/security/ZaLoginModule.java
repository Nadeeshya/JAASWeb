package com.za.tutorial.java.security;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class ZaLoginModule implements LoginModule {
	
	public static final String TEST_USERNAME = "zauser";
	public static final String TEST_PASSWORD = "password";
	private boolean authenticationSuccessFlag;
	
	private CallbackHandler callbackHandler = null;

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		// TODO Auto-generated method stub
		System.out.println("16");
		return authenticationSuccessFlag;
	}

	@Override
	public void initialize(Subject arg0, CallbackHandler callbackHandler,
			Map<String, ?> arg2, Map<String, ?> arg3) {
		this.callbackHandler = callbackHandler;
		System.out.println("6");
	}

	@Override
	public boolean login() throws LoginException {
		System.out.println("7");
		Callback[] callbackArray = new Callback[2];
		callbackArray[0] = new NameCallback("User Name : ");
		callbackArray[1] = new PasswordCallback("Password : ", false);
		try {
			System.out.println("8");
			callbackHandler.handle(callbackArray);
			System.out.println("12");
		} catch (IOException | UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("13");
		String name = ((NameCallback)callbackArray[0]).getName();
		String password = new String(((PasswordCallback)callbackArray[1]).getPassword());
		System.out.println("name "+name);
		System.out.println("pass "+password);
		System.out.println("14");
		if((TEST_USERNAME.equals(name)) && TEST_PASSWORD.equals(password)){
			System.out.println("Authentication Success..");
			System.out.println("15s");
			authenticationSuccessFlag = true;
		}else{
			authenticationSuccessFlag = false;
			System.out.println("15f");
			throw new FailedLoginException("Authentication Failed..");
			
		}
		return authenticationSuccessFlag;
	}

	
	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}
