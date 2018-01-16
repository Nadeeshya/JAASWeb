package com.za.tutorial.java.security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ZaCallbackHandler implements CallbackHandler {
	
	private String user = null;
	private String password = null;
	
	public ZaCallbackHandler(String user, String password) {
		System.out.println("4");
		this.user = user;
		this.password = password;
	}

	@Override
	public void handle(Callback[] callbackArray) throws IOException,
			UnsupportedCallbackException {
		System.out.println("9");
		int counter = 0;
		while(counter < callbackArray.length){
			if(callbackArray[counter] instanceof NameCallback){
				NameCallback nameCallback = (NameCallback)callbackArray[counter++];
				nameCallback.setName(user);
				System.out.println("10");
				System.out.println("user" +user);
			}else if(callbackArray[counter] instanceof PasswordCallback){
				PasswordCallback passwordCallback = (PasswordCallback)callbackArray[counter++];
				passwordCallback.setPassword(password.toCharArray());
				System.out.println("11");
			}
		}

	}

}
