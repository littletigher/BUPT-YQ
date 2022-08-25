package teleDemo.service;

import lombok.experimental.var;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import teleDemo.entities.tbInfo;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {
    @Resource
    teleDemo.mapper.comInfoMapper comInfoMapper;

    public ArrayList<ArrayList<Double>> gettbInfo(int user_id) {

        List<tbInfo> tbInfos = comInfoMapper.gettbInfoById(user_id);
        if (tbInfos.size() < 1) {
            return null;
        }
        ArrayList<ArrayList<Double>> list = new ArrayList<ArrayList<Double>>(tbInfos.size() * 2);
        for (tbInfo info : tbInfos) {
            ArrayList<Double> tmp = new ArrayList<>();
            tmp.add(info.getLon());
            tmp.add(info.getLat());
            list.add(tmp);
        }
        return list;
    }
}
