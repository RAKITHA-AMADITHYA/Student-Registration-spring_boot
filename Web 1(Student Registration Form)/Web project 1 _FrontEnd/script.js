getAllStudents();
function saveStudent() {

    let name = $('#box2').val();
    let age = $('#box3').val();
    let address = $('#box4').val();
    let contact = $('#box5').val();
    let gender = $('#box6').val();

    $.ajax({
        method: "POST",
        contentType: "application/json",
        url: "http://localhost:3000/api/v1/student/save", 
        async: true,
        data: JSON.stringify({
            "stdId": "",
            "fullName": name,
            "age": age,
            "address": address,
            "contact": contact,
            "gender": gender
        }),
        success: function(data) {
            clearFeilds();
            alert("Student had saved successful to the database");
            getAllStudents();     
        },
        error: function(xhr, exception) { 
            alert("Error");
        }
    });
}

function updateStudent() {

    let studentId = $('#box1').val();
    let name = $('#box2').val();
    let age = $('#box3').val();
    let address = $('#box4').val();
    let contact = $('#box5').val();
    let gender = $('#box6').val();

    $.ajax({
        method: "PUT",
        contentType: "application/json",
        url: "http://localhost:3000/api/v1/student/update", 
        async: true,
        data: JSON.stringify({
            "stdId": studentId,
            "fullName": name,
            "age": age,
            "address": address,
            "contact": contact,
            "gender": gender
        }),
        success: function(data) {
            clearFeilds();
            alert("Student Updated");   
            getAllStudents();  
        },
        error: function(xhr, exception) { 
            alert("Error");
        }
    });
}

function deleteStudent(){

    let stdId = $('#box1').val();

    $.ajax({
        method: "DELETE",
        url: "http://localhost:3000/api/v1/student/delete/"+stdId, 
        async: true,
        
        success: function(data) {
            alert("Student Delted");
            getAllStudents() ;
        },
        error: function(xhr, exception) { 
            alert("Error");
        }
    });
}


function getAllStudents() {
    $.ajax({
        method: "GET",
        url: "http://localhost:3000/api/v1/student/getAll",
        async: true,
        success: function (data) {
            if (data.code === "00") {
                var table = $('#StudentTable').empty();

                for (let Student of data.content) {
                    let Id = Student.stdId || '';
                    let Name = Student.fullName || '';
                    let Age = Student.age || '';
                    let Address = Student.address || '';
                    let Number = Student.contact || '';
                    let Gender = Student.gender || '';

                    // Check if any field is undefined or empty
                    if (Id !== '' || Name !== '' || Age !== '' || Address !== '' || Number !== '' || Gender !== '') {
                        // Create a new row and append cells
                        var row = `
                            <tr>
                                <td>${Id}</td>
                                <td>${Name}</td>
                                <td>${Age}</td>
                                <td>${Address}</td>
                                <td>${Number}</td>
                                <td>${Gender}</td>
                            </tr>
                        `;
                        $('#StudentTable').append(row);
                    }
                }
            }
        },
        error: function (xhr, exception) {
            alert("Error");
        }
    });
}


$(document).ready(function () {
    $(document).on('click', '#StudentTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();
        var col4 = $(this).find('td:eq(4)').text();
        var col5 = $(this).find('td:eq(5)').text();

        $('box1').val(col0);
        $('#box2').val(col1);
        $('#box3').val(col2);
        $('#box4').val(col3);
        $('#box5').val(col4);
        $('#box6').val(col5);
       

    })
})




function clearFeilds(){
    $('#box2').val('');
    $('#box3').val('');
    $('#box4').val('');
    $('#box5').val('');
    $('#box6').val('');
}