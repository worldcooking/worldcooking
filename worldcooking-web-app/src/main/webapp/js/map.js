function localizeWithGoogleMap(fullAddress) {
  // localize address
  var geocoder = new google.maps.Geocoder();
  geocoder
      .geocode(
          {
            'address' : fullAddress
          },
          function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
              // location was successfully located
              var location = results[0].geometry.location;
              if (location != null) {
                alert("Address successfully localized: please add the following options to your JSP map tag: latitude=\""
                    + location.Pa + "\" longitude=\"" + location.Qa + "\".");
              }
            } else {
              alert("Geocode was not successful for the following reason: "
                  + status);
            }
          });
}

function displayGoogleMap(mapContainer, name, fullAddress, lat, lng, zoom, icon) {
  
  var latLng = new google.maps.LatLng(lat, lng);
  var mapOptions = {
    zoom : 13,
    center : latLng,
    mapTypeId : google.maps.MapTypeId.ROADMAP
  };
  // create map
  var map = new google.maps.Map(mapContainer, mapOptions);
  
  // add marker
  var marker = new google.maps.Marker({
    map : map,
    position : latLng,
    title : name,
  });
  if (icon != null) {
    // use custom icon for marker
    marker.setIcon(icon);
  }

  return map;
}