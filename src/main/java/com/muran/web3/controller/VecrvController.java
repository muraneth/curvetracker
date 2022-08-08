package com.muran.web3.controller;

import com.muran.web3.manager.VecrvManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("/vecrv")
public class VecrvController {
    @Autowired
    private VecrvManager vecrvManager;

    @RequestMapping(path = "/weightByTimeScale")
    public Object queryWeightByTimeScale(){
        return null;
    }

}
