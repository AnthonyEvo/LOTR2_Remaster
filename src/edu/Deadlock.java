package edu;

class А {
	synchronized void foo(B b) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " вошел в A.foo");
		try {
			Thread.sleep(1000);
		} catch(Exception e) {
			System.out.println("А прерван");
		}
		System.out.println(name + " пытается вызвать B.last()");
		b.last();
	}
	synchronized void last() {
		System.out.println("внутри A.last");
	}
}

class B {
	synchronized void bar(А a) {
	String name = Thread.currentThread().getName();
	System.out.println(name + " вошел в В.bar");
	try {
		
	} catch(Exception e) {
		System.out.println("ИВ прерван");
	}
		System.out.println(name + " пытается вызвать A.last()");
	a.last();
	}
	synchronized void last() {
		System.out.println("внутри A.last");
	}
}

public class Deadlock implements Runnable {
	
	А a = new А();
	B b = new B();

	Deadlock() {
		Thread.currentThread().setName("MainThread");
		Thread t = new Thread(this, "RacingThread");
		t.start();
		a.foo(b);	// получить блокировку внутри этого потока.
		System.out.println("Назад в главный поток");
	}
	
	public static void main(String args[]) {
		new Deadlock();
	}
	
	public void run() {
		b.bar(a);	// получить блокировку b в другом потоке.
		System.out.println("Назад в другой поток");
	}
}