package com.data.integration;

import com.data.integration.model.Cama;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class CamaController {

    @Autowired
    CamaService camaService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    @ApiOperation("Record seller buyer info")
    public @ResponseBody String createPerson(@ApiParam("Came data.")
                               @RequestBody Cama payload) {

        camaService.process(payload);
        return "accepted";

    }
}
