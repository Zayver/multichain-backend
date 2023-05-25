package com.zayver.multichainServerWrapper.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import ykarav.multichain.chain.Method;
import ykarav.multichain.chain.MultichainService;

import java.security.MessageDigest;
import java.util.*;

@RestController
@RequestMapping("/omnic")
public class BlockChainController {

    @Autowired
    private MultichainService multichainService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageDigest messageDigest;

    private final String CHAIN_NAME = "Omnic";

    @GetMapping("/getinfo")
    String getInfo() {
        return multichainService.apiCall(new ArrayList<>(), Method.GET_INFO, CHAIN_NAME);
    }

    @GetMapping("/gettokeninfo")
    String getTokenInfo(@RequestParam String asset, @RequestParam String token){
        List<Object> params = List.of(
                asset,
                Hex.encodeHexString(messageDigest.digest(token.getBytes()))
        );
        return multichainService.apiCall(params, "gettokeninfo", CHAIN_NAME);
    }

    @PostMapping("/issuetoken")
    String saveTokenInBlockChain(@RequestBody Map<String, String> params) throws JsonProcessingException {
        List<Object> bparams = List.of(
                "1sMrhmXQPhQBuVoUZm5XFrH91cnYxm2vHoP8F", //wallet address of some node
                "nfts",
                Hex.encodeHexString(messageDigest.digest(params.get("cc").getBytes())), //hash de la cedula
                1,
                0,
                params
        );
        return multichainService.apiCall(bparams, "issuetoken", CHAIN_NAME);
    }

}
