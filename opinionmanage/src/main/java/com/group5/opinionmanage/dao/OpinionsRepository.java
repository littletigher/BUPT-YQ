package com.group5.opinionmanage.dao;

import com.group5.opinionmanage.entity.Opinions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author 10569
 * @version 1.0
 * @description
 * @Date 2022/8/24 15:07
 */

public interface OpinionsRepository extends JpaRepository<Opinions, Integer> {
    Opinions findByOid(Integer oid);
    @Query(value = "select o from Opinions o where o.context like %?1%")
    List<Opinions> findByContextLike(String context);

    Page<Opinions> findAll(Pageable pageable);
    long count();
}


