package com.esca.escahp.keeper;

import com.esca.escahp.keeper.KeeperService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keeper")
@CrossOrigin(origins = {"*"})
public class KeeperController {

    private final KeeperService keeperService;

    public KeeperController(KeeperService keeperService) {
        this.keeperService = keeperService;
    }

    @ApiOperation(value = "지킴이 선정 게시판을 생성한다.")
    @GetMapping
    public void getKeeperBoard() {
        return;
    }

}