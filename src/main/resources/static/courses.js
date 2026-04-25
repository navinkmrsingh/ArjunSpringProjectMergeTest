// Husna Syeda
// UID: 393005210
// Course Management JS
// ISTE 240 - Group 1

const API = '/api/courses';
const TUTOR_API = '/api/tutors';

document.addEventListener('DOMContentLoaded', () => {
    loadTutorDropdown();
    loadCourses();
});

async function loadTutorDropdown() {
    try {
        const res    = await fetch(TUTOR_API);
        const tutors = await res.json();
        const select = document.getElementById('tutorId');
        tutors.forEach(t => {
            const opt       = document.createElement('option');
            opt.value       = t.id;
            opt.textContent = t.name;
            select.appendChild(opt);
        });
    } catch {
        console.warn('Could not load tutors.');
    }
}

async function loadCourses() {
    try {
        const res     = await fetch(API);
        const courses = await res.json();
        renderTable(courses);
    } catch {
        alert('Failed to load courses. Is the server running?');
    }
}

async function searchCourses() {
    const name = document.getElementById('searchName').value.trim();
    if (!name) { loadCourses(); return; }
    try {
        const res     = await fetch(`${API}/search?name=${encodeURIComponent(name)}`);
        const courses = await res.json();
        renderTable(courses);
    } catch {
        alert('Search failed.');
    }
}

function renderTable(courses) {
    const tbody = document.getElementById('courseTable');
    tbody.innerHTML = '';
    if (!courses || courses.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7">No courses found.</td></tr>';
        return;
    }
    courses.forEach(c => {
        const tutorName = c.tutor ? c.tutor.name : '-';
        tbody.innerHTML += `
        <tr>
            <td>${c.courseId}</td>
            <td>${c.courseCode}</td>
            <td>${c.courseName}</td>
            <td>${c.credits}</td>
            <td>${c.department || '-'}</td>
            <td>${tutorName}</td>
            <td>
                <button class="btn" onclick="editCourse(${c.courseId})">Edit</button>
                <button class="btn" onclick="deleteCourse(${c.courseId})">Delete</button>
            </td>
        </tr>`;
    });
}

async function editCourse(id) {
    try {
        const res = await fetch(`${API}/${id}`);
        const c   = await res.json();
        document.getElementById('courseId').value    = c.courseId;
        document.getElementById('courseName').value  = c.courseName;
        document.getElementById('courseCode').value  = c.courseCode;
        document.getElementById('description').value = c.description || '';
        document.getElementById('credits').value     = c.credits;
        document.getElementById('department').value  = c.department || '';
        document.getElementById('tutorId').value     = c.tutor ? c.tutor.id : '';
    } catch {
        alert('Could not load course details.');
    }
}

async function saveCourse() {
    const id         = document.getElementById('courseId').value;
    const courseName = document.getElementById('courseName').value.trim();
    const courseCode = document.getElementById('courseCode').value.trim();
    const credits    = document.getElementById('credits').value;

    if (!courseName || !courseCode || !credits) {
        alert('Please fill in Course Name, Code and Credits.');
        return;
    }

    const tutorIdVal = document.getElementById('tutorId').value;
    const payload = {
        courseName,
        courseCode,
        description : document.getElementById('description').value.trim(),
        credits     : parseInt(credits),
        department  : document.getElementById('department').value.trim(),
        tutorId     : tutorIdVal ? parseInt(tutorIdVal) : null
    };

    const isEdit = id !== '';
    try {
        const res = await fetch(isEdit ? `${API}/${id}` : API, {
            method  : isEdit ? 'PUT' : 'POST',
            headers : { 'Content-Type': 'application/json' },
            body    : JSON.stringify(payload)
        });
        if (!res.ok) throw new Error();
        clearForm();
        loadCourses();
        alert(isEdit ? 'Course updated successfully!' : 'Course added successfully!');
    } catch {
        alert('Failed to save course.');
    }
}

async function deleteCourse(id) {
    if (!confirm('Are you sure you want to delete this course?')) return;
    try {
        const res = await fetch(`${API}/${id}`, { method: 'DELETE' });
        if (!res.ok) throw new Error();
        loadCourses();
    } catch {
        alert('Failed to delete course.');
    }
}

function clearForm() {
    ['courseId','courseName','courseCode','description','credits','department']
        .forEach(id => document.getElementById(id).value = '');
    document.getElementById('tutorId').value = '';
}
