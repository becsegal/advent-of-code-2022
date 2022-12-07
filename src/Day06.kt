import java.io.File

fun main() {


    fun part1(filename: String): Int  {
        val text = File(filename).readText()
        var window: ArrayList<Char> = ArrayList()
        text.forEachIndexed { index, element ->
            window.add(element) 
            if (window.size > 4) window.removeAt(0) 
            if (window.toSet().size == 4) return (index + 1)
        }
        return -1
    }

    fun part2(filename: String): Int  {
        val text = File(filename).readText()
        var window: ArrayList<Char> = ArrayList()
        text.forEachIndexed { index, element ->
            window.add(element) 
            if (window.size > 14) window.removeAt(0) 
            if (window.toSet().size == 14) return (index + 1)
        }
        return -1
    }

    println("part 1: " + part1("input_day06.txt"))
    println("part 2: " + part2("input_day06.txt"))
}
