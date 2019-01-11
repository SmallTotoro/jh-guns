/**
 * 初始化附件详情对话框
 */
var SendFileInfoDlg = {
    sendFileInfoData : {}
};

/**
 * 清除数据
 */
SendFileInfoDlg.clearData = function() {
    this.sendFileInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SendFileInfoDlg.set = function(key, val) {
    this.sendFileInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SendFileInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SendFileInfoDlg.close = function() {
    parent.layer.close(window.parent.SendFile.layerIndex);
}

/**
 * 收集数据
 */
SendFileInfoDlg.collectData = function() {
    this
    .set('id')
    .set('owenid')
    .set('filename')
    .set('filesuffix')
    .set('fileurl')
    .set('filetype')
    .set('description')
    .set('filesource')
    .set('uuid');
}

/**
 * 提交添加
 */
SendFileInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sendFile/add", function(data){
        Feng.success("添加成功!");
        window.parent.SendFile.table.refresh();
        SendFileInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sendFileInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SendFileInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sendFile/update", function(data){
        Feng.success("修改成功!");
        window.parent.SendFile.table.refresh();
        SendFileInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sendFileInfoData);
    ajax.start();
}

$(function() {

});
