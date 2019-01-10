package com.data.integration;

import com.data.integration.model.Leon;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ResponseHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/ja")
public class CamaController {
    final static Logger logger = LoggerFactory.getLogger(CamaController.class);

    @Autowired
    CamaService camaService;

    @RequestMapping(value = "/import", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Record seller buyer info")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseHeader()
    public ValidationError createPerson(@RequestHeader(value="Authorization") String token, @Valid @RequestBody Leon payload) {
        boolean result = false;
        ValidationError errors = new ValidationError();
        List<Error> error = new ArrayList<>();
        try {
            result = camaService.LeonProcess(payload);
        } catch (Exception e) {
            e.printStackTrace();
            error.add( new Error("400", e.toString()));
            errors.setFieldErrors(error);

            return errors;
        }
        if(result) {
            error.add(new Error("200", "successful"));
            errors.setFieldErrors(error);
            return errors;
        } else{
            error.add(new Error("400", "unsuccessful"));
            errors.setFieldErrors(error);
            return errors;
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/html")
    @ApiOperation("test")
    public @ResponseBody String createPerson() {
            return "accepted";
    }
}
