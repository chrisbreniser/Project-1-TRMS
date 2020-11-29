
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
    // let rejectionStatusCol = document.createElement("td");
    // let rejectionReasonCol = document.createElement("td");


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
    // tableRow.appendChild(rejectionStatusCol);
    // tableRow.appendChild(rejectionReasonCol);
    table.appendChild(tableRow);

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
    // rejectionStatusCol.innerHTML = myForm.rejected;
    // rejectionReasonCol.innerHTML = myForm.rejReason;

    employeeCol.className = "table-style";
    typeCol.className = "table-style";
    dateCol.className = "table-style";
    locationCol.className = "table-style";
    descriptionCol.className = "table-style";
    reimbursmentCol.className = "table-style";
    justificationCol.className = "table-style";
    gradingCol.className = "table-style";
    gradeCol.className = "table-style";
    hoursMissedCol.className = "table-style";
    supervisalApprovalCol.className = "table-style";
    depHeadApprovalCol.className = "table-style";
    benCoApprovalCol.className = "table-style";
    // rejectionStatusCol.className = "table-style";
    // rejectionReasonCol.className = "table-style";
}