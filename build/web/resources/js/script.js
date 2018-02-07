var type = document.getElementById("type");
var size = document.getElementById("size");
var pgrid = document.getElementById("pgrid");

function checkType() {
    //if (type.value === "2") {
    var target = document.createElement("LABEL");
    target.setAttribute("id", "target");
    target.setAttribute("value", "Target Hosts");
    pgrid.appendChild(target);
    //}
}
