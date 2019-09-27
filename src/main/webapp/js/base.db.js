// const firebase = require('firebase');
//this is the controller
//initialising the firebase connection
var app = firebase.initializeApp({
    apiKey: "AIzaSyBgiU018OFcZn-eV2rRBR-zSG1yZ0VZXes",
    authDomain: "online-auction-system-8c033.firebaseapp.com",
    databaseURL: "https://online-auction-system-8c033.firebaseio.com",
    projectId: "online-auction-system-8c033",
    storageBucket: "online-auction-system-8c033.appspot.com",
    messagingSenderId: "384713657821",
    appId: "1:384713657821:web:1b299fa83e19757c"
});

//firebase live database is the model
var db = app.database();
