package com.ifood.quarkus.commad.mode;

import java.util.stream.Stream;

import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class HelloCommand implements QuarkusApplication {
	
	
	@Inject
	@Channel("parametros")
	private Emitter<String> emitter;

	// java  -jar arquivo.jar -Dquarkus.args=a -Dquarkus.args=b -Dquarkus.args=c
    @Override
    public int run(String... args) throws Exception {
    	System.out.println(args.length);
        Stream.of(args).forEach(arg -> this.emitter.send(arg));
        Quarkus.waitForExit();
        return 0;
    }
}
