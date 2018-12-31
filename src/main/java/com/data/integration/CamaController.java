package com.data.integration;

import com.data.integration.model.Leon;
import com.data.integration.model.old.Cama;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class CamaController {

    @Autowired
    CamaService camaService;

    @RequestMapping(value = "/api", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    @ApiOperation("Record seller buyer info")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody String createPerson(@ApiParam("Came data.")
                               @Valid @RequestBody Leon payload, Errors errors) {
        try {
            camaService.LeonProcess(payload);
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
            return "accepted";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html")
    @ApiOperation("test")
    public @ResponseBody String createPerson() {
            return "accepted";
    }
}
