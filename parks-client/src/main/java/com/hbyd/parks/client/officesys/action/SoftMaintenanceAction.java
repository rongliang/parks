package com.hbyd.parks.client.officesys.action;

import com.google.gson.Gson;
import com.hbyd.parks.client.util.ComboHelper;
import com.hbyd.parks.client.util.ExportExcelHelper;
import com.hbyd.parks.client.util.ExportWordHelper;
import com.hbyd.parks.client.util.JsonHelper;
import com.hbyd.parks.common.log.Module;
import com.hbyd.parks.common.log.Operation;
import com.hbyd.parks.common.model.*;
import com.hbyd.parks.dto.managesys.UserDTO;
import com.hbyd.parks.dto.officesys.SoftMaintenanceDTO;
import com.hbyd.parks.dto.officesys.SoftMaintenanceHandleDTO;
import com.hbyd.parks.ws.managesys.PriviledgeWS;
import com.hbyd.parks.ws.managesys.UserWS;
import com.hbyd.parks.ws.officesys.SoftMaintenanceHandleWS;
import com.hbyd.parks.ws.officesys.SoftMaintenanceWS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhao_d on 2016/12/8.
 */
@Controller
@Scope("prototype")
@Module(description = "售后维护跟踪记录")
public class SoftMaintenanceAction extends ActionSupport implements ModelDriven<SoftMaintenanceQuery>{

    private Gson gson = new Gson();

    private SoftMaintenanceQuery page = new SoftMaintenanceQuery();
    private SoftMaintenanceDTO maintenance = new SoftMaintenanceDTO();
    private SoftMaintenanceHandleDTO handle = new SoftMaintenanceHandleDTO();
    private String id;
    private String parentIdFK;
    private String deptName;
    private String assignPath = "officesys/softMaintenance/getMaintenanceListForAssign";    //查询被指派记录的地址
    private String allPath = "officesys/softMaintenance/getMaintenanceList";                //查询所有记录的地址
    private String regPath = "officesys/softMaintenance/getMaintenanceListForReg";          //只能查询自己记录的数据的地址

    @Resource
    private UserWS userWS;

    @Resource
    private SoftMaintenanceWS softMaintenanceWS;

    @Resource
    private SoftMaintenanceHandleWS softMaintenanceHandleWS;

    @Resource
    private PriviledgeWS priviledgeWS;

    public void maintenanceList(){
        PageBeanEasyUI list = getPageBean();        //获得数据列表
        if(list.getRows() == null){
            list.setRows(new ArrayList());
        }
        String result = gson.toJson(list);
        JsonHelper.writeJson(result);
    }

    public PageBeanEasyUI getPageBean(){
        PageBeanEasyUI list = new PageBeanEasyUI();
        //获得当前登录的用户
        UserDTO user = (UserDTO) ServletActionContext.getRequest().getSession().getAttribute("user");
        //判断该用户是否有查询被指派记录的权限
        boolean hasPriAssign = priviledgeWS.validatePriviledge(user.getId(), assignPath);
        //判断该用户是否有查询所有记录的权限
        boolean hasPriAll = priviledgeWS.validatePriviledge(user.getId(), allPath);
        //判断该用户是否有查询自己添加的记录的权限
        boolean hasPriReg = priviledgeWS.validatePriviledge(user.getId(), regPath);
        if(hasPriAll){
            list = softMaintenanceWS.getPageBeanByQueryBean(page);
        }else if(hasPriAssign || hasPriReg){
            if(hasPriAssign) {
                page.setAssignPersonQuery(user.getId());
            }
            if(hasPriReg) {
                page.setCheckPersonQuery(user.getId());
            }
            list = softMaintenanceWS.getPageBeanByQueryBean(page);
        }else{
            list.setRows(new ArrayList());
        }
        return list;
    }

    public void handleList(){
        PageBeanEasyUI list = softMaintenanceHandleWS.getPageBeanByQueryBean(page,parentIdFK);
        if(list.getRows() == null){
            list.setRows(new ArrayList());
        }
        String result = gson.toJson(list);
        JsonHelper.writeJson(result);
    }

