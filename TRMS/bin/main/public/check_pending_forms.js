
window.onload = function () {
    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
    let xhr = new XMLHttpRequest();
    const url = "http://localhost:9090/form/pending";
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

let updateFunds = function (reimbursmentAmount, status) {
    let available = document.getElementById("available-funds");
    let pending = document.getElementById("pending-funds");
    let awarded = document.getElementById("awarded-funds");

    let placeHolder;

    if(status === "pending"){
        placeHolder = parseInt(pending.innerHTML);
        pending.innerHTML = placeHolder + reimbursmentAmount;
        available.innerHTML -= reimbursmentAmount;
    } else if(status === "approved"){
        placeHolder = parseInt(awarded.innerHTML);
        awarded.innerHTML = placeHolder + reimbursmentAmount;
        available.innerHTML -= reimbursmentAmount;
    }
}

let addRow = function (myForm) {
    updateFunds(myForm.reimbursmentAmount, myForm.status);
    let tableBody = document.getElementById("pending-table-body");
    let tableRow = document.createElement("tr");
    let formCol = document.createElement("td");
    let employeeCol = document.createElement("td");
    let typeCol = document.createElement("td");
    let dateCol = document.createElement("td");
    let locationCol = document.createElement("td");
    let descriptionCol = document.createElement("td");
    let reimbursmentCol = document.createElement("td");
    let justificationCol = document.createElement("td");
    let gradingCol = document.createElement("td");
    let gradeCol = document.createElement("td");
    let hoursMissedCol = document.createElement("td");
    let supervisalApprovalCol = document.createElement("td");
    let depHeadApprovalCol = document.createElement("td");
    let benCoApprovalCol = document.createElement("td");
    let statusCol = document.createElement("td");
    // let rejectionStatusCol = document.createElement("td");
    // let rejectionReasonCol = document.createElement("td");

    tableRow.appendChild(formCol);
    tableRow.appendChild(employeeCol);
    tableRow.appendChild(typeCol);
    tableRow.appendChild(dateCol);
    tableRow.appendChild(locationCol);
    tableRow.appendChild(descriptionCol);
    tableRow.appendChild(reimbursmentCol);
    tableRow.appendChild(justificationCol);
    tableRow.appendChild(gradingCol);
    tableRow.appendChild(gradeCol);
    tableRow.appendChild(hoursMissedCol);
    tableRow.appendChild(supervisalApprovalCol);
    tableRow.appendChild(depHeadApprovalCol);
    tableRow.appendChild(benCoApprovalCol);
    tableRow.appendChild(statusCol);
    // tableRow.appendChild(rejectionStatusCol);
    // tableRow.appendChild(rejectionReasonCol);
    tableBody.appendChild(tableRow);

    formCol.innerHTML = myForm.formId;
    employeeCol.innerHTML = myForm.employeeId;
    typeCol.innerHTML = myForm.eventType;
    dateCol.innerHTML = myForm.eventDate;
    locationCol.innerHTML = myForm.eventLocation;
    descriptionCol.innerHTML = myForm.eventDescription;
    reimbursmentCol.innerHTML = myForm.reimbursmentAmount;
    justificationCol.innerHTML = myForm.justification;
    gradingCol.innerHTML = myForm.gradingFormat;
    gradeCol.innerHTML = myForm.grade;
    hoursMissedCol.innerHTML = myForm.hoursMissed;
    supervisalApprovalCol.innerHTML = myForm.supervisorApproved;
    depHeadApprovalCol.innerHTML = myForm.depHeadApproved;
    benCoApprovalCol.innerHTML = myForm.benCoApproved;
    statusCol.innerHTML = myForm.status;
    // rejectionStatusCol.innerHTML = myForm.rejected;
    // rejectionReasonCol.innerHTML = myForm.rejReason;

    // employeeCol.className = "table_style_col";
    // typeCol.className = "table_style_col";
    // dateCol.className = "table_style_col";
    // locationCol.className = "table_style_col";
    // descriptionCol.className = "table_style_col";
    // reimbursmentCol.className = "table_style_col";
    // justificationCol.className = "table_style_col";
    // gradingCol.className = "table_style_col";
    // gradeCol.className = "table_style_col";
    // hoursMissedCol.className = "table_style_col";
    // supervisalApprovalCol.className = "table_style_col";
    // depHeadApprovalCol.className = "table_style_col";
    // benCoApprovalCol.className = "table_style_col";
    // rejectionStatusCol.className = "table_style_col";
    // rejectionReasonCol.className = "table_style_col";
}