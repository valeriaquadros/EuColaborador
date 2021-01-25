function GetRequest(url, sucesscallback, errorcallback) {
    $.ajax({
        url: getCurrentBaseURL()+url,
        type: 'get',
        dataType: 'json',
        async: true,
        success: function (data) {
            sucesscallback(data);
        },
        error: function (data) {
            errorcallback(data);
        }
    });
}

function getCurrentBaseURL() {
    var basePath = document.location.protocol + '//' + document.location.host;
    return basePath;
}


function FormatDate(rawDate) {
    var date = new Date(rawDate);

    var dia = date.getUTCDate();
    if (dia < 10) {
        dia = "0" + dia;
    }

    var mes = date.getUTCMonth() + 1;
    if (mes < 10) {
        mes = "0" + mes;
    }

    var ano = date.getUTCFullYear();
    var dataFormatada = dia + "/" + mes + "/" + ano;

    return dataFormatada;
}