package com.data.integration;

import com.data.integration.model.Cama;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class CamaController {

    @Autowired
    CamaService camaService;

    @RequestMapping(value = "/api", method = RequestMethod.POST, produces = "text/html")
    @ApiOperation("Record seller buyer info")
    public @ResponseBody String createPerson(@ApiParam("Came data.")
                               @Valid @RequestBody Cama payload) {
            camaService.LeonProcess(payload);
            return "accepted";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html")
    @ApiOperation("test")
    public @ResponseBody String createPerson() {
            return "accepted";
    }
}
