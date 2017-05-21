package com.timxyz.services;

import com.timxyz.models.AccessLog;
import com.timxyz.repositories.AccessLogRepository;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessLogService extends ReadOnlyService<AccessLog, AccessLogRepository> {

    public List<AccessLog> getByAccountUsername(String username) {
        return repository.getAllByAccountUsername(username);
    }

    public List<AccessLog> getByAccountFullName(String fullName) {
        return repository.getAllByAccountFullName(fullName);
    }

    public List<AccessLog> getAllByFilter(String filter) {
        return repository.getAllByFilter(filter);
    }

}
