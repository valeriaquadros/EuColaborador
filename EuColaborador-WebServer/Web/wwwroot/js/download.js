function ToastDownloadRelatorio() {
    toastr.info('Seu relatório está sendo processado. Aguarde!');
    var timeInterval = 500; // milliseconds (checks the cookie for every half second )

    var loop = setInterval(function () {
        if (IsCookieValid()) {
            toastr.success('Sucesso', 'Seu relatório está sendo baixado.');
            clearInterval(loop);
            deleteCookie();
        }
    }, timeInterval);
}

function deleteCookie() {
    var cook = getCookie('ExcelDownloadFlag');
    if (cook != "") {
        document.cookie = "ExcelDownloadFlag=";
        Path = "/";
        expires = "Thu, 01 Jan 1970 00: 00: 00 UTC";
    }
}

function IsCookieValid() {
    var cook = getCookie('ExcelDownloadFlag');
    return cook != '';
}

function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}