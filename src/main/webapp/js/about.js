window.onload = function () {
    let userString = sessionStorage.getItem('currentUser');
    if (!userString) {
        window.location = 'http://localhost:8080/ERS/';
    } else {
        let userData = JSON.parse(userString);

        // 未修改桌面
        document.getElementById("userid").innerHTML = userData.user_id;
        document.getElementById("username").innerHTML = userData.username;
        document.getElementById("firstname").innerHTML = userData.first_name;
        document.getElementById("lastname").innerHTML = userData.last_name;
        document.getElementById("email").innerHTML = userData.email;
        document.getElementById("role").innerHTML = userData.user_role.user_role;
        document.getElementById("update-info").setAttribute('display', 'none');

        // 需要修改的桌面
        document.getElementById("u-userid").innerHTML = userData.user_id;
        document.getElementById("u-username").innerHTML = userData.username;
        document.getElementById("u-password").setAttribute('placeholder', "*********");
        document.getElementById("u-firstname").setAttribute('placeholder', userData.first_name);
        document.getElementById("u-lastname").setAttribute('placeholder', userData.last_name);
        document.getElementById("u-email").setAttribute('placeholder', userData.email);
        document.getElementById("u-userRole").setAttribute('placeholder', userData.user_role.user_role);
    }
}

function toggleInfo() {
    document.getElementById("info-table").style.display = 'none';
    document.getElementById("update-info").style.display = 'block';
    document.getElementById("updateBtn").style.display = 'none';
}

function backToHome() {
    
    window.location = 'http://localhost:8080/ERS/html/employee.html';
}

function updateFirstName() {
    let firstname = document.getElementById("u-firstname").value;
    if (firstname == '') {
        return false;
    } else {
        let userString = sessionStorage.getItem('currentUser');
        let currentUser = JSON.parse(userString);

        let obj = {
            type: 'firstname',
            updatedValue: firstname,
            userId: currentUser.user_id
        }
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
               alert("修改成功");
               sessionStorage.setItem('currentUser', this.responseText)
               window.location = "http://localhost:8080/ERS/html/employee.html";
            }
        }
        xhr.open("POST", "http://localhost:8080/ERS/updateInfo");
        xhr.send(JSON.stringify(obj));
    }
}

function updateLastName() {
    let lastname = document.getElementById("u-lastname").value;
    if (lastname == '') {
        return false;
    } else {
        let userString = sessionStorage.getItem('currentUser');
        let currentUser = JSON.parse(userString);

        let obj = {
            type: 'lastname',
            updatedValue: lastname,
            userId: currentUser.user_id
        }
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
               alert("修改成功");
               sessionStorage.setItem('currentUser', this.responseText)
               window.location = "http://localhost:8080/ERS/html/employee.html";
            }
        }
        xhr.open("POST", "http://localhost:8080/ERS/updateInfo");
        xhr.send(JSON.stringify(obj));
    }
}

function updatePassword() {
    let password = document.getElementById("u-password").value;
    if (password == '') {
        return false;
    } else {
        let userString = sessionStorage.getItem('currentUser');
        let currentUser = JSON.parse(userString);

        let obj = {
            type: 'password',
            updatedValue: password,
            userId: currentUser.user_id
        }
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
               alert("修改成功");
               sessionStorage.setItem('currentUser', this.responseText)
               window.location = "http://localhost:8080/ERS/html/employee.html";
            }
        }
        xhr.open("POST", "http://localhost:8080/ERS/updateInfo");
        xhr.send(JSON.stringify(obj));
    }
}

function updateEmail() {
    let email = document.getElementById("u-email").value;
    if (email == '') {
        return false;
    } else {
        let userString = sessionStorage.getItem('currentUser');
        let currentUser = JSON.parse(userString);

        let obj = {
            type: 'email',
            updatedValue: email,
            userId: currentUser.user_id
        }
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
               alert("修改成功");
               sessionStorage.setItem('currentUser', this.responseText)
               window.location = "http://localhost:8080/ERS/html/employee.html";
            }
        }
        xhr.open("POST", "http://localhost:8080/ERS/updateInfo");
        xhr.send(JSON.stringify(obj));
    }
}

function gotoHome(){
    let userString = sessionStorage.getItem('currentUser');
    let currentUser = JSON.parse(userString);
    console.log(currentUser);
    if(currentUser.user_role.user_role == 'employee'){
        window.location = "http://localhost:8080/ERS/html/employee.html";
        console.log(currentUser);
    }else{
        window.location = "http://localhost:8080/ERS/html/manager.html";
        console.log(currentUser);
    }
}