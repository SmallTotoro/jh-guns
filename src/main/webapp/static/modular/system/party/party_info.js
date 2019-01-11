/**
 * 初始化报名详情对话框
 */
var PartyInfoDlg = {
    partyInfoData : {}
};

/**
 * 清除数据
 */
PartyInfoDlg.clearData = function() {
    this.partyInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PartyInfoDlg.set = function(key, val) {
    this.partyInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
PartyInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
PartyInfoDlg.close = function() {
    parent.layer.close(window.parent.Party.layerIndex);
}

/**
 * 收集数据
 */
PartyInfoDlg.collectData = function() {
    this
    .set('id')
    .set('name')
    .set('phone')
    .set('content')
    .set('createtime')
    .set('nickName')
    .set('avatarUrl')
    .set('gender')
    .set('activityid')
    .set('openid');
}

/**
 * 提交添加
 */
PartyInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/party/add", function(data){
        Feng.success("添加成功!");
        window.parent.Party.table.refresh();
        PartyInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.partyInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
PartyInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/party/update", function(data){
        Feng.success("修改成功!");
        window.parent.Party.table.refresh();
        PartyInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.partyInfoData);
    ajax.start();
}

$(function() {

});
