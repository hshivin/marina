$(document).ready(function () {
    findScreen();

});

findScreen = function () {

    var type = $('#type').val();
    var datepicker = $('#date').val();

    console.log(type)
    console.log(datepicker)

    if (type == '--请选择--') {
        type = "";
    }

    var args = {
        "type": type,
        "ctime": datepicker
    }

    $.getJSON("/screen/find", args, function (data) {
        var trCode = "";
        
console.log(data);
        if (data.errno != 0) {
            alert("请求接口失败！！！");
            return;
        }
        var json = data.data;
        $.each(json, function (index, content) {
            trCode += '<tr><td>' + content.type + '</td>';
            trCode += '<td>' + content.progress + '</td>';
            trCode += '<td>' + content.head + '</td>';
            trCode += '<td>' + content.ctime + '</td>';
            trCode += '<td>' + content.content + '</td>';
            trCode += '<td>' + content.remark + '</td>';
            trCode += "</tr>";
        });
    
        console.log(trCode);
         var tbody=document.getElementById("tbody");
           while(tbody.hasChildNodes()){
             tbody.removeChild(tbody.firstChild);
         }
        
        $('#tbody').append(trCode);



    });


}
