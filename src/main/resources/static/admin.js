const API_URL = "/api/admins";

function loadAdmins() {
    const name = document.getElementById("searchName").value;
    let url = API_URL;

    if (name && name.trim() !== "") {
        url += "/search?name=" + encodeURIComponent(name);
    }

    fetch(url)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("adminTable");
            table.innerHTML = "";

            for (let i = 0; i < data.length; i++) {
                const a = data[i];

                table.innerHTML +=
                    "<tr>" +
                    "<td>" + a.id + "</td>" +
                    "<td>" + a.name + "</td>" +
                    "<td>" + a.email + "</td>" +
                    "<td>" + a.department + "</td>" +
                    "<td>" +
                    "<button onclick='fillForm(" + a.id + ", `" + a.name + "`, `" + a.email + "`, `" + a.department + "`)'>Edit</button> " +
                    "<button onclick='deleteAdmin(" + a.id + ")'>Delete</button>" +
                    "</td>" +
                    "</tr>";
            }
        });
}

function saveAdmin() {
    const id = document.getElementById("adminId").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const department = document.getElementById("department").value;

    const admin = {
        name: name,
        email: email,
        department: department
    };

    if (id && id.trim() !== "") {
        fetch(API_URL + "/" + id, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(admin)
        }).then(() => {
            clearForm();
            loadAdmins();
        });
    } else {
        fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(admin)
        }).then(() => {
            clearForm();
            loadAdmins();
        });
    }
}

function deleteAdmin(id) {
    fetch(API_URL + "/" + id, {
        method: "DELETE"
    }).then(() => loadAdmins());
}

function fillForm(id, name, email, department) {
    document.getElementById("adminId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("department").value = department;
}

function clearForm() {
    document.getElementById("adminId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("department").value = "";
    document.getElementById("searchName").value = "";
}

window.onload = loadAdmins;