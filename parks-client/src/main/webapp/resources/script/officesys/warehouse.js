/**
 * Created by Zhao_d on 2016/12/30.
 */
$(function () {
    $('#warehouse-dg').datagrid({
        title:'库存统计',
        toolbar:'#warehouse-toolbar',
        method:'post',
        nowrap:true,
        sortName:'id',
        sortOrder:'asc',
        striped:true,
        rownumbers:true,
        fitColumns:false,
        fit:true,
        singleSelect:true,
        pagination: true,
        url:'warehouse/warehouseList',
        frozenColumns:[[
            {field:'id',title:'ID',hidden:true},
            {field:'name',title:'名称'}
        ]],
        columns:[[
            {field:'type',title:'分类'},
            {field:'modelNumber',title:'型号'},
            {field:'specifications',title:'规格'},
            {field:'brand',title:'品牌'},
            {field:'unit',title:'单位'},
            {field:'newCost',title:'最新单价'},
            {field:'quantityUse',title:'可用数量'},
            {field:'quantityBorrow',title:'借用数量'},
            {field:'quantity',title:'库存总量'}
        ]]
    })
});

/**
 * 查询
 */
function warehouseQuery(){
    var query = {
        nameQuery:$('#nameQuery').val(),
        typeQuery:$('#typeQuery').combobox('getValue'),
        brandQuery:$('#brandQuery').val()
    };
    $('#company-dg').datagrid({
        queryParams:query
    });
}