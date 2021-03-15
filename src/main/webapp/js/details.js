getSingleReimburse();

function getSingleReimburse(){
    let data = sessionStorage.getItem('currentReimbursement');
    let reimburse = JSON.parse(data);
    console.log(reimburse);

    document.getElementById('u_id').innerHTML = reimburse.user_id;
    document.getElementById('r_id').innerHTML = reimburse.reimb_id;
    document.getElementById('type').innerHTML = reimburse.type;
    document.getElementById('amount').innerHTML = '$' +Number.parseFloat(reimburse.amount).toFixed(2);
    document.getElementById('status').innerHTML = reimburse.status;
    document.getElementById('description').innerHTML = reimburse.description;
    document.getElementById('sub_date').innerHTML = reimburse.submitted;
    document.getElementById('dec_by').innerHTML = reimburse.author;
    document.getElementById('res_date').innerHTML = reimburse.resolved;
}