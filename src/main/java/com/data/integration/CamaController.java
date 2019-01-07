package com.data.integration;

import com.data.integration.model.Leon;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CamaController {
    final static Logger logger = LoggerFactory.getLogger(CamaController.class);

    @Autowired
    CamaService camaService;

    @RequestMapping(value = "/api", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Record seller buyer info")
    @ResponseStatus(HttpStatus.CREATED)
    public CamaOutput createPerson(@Valid @RequestBody Leon payload) {
        boolean result = false;
        try {
            result = camaService.LeonProcess(payload);
        } catch (Exception e) {
            e.printStackTrace();
            return new CamaOutput("400", e.toString());
        }
        if(result) {
            return new CamaOutput("201", "successful");
        }
            return new CamaOutput("400", "unsuccessful");
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html")
    @ApiOperation("test")
    public @ResponseBody String createPerson() {
            return "accepted";
    }
}
