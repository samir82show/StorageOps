
function targetHostRequired() {
    if (document.getElementById("targetHosts").readOnly === false) {
        return true;
    }
}

function targetSwitch() {
    if (document.getElementById("shareType").value === "NFS") {
        document.getElementById("targetHosts").disabled = false;
        document.getElementById("targetHosts").style.background = "#cccccc";
        document.getElementById("targetHosts").required = true;
    } else {
        document.getElementById("targetHosts").value = null;
        document.getElementById("targetHosts").disabled = true;
        document.getElementById("targetHosts").style.background = "#044344";
        document.getElementById("targetHosts").required = false;
    }
}
