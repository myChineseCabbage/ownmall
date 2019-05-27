package com.zxb.ownmalladmin.controller;

import com.zxb.ownmalladmin.medecryption.EncryptionOperationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController("/encrypt")
public class EncrypTestController  {
    private static Logger logger = LoggerFactory.getLogger(EncryptionOperationUtils.class);

}
