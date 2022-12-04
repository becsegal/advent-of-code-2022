import java.io.File

fun main() {

    // assumes sorted
    fun List<Int>.fullyContains(innerList: List<Int>): Boolean {
        return this.first() <= innerList.first() && this.last() >= innerList.last()
    }

    // assumes sorted
    fun List<Int>.overlaps(otherList: List<Int>): Boolean {
        return (this.first() <= otherList.last() && this.last() >= otherList.first())
    }

    fun part1(filename: String): Int? {
        var count: Int = 0;
        File(filename).forEachLine { 
            val ranges = it.split(",")
            val range1: List<Int> = ranges[0].split("-").map{ it.toInt() }
            val range2: List<Int> = ranges[1].split("-").map{ it.toInt() }
            if (range1.fullyContains(range2) || range2.fullyContains(range1)) {
                count += 1
            }
        }
        return count;
    }

    fun part2(filename: String): Int? {
        var count: Int = 0;
        File(filename).forEachLine { 
            val ranges = it.split(",")
            val range1: List<Int> = ranges[0].split("-").map{ it.toInt() }
            val range2: List<Int> = ranges[1].split("-").map{ it.toInt() }
            if (range1.overlaps(range2)) {
                count += 1
            }
        }
        return count;
    }

    println("part 1: " + part1("input_day04.txt"))
    println("part 2: " + part2("input_day04.txt"))
}
