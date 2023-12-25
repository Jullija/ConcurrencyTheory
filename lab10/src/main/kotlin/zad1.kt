import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select

fun main() {

    val brokers = 10;
    val producerChannels = ArrayList<Channel<Int>>(); //producer have channels for each brokers
    val consumerChannels = ArrayList<Channel<Int>>(); //consumer have channels for each brokers


    for (num in 0..brokers) {
        producerChannels.add(Channel());
        consumerChannels.add(Channel());
    }


    runBlocking {
        for (num in 0..brokers){
            launch {brokeFun(producerChannels[num], consumerChannels[num]) }
        }
        launch { produce(producerChannels) }
        launch { consume(consumerChannels) }
    }


}


suspend fun brokeFun(fromChannel: Channel<Int>, toChannel: Channel<Int>) {
    while (true) {
        val portion = fromChannel.receive();
        println("[PORTION] $portion")
        toChannel.send(portion);
    }

}


suspend fun produce(channels: ArrayList<Channel<Int>>){
    while (true){
        delay(1000L)
        select{
            channels.forEach{channel -> channel.onSend((0..100).random()){} }
        }
    }
}

suspend fun consume(channels: ArrayList<Channel<Int>>){
    while (true){
        delay(1000L)
        select{
            channels.forEach{channel -> channel.onReceive{value -> println("Received $value.")} }
        }
    }
}



