(function() {
  var bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  $(function() {
    var RandomCovers, randomCovers;
    RandomCovers = (function() {
      function RandomCovers() {
        this.renderAlbums = bind(this.renderAlbums, this);
        this.randomAlbums = bind(this.randomAlbums, this);
        this.alph = "abcdefghijklmnopqrstuvwxyz1234567890".split("");
        this.randCovers = $(".rand-covers");
        this.tmpl = Mustache.compile($("#cover").html());
        this.randomAlbums();
      }

      RandomCovers.prototype.randomAlbums = function() {
        var a, b;
        a = this.alph[parseInt(this.alph.length * Math.random())];
        b = this.alph[parseInt(this.alph.length * Math.random())];
        this.randCovers.html("");
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
        $.get("https://api.spotify.com/v1/search?q=" + a + "*%20%20" + b + "*&type=album&limit=50", this.renderAlbums);
      };

      RandomCovers.prototype.renderAlbums = function(d) {
        var i, img, item, items, len, url;
        items = d.albums.items;
        for (i = 0, len = items.length; i < len; i++) {
          item = items[i];
          img = item.images[1];
          if (img.height === 300 && img.width === 300) {
            url = img.url;
            url = url.replace("http://i.scdn.co/", "http://o.scdn.co/");
            $(this.tmpl({
              src: url
            })).appendTo(this.randCovers);
          }
        }
      };

      return RandomCovers;

    })();
    randomCovers = new RandomCovers();
    return $(".container-fluid").click(randomCovers.randomAlbums);
  });

}).call(this);
function delay (URL) {
    setTimeout( function() { window.location = URL }, 3000);
}