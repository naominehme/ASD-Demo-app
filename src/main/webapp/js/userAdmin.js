    new Vue({
        el: '#userApp',
        data() {
            return {
                userlist: [],
                username: '',
                a: 6615,
                form: {
                    DOB: "",
                    username: "",
                    state: "",
                    password: "",
                    phone: "",
                    postcode: "",
                    streetname: "",
                    streetnumber: "",
                    emailaddress: "",
                    active: "",
                    admin: "0",
                }
            }
        },
        methods: {
            ser() {
                var valiii = this.username || '';
                if (valiii) {
                    db.ref('/Users').orderByChild('username').equalTo(this.username).once('value').then(res => {
                        const users = []
                        res.forEach(user => {
                            users.push({
                                ...user.val(),
                                userkey: user.key
                            });
                        });
                        this.userlist = users
                    })
                } else {
                    this.getAll()
                }
            },
            getAll: function(message) {
                db.ref('/Users').once('value').then(res => {
                    const users = []
                    res.forEach(user => {
                        users.push({
                            ...user.val(),
                            userkey: user.key
                        });
                    });
                    this.userlist = users
                })
            },
            delItem(row) {
                db.ref('/Users/' + row.userkey).remove(res => {
                    if (!res) {
                        this.getAll();
                    }
                })
            },
            change(row) {
                db.ref('/Users/' + row.userkey).update({ active: !row.active }, res => {
                    if (!res) {
                        this.getAll();
                    }
                });
            },
            adduser() {
                var fg = false;
                for (obj in this.form) {
                    if (!this.form[obj]) {
                        fg = true;
                    }
                }
                if (fg) {
                    alert("value not emty")
                    return false
                }
                db.ref('/Users').push(this.form).then(res => {
                    if (res.key) {
                        this.form = {}
                        this.getAll();
                    }
                })
            }
        },
        created() {
            this.getAll();
        }

    })
