// store all employees
var employees = [];

// get all employees
getEmployees();









// 用于获取所有的员工资料
function getEmployees(){
    fetch('http://localhost:8080/ERS/employees')
        .then(res => res.json())
        .then(data => {
            employees = data;
            console.log("接收到所有的员工数据" + employees);
        });
}

// 用于添加所有员工到
