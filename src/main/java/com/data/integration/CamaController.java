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

    final static String CLIENTSECRET = "1";
    @Autowired
    CamaService camaService;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    @ApiOperation("Record seller buyer info")
    public @ResponseBody String createPerson(@RequestHeader(value="cama_client") String client,
                                             @ApiParam("Came data.")
                               @Valid @RequestBody Cama payload) {
        if(CLIENTSECRET.equals(client)) {
            camaService.LeonProcess(payload);
            return "accepted";
        } else {
            return "Unknown client. Dropping message.";
        }
    }
}
