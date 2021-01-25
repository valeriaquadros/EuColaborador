function LoadHeatMap() {
    Microsoft.Maps.loadModule('Microsoft.Maps.HeatMap', function () {
        var heatmap = new Microsoft.Maps.HeatMapLayer(posicoes);
        map.layers.insert(heatmap);
        loadedLayers["HeatMap"] = heatmap;
    });
}