var stompClient = null;

function checkForNotifications() {
  stompClient.send("/app/notificationListener", {}, "{}");
}

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

function createNotification(notification) {
  window.$j.notify({title: '<h4><a href="/homedetail/' + notification.propertyID + '">' +
                  notification.property.title + '</a> has had a new bid placed on it! The new bid is $' + notification.bid.Price + '.</h4>',
             button: 'Dismiss',
             suburb: 'Suburb: ' + notification.property.suburb,
             bedrooms: 'Bedrooms: ' + notification.property.bedroom,
             bathrooms: 'Bathrooms: ' + notification.property.bathroom,
             garageSpaces: 'Garage Spaces: ' + notification.property.garage,
             image: '<img src="' + notification.property.url + '" width="100" height="100"/>',
             viewProperty: "<button style='margin-right: 10px;' onclick='location.href=\"/homedetail/" + notification.propertyID + "\";' class='pure-button button-secondary'>Open Property</button>"},
             { position:"top right", style: "bid" }
  );
}

function playNotificationSound() {
    var audio = new Audio('/sound/beyond-doubt.mp3');
    var promise = audio.play();
    if (promise !== undefined) {
      promise.then(_ => {
          // Playing worked.
      }).catch(error => {
          // Play did not work.
          // Most likely the case that user interaction is required to play sound.
      });
   }
}


// Before creating a socket, check if notifications are enabled
window.$j.get("/notification/preferences/get", function(data){
    if (!data.notificationsEnabled) return;
    // Initialise socket connection and stompclient
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      console.log('Connected: ' + frame);
      stompClient.subscribe('/user/topic/notifications', async function (reply) {
              createNotification(JSON.parse(reply.body));
              checkForNotifications();
              if (!data.soundEnabled) return;
              playNotificationSound();
      });
      checkForNotifications();
    });
});

// Configure notify.js
window.$j.notify.defaults({
    // if autoHide, hide after milliseconds
    autoHideDelay: 30000,
    // default class (string or [string])
    className: 'error',
});
// Add style for bid notifications
window.$j.notify.addStyle('bid', {
  html:
    "<div>" +
      "<div class='content-notification' style='width: 350px;'>" +
        "<p class='title' data-notify-html='title'/><br>" +
        "<div data-notify-html='image'/>" +
        "<div>" +
            "<p data-notify-text='suburb'/>" +
            "<p data-notify-text='bedrooms'/>" +
            "<p data-notify-text='bathrooms'/>" +
            "<p data-notify-text='garageSpaces'/>" +
        "</div>" +
        "<br><br>" +
        "<div class='buttons'>" +
          "<span data-notify-html='viewProperty'/>" +
          "<button class='pure-button pure-button-primary' data-notify-text='button'></button>" +
        "</div>" +
      "</div>" +
    "</div>"
});
