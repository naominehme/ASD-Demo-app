var populateWatchlistPropertyItems = function(elementID, customerID) {
    if (customerID == null) return;
    $.get('/getWatchlistPropertyItems?customerID='+ customerID, function(data) {
        content = ""
        for(i=0; i<data.length; i++){
            content += '<div class="content-watchlist-item">'
            content += '<img border="0" alt="" src="https://www.rawsonhomes.com.au/-/media/rawson-homes/home-designs/bronte/facades/hi-res-bronte-hamptons-2168x1355.ashx?thn=0&w=1016&hash=96A840845863F5C11C9429D577F0F76C" width="100" height="100">';
            content += "<p>" + data[i].propertyID + "</p>"
            content += "<i>Added to Watchlist on " + new Date(data[i].createdDate).toLocaleDateString() + " at " + new Date(data[i].createdDate).toLocaleTimeString() + "</i>"
            content += '<a class="remove-watchlist-item" href="removePropertyFromWatchlist?propertyID=' + data[i].propertyID + '&customerID=' + customerID +'">Remove from watchlist</a>'
            content += "</div>"
        }
        $(elementID).append(content);
     });
}

var populateWatchlistPropertyPreferences = function(elementID, customerID) {
    if (customerID == null) return;
    var preferenceCount = 1;
    $.get('/getWatchlistPropertyPreferences?customerID='+ customerID, function(data) {
        content = ""
        for(i=0; i<data.length; i++){
            content += '<div class="content-watchlist-preference">'
            content += "<h3>Preference " + preferenceCount + "</h3>"
            content += '<img border="0" alt="' + data[i].typeID + '" src="' + mapPropertyTypeToImage(data[i].typeID) + '" width="100" height="100">';
            content += "<p>Property Type: " + data[i].typeID + "</p>"
            content += "<p>Post Code: " + data[i].postCode + "</p>"
            content += "<p>Bedrooms: " + data[i].numOfBedrooms + "</p>"
            content += "<p>Bathrooms: " + data[i].numOfBathrooms + "</p>"
            content += "<p>Garage Spaces: " + data[i].garageSpaces + "</p>"
            content += '<a class="remove-watchlist-item" href="removePropertyPreferencesFromWatchlist?preferenceID=' + data[i].preferenceID + '&customerID=' + customerID +'">Remove from watchlist</a>'
            content += "</div>"
            preferenceCount += 1;
        }
        $(elementID).append(content);
    });
}

var submitAddPropertyForm = function(elementID) {
    $.get('/addPropertyToWatchlist?customerID=' + $("#propertyAddCustomerID")[0].value +
    '&propertyID=' + $("#propertyAddPropertyID")[0].value, function(data) {
        $(elementID)[0].innerText = data;
    });
}

submitAddPropertyPreferenceForm  = function(elementID) {
    $.get('/addPropertyPreferenceToWatchlist?customerID=' + $("#propertyAddPreferenceCustomerID")[0].value +
    '&typeID=' + $("#propertyPreferenceAddTypeID")[0].value +
    '&postCode=' + $("#propertyPreferenceAddPostCode")[0].value +
    '&numOfBedrooms=' + $("#propertyPreferenceAddNumOfBedrooms")[0].value +
    '&numOfBathrooms=' + $("#propertyPreferenceAddNumOfBathrooms")[0].value +
    '&garageSpaces=' + $("#propertyPreferenceAddNumOfGarageSpaces")[0].value, function(data) {
        $(elementID)[0].innerText = data;
    });
}

var mapPropertyTypeToImage = function(typeID) {
    if (typeID == "House") {
        return "http://clipartmag.com/images/house-clipart-images-12.jpg";
    }
    return "http://clipartmag.com/images/apartment-building-clipart-30.png";
}