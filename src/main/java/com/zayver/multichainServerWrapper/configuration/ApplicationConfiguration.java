package com.zayver.multichainServerWrapper.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ykarav.multichain.chain.Chain;
import ykarav.multichain.chain.MultichainService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public MultichainService multichainService(){
        var omnic = Chain.initialize("127.0.0.1", 7332, "multichainrpc", "DeafwhmsToc8NUd6DU2oMSYmUaZWfdWFN3PjucEJ9kQD", "Omnic");
        return MultichainService.setChain(omnic);
    }

    @Bean
    public MessageDigest messageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256");
    }
}
