
window.onload = function () {
    console.log("debugCookie: " + document.cookie);

    // insert form id into url in form on dom
    let reimbursmentForm = document.getElementById("reimbursementForm");
    reimbursmentForm.action = "/form/grade/" + localStorage.formToEdit;

    //AJAX - Asynchronous JavaScript and XML
    //Initialize xhr object
    let xhr = new XMLHttpRequest();
    const url = "http://localhost:9090/form/" + localStorage.formToEdit;
    //sets up ready state handler
    xhr.onreadystatechange = function () {
        console.log(xhr.readyState);
        switch (xhr.readyState) {
            case 4:
                console.log("FINISHED!!!!!!!!!!!");
                //logic to add form to table
                console.log(xhr.status);
                if (xhr.status === 200) {
                    console.log("xhr.responceText: " + xhr.responseText);
                    let thisForm = JSON.parse(xhr.responseText);
                    console.log(thisForm);

                    // display form number to user on dom
                    let displayFormId = document.getElementById("form-id");
                    displayFormId.innerHTML = thisForm.formId;

                    addRow(thisForm);
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
    let eventAttach = document.createElement("td");
    let eventAttachImg = document.createElement("img")
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
    tableRow.appendChild(eventAttach);
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

    eventAttachImg.src = "data:image/png;base64," + myForm.eventAttach;
    eventAttachImg.alt = "No attachment Found";
    eventAttachImg.style = "width:200px";
    eventAttach.appendChild(eventAttachImg);

    hoursMissedCol.innerHTML = myForm.hoursMissed;
    supervisalApprovalCol.innerHTML = myForm.supervisorApproved;
    depHeadApprovalCol.innerHTML = myForm.depHeadApproved;
    benCoApprovalCol.innerHTML = myForm.benCoApproved;
    if(myForm.rejReason != null){
        statusCol.innerHTML = myForm.status + "\nReason: " + myForm.rejReason;
    } else{
        statusCol.innerHTML = myForm.status;
    }
    // rejectionStatusCol.innerHTML = myForm.rejected;
    // rejectionReasonCol.innerHTML = myForm.rejReason;
}