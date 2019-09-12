
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

function addUser(username,password,phone,emailaddress,streetname,streetnumber,postcode,state,DOB,fname,lname){
	return db.ref('/Users').push({
		username: username,
		password: password,
		phone: phone,
		emailaddress: emailaddress,
		streetname: streetname,
		streetnumber: streetnumber,
		postcode: postcode,
		state: state,
		DOB: DOB,
		fname: fname,
		lname: lname
	})
}

async function login(username, password){
	var users = await getUsers();
	return new Promise((resolve, reject) => {
		users.forEach(user => {
			if(user.username === username && user.password === password){
				resolve(user);
			}
		});
		resolve(false);
	})
}

function logOut(){
	document.location.pathname = 'login.html';
	var userKey = JSON.parse(localStorage.loggedInUser).key;
	logUser(userKey, 'logout');
	localStorage.loggedInUser = '';
}

async function getUsers(){
	return new Promise((resolve, reject) => {
		var users = [];
		db.ref('/Users').once('value', (snap) => {
			snap.forEach(user => {
				var userObj = user.val();
				userObj.key = user.key;
				users.push(userObj);
			});
			resolve(users);
		})

	})
}

async function handleReset(){
	var username = document.getElementById('username').value;
	var dob = document.getElementById('dob').value;
	var newPassword = document.getElementById('password').value;
	var validKey = await isValidUser(username,dob);
	console.log(validKey);
	if(!!validKey){
		db.ref('/Users/'+validKey).update({password:newPassword});
		showMessage('Password successfully reset')
	} else {
		showMessage('Invalid username or DOB')
	}
}

function isValidUser(username,dob){
	return new Promise((resolve) => {
		db.ref('/Users').once('value', (snap) => {
			snap.forEach(user => {
				var obj = user.val();
				if(obj.username === username && obj.dob === dob){
					resolve(user.key)
				}
			});
			resolve(null);
		})
	})
}

async function handleLogin(){
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var auth = await login(username,password);
	if(!!auth){
		logUser(auth.key,'login');
		localStorage.loggedInUser = JSON.stringify({key: auth.key, fname:auth.fname, emailaddress:auth.emailaddress});
		document.location.pathname = 'index.html'
	}
	else {
		showMessage('User Not Found');
	}
}

function logUser(userKey,logType){
	db.ref('/Users/'+userKey+'/Logs').push({
		logTime: new Date().getTime(),
		logType:logType
	})
}

function isActiveSession(){
	var data = localStorage.loggedInUser;
	return !!data;
}

async function handleRegister(){
	var username = document.getElementById('username').value;
	var password = document.getElementById('password').value;
	var phone = document.getElementById('phone').value;
	var emailaddress = document.getElementById('emailaddress').value;
	var streetname = document.getElementById('streetname').value;
	var streetnumber = document.getElementById('streetnumber').value;
	var postcode = document.getElementById('postcode').value;
	var state = document.getElementById('state').value;
	var DOB = document.getElementById('DOB').value;
	var fname = document.getElementById('fname').value;
	var lname = document.getElementById('lname').value;
	addUser(username,password,phone,emailaddress,streetname,streetnumber,postcode,state,DOB,fname,lname).then(e => {});
}

function clearScreen(){
	document.getElementById('username').value = '';
	document.getElementById('password').value = '';
}

function showMessage(message){
	document.getElementById('message').innerHTML = message;
}