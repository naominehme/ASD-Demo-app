function checkForNotifications() {
    stompClient.send("/app/notificationListener", {}, "{}");
}

// Initialise socket connection and stompclient
var socket = new SockJS('/ws');
var stompClient = Stomp.over(socket);
stompClient.connect({}, function (frame) {
   console.log('Connected: ' + frame);
   stompClient.subscribe('/user/topic/notifications', async function (reply) {
       checkForNotifications();
   });
   checkForNotifications();
});

function sleep(ms) {
  return new Promise(resolve => setTimeout(resolve, ms));
}