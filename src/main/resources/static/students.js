const API_URL = "/api/students";

function loadStudents() {
    const name = document.getElementById("searchName").value;
    let url = API_URL;

    if (name && name.trim() !== "") {
        url += "/search?name=" + encodeURIComponent(name);
    }

    fetch(url)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("studentTable");
            table.innerHTML = "";

            for (let i = 0; i < data.length; i++) {
                const s = data[i];

                table.innerHTML +=
                    "<tr>" +
                    "<td>" + s.id + "</td>" +
                    "<td>" + s.name + "</td>" +
                    "<td>" + s.email + "</td>" +
                    "<td>" + s.enrolledCourse + "</td>" +
                    "<td>" +
                    "<button onclick='fillForm(" + s.id + ", `" + s.name + "`, `" + s.email + "`, `" + s.enrolledCourse + "`)'>Edit</button> " +
                    "<button onclick='deleteStudent(" + s.id + ")'>Delete</button>" +
                    "</td>" +
                    "</tr>";
            }
        });
}

function addStudent() {
    const id = document.getElementById("studentId").value;
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const enrolledCourse = document.getElementById("course").value;

    const student = {
        name: name,
        email: email,
        enrolledCourse: enrolledCourse
    };

    if (id && id.trim() !== "") {
        fetch(API_URL + "/" + id, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(student)
        }).then(() => {
            clearForm();
            loadStudents();
        });
    } else {
        fetch(API_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(student)
        }).then(() => {
            clearForm();
            loadStudents();
        });
    }
}

function deleteStudent(id) {
    fetch(API_URL + "/" + id, {
        method: "DELETE"
    }).then(() => loadStudents());
}

function fillForm(id, name, email, enrolledCourse) {
    document.getElementById("studentId").value = id;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("course").value = enrolledCourse;
}

function clearForm() {
    document.getElementById("studentId").value = "";
    document.getElementById("name").value = "";
    document.getElementById("email").value = "";
    document.getElementById("course").value = "";
    document.getElementById("searchName").value = "";
}

window.onload = loadStudents;