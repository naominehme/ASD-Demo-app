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
            console.log('SUCCESS: When you ' + this.desc + ' using:', this.using )
        } else {
            console.log('FAILED: When you ' + this.desc + ' using:', this.using );
            console.log('EXPECTED: ' + this.expected + '\t ACTUAL: ' + this.lastData)
        }
    };
};
var Core = {};
var login = new $Test;
Core.getSessions = function(){
    return [1,2,3]
};
login
    .whenI('Veirfy Users Exist')
    .using(Core.getSessions)
    .IShouldGet((array) => {
        return array.length > 0;
    })
    .print();

var isValidUser = new $Test;
Core.getSessions = function(){
    return [true]
};
isValidUser
    .whenI('Veirfy the user auth')
    .using(Core.getSessions)
    .IShouldGet((user) => {
        user == user.userame && user ==user.DOB
        return true
    })
    .print();