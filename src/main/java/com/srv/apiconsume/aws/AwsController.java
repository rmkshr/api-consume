package com.srv.apiconsume.aws;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/aws/apigatewaymeta")
@RequiredArgsConstructor
public class AwsController {

    @Autowired
    private ApiGatewayConfig config;


    @GetMapping
    public String getMeta() throws IOException {
        config.fetchApiGatewayConfigurations();
        return "SRV";
    }
}
