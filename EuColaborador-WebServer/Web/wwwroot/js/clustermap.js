﻿function LoadClusterMap() {
    var pins = posicoes.map(function (posicao) {
        return new Microsoft.Maps.Pushpin(posicao, null)
    });
    Microsoft.Maps.loadModule('Microsoft.Maps.Clustering', function () {
        //Create a ClusterLayer with options and add it to the map.
        var clusterLayer = new Microsoft.Maps.ClusterLayer(pins, {
            clusteredPinCallback: createCustomClusteredPin,
            gridSize: 80
        });
        map.layers.insert(clusterLayer);
        loadedLayers["ClusterMap"] = clusterLayer;
    });
}

function createCustomClusteredPin(cluster) {
    //Define variables for minimum cluster radius, and how wide the outline area of the circle should be.
    var minRadius = 12;
    var outlineWidth = 7;
    //Get the number of pushpins in the cluster
    var clusterSize = cluster.containedPushpins.length;
    //Calculate the radius of the cluster based on the number of pushpins in the cluster, using a logarithmic scale.
    var radius = Math.log(clusterSize) / Math.log(10) * 5 + minRadius;
    //Default cluster color is red.
    var fillColor = 'rgba(255, 40, 40, 0.5)';
    if (clusterSize < 10) {
        //Make the cluster green if there are less than 10 pushpins in it.
        fillColor = 'rgba(20, 180, 20, 0.5)';
    }
    else if (clusterSize < 100) {
        //Make the cluster yellow if there are 10 to 99 pushpins in it.
        fillColor = 'rgba(255, 210, 40, 0.5)';
    }
    //Create an SVG string of two circles, one on top of the other, with the specified radius and color.
    var svg = ['<svg xmlns="http://www.w3.org/2000/svg" width="', (radius * 2), '" height="', (radius * 2), '">',
        '<circle cx="', radius, '" cy="', radius, '" r="', radius, '" fill="', fillColor, '"/>',
        '<circle cx="', radius, '" cy="', radius, '" r="', radius - outlineWidth, '" fill="', fillColor, '"/>',
        '</svg>'];
    //Customize the clustered pushpin using the generated SVG and anchor on its center.
    cluster.setOptions({
        icon: svg.join(''),
        anchor: new Microsoft.Maps.Point(radius, radius),
        textOffset: new Microsoft.Maps.Point(0, radius - 8) //Subtract 8 to compensate for height of text.
    });
}