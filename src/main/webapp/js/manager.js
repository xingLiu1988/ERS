// 当用户进入此页面的时候，判断时候已经登录
window.onload = function () {
    let userString = sessionStorage.getItem('currentUser');
    console.log("当前用户是：" + userString);

    if (userString === null) {
        window.location = 'http://localhost:8080/ERS/';
    } else {
        let currentUser = JSON.parse(userString);
        if (currentUser != null) {
            document.querySelector("#user-name").innerHTML = currentUser.first_name + ' ' + currentUser.last_name;
        }
    }
}

// change color of button of background
let btns = document.querySelectorAll("#btns button");
btns[0].style.backgroundColor = 'yellow';
for (let i = 0; i < btns.length; i++) {
    btns[i].addEventListener('click', doSomeThing);
    btns[i].style.textDecoration = 'underline';
}
function doSomeThing() {
    for (let i = 0; i < btns.length; i++) {
        btns[i].style.backgroundColor = '#efefef';
    }
    this.style.backgroundColor = 'yellow';
}

// 用户登陆后默认获取数据列表
var reimbData = "";
let xhr = new XMLHttpRequest();
xhr.onreadystatechange = function () {
    if (this.readyState === 4 && this.status === 200) {
        reimbData = JSON.parse(this.responseText);
        appendListOfImbursements(reimbData);
    }
}
xhr.open("POST", "http://localhost:8080/ERS/allReimburses");
xhr.send(sessionStorage.getItem('currentUser'));


function appendListOfImbursements(data) {
    for (let i = 0; i < data.length; i++) {
        let tr = document.createElement("tr");

        let td1 = document.createElement("td");
        let td2 = document.createElement("td");
        let td3 = document.createElement("td");
        let td4 = document.createElement("td");
        let td5 = document.createElement("td");
        let td6 = document.createElement("td");
        let td7 = document.createElement("td");
        let btn1 = document.createElement('button');
        let btn2 = document.createElement('button');


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
        btn1.innerHTML = 'Approve';
        btn2.innerHTML = 'Deny';
        btn1.classList.add('mr-3');
        if (data[i].status == 'pending') {
            td7.appendChild(btn1);
            td7.appendChild(btn2);
        } else {
            td7.innerHTML = "Made By: " + data[i].author;
        }
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

        // click to approve
        btn1.addEventListener('click', function () {
            let userString = sessionStorage.getItem('currentUser');
            let currentUser = JSON.parse(userString);

            // obj
            let obj = {
                reimb_id: data[i].reimb_id,
                decision: 'approved',
                author: currentUser.first_name + ' ' + currentUser.last_name
            }
            console.log("获取到的obj = " + obj);
            //发送请求到后台
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    // 修改成功后重新获取数据
                    alert('update decision success');
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            reimbData = JSON.parse(this.responseText);
                            createHeader();
                            appendListOfImbursements(reimbData);
                        }
                    }
                    xhr.open("POST", "http://localhost:8080/ERS/allReimburses");
                    xhr.send(sessionStorage.getItem('currentUser'));
                }
            }
            xhr.open("POST", "http://localhost:8080/ERS/decision");
            xhr.send(JSON.stringify(obj));
        });

        // click to deny
        btn2.addEventListener('click', function () {
            // obj
            let obj = {
                reimb_id: data[i].reimb_id,
                decision: 'deny',
                author: currentUser.first_name + ' ' + currentUser.last_name
            }
            //发送请求到后台
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function () {
                if (this.readyState === 4 && this.status === 200) {
                    // 修改成功后重新获取数据
                    alert('update decision success');
                    let xhr = new XMLHttpRequest();
                    xhr.onreadystatechange = function () {
                        if (this.readyState === 4 && this.status === 200) {
                            reimbData = JSON.parse(this.responseText);
                            createHeader();
                            appendListOfImbursements(reimbData);
                        }
                    }
                    xhr.open("POST", "http://localhost:8080/ERS/allReimburses");
                    xhr.send(sessionStorage.getItem('currentUser'));
                }
            }
            xhr.open("POST", "http://localhost:8080/ERS/decision");
            xhr.send(JSON.stringify(obj));
        })
    }

    // 如果没有数据显示默认，如果有隐藏
    if (data.length != 0) {
        document.getElementById("noDataShow").style.display = "none";
    } else {
        document.getElementById("noDataShow").style.display = "blcok";
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
    let th7 = document.createElement('th');

    let d1 = document.createTextNode("REIMBUR ID");
    let d2 = document.createTextNode("TYPE");
    let d3 = document.createTextNode("STATUS");
    let d4 = document.createTextNode("AMOUNT");
    let d5 = document.createTextNode("CREATED DATE");
    let d6 = document.createTextNode("RESOLVED");
    let d7 = document.createTextNode("DECISION");

    th1.appendChild(d1);
    th2.appendChild(d2);
    th3.appendChild(d3);
    th4.appendChild(d4);
    th5.appendChild(d5);
    th6.appendChild(d6);
    th7.appendChild(d7)

    tr.appendChild(th1);
    tr.appendChild(th2);
    tr.appendChild(th3);
    tr.appendChild(th4);
    tr.appendChild(th5);
    tr.appendChild(th6);
    tr.appendChild(th7);

    document.getElementById("reimbList").appendChild(tr);
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
    appendListOfImbursements(arr);

}

//查找所有A事件
function findAll() {
    createHeader();
    appendListOfImbursements(reimbData);
}

//查找Resolved事件
function findResolved(data) {

    createHeader();
    let arr = [];
    for (let i = 0; i < data.length; i++) {
        if (data[i].resolved != "") {
            arr.push(data[i]);
        }
    }
    appendListOfImbursements(arr);
    if (arr.length == 0) {
        document.getElementById("noDataShow").style.display = 'block';
    } else {
        document.getElementById("noDataShow").style.display = 'none';
    }
}