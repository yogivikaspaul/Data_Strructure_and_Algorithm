package com.test;

import java.util.concurrent.Callable;

public class CallableTest2 implements Callable<Integer>
{

	@Override
	public Integer call() throws Exception
	{
		System.out.println("Callable Test2");
		return 10+10;
	}
	
}
