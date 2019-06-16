import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ExceptionDemo {
    private suspend fun readIntWithDelay(value: Int, delay: Long, label: String) {
        kotlinx.coroutines.delay(delay)
        if (value < 20) {
            println("$label :: $delay :: $value")
        } else {
            throw RuntimeException("value $value not expected after $delay")
        }
    }

    /**
     * co-routines running in parallel but they a common
     * parent scope so if any one get fail it will propagate
     * exception to parent and parent will cancel all the
     * child co-routines with first exception as main exception
     * and remaining exception will be suppressed
     */

    suspend fun runInParallelAndThrowException() {
        coroutineScope {
            repeat(100) {
                println("loop counter $it")
                launch {
                    readIntWithDelay(it, it * 100L, "DELAY_100")
                }
                launch {
                    readIntWithDelay(it, it * 500L, "DELAY_500")
                }

            }
        }
    }


    /**
     * co-routines running in parallel but they don't have a
     * common parent scope so if any one get fail it will not propagate
     * exception to the parent and all co-routines will be failed one
     * by one which causes resource leaking.
     */
    suspend fun runInParallelAndThrowExceptionWithoutScope() {
        repeat(100) {
            println("loop counter $it")
            GlobalScope.launch {
                readIntWithDelay(it, it * 100L, "DELAY_100")
            }
            GlobalScope.launch {
                readIntWithDelay(it, it * 500L, "DELAY_500")
            }

        }
    }

    /**
     * todo
     */
    suspend fun runInParallelAndThrowExceptionWithoutScope1() {
        repeat(100) {
            println("loop counter $it")
            coroutineScope {
                launch {
                    readIntWithDelay(it, it * 100L, "DELAY_100")
                }
            }
        }
    }
}