package com.data.integration;

import com.data.integration.model.Leon;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/ja")
public class CamaController {
    final static Logger logger = LoggerFactory.getLogger(CamaController.class);

    @Autowired
    CamaService camaService;

    @RequestMapping(value = "/import", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Record seller buyer info")
    @ResponseStatus(HttpStatus.OK)
    @ResponseHeader()
    public ResponseEntity createLeon(@RequestHeader(value="Authorization") String token, @Valid @RequestBody Leon payload) {
        camaService.LeonProcess(payload);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html")
    @ApiOperation("test")
    public @ResponseBody String createPerson() {
            return "accepted";
    }
}
