/**
 * 附件管理初始化
 */
var SendFile = {
    id: "SendFileTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
SendFile.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '归属id', field: 'owenid', visible: true, align: 'center', valign: 'middle'},
            {title: '文件名称', field: 'filename', visible: true, align: 'center', valign: 'middle'},
            {title: '文件后缀', field: 'filesuffix', visible: true, align: 'center', valign: 'middle'},
            {title: '文件地址', field: 'fileurl', visible: true, align: 'center', valign: 'middle'},
            {title: '文件类型', field: 'filetype', visible: true, align: 'center', valign: 'middle'},
            {title: '描述', field: 'description', visible: true, align: 'center', valign: 'middle'},
            {title: '文件来源', field: 'filesource', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'uuid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
SendFile.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        SendFile.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加附件
 */
SendFile.openAddSendFile = function () {
    var index = layer.open({
        type: 2,
        title: '添加附件',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/sendFile/sendFile_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看附件详情
 */
SendFile.openSendFileDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '附件详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/sendFile/sendFile_update/' + SendFile.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除附件
 */
SendFile.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/sendFile/delete", function (data) {
            Feng.success("删除成功!");
            SendFile.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("sendFileId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询附件列表
 */
SendFile.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    SendFile.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = SendFile.initColumn();
    var table = new BSTable(SendFile.id, "/sendFile/list", defaultColunms);
    table.setPaginationType("client");
    SendFile.table = table.init();
});
