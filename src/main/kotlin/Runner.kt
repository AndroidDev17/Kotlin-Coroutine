import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking{
     try {
          ExceptionDemo().runInParallelAndThrowExceptionWithoutScope1()
          delay(50000)
     } catch (e: Exception) {
          println(e.message)
     }

}



