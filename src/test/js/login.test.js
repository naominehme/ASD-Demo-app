var $Test = function(){
    this.desc = '';
    this.whenI = function(whenI){
        this.desc = whenI;
        return this;
    };
    this.lastData = null;
    this.lastResult = null;
    this.lastUsing = null;
    this.expected = null;
    this.using = function(callback, args){
        this.lastUsing = callback;
        if(!!args){
            this.lastData = callback(arguments[1],arguments[2],arguments[3]);
        } else {
            this.lastData = callback();
        }
        return this;
    };
    this.IShouldGet = function(value,args){
        if(typeof value !== 'function'){
            this.expected = value;
            this.lastResult = value === this.lastData;
            return this;
        } else if (arguments.length == 1 || argument.length == 0){
            this.expected = value(this.lastData);
            this.lastResult = this.expected === true;
            return this;
        } else {
            this.expected = value(args[1], args[2], args[3]);
            this.lastResult = this.expected === true;
            return this;
        }
    };
    this.toBool = function(){
        return this.lastResult === true;
    };
    this.andThen = function(value,args){
        if(typeof value !== 'function'){
            this.expected = value;
            this.lastResult = value === this.lastData;
            return this;
        } else if (arguments.length == 1 || argument.length == 0){
            this.expected = value(this.lastResult);
            this.lastResult = this.expected === true;
            return this;
        } else {
            this.expected = value(this.lastResult,args[1], args[2], args[3]);
            this.lastResult = this.expected === true;
            return this;
        }
    };
    this.print = function(){
        if(this.lastResult === true){
            console.log('OPERATIONAL: When you ' + this.desc + ' using:', this.using )
        } else {
            console.log('FAILED: When you ' + this.desc + ' using:', this.using );
            console.log('EXPECTED: ' + this.expected + '\t ACTUAL: ' + this.lastData)
        }
    };
};
var Core = {};
var sessionsPopulatedTest = new $Test;
Core.getSessions = function(){
    return [1,2,3]
};
sessionsPopulatedTest
    .whenI('Check if user can login with correct details')
    .using(Core.Login, 'naomi12', 'password123')
    .IShouldGet((user) => {
        return !!user.username && !!user.password
    })
    .print();
getQuizFromSessionTest
    .whenI('Check for valid user with DOB and username')
    .using(Core.isValidUser, 'naomi12', '20/10/1999')
    .IShouldGet((user) => {
        return !!user.username && !!user.DOB
    })
    .print();
