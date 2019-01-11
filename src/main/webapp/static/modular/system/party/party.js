/**
 * 报名管理初始化
 */
var Party = {
    id: "PartyTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Party.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '姓名', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '手机号', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: '内容', field: 'content', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},
            {title: '微信昵称', field: 'nickName', visible: true, align: 'center', valign: 'middle'},
            {title: '微信头像', field: 'avatarUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '微信性别', field: 'gender', visible: true, align: 'center', valign: 'middle'},
            {title: '活动id', field: 'activityid', visible: true, align: 'center', valign: 'middle'},
            {title: '微信id', field: 'openid', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Party.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Party.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加报名
 */
Party.openAddParty = function () {
    var index = layer.open({
        type: 2,
        title: '添加报名',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/party/party_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看报名详情
 */
Party.openPartyDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '报名详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/party/party_update/' + Party.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除报名
 */
Party.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/party/delete", function (data) {
            Feng.success("删除成功!");
            Party.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("partyId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询报名列表
 */
Party.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Party.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Party.initColumn();
    var table = new BSTable(Party.id, "/party/list", defaultColunms);
    table.setPaginationType("client");
    Party.table = table.init();
});
