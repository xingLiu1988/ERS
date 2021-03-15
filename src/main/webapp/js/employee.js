// 当用户进入此页面的时候，判断时候已经登录
window.onload = function () {
    let userString = sessionStorage.getItem('currentUser');

    if (userString === null) {
        window.location = 'http://localhost:8080/ERS/';
    } else {
        let currentUser = JSON.parse(userString);
        if (currentUser != null) {
            document.querySelector("#user-name").innerHTML = currentUser.first_name + ' ' + currentUser.last_name;
        }
    }
}

// 当用户点击添加reimbursement的时候跳转页面到reimburse页面
function addReimbursement() {
    window.location = "http://localhost:8080/ERS/html/reimburse.html";
}

// 用户登陆后默认获取数据列表
var reimbData = "";
let xhr = new XMLHttpRequest();

let currentUser = sessionStorage.getItem('currentUser');

xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        reimbData = JSON.parse(this.responseText);
        appendListOfImbursement(reimbData);
    }
}

xhr.open("POST", "http://localhost:8080/ERS/reimbOfCurrent");

xhr.send(currentUser);

function appendListOfImbursement(data) {
    for (let i = 0; i < data.length; i++) {
        let tr = document.createElement("tr");
        let td1 = document.createElement("td");
        td1.classList.add('first-data');
        td1.addEventListener('click', function(){
            sessionStorage.setItem('currentReimbursement', JSON.stringify(data[i]));
            window.location = "http://localhost:8080/ERS/html/details.html";
        });
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");

        // 获取每个数据
        let reimb_id = document.createTextNode(data[i].reimb_id);
        let amount = Number(data[i].amount).toFixed(2);
        let amountValue = document.createTextNode('$' + amount);
        let submitted = document.createTextNode(data[i].submitted);
        let type = document.createTextNode(data[i].type);
        let status = document.createTextNode(data[i].status);
        if (data[i].resolved === null) {
            data[i].resolved = '';
        }
        let resolved = document.createTextNode(data[i].resolved);

        // 添加数据到td
        td1.appendChild(reimb_id);
        td2.appendChild(type);
        td3.appendChild(status);
        td4.appendChild(amountValue);
        td5.appendChild(submitted);
        td6.appendChild(resolved);

        // 添加数据到tr
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);

        // 添加数据到table
        document.getElementById("reimbList").appendChild(tr);
    }

    // 如果没有数据显示默认，如果有隐藏
    if (data.length != 0) {
        document.getElementById("noDataShow").style.display = "none";
    } else {
        document.getElementById("noDataShow").style.display = "blcok";
    }

}

//给3个查找All, Pending, Resolved添加点击切换背景事件
let btns = document.querySelectorAll("#btns button");
btns[0].style.backgroundColor = 'yellow';
for (let i = 0; i < btns.length; i++) {
    btns[i].addEventListener('click', doSomeThing);
}
function doSomeThing() {
    for (let i = 0; i < btns.length; i++) {
        btns[i].style.backgroundColor = '#efefef';
    }
    this.style.backgroundColor = 'yellow';
}

//查找所有A事件
function findAll() {
    createHeader();
    appendListOfImbursement(reimbData);
}

//查找Pending事件
function findPending(data) {
    createHeader();

    let arr = [];
    for (let i = 0; i < data.length; i++) {
        if (data[i].status == 'pending') {
            arr.push(data[i]);
        }
    }
    appendListOfImbursement(arr);

}

//查找Resolved事件
function findResolved(data) {
    console.log(data);
    //添加头部
    document.getElementById("reimbList").innerHTML = '';
    let tr = document.createElement('tr');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');
    let th4 = document.createElement('th');
    let th5 = document.createElement('th');
    let th6 = document.createElement('th');
    let th7 = document.createElement('th');

    let d1 = document.createTextNode("REIMBUR ID");
    let d2 = document.createTextNode("TYPE");
    let d3 = document.createTextNode("STATUS");
    let d4 = document.createTextNode("AMOUNT");
    let d5 = document.createTextNode("CREATED DATE");
    let d6 = document.createTextNode("RESOLVED");
    let d7 = document.createTextNode("RESOLVED BY");

    th1.appendChild(d1);
    th2.appendChild(d2);
    th3.appendChild(d3);
    th4.appendChild(d4);
    th5.appendChild(d5);
    th6.appendChild(d6);
    th7.appendChild(d7);

    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    tr.appendChild(th5);
    tr.appendChild(th6);
    tr.appendChild(th7);

    document.getElementById("reimbList").appendChild(tr);

    //过滤
    let arr2 = [];
    for (let i = 0; i < data.length; i++) {
        if (data[i].resolved.length > 1) {
            arr2.push(data[i]);
        }
    }
    console.log(arr2);

    //添加资料
    for (let i = 0; i < arr2.length; i++) {
        let tr = document.createElement("tr");

        let td1 = document.createElement("td");
        td1.addEventListener('click', function(){
            sessionStorage.setItem('currentReimbursement', JSON.stringify(arr2[i]));
            window.location = "http://localhost:8080/ERS/html/details.html";
        });
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");
        let td7 = document.createElement("td");

        // 获取每个数据
        let reimb_id = document.createTextNode(arr2[i].reimb_id);
        let amount = Number(arr2[i].amount).toFixed(2);
        let amountValue = document.createTextNode('$' + amount);
        let submitted = document.createTextNode(arr2[i].submitted);
        let type = document.createTextNode(arr2[i].type);
        let status = document.createTextNode(arr2[i].status);
        if (arr2[i].resolved === null) {
            arr2[i].resolved = '';
        }
        let resolved = document.createTextNode(arr2[i].resolved);
        let author = '';
        if (arr2[i].author == null) {
            author = document.createTextNode('');
        } else {
            author = document.createTextNode(arr2[i].author);
        }

        // 添加数据到td
        td1.appendChild(reimb_id);
        td2.appendChild(type);
        td3.appendChild(status);
        td4.appendChild(amountValue);
        td5.appendChild(submitted);
        td6.appendChild(resolved);
        td7.appendChild(author);

        // 添加数据到tr
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);

        // 添加数据到table
        document.getElementById("reimbList").appendChild(tr);
    }





    // createHeader();
    // let arr = [];
    // for (let i = 0; i < data.length; i++) {
    //     if (data[i].resolved != "" ) {
    //         arr.push(data[i]);
    //     }
    // }
    // appendListOfImbursement(arr);
    if (arr2.length == 0) {
        document.getElementById("noDataShow").style.display = 'block';
    } else {
        document.getElementById("noDataShow").style.display = 'none';
    }
}

function createHeader() {
    document.getElementById("reimbList").innerHTML = '';
    let tr = document.createElement('tr');

    let th1 = document.createElement('th');
    let th2 = document.createElement('th');
    let th3 = document.createElement('th');
    let th4 = document.createElement('th');
    let th5 = document.createElement('th');
    let th6 = document.createElement('th');

    let d1 = document.createTextNode("REIMBUR ID");
    let d2 = document.createTextNode("TYPE");
    let d3 = document.createTextNode("STATUS");
    let d4 = document.createTextNode("AMOUNT");
    let d5 = document.createTextNode("CREATED DATE");
    let d6 = document.createTextNode("RESOLVED");

    th1.appendChild(d1);
    th2.appendChild(d2);
    th3.appendChild(d3);
    th4.appendChild(d4);
    th5.appendChild(d5);
    th6.appendChild(d6);

    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    tr.appendChild(th5);
    tr.appendChild(th6);

    document.getElementById("reimbList").appendChild(tr);
}
