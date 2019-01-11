/**
 * 初始化信息详情对话框
 */
var SendInfoInfoDlg = {
    sendInfoInfoData : {}
};

/**
 * 清除数据
 */
SendInfoInfoDlg.clearData = function() {
    this.sendInfoInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SendInfoInfoDlg.set = function(key, val) {
    this.sendInfoInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
SendInfoInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
SendInfoInfoDlg.close = function() {
    parent.layer.close(window.parent.SendInfo.layerIndex);
}

/**
 * 收集数据
 */
SendInfoInfoDlg.collectData = function() {
    this
    .set('id')
    .set('title')
    .set('txt')
    .set('longitude')
    .set('latitude')
    .set('sender')
    .set('createtime')
    .set('uuid');
}

/**
 * 提交添加
 */
SendInfoInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sendInfo/add", function(data){
        Feng.success("添加成功!");
        window.parent.SendInfo.table.refresh();
        SendInfoInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sendInfoInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
SendInfoInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/sendInfo/update", function(data){
        Feng.success("修改成功!");
        window.parent.SendInfo.table.refresh();
        SendInfoInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.sendInfoInfoData);
    ajax.start();
}

$(function() {

});
