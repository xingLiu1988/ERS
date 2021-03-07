function sendLogin() {
    // get type of user
    let type = document.querySelectorAll(".typeOfUser");
    let typeOfUser = '';
    for (let i = 0; i < type.length; i++) {
        if (type[i].checked) {
            typeOfUser = type[i].value;
        }
    }

    // get username
    let username = document.querySelector("#username").value;

    // get password
    let password = document.querySelector("#password").value;

    // validate username and password
    if (username === "" || password === "") {
        return false;
    }

    // convert to object
    let user = {
        typeOfUser: typeOfUser,
        username: username,
        password: password
    }

    // ajax
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            sessionStorage.setItem('currentUser', this.responseText)
            if (typeOfUser === 'employee') {
                window.location = "http://localhost:8080/ERS/html/employee.html";
            }
            if (typeOfUser === 'manager') {
                window.location = "http://localhost:8080/ERS/html/manager.html";
            }
        }
        if (this.readyState === 4 && this.status === 204) {
            document.getElementById('warningText').textContent = "Failed to Login";
        }
    }
    
    xhr.open("POST", "http://localhost:8080/ERS/login");

    xhr.send(JSON.stringify(user));
}