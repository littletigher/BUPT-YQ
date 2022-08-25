package teleDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import teleDemo.entities.GetVo;
import teleDemo.entities.tbHotSearch;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class hsInfoController {
    @Resource
    teleDemo.mapper.hsInfoMapper hsInfoMapper;

    @GetMapping("/v1/hsInfo")
    public GetVo getHsInfo(HttpServletRequest request) {
        List<tbHotSearch> tbHotSearchList = hsInfoMapper.getTop10HotSearch();
        GetVo<tbHotSearch> getVo = new GetVo<>(0, "获取数据成功！",10, tbHotSearchList);
        return getVo;
    }
}
