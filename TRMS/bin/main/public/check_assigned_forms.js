
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
    let xhr = new XMLHttpRequest();
    const url = "http://localhost:9090/form/assigned";
    //sets up ready state handler
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {

            case 0:
                console.log("nothing, initalized not sent");
                break;
            case 1:
                console.log("connection established");
                break;
            case 2:
                console.log("request sent");
                break;
            case 3:
                console.log("waiting response");
                break;
            case 4:
                console.log("FINISHED!!!!!!!!!!!");
                //logic to add form to table
                console.log(xhr.status);
                if (xhr.status === 200) {
                    console.log("xhr.responceText: " + xhr.responseText);
                    let formList = JSON.parse(xhr.responseText);
                    console.log(formList);
                    console.log(formList[0]);
                    formList.forEach(element => {
                        addRow(element);
                    });
                }
                break;

        }
    }

    //opens up the request
    xhr.open("GET", url, true);
    //sends request
    xhr.send();

}

let addRow = function (myForm) {
    let table = document.getElementById("form-table");
    let tableRow = document.createElement("tr");
    let employeeCol = document.createElement("td");
    let typeCol = document.createElement("td");
    let descriptionCol = document.createElement("td");
    let reimbursmentCol = document.createElement("td");
    let justificationCol = document.createElement("td");
    let gradingCol = document.createElement("td");
    let hoursMissedCol = document.createElement("td");
    let approveButtonCol = document.createElement("td");
    let rejectButtonCol = document.createElement("td");
    let requestButtonCol = document.createElement("td");
    
    // let approveBtn = document.createElement('input');
    // approveBtn.type = "button";
    // approveBtn.id = "approve";
    // approveBtn.innerHTML = "Approve";


    tableRow.appendChild(employeeCol);
    tableRow.appendChild(typeCol);
    tableRow.appendChild(descriptionCol);
    tableRow.appendChild(reimbursmentCol);
    tableRow.appendChild(justificationCol);
    tableRow.appendChild(gradingCol);   
    tableRow.appendChild(hoursMissedCol);
    tableRow.appendChild(approveButtonCol);
    tableRow.appendChild(rejectButtonCol);
    tableRow.appendChild(requestButtonCol);
    table.appendChild(tableRow);

    employeeCol.innerHTML = myForm.employeeId;
    typeCol.innerHTML = myForm.eventType;
    descriptionCol.innerHTML = myForm.eventDescription;
    reimbursmentCol.innerHTML = myForm.reimbursmentAmount;
    justificationCol.innerHTML = myForm.justification;
    gradingCol.innerHTML = myForm.gradingFormat;
    hoursMissedCol.innerHTML = myForm.hoursMissed;
    approveButtonCol.innerHTML = '<button class="approve">Approve</button>';
    rejectButtonCol.innerHTML = '<button class="reject">Reject</button>';
    requestButtonCol.innerHTML = '<button class="request">Request More Info</button>';
 
    employeeCol.className = "table_style_col";
    typeCol.className = "table_style_col";
    descriptionCol.className = "table_style_col";
    reimbursmentCol.className = "table_style_col";
    justificationCol.className = "table_style_col";
    gradingCol.className = "table_style_col";
    hoursMissedCol.className = "table_style_col";
    approveButtonCol.className = "table_style_col";
    rejectButtonCol.className = "table_style_col";
    requestButtonCol.className = "table_style_col";
}