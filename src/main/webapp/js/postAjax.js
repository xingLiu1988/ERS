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
// xhr.open("POST", "http://localhost:8080/ERS/login");

xhr.send(currentUser);

function appendListOfImbursement(data) {
    console.log(data);
    for(let i = 0; i < data.length; i++) {
        let tr = document.createElement("tr");
        let td1 = document.createElement("td");
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
        if(data[i].resolved === null) {
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
    if(data.length != 0) {
        document.getElementById("noDataShow").style.display = "none";
    }else{
        document.getElementById("noDataShow").style.display = "blcok";
    }

}

//给3个查找All, Pending, Resolved添加点击切换背景事件
let btns = document.querySelectorAll("#btns button");
for(let i = 0; i < btns.length; i++){
    btns[i].addEventListener('click', doSomeThing);
}
function doSomeThing(){
    for(let i = 0; i < btns.length; i++){
        btns[i].style.backgroundColor = '#efefef';
    }
    this.style.backgroundColor = 'yellow';
}

//查找所有A事件
function findAll(){
    appendListOfImbursement(reimbData);
}

//查找Pending事件
function findPending(){

}

//查找Resolved事件
function findResolved(){

}
