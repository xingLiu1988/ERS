// store all employees
var employees = [];

// get all employees
getEmployees();









// this method used to get all employees from the database
function getEmployees(){
    fetch('http://localhost:8080/ERS/employees')
        .then(res => res.json())
        .then(data => {
            employees = data;
            console.log("接收到所有的员工数据" + employees);
            appendEmployeesToTable(employees);
        });
}

// this method used to add all employees to the table
function appendEmployeesToTable(data){
    // if no employee and display no employee, else list all employees
    if(data.length == 0){
        document.getElementById('noDataShow').style.display = 'block';
    }else{
        document.getElementById('noDataShow').style.display = 'none';
    }

    // add employees to the table
    for(let i = 0; i < data.length; i++){

        let tr = document.createElement('tr');
    
        let td1 = document.createElement('td');
        let td2 = document.createElement('td');
        let td3 = document.createElement('td');
        let td4 = document.createElement('td');
        let td5 = document.createElement('td');
    
        let data1 = document.createTextNode(data[i].user_id);
        let data2 = document.createTextNode(data[i].username);
        let data3 = document.createTextNode(data[i].first_name);
        let data4 = document.createTextNode(data[i].last_name);
        let data5 = document.createTextNode(data[i].email);

        td1.appendChild(data1);
        td2.appendChild(data2);
        td3.appendChild(data3);
        td4.appendChild(data4);
        td5.appendChild(data5);

        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        
        document.getElementById('reimbList').appendChild(tr);
        console.log("add emp" + i + 1);
    }
}