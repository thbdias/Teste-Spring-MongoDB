package io.codementor.gtommee.rest_tutorial.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest implements MeuService {

	public void testeAsync() {
	}
	
	@Async("fileExecutor")
	public void t1() {
		// [...] 
		System.out.println("Ação a ser executada de forma assincrona");
	}

	public void asyncMethodWithVoidReturnType() {
	}

	public void t2() {
		// TODO Auto-generated method stub
		
	}

	public void t3(int i) {
		// TODO Auto-generated method stub
		
	}

}
