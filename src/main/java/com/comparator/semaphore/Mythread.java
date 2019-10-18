package com.comparator.semaphore;

import java.util.concurrent.Semaphore;

public class Mythread extends Thread
{
	Semaphore sem;
	String threadName;

	public Mythread(Semaphore sem, String threadname)
	{
		this.sem = sem;
		this.threadName = threadname;
	}

	@Override
	public void run()
	{
		if (this.getName().equals("A"))
		{
			System.out.println("Starting " + threadName);
			try
			{
				// First, get a permit.
				System.out.println(threadName + " is waiting for a permit.");

				// acquiring the lock
				sem.acquire();

				System.out.println(threadName + " gets a permit.");

				// Now, accessing the shared resource.
				// other waiting threads will wait, until this
				// thread release the lock
				for (int i = 0; i < 5; i++)
				{
					Shared.count++;
					System.out.println(threadName + ": " + Shared.count);

					// Now, allowing a context switch -- if possible.
					// for thread B to execute
					Thread.sleep(10);
				}
			} catch (InterruptedException exc)
			{
				System.out.println(exc);
			}

			// Release the permit.
			System.out.println(threadName + " releases the permit.");
			sem.release();
		}

		// run by thread B
		else
		{
			System.out.println("Starting " + threadName);
			try
			{
				// First, get a permit.
				System.out.println(threadName + " is waiting for a permit.");

				// acquiring the lock
				sem.acquire();

				System.out.println(threadName + " gets a permit.");

				// Now, accessing the shared resource.
				// other waiting threads will wait, until this
				// thread release the lock
				for (int i = 0; i < 5; i++)
				{
					Shared.count--;
					System.out.println(threadName + ": " + Shared.count);

					// Now, allowing a context switch -- if possible.
					// for thread A to execute
					Thread.sleep(10);
				}
			} catch (InterruptedException exc)
			{
				System.out.println(exc);
			}
			// Release the permit.
			System.out.println(threadName + " releases the permit.");
			sem.release();
		}
	}
}
