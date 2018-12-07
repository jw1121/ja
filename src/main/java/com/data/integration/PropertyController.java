package com.data.integration;

import com.data.integration.domain.Deed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PropertyController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postData(@RequestBody Deed deed) {

        return null;

    }
}
