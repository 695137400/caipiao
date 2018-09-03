/**
 * Created by 69513 on 2017年2年19年0019.
 */
Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/mm/, (this.getMinutes() + 1) > 9 && (this.getMinutes() + 1) <= 59 ? (this.getMinutes() + 1).toString() : ((this.getMinutes() + 1) <= 59 ? ('0' + (this.getMinutes() + 1)) : (this.getMinutes())));
    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    return str;
};
function inin_s_f() {
    var date = new Date();
    $('#sj_s').html(date.Format('hh'));
    $('#sj_f').html(date.Format('mm'));
    for (var i = 0; i < 24; i++) {
        var li = '<li class="aui-list-item" style="font-size: 1.5em;" onclick=""><div class="aui-list-item-inner">';
        if (i < 10) {
            i = '0' + i;
        }
        li += i + '</div></li>';
        $('#s').append(li);
    }
    for (var i = 0; i < 60; i++) {
        var li = '<li class="aui-list-item" style="font-size: 1.5em;" onclick=""><div class="aui-list-item-inner">';
        if (i < 10) {
            i = '0' + i;
        }
        li += i + '</div></li>';
        $('#f').append(li);
    }
    $('#s li').click(function () {
        $('#sj_s').html($(this).text());
        $('#s li').css({
            'color': '#757575'
        });
        $(this).css({
            'color': '#0894ec'
        })
    })
    $('#f li').click(function () {
        $('#sj_f').html($(this).text());
        $('#f li').css({
            'color': '#757575'
        });
        $(this).css({
            'color': '#0894ec'
        })
    })
}
function szClose() {
    $('#szzz').remove();
    $('#szkj').remove();
}
function szShow(dom, callback) {
    var sz =
        '<div class="aui-mask aui-mask-in" style="z-index: 999999" id="szzz"></div>' +
        '<div class="aui-dialog aui-dialog-in" style="margin-top: -93px;z-index: 999999;" id="szkj">' +
        '    <div class="aui-dialog-header">' +
        '        <div style="font-size: 1.5em;">' +
        '            <span id="sj_s">00</span>:<span id="sj_f">00</span>' +
        '        </div>' +
        '    </div>' +
        '    <div></div>' +
        '    <div class="aui-dialog-body aui-tab aui-margin-l-5">' +
        '        <ul style="width: 45%;max-height: 100px;overflow: scroll;border: solid 1px rgba(165, 166, 166, 0.48);margin-left: 15%;"' +
        '            id="s">' +
        '        </ul>' +
        '        <b style="line-height: 2em;font-size: 2.7em;margin: 2px;">:</b>' +
        '        <ul style="width: 45%;max-height: 100px;overflow: scroll;border: solid 1px rgba(165, 166, 166, 0.48);margin-right: 15%;"' +
        '            id="f">' +
        '        </ul>' +
        '    </div>' +
        '    <div class="aui-dialog-footer">' +
        '        <div class="aui-dialog-btn" tapmode="" button-index="0" onclick="szClose()">取消</div>' +
        '        <div class="aui-dialog-btn" tapmode="" button-index="1" onclick="szSave('+callback+')">确定</div>' +
        '    </div>' +
        '</div>';
    var html = document.createElement('div');
    $(html).html(sz);
    $(dom).append( $(html).html());
    inin_s_f();

}
function szSave(callback) {
    var s = $('#sj_s').text();
    var f = $('#sj_f').text();
    callback(s,f);
    szClose();
}
