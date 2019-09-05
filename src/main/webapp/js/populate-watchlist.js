var populateWatchlistPropertyItems = function(elementID, customerID) {
    if (customerID == null) return;
    $.get('/getWatchlistPropertyItems?customerID='+ customerID, function(data) {
        content = ""
        for(i=0; i<data.length; i++){
            content += '<div class="content-watchlist-item">'
            content += '<img border="0" alt="24 Yorkshire Street" src="https://www.rawsonhomes.com.au/-/media/rawson-homes/home-designs/bronte/facades/hi-res-bronte-hamptons-2168x1355.ashx?thn=0&w=1016&hash=96A840845863F5C11C9429D577F0F76C" width="100" height="100">';
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
    $.get('/getWatchlistPropertyPreferences?customerID='+ customerID, function(data) {
        content = ""
        for(i=0; i<data.length; i++){
            content += '<div class="content-watchlist-preference">'
           // content += '<img border="0" alt="24 Yorkshire Street" src="https://www.rawsonhomes.com.au/-/media/rawson-homes/home-designs/bronte/facades/hi-res-bronte-hamptons-2168x1355.ashx?thn=0&w=1016&hash=96A840845863F5C11C9429D577F0F76C" width="100" height="100">';
            content += "<p>" + data[i].preferenceID + "</p>"
            //content += '<a class="remove-watchlist-item" href="removePropertyFromWatchlist?propertyID=' + data[i].propertyID + '&customerID=' + customerID +'">Remove from watchlist</a>'
            content += "</div>"
        }
        $(elementID).append(content);
    });
}