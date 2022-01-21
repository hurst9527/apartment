package hxj.apartment.service.impl;

import hxj.apartment.bean.AdminInfo;
import hxj.apartment.dao.AdminInfoMapper;
import hxj.apartment.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HXJ
 * @create 2022-01-02 11:18
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoMapper adminInfoMapper;

    @Override
    public List<AdminInfo> getNomalAdminInfo() {
        return adminInfoMapper.getNomalAdminInfos();
    }
}
