
function targetHostRequired() {
    if (document.getElementById("targetHosts").readOnly === false) {
        return true;
    }
}

function targetSwitch() {
    if (document.getElementById("shareType").value === "NFS") {
        document.getElementById("targetHosts").disabled = false;
    } else {
        document.getElementById("targetHosts").value = null;
        document.getElementById("targetHosts").disabled = true;
    }
}
