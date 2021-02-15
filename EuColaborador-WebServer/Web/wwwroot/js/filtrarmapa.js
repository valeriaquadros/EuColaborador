function FiltrarMapa() {
    var datainicial = document.getElementById("dataInicialFiltro").value;
    var datafinal = document.getElementById("dataFinalFiltro").value;

    if (new Date(datainicial) > new Date(datafinal)) {
        document.getElementById("validationerror").textContent = "A data inicial não pode ser maior que a data final.";
    } else {
        document.getElementById("validationerror").textContent = "";
        GetRequest("/Mapa/buscarposicoes?datainicial=" + datainicial + "&datafinal=" + datafinal, FiltraMapaCallback, ErrorCallback);
    }
}

function FiltraMapaCallback(novasPosicoes) {
    document.getElementById("statusdatainicial").textContent = FormatDate(document.getElementById("dataInicialFiltro").value);
    document.getElementById("statusdatafinal").textContent = FormatDate(document.getElementById("dataFinalFiltro").value);
    $('#filtroPeriodoModal').modal('hide')
    SetBingPosicoes(novasPosicoes.posicoes);
    loadedLayers = {};
    map.layers.clear();
    ShowLayer(activeLayer, activeFunction);
}

function ErrorCallback(posicoes) {
    document.getElementById("validationerror").textContent = posicoes.responseText;
}