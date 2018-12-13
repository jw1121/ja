package com.data.integration;

import com.data.integration.domain.CAMAPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CAMAController {

    @Autowired
    CAMAService camaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postData(@RequestBody CAMAPayload camaPayload) {

        boolean successful = camaService.process(camaPayload);
        return null;

    }
}
