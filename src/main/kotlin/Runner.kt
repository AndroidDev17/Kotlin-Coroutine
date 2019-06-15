import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(args: Array<String>) {
    coroutineScope {
        launch {
            repeat(100) {
                delay(20)
                println("hello kotlin")
            }
        }
    }
}