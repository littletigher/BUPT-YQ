package com.group5.opinionmanage.service.impl;

import com.group5.opinionmanage.dao.OpinionsRepository;
import com.group5.opinionmanage.entity.Opinions;
import com.group5.opinionmanage.service.OpinionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 10569
 * @version 1.0
 * @description
 * @Date 2022/8/24 15:18
 */
@Service("OpinionsService")
public class OpinionsServiceImpl implements OpinionsService {
    @Autowired
    private OpinionsRepository opinionsRepository;

    @Override
    public Opinions findByOid(Integer oid) {
        return opinionsRepository.findByOid(oid);
    }

    @Override
    public List<Opinions> findByContext(String context) {
        return opinionsRepository.findByContextLike(context);
    }

    @Override
    public Page<Opinions> findAll(Pageable pageable) {
        return opinionsRepository.findAll(pageable);
    }

    @Override
    public Long count() {
        return opinionsRepository.count();
    }
}


