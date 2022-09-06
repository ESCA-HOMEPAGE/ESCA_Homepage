package com.esca.escahp;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"*"})
public class EscahpController {

    @ApiOperation("health-check")
    @GetMapping()
    public String hello() {
        return "hello World!";
    }
}
