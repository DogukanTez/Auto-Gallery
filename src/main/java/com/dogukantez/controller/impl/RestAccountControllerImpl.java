package com.dogukantez.controller.impl;

import com.dogukantez.controller.IRestAccountController;
import com.dogukantez.controller.RestBaseController;
import com.dogukantez.controller.RootEntity;
import com.dogukantez.dto.DtoAccount;
import com.dogukantez.dto.DtoAccountIU;
import com.dogukantez.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }
}
