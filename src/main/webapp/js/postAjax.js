var reimbData = "";
let xhr = new XMLHttpRequest();

let currentUser = sessionStorage.getItem('currentUser');

xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        reimbData = JSON.parse(this.responseText);
        appendListOfImbursement(reimbData);
    }
}

xhr.open("POST", "http://localhost:8080/ERS/reimbursements");

xhr.send(currentUser);

function appendListOfImbursement(data) {
    for(let i = 0; i < data.length; i++) {
        let tr = document.createElement("tr");
        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");

        // 获取每个数据
        let reimb_id = document.createTextNode(data[i].reimb_id);
        let amount = document.createTextNode(data[i].amount);
        let submitted = document.createTextNode(data[i].submitted);
        let type = document.createTextNode(data[i].type);
        let status = document.createTextNode(data[i].status);

        // 添加数据到td
        td1.appendChild(reimb_id);
        td2.appendChild(type);
        td3.appendChild(status);
        td4.appendChild(amount);
        td5.appendChild(submitted);

        // 添加数据到tr
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);

        // 添加数据到table
        document.getElementById("reimbList").appendChild(tr);
    }

    // 如果没有数据显示默认，如果有隐藏
    if(data.length != 0) {
        document.getElementById("noDataShow").style.display = "none";
    }else{
        document.getElementById("noDataShow").style.display = "blcok";
    }
}