    @Operation(type="添加记录")
    public void addMaintenance(){
        AjaxMessage message = new AjaxMessage();
        try{
            softMaintenanceWS.save(maintenance);
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    @Operation(type="修改记录")
    public void editMaintenance(){
        AjaxMessage message = new AjaxMessage();
        try{
            softMaintenanceWS.update(maintenance);
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    @Operation(type="删除记录")
    public void deleteMaintenance(){
        AjaxMessage message = new AjaxMessage();
        try{
            softMaintenanceWS.delFake(id);  //伪删
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    @Operation(type="添加处理过程")
    public void addHandle(){
        AjaxMessage message = new AjaxMessage();
        try{
            softMaintenanceHandleWS.save(handle);
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    @Operation(type="修改处理过程")
    public void editHandle(){
        AjaxMessage message = new AjaxMessage();
        try{
            softMaintenanceHandleWS.update(handle);
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    @Operation(type="删除处理过程")
    public void deleteHandle(){
        AjaxMessage message = new AjaxMessage();
        try{
            softMaintenanceHandleWS.delFake(id);  //伪删
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    /**
     * 导出Word格式售后维护记录表
     */
    public void exportWord(){
        AjaxMessage message = new AjaxMessage();
        try{
            //查询数据
            page.setSort("id");
            page.setOrder("desc");
            page.setRows(1000);
            PageBeanEasyUI pageBean = softMaintenanceWS.getPageBeanByQueryBean(page);
            SoftMaintenanceDTO soft = (SoftMaintenanceDTO)pageBean.getRows().get(0);
            page.setSort("handleBegTime");
            page.setOrder("asc");
            PageBeanEasyUI pageBeanHandle = softMaintenanceHandleWS.getPageBeanByQueryBean(page,soft.getId());
            List<SoftMaintenanceHandleDTO> handles = pageBeanHandle.getRows();
            if(handles == null){
                handles = new ArrayList<>();
            }

            //获得替换字段与替换数据对
            Map<String,String> map = getDataMap(soft,handles);

            String classPath = this.getClass().getClassLoader().getResource("").getFile();      //获得项目部署位置
            String file = classPath + "accessory/售后管理模板/003-售后维护跟踪单.docx";          //模板文件地址
            file = URLDecoder.decode(file,"utf-8");
            String fileName = java.net.URLEncoder.encode(soft.getProjectName(),"UTF-8");                  //生成文件名
            fileName += ".docx";
            ExportWordHelper.writeWord(file,fileName,map);
        }catch(Exception e){
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally {
            String result = gson.toJson(message);
            JsonHelper.writeJson(result);
        }
    }

    /**
     * 获取数据与替换字段对
     */
    public  Map<String,String> getDataMap(SoftMaintenanceDTO soft, List<SoftMaintenanceHandleDTO> handles){
        Map<String,String> map = new HashMap<>();
        map.put("projectName",soft.getProjectName());
        map.put("number",soft.getNumber());
        map.put("productName",soft.getProductName());
        map.put("regPerson",soft.getRegPersonName());
        map.put("regDate",soft.getRegDate());
        map.put("hopeEndDate",soft.getHopeEndDate());
        map.put("projectContracts",soft.getContractsName());
        map.put("phoneNo",soft.getPhoneNo());
        map.put("faultDesc",soft.getFaultDesc());
        map.put("resultPerson",soft.getResultPersonName());
        map.put("resultDate",soft.getResultDate());
        map.put("resultDesc",soft.getResult());

        int handleCount = handles.size();       //处理过程数量
        for(int i=0;i<5;i++){
            if(i<handleCount) {                 //有数据的部分
                SoftMaintenanceHandleDTO handle = (SoftMaintenanceHandleDTO)handles.get(i);
                map.put("handlePerson" + String.valueOf(i + 1), handle.getHandlePersonName());
                map.put("handleBegTime" + String.valueOf(i + 1), handle.getHandleBegTime());
                map.put("handleDesc" + String.valueOf(i + 1), handle.getHandleDesc());
                map.put("handleEndTime" + String.valueOf(i + 1), handle.getHandleEndTime());
                map.put("handleResult" + String.valueOf(i + 1), handle.getHandleResult());
            }else{                              //没有数据的补空值
                map.put("handlePerson" + String.valueOf(i + 1), "");
                map.put("handleBegTime" + String.valueOf(i + 1), "");
                map.put("handleDesc" + String.valueOf(i + 1), "");
                map.put("handleEndTime" + String.valueOf(i + 1), "");
                map.put("handleResult" + String.valueOf(i + 1), "");
            }
        }
        return map;
    }

    /**
     * 导出列表
     */
    public void exportExcel() throws Exception{
        AjaxMessage message = new AjaxMessage();
        try {
            page.setSort("number");
            page.setOrder("asc");
            page.setRows(10000);
            PageBeanEasyUI pageBean = getPageBean();
            ExportExcelHelper.exportSoftMaintenance(pageBean.getRows());
        }catch(Exception e) {
            message.setSuccess(false);
            message.setMessage(e.getMessage());
        }finally{
            //由于导出文件时Response存在冲突，因此只在导出失败时返回信息
            if(!message.getSuccess()) {
                String result = gson.toJson(message);
                JsonHelper.writeJson(result);
            }
        }
    }

    /**
     * 获得编号
     */
    public void getNewNumber(){
        page.setOrder("asc");
        page.setSort("id");
        page.setRows(1000);
        String number = softMaintenanceWS.getNewNumber(page);
        if(number.length() == 10){
            JsonHelper.writeJson(number);
        }
    }

    /**
     * 获得记录人员的可选列表
     */
    public void getRegPerson() {
        //按名称顺序排序
        //排除管理员与超级管理员
        QueryBeanEasyUI query = new QueryBeanEasyUI(1, 1000, "nickname", "asc");
        String hql_where = "WHERE isValid=true AND userName!='super' AND userName!='admin'";
        List<UserDTO> lists = userWS.getPageBean(query, hql_where).getRows();
        if (lists == null) {
            lists = new ArrayList<>();
        }
        List<Combobox> project = ComboHelper.getNicknameCombobox(lists);
        String result = gson.toJson(project);
        JsonHelper.writeJson(result);
    }

    /**
     * 获得操作人员的可选列表
     */
    public void getHandlePerson(){
        //按名称顺序排序
        //排除管理员与超级管理员
        QueryBeanEasyUI query = new QueryBeanEasyUI(1,1000,"nickname","asc");
        String hql_where = "WHERE isValid=true AND userName!='super' AND userName!='admin'";
        List<UserDTO> lists = userWS.getPageBean(query,hql_where).getRows();
        if(lists==null){
            lists=new ArrayList<>();
        }
        List<Combobox> project = ComboHelper.getNicknameCombobox(lists);
        String result = gson.toJson(project);
        JsonHelper.writeJson(result);
    }

    public SoftMaintenanceQuery getModel() {
        return page;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SoftMaintenanceDTO getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(SoftMaintenanceDTO maintenance) {
        this.maintenance = maintenance;
    }

    public SoftMaintenanceHandleDTO getHandle() {
        return handle;
    }

    public void setHandle(SoftMaintenanceHandleDTO handle) {
        this.handle = handle;
    }

    public String getParentIdFK() {
        return parentIdFK;
    }

    public void setParentIdFK(String parentIdFK) {
        this.parentIdFK = parentIdFK;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
