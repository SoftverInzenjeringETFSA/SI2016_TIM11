package com.timxyz.controllers;

import com.timxyz.models.AccessLog;
import com.timxyz.services.AccessLogService;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class AccessLogController extends ReadOnlyController<AccessLog, AccessLogService> {

    @RequestMapping(value = "/access-logs/search-by/filter/{val}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity filterByAccountUsernameOrFullName(@PathVariable("val") String filter) {
        return ResponseEntity.ok(service.getAllByFilter(filter));
    }
}