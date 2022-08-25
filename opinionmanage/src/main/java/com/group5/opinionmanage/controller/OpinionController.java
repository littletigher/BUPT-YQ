package com.group5.opinionmanage.controller;

import com.group5.opinionmanage.entity.Opinions;
import com.group5.opinionmanage.formEntity.GetVo;
import com.group5.opinionmanage.service.OpinionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 10569
 * @version 1.0
 * @description
 * @Date 2022/8/24 14:17
 */

@RestController
public class OpinionController {

    @Autowired
    OpinionsService opinionsServiceImpl;

    @GetMapping("/formGet")
    public GetVo getForm(HttpServletRequest request) {
        int limit = Integer.parseInt(request.getParameter("limit"));
        int page = Integer.parseInt(request.getParameter("page"));
        Sort sort = Sort.by(Sort.Order.asc("oid"));
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        Page<Opinions> info = opinionsServiceImpl.findAll(pageable);
        Long count = opinionsServiceImpl.count();
        return new GetVo(0, "success", count, info);
    }

    @GetMapping("/test")
    public String test() {
        List<Opinions> a = opinionsServiceImpl.findByContext("co");
        return a.get(0).getContext();
    }
}


