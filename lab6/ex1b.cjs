var async = require("async");

function printAsync(s, cb) {
    var delay = Math.floor((Math.random() * 1000) + 500);
    setTimeout(function () {
        console.log(s);
        if (cb) cb(null, s);
    }, delay);
}

function task1(cb) {
    printAsync("1", function (_, result) {
        cb(null, result);
    });
}

function task2(resultFromTask1, cb) {
    printAsync("2", function (_, result) {
        cb(null, resultFromTask1, result);
    });
}

function task3(resultFromTask1, resultFromTask2, cb) {
    printAsync("3", function (_, result) {
        cb(null, resultFromTask1, resultFromTask2, result);
    });
}

function runTasks(n) {
    var iterations = 4;
    var completedIterations = 0;

    function runIteration(iteration) {
        async.waterfall([
            task1,
            task2,
            task3
        ], function (err, resultFromTask1, resultFromTask2, finalResult) {
            if (err) {
                console.error(err);
            }

            completedIterations++;

            if (completedIterations == 4) {
                console.log('done!');
            } else {
                runIteration(iteration + 1);
            }
        });
    }

    runIteration(0);
}

runTasks(); //I was trying to do it with parameter but I hate js ;-;