package teleDemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import teleDemo.entities.GetVo;
import teleDemo.entities.PostVo;
import teleDemo.entities.tbuser;
import teleDemo.mapper.userInfoMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PeopleStatusController {

    @Resource
    userInfoMapper userMapper;
    @GetMapping("/getuserpage")
    public GetVo<tbuser> getuserpage(@RequestParam(value = "searchId",defaultValue = "0") String Id, @RequestParam("page")int page, @RequestParam("limit")int limit){
        int searchId=Integer.valueOf(Id.split("=")[1]);
        GetVo<tbuser> tbuserGetVo;
        if(searchId==0){
            int size = userMapper.getAlltbUser().size();
            List<tbuser> tbInfos = userMapper.gettbUserByPage((page-1)*limit,limit);
            tbuserGetVo = new GetVo<>(0, "获取数据成功！", size, tbInfos);
        }else {
            tbuser user = userMapper.gettbUserById(searchId);
            List<tbuser> tbInfos = new ArrayList<>();
            if(user!=null){
                tbInfos.add(user);
            }
            tbuserGetVo = new GetVo<>(0, "获取数据成功！", tbInfos.size(), tbInfos);
        }
        return tbuserGetVo;
    }

    @PostMapping("/changeuserstatus")
    public PostVo<tbuser> changeuserstatus(@RequestParam("id")String id,@RequestParam("phoneNumber")String phoneNumber,@RequestParam("status")String status ){
        int ret = userMapper.changeUser(String.valueOf(status),Integer.valueOf(id));
        PostVo<tbuser> tbuserPostVo;
        if(ret==1){
            tbuserPostVo = new PostVo<>(0, "修改数据成功！", null);
        }else {
            tbuserPostVo = new PostVo<>(1, "修改数据失败！", null);
        }
        return tbuserPostVo;
    }

    @PostMapping("/deleteuser")
    public PostVo<tbuser> deleteuser(@RequestParam("id")int id){
        int ret = userMapper.deleteUserById(id);
        PostVo<tbuser> tbuserDeleteVo;
        if(ret==1){
            tbuserDeleteVo = new PostVo<>(0, "删除数据成功！", null);
        }else {
            tbuserDeleteVo = new PostVo<>(1, "删除数据失败！", null);
        }
        return tbuserDeleteVo;
    }
}

