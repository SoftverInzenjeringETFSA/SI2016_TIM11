package com.timxyz.controllers;

import com.timxyz.models.BaseModel;
import com.timxyz.services.BaseService;
import com.timxyz.services.exceptions.ServiceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.validation.Valid;

import com.timxyz.services.LogHelperService;

public abstract class BaseController<M extends BaseModel, S extends BaseService<M, ? > > {
    protected S service;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    public void setService(S service) {
        this.service = service;
    }

    @Autowired
    protected LogHelperService logHelperService;

    @ResponseBody
    public Iterable<M> all() {
        return service.all();
    }

    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid M newModel, @RequestHeader("Authorization") String token) {
        try {
            M model = service.save(newModel);
            logForCreate(token, model);
            return ResponseEntity.ok(model);
        } catch(ServiceException e) {
            return error(e);
        }
    }

    @ResponseBody
    public ResponseEntity get(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(service.get(id));
        } catch(ServiceException e) {
            return error(e);        }
    }

    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Long id, @RequestHeader("Authorization") String token) {
        try {
            logForDelete(token, service.get(id));
            service.delete(id);
            return ResponseEntity.ok(true);
        }
        catch (ServiceException e) {
            return error(e);
        }

    }

    @ResponseBody
    public ResponseEntity getPage(@PathVariable("pageNumber") int pageNumber) {
        Pageable page =new PageRequest(pageNumber-1, 5);
        return ResponseEntity.ok(service.listAllByPage(page));
    }

    protected void logForCreate (String token, BaseModel model) {
        logHelperService.logCreate(token, model);
    }

    protected void logForUpdate (String token, BaseModel model) {
        logHelperService.logUpdate(token, model);
    }

    protected void logForDelete (String token, BaseModel model) {
        logHelperService.logDelete(token, model);
    }
    
    @ResponseBody
    protected ResponseEntity error(Exception e) {
    	JsonObjectBuilder objectBuilder = Json.createObjectBuilder()
                .add("error", "Bad request")
                .add("message", e.getMessage());
        JsonObject responseObj = objectBuilder.build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseObj.toString());
    }
}
