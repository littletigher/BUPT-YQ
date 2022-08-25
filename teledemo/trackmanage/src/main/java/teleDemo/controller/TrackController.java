package teleDemo.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teleDemo.entities.GetVo;
import teleDemo.entities.tbuser;
import teleDemo.mapper.userInfoMapper;
import teleDemo.service.TrackService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wyj
 */
@RestController
public class TrackController {
    @Resource
    userInfoMapper userMapper;

    @Resource
    TrackService trackService;

    private final String DEFINITE_DIAGNOSIS="1";

    @GetMapping("/gettrack")
    public GetVo gettbInfo(@RequestParam("searchId")String searchId){
        String user_id=searchId.split("=")[1];
        tbuser user= userMapper.gettbUserById(Integer.valueOf(user_id));
        ArrayList<ArrayList<ArrayList<Double>>> ret=new ArrayList<>();
        if(DEFINITE_DIAGNOSIS.equals(user.getStatus())){
            ArrayList<ArrayList<Double>> tbInfos = trackService.gettbInfo(Integer.valueOf(user_id));
            ret.add(tbInfos);
            int size=tbInfos.size();
            GetVo<ArrayList<ArrayList<Double>>> getVo = new GetVo<>(0,"获取数据成功！",size,ret);
            return getVo;
        }else {
            GetVo<ArrayList<ArrayList<Double>>> getVo = new GetVo<>(-1,"获取数据失败！",0,ret);
            return getVo;
        }

    }

    @GetMapping("/getalltrack")
    public GetVo getAlltbInfo(){
        ArrayList<ArrayList<ArrayList<Double>>> ret=new ArrayList<>();
        List<tbuser> list = userMapper.getAlltbUser();
        for (tbuser user:list){
            if(DEFINITE_DIAGNOSIS.equals(user.getStatus())){
                ArrayList<ArrayList<Double>> tmp =trackService.gettbInfo(user.getId());
                if(tmp!=null){
                    ret.add(tmp);
                }
            }
        }
        int size=ret.size();
        GetVo<ArrayList<ArrayList<Double>>> getVo = new GetVo<>(0,"获取数据成功！",size,ret);
        return getVo;
    }
}
