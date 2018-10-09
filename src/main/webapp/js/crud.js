const PATH = "/";

function writeStatus(element, message) {
    let now = new Date();
    let hours = now.getHours();
    let minutes = now.getMinutes();
    let seconds = now.getSeconds();
    hours = "" + (hours < 10 ? "0" + hours : hours);
    minutes = "" + (minutes < 10 ? "0" + minutes : minutes);
    seconds = "" + (seconds < 10 ? "0" + seconds : seconds);
    let currentTime = "[" + hours + ":" + minutes + ":" + seconds + "]";
    $(element).text(currentTime + ": " + message);
}

$(document).ready(function () {
    $('#addValue').click(function () {
        const name = $('#name').val();
        $.ajax({
            type: 'POST',
            url: PATH,
            data: {name: name},
            success: function (data) {
                writeStatus('#createStatus', "OK " +
                    (data ? "Added: [" + data.id + "] " + data.name : "")
                );
            },
            error: function (data) {
                writeStatus('#createStatus', "ERROR " +
                    (data.status ? data.status : "")
                );
            }
        });
    });

    $('#getButton').click(function () {
        $.ajax({
            type: 'GET',
            url: PATH + '?getAll=getAll',
            success: function (data) {
                let elementById = document.getElementById("results");
                elementById.innerText = "";
                $.each(data, function (index, obj) {
                    $('#results').append("<li>[" + obj.id + "] "
                        + obj.name + "</li>")
                });
                if (!elementById || elementById.innerHTML === "") {
                    elementById.innerHTML = "No results";
                }
                writeStatus('#getStatus', "OK. Results count: " + data.length);
            },
            error: function (data) {
                writeStatus('#getStatus', "ERROR " +
                    (data && data.status ? data.status : "")
                );
            }
        });
    });


    $('#deleteValue').click(function () {
        const idValue = $('#idValue').val();
        $.ajax({
            type: 'DELETE',
            url: PATH + '?idValue=' + idValue,
            success: function (data) {
                writeStatus('#deleteStatus', "OK " +
                    (data ? "Deleted [" + data.id + "] " + data.name : "")
                );
            },
            error: function (data) {
                writeStatus('#deleteStatus', "ERROR " +
                    (data && data.status ? data.status : "")
                )
            }
        });
    });

    $('#putValue').click(function () {
        let id = $('#id').val();
        let value = $('#value').val();
        $.ajax({
            type: 'PUT',
            url: PATH + '?id=' + id + '&value=' + value,
            success: function (data) {
                writeStatus('#putStatus', "OK " +
                    (data ? "Put: [" + data.id + "] " + data.name : "")
                );
            },
            error: function (data) {
                writeStatus('#putStatus', "ERROR " +
                    (data && data.status ? data.status : "")
                );
            }
        });
    });
});