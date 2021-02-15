let map;
let posicoes;
let loadedLayers = {};
let activeLayer;
let activeFunction;

function GetMap() {
    if (latitude != 'null' && longitude != 'null') {
        CarregarPosicao(latitude, longitude);
    }
    else if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(CarregarPosicaoAtual, CarregarCentroBrasil);
    } else {
        CarregarCentroBrasil();
    }
}

function CarregarPosicao(latitude, longitude) {
    map = new Microsoft.Maps.Map('#myMap', {
        center: new Microsoft.Maps.Location(latitude, longitude),
        zoom: 12
    });
    MostrarMapa();
}

function CarregarPosicaoAtual(position) {
    map = new Microsoft.Maps.Map('#myMap', {
        center: new Microsoft.Maps.Location(position.coords.latitude, position.coords.longitude),
        zoom: 12
    });
    MostrarMapa();
}

function CarregarCentroBrasil() {
    map = new Microsoft.Maps.Map('#myMap', {
        center: new Microsoft.Maps.Location(-14.235, -51.9253),
        zoom: 4
    });
    MostrarMapa();
}

function MostrarMapa() {
    SetBingPosicoes(JSON.parse(posicoesJson));
    GetNavBar();
    ShowLayer('HeatMap', LoadHeatMap);
}

function SetBingPosicoes(novasPosicoes) {
    posicoes = novasPosicoes.map(function (posicao) {
        return new Microsoft.Maps.Location(posicao.latitude, posicao.longitude)
    });
}

function GetNavBar() {
    PanningOverlay.prototype = new Microsoft.Maps.CustomOverlay({ beneathLabels: false });

    //Define a constructor for the custom overlay class.
    function PanningOverlay() {
        this.mapas = document.getElementById('mapadrowndown');
    }

    //Implement the onAdd method to set up DOM elements, and use setHtmlElement to bind it with the overlay.
    PanningOverlay.prototype.onAdd = function () {
        this.setHtmlElement(this.mapas);
    };

    //Implement the new custom overlay class.
    var overlay = new PanningOverlay();

    //Add the custom overlay to the map.
    map.layers.insert(overlay);
}

function ShowLayer(selectedLayer, loadFunction) {
    activeLayer = selectedLayer;
    activeFunction = loadFunction;
    if (!(selectedLayer in loadedLayers))
        loadFunction();

    for (var layer in loadedLayers) {
        if (layer === selectedLayer) {
            loadedLayers[layer].setVisible(true);
        } else {
            loadedLayers[layer].setVisible(false);
        }
    }
}