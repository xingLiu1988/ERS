function sendImbursement() {
    // get all input from user
    let type = document.getElementById("type").value;
    let amount = document.getElementById("amount").value;
    let description = document.getElementById("description").value;
    let userString = sessionStorage.getItem('currentUser');
    let currentUser = JSON.parse(userString);
    let id = currentUser.user_id;
    console.log(type, amount, description, id);

    //convert input as obj
    let obj = {
        type: type,
        amount: amount,
        description: description,
        user_id: id
    }

    // ajax to reimburse end point
    let xhr = new XMLHttpRequest();


    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            alert('成功发送reimburse到后台');
            window.location = 'http://localhost:8080/ERS/html/employee.html';
        }
    }

    xhr.open('post', 'http://localhost:8080/ERS/reimbursements');

    xhr.send(JSON.stringify(obj));

}