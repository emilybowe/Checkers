class Test {
    fun arr () {
        val i = 8
        val j = 8
        var array = Array(i) { IntArray(j) {1} }
        array[0][0] = 2

        for (array in array) {
            println()
            for(value in array){
                print(value)
            }
        }
    }
}

fun main () {
    val test = Test()
    test.arr()
}