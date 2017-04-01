// import * as d3 from 'd3';
const weatherData = require('../data/weather.json');


export default {
  getLineAreaData(w, h) {
    var ele = document.createElement('svg');
    var svg = d3.select(ele);
    var x = d3.scaleTime().rangeRound([0, w]);
    var y = d3.scaleLinear().rangeRound([h, 0]);
    var line = d3.line()
        .x(function (d) {
          return x(new Date(d.date));
        })
        .y(function (d) {
          return y(d.close);
        });
    var g = svg.append("g")
    x.domain(d3.extent(weatherData, function (d) {
      return new Date(d.date);
    }));
    y.domain(d3.extent(weatherData, function (d) {
      return d.close;
    }));
    g.append("g").attr("transform", "translate(0," + h + ")").call(d3.axisBottom(x))
    g.append("g").call(d3.axisLeft(y))
    var path = svg.datum(weatherData).attr('d', line);
    return path._groups[0][0]['attributes']['d']['value'];
  },
  
  getXAixPath(w, h) {
    return 'M0,' + h + 'L' + w + ',' + h;
  },
  
  getYAixPath(w, h) {
    return 'M0,0L0,' + h;
  },
  
  generateStockData(w, h) {
    var n = 4;
    var m = 20;
    var xz = d3.range(m),
        yz = d3.range(n).map(function() { return bumps(m); }),
        y01z = d3.stack().keys(d3.range(n))(d3.transpose(yz)),
        yMax = d3.max(yz, function(y) { return d3.max(y); }),
        y1Max = d3.max(y01z, function(y) { return d3.max(y, function(d) { return d[1]; }); });
    var ele = document.createElement('svg');
    var svg = d3.select(ele),
        g = svg.append("g");
    
    var x = d3.scaleBand()
        .domain(xz)
        .rangeRound([0, w])
        .padding(0.08);

    var y = d3.scaleLinear()
        .domain([0, y1Max])
        .range([h, 0]);

    var color = d3.scaleOrdinal()
        .domain(d3.range(n))
        .range(d3.schemeCategory20c);
    
    var series = g.selectAll(".series")
      .data(y01z)
      .enter().append("g")
        .attr("fill", function(d, i) { return color(i); });
    y.domain([0, yMax]);
    var rect = series.selectAll("rect")
      .data(function(d) { return d; })
      .enter().append("rect")
      .attr("fill", function(d, i) { return color(i); })
      .attr("x", function(d, i) { return x(i) + x.bandwidth() / n * this.parentNode.__data__.key; })
        .attr("width", x.bandwidth() / n)
        .attr("y", function(d) { return y(d[1] - d[0]); })
        .attr("height", function(d) { return y(0) - y(d[1] - d[0]); });
    var rects = [];
    rect._groups.forEach((arr) => {
      arr.forEach((item) => {
        rects.push(this.getAttributeObject(item, ['x', 'y', 'width', 'height', 'fill']))
      }) 
    })
    return rects;

    function bumps(m) {
      var values = [], i, j, w, x, y, z;

      // Initialize with uniform random values in [0.1, 0.2).
      for (i = 0; i < m; ++i) {
        values[i] = 0.1 + 0.1 * Math.random();
      }

      // Add five random bumps.
      for (j = 0; j < 5; ++j) {
        x = 1 / (0.1 + Math.random());
        y = 2 * Math.random() - 0.5;
        z = 10 / (0.1 + Math.random());
        for (i = 0; i < m; i++) {
          w = (i / m - y) * z;
          values[i] += x * Math.exp(-w * w);
        }
      }

      // Ensure all values are positive.
      for (i = 0; i < m; ++i) {
        values[i] = Math.max(0, values[i]);
      }

      return values;
    }  
  },
  
  getAttributeObject(item, attrs) {
    var o = {};
    attrs.forEach((attr) => {
      if (item['attributes'][attr]) {
        o[attr] = item['attributes'][attr]['value'];  
      } else {
        o[attr] = ''; 
      }
    });
    return o;
  }
};
