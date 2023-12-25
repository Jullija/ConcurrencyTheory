import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    var converters = 10;
    var channels = ArrayList<Channel<Int>>()
    for (num in 0..converters){
        channels.add(Channel());
    }

    runBlocking {
        for (num in 0 until converters){
            launch{convert(channels[num], channels[num + 1])}
        }

        launch{produce(channels[0])};
        launch{consume(channels[converters])};
    }
}



suspend fun convert(fromChannel: Channel<Int>, toChannel: Channel<Int>){
    while (true) {
        val portion = fromChannel.receive();
        println("[PORTION] $portion")
        toChannel.send(portion);

    }
}

suspend fun produce(channel: Channel<Int>){
    while (true){
        delay(1000L);
        channel.send((0..100).random());
    }
}


suspend fun consume(channel: Channel<Int>){
    while (true){
        delay(1000L);
        val ans = channel.receive();
        println("Consumer received $ans.")

    }
}










