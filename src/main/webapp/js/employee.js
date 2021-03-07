window.onload = function(){
    let userString = sessionStorage.getItem('currentUser');

    if(userString === null) {
        window.location = 'http://localhost:8080/ERS/';
    }else{
        let currentUser = JSON.parse(userString);
        if(currentUser != null) {
            document.querySelector("#user-name").innerHTML = currentUser.first_name + ' ' + currentUser.last_name;
        }
    }
}

