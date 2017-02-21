var count = 0;

addContent = function () {
    var qualityContent = document.getElementById("quality_content").value;
    var editorname = document.getElementById("editorname").value;

    if (!checkParams(qualityContent)) {
        alert("请检查质量概况内容！！！")
        return;
    }
    jsoncontent = "[";

    for (var i = 0; i <= count; i++) {
        childJson = "";
        bugname = document.getElementById("bug_" + i).value;
        fzname = document.getElementById("fzName_" + i).value;
        bzContent = document.getElementById("bz_" + i).value;
        if (!checkParams(bugname) || !checkParams(fzname) || !checkParams(bzContent)) {
            alert("请检查用户反馈！！！")
            return;
        }
        yewu = document.getElementById("yw_" + i).value;
        status = document.getElementById("status_" + i).value;
        if (count == i) {
            childJson += "{\"content\":\"" + bugname + "\",\"head\":\"" + fzname + "\",\"progress\":\"" + status + "\",\"remark\":\"" + bzContent + "\",\"type\":\"" + yewu + "\"}";
        } else {
            childJson += "{\"content\":\"" + bugname + "\",\"head\":\"" + fzname + "\",\"progress\":\"" + status + "\",\"remark\":\"" + bzContent + "\",\"type\":\"" + yewu + "\"},";
        }
        jsoncontent += childJson;
    }
    jsoncontent += "]";

    jsoncontent=jsoncontent.replaceAll("\n", "<br/>");
    qualityContent=qualityContent.replaceAll("\n", "<br/>");

    console.log("feedback====" + jsoncontent);
    console.log("content====" + qualityContent);

    exit;


    var args = {
        "content": qualityContent,
        "editer": editorname,
        "feedback": jsoncontent
    };
    $.post("http://54.222.222.47:8086/quality/open/action_insert_summary.php", args, function (data) {
        console.log(data);
        var dataJson = JSON.parse(data);
        if (dataJson.data) {
            alert("提交成功");
            location.reload();
        } else {
            alert("提交失败");
        }
    });

}


checkParams = function (param) {
    param = param.replace(/(^\s*)|(\s*$)/g, "");///去除空格的方法
    if (param.length == 0) {
        return false;
    }
    if (param.length > 140) {
        return false;
    }
    return param;
}


addTr = function () {

    count = count + 1;
    addtr = "<tr id='tr_" + count + "'>";

    addtr += "<td><select id='yw_" + count + "' class='validate-selection'> <option>家长端</option><option>管理端</option><option>教师端</option><option>App</option><option>在线教师</option></select></td>";
    addtr += "<td><select id='status_" + count + "' class='validate-selection'><option>解决中</option> <option>已解决</option><option>不解决</option></select></td>";
    addtr += "<td><input type='text' id='fzName_" + count + "' class='validate-selection'></td>";
    addtr += "<td><textarea id='bug_" + count + "' class='validate-selection' rows='1'></textarea></td>";
    addtr += "<td><textarea class='col-lg-12 validate-selection' rows='1' id='bz_" + count + "'></textarea></td></tr>";

    $(addtr).insertBefore('#baseTr');

    console.log(count);

}

delTr = function () {
    if (count >= 1) {
        $("#tr_" + count).remove();
        count = count - 1;
    }
    console.log(count);

}
