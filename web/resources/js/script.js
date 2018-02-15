
function targetHostRequired() {
    if (document.getElementById("targetHosts").readOnly === false) {
        return true;
    }
}

function targetSwitch() {
    if (document.getElementById("shareType").value === "2") {
        document.getElementById("targetHosts").readOnly = false;
    } else {
        document.getElementById("targetHosts").readOnly = true;
    }
}
