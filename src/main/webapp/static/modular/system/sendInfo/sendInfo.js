/**
 * 信息管理初始化
 */
var SendInfo = {
    id: "SendInfoTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SendInfo.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'txt', visible: true, align: 'center', valign: 'middle'},
            {title: '经度', field: 'longitude', visible: true, align: 'center', valign: 'middle'},
            {title: '纬度', field: 'latitude', visible: true, align: 'center', valign: 'middle'},
            {title: '发送人', field: 'sender', visible: true, align: 'center', valign: 'middle'},
            {title: '发送时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'uuid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SendInfo.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SendInfo.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加信息
 */
SendInfo.openAddSendInfo = function () {
    var index = layer.open({
        type: 2,
        title: '添加信息',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sendInfo/sendInfo_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看信息详情
 */
SendInfo.openSendInfoDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '信息详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sendInfo/sendInfo_update/' + SendInfo.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除信息
 */
SendInfo.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sendInfo/delete", function (data) {
            Feng.success("删除成功!");
            SendInfo.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sendInfoId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询信息列表
 */
SendInfo.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SendInfo.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SendInfo.initColumn();
    var table = new BSTable(SendInfo.id, "/sendInfo/list", defaultColunms);
    table.setPaginationType("client");
    SendInfo.table = table.init();
});
