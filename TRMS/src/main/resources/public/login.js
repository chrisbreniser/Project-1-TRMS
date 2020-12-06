window.onload = function() {
    var cookies = document.cookie.split(";");

    for (var i = 0; i < cookies.length; i++) {
        var cookie = cookies[i];
        console.log("cookie: " + cookie);
        var eqPos = cookie.indexOf("=");
        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
    }
    
    console.log("debugCookie:" + document.cookie);

    let path =  window.location.search;
    console.log(path);
    let error = document.getElementById("error");
    console.log("Path: " + path);
    if(path != ""){
        error.innerHTML = "Invalid Login, please try again.";
    }
    
 }