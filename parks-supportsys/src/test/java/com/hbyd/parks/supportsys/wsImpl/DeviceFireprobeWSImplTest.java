package com.hbyd.parks.supportsys.wsImpl;

import com.google.gson.Gson;
import com.hbyd.parks.common.model.PageBeanEasyUI;
import com.hbyd.parks.common.model.QueryBeanEasyUI;
import com.hbyd.parks.dto.supportsys.DeviceFireprobeDTO;
import com.hbyd.parks.ws.supportsys.DeviceFireprobeWS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试：DeviceFireprobeWSImpl
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/supportsys/applicationContext_CXF_Client.xml"})
public class DeviceFireprobeWSImplTest {

    private Gson gson = new Gson();

    @Resource
    private DeviceFireprobeWS deviceFireprobeWS;

    @Test
    public void testFindAll(){
        List<DeviceFireprobeDTO> all = deviceFireprobeWS.findAll();

        if(all == null){
            System.out.println("没有记录");
        }else{
            System.out.println("共有" + all.size() + "条记录");
//            System.out.println(gson.toJson(all.get(0)));
            for (DeviceFireprobeDTO deviceFireprobeDTO : all) {
                System.out.println(gson.toJson(deviceFireprobeDTO));
            }
        }
    }

    @Test
    public void testGetPageBean(){
        QueryBeanEasyUI queryBean = new QueryBeanEasyUI();
        queryBean.setOrder("asc");
        queryBean.setSort("id");
        queryBean.setPage(1);
        queryBean.setRows(10);

        String hql_where = "";
        Object[] params = new Object[]{};
        PageBeanEasyUI pageBean = deviceFireprobeWS.getPageBean(queryBean, hql_where, params);

        if(pageBean.getTotal() == 0){
            System.out.println("没有符合条件的记录");
        }else{
            for (Object o : pageBean.getRows()) {
                System.out.println(gson.toJson(o));
            }
        }
    }

    @Test
    public void testCrud(){
        String parentId = "F57F632C-40DD-434E-A548-ABE81C4EC9FD";
        String devTypeId = "1";
        String regionId = "f4fa7f01-fbf7-43e0-ad7c-fa515acce756";
        String groupId = "1";//默认组

        String parentIdNew = "000B7722-0378-4C04-94B9-7CCB0D48123E";
        String devTypeIdNew = "10";
        String regionIdNew = "67b44421-3a14-4371-9ba6-987c98b89094";
        String groupIdNew = "127738";//B7厂房

//      保存
        DeviceFireprobeDTO dto = new DeviceFireprobeDTO();

//      关联字段
        dto.setParentId(parentId);//上级设备
        dto.setTypeId(devTypeId);//设备类型
        dto.setRegionId(regionId);//区域
        dto.setGroupId(groupId);//探头组

//      普通字段
//        非空
        dto.setOneType("one_type");
        dto.setDeviceId("device_id");
        dto.setState("state");

//        特有
        dto.setCommAddr("ca");
        dto.setLoopAddr("la");

//      执行保存
        DeviceFireprobeDTO saved = deviceFireprobeWS.save(dto);
        System.out.println(saved.getId());

//      断言
//        普通非空
        assert "one_type".equals(saved.getOneType());
//        普通特有
        assert "ca".equals(saved.getCommAddr());
        assert "la".equals(saved.getLoopAddr());
//        关联字段
        assert parentId.equals(saved.getParentId());
        assert regionId.equals(saved.getRegionId());
        assert devTypeId.equals(saved.getTypeId());
        assert groupId.equals(saved.getGroupId());

//      更新
//        普通非空
        saved.setOneType("one_type_new");
//        普通特有
        saved.setCommAddr("ca_new");
        saved.setLoopAddr("la_new");
//        关联字段
        saved.setParentId(parentIdNew);
        saved.setRegionId(regionIdNew);
        saved.setTypeId(devTypeIdNew);
        saved.setGroupId(groupIdNew);

//      执行更新
        deviceFireprobeWS.update(saved);

//      执行查找
        DeviceFireprobeDTO updated = deviceFireprobeWS.getByID(saved.getId());
//      断言
//        普通非空
        assert "one_type_new".equals(updated.getOneType());
//        普通特有
        assert "ca_new".equals(updated.getCommAddr());
        assert "la_new".equals(updated.getLoopAddr());
//        关联字段
        assert parentIdNew.equals(updated.getParentId());
        assert regionIdNew.equals(updated.getRegionId());
        assert devTypeIdNew.equals(updated.getTypeId());
        assert groupIdNew.equals(updated.getGroupId());

//      执行删除
        deviceFireprobeWS.delByID(saved.getId());
        assert deviceFireprobeWS.getByID(saved.getId()) == null;
    }

}
