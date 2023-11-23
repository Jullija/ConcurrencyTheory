
var Fork = function() {
    this.state = 0; //0 - free fork
    return this;
}

Fork.prototype.acquire = function(id, cb) { 
    // zaimplementuj funkcje acquire, tak by korzystala z algorytmu BEB
    // (http://pl.wikipedia.org/wiki/Binary_Exponential_Backoff), tzn:
    // 1. przed pierwsza proba podniesienia widelca Filozof odczekuje 1ms
    // 2. gdy proba jest nieudana, zwieksza czas oczekiwania dwukrotnie
    //    i ponawia probe itd.


    var waitingTime = 1; //waiting time
    var fork = this;
    var beb = function(){
        if (fork.state == 0){
            console.log("[PHILOSOPHER " + id + "] taken fork.")
            fork.state = 1;
            waitingTime = 1;
            if (cb) cb();
        }else{
            waitingTime *= 2;
            console.log("WAITING "+ waitingTime + " s.")
            setTimeout(beb, waitingTime);
        }
    }

    setTimeout(beb, waitingTime);


}

Fork.prototype.release = function() { 
    this.state = 0; 
}

var Philosopher = function(id, forks) {
    this.id = id;
    this.forks = forks;
    this.f1 = id % forks.length;
    this.f2 = (id+1) % forks.length;
    return this;
}

Philosopher.prototype.startNaive = function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
    
    // zaimplementuj rozwiazanie naiwne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    for (let i = 0; i < count; i++){
        forks[f1].acquire(id, function(){
            forks[f2].acquire(id, function(){
                console.log("[PHILOSPHER "+ id + "] starting meal with forks " + f1 + ", " + f2 +".");
                forks[f1].release();
                forks[f2].release();
                console.log("[PHILOSPHER "+ id + "] released forks " + f1 + ", " + f2 +".");
            })
        })

    }
}


// --------------ASYMETRIC---------------

Philosopher.prototype.startAsym = function(count) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
    
    // zaimplementuj rozwiazanie asymetryczne
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    //not even id - right fork

    var philosopher = this;
    if (id % 2 == 0){
        var firstFork = f2;
        var secondFork = f1;
    }else{
        var firstFork = f1;
        var secondFork = f2; 
    }


   if(count > 0){
        forks[firstFork].acquire(id, function(){
            forks[secondFork].acquire(id, function(){
                console.log("[PHILOSPHER "+ id + "] starting meal with forks " + f1 + ", " + f2 +".");
                forks[firstFork].release();
                forks[secondFork].release();
                console.log("[PHILOSPHER "+ id + "] released forks " + f1 + ", " + f2 +".");
                philosopher.startAsym(count - 1);
            })
        })

    }
}

// --------------CONDUCTOR---------------

//just like semaphore with limit N-1, where N is the number of philosophers
//state is the number of philosophers fighting for forks
var Conductor = function(N) {
    this.N = N;
    this.state = 0;
    return this;
}

Conductor.prototype.acquire = function(id, cb){
    var waitingTime = 1; //waiting time
    var beb = function(){
        if (this.state < this.N){
            console.log("[FORK " + id + "] taken.")
            waitingTime = 1;
            this.state += 1;
            if (cb) cb();
        }else{
            waitingTime *= 2;
            setTimeout(beb.bind(this), waitingTime); //bind is used when calling this, so we get the state
        }
    }

    setTimeout(beb.bind(this), waitingTime);
}


Conductor.prototype.release = function(){
    this.state -= 1;
}


Philosopher.prototype.startConductor = function(count, conductor) {
    var forks = this.forks,
        f1 = this.f1,
        f2 = this.f2,
        id = this.id;
    
    // zaimplementuj rozwiazanie z kelnerem
    // kazdy filozof powinien 'count' razy wykonywac cykl
    // podnoszenia widelcow -- jedzenia -- zwalniania widelcow

    if (count > 0) {
        conductor.acquire(id, function () {
            forks[f1].acquire(id, function () {
                forks[f2].acquire(id, function () {
                    console.log("[PHILOSPHER "+ id + "] starting meal.");
                    forks[f1].release();
                    forks[f2].release();
                    console.log("[PHILOSPHER "+ id + "] released forks.");
                    conductor.release();
                    () => this.startConductor(count - 1, conductor);
                })
            });
        });
    }
}


var N = 5;
var forks = [];
var philosophers = []
var conductor = new Conductor(N - 1)
for (var i = 0; i < N; i++) {
    forks.push(new Fork());
}

for (var i = 0; i < N; i++) {
    philosophers.push(new Philosopher(i, forks));
}

// for (var i = 0; i < N; i++) {
//     philosophers[i].startNaive(10);
// }

// for (var i = 0; i < N; i++) {
//     philosophers[i].startAsym(10);
// }

for (var i = 0; i < N; i++) {
    philosophers[i].startConductor(2, conductor);
}


