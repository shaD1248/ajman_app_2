window.addEventListener('load', function() {
    var errorLog = document.getElementById('errorLog');
    var canvas = document.getElementById('joistCanvas');
    var context = canvas.getContext('2d');
//    errorLog.innerText = canvas.getAttribute('data');
    var data = JSON.parse(canvas.getAttribute('data'));
    if (data) drawShapes(context, data)
})

function drawShapes(context, data) {
    data.rectangles.forEach(function(rectangle) {
        drawRectangle(context, rectangle.x, rectangle.y, rectangle.w, rectangle.h);
    });
    data.circles.forEach(function(circle) {
        drawCircle(context, circle.x, circle.y, circle.r);
    });
    data.paths.forEach(function(path) {
        drawPath(context, path);
    });
}

// Draw a rectangle
function drawRectangle(context, x, y, width, height) {
    context.beginPath();
    context.rect(x, y, width, height);
    context.stroke();
}

// Draw a circle
function drawCircle(context, x, y, radius) {
    context.beginPath();
    context.arc(x, y, radius, 0, 2 * Math.PI);
    context.stroke();
}

function drawPath(context, points) {
    if (points.length < 2) {
        return; // Need at least 2 points to draw a path
    }

    context.beginPath();
    context.moveTo(points[0].x, points[0].y);

    for (var i = 1; i < points.length; i++) {
        context.lineTo(points[i].x, points[i].y);
    }

    context.closePath(); // Close the path to create a closed shape
    context.stroke();
}
