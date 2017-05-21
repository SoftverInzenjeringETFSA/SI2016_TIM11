package com.timxyz.services;

import com.timxyz.models.BaseModel;
import com.timxyz.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public class ReadOnlyService<M extends BaseModel, R extends PagingAndSortingRepository<M, Long> > {
    protected R repository;

    @Autowired
    public void setRepository(R repository) {
        this.repository = repository;
    }

    public Iterable<M> all() {
        return repository.findAll();
    }

    public M get(Long id) throws ServiceException {
        if(id == null) return null;
        M model = repository.findOne(id);
        if(model == null)
            throw new ServiceException("Could not find a model with the given ID!");

        return model;
    }

    public M save(M model) throws ServiceException {
        return repository.save(model);
    }

    public Boolean exists(Long id) { return repository.exists(id); }

    public Long count() { return repository.count(); }

    public Page<M> listAllByPage(Pageable p) {
        return repository.findAll(p);
    }
}
