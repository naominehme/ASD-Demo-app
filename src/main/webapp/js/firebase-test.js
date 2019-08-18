// const firebase = require('firebase');

//this is the controller

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

function addUser(username,password){
	return db.ref('/Users').push({
		username: username,
		password: password
	})
}

async function login(username, password){
	var users = await getUsers();
	return new Promise((resolve, reject) => {
		users.forEach(user => {
			if(user.username === username && user.password === password){
				resolve(user);
			}
		})
		resolve(false);
	})
}

async function getUsers(){
	return new Promise((resolve, reject) => {
		var users = [];
		db.ref('/Users').once('value', (snap) => {
			snap.forEach(user => {
				var userObj = user.val();
				userObj.key = user.key;
				users.push(userObj);
			})
			resolve(users);
		})
		
	})
}

async function handleLogin(){
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	// clearScreen()
	var auth = await login(username,password);
	if(!!auth){
		console.log(auth);
		document.getElementById('message').innerHTML = "User Found: \n" + JSON.stringify(auth);
	} else {
		console.log('%c Not Found', 'font-size:20px;color:red;')
		document.getElementById('message').innerHTML = 'User Not Found';
	}
}

async function handleRegister(){
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	clearScreen()
	addUser(username,password).then(e => {
		document.getElementById('message').innerHTML = 'User Added';
	});
	
}

function clearScreen(){
	document.getElementById('username').value = '';
	document.getElementById('password').value = '';
}
