import java.io.File

fun main() {
    fun calorieCounts(filename: String): ArrayList<Int> {
        var counts: ArrayList<Int> = ArrayList()
        counts.add(0)
        File(filename).forEachLine { 
            if (it.trim().length == 0) {
                counts.add(0);
            } else {
                counts[counts.size - 1] += it.toInt();
            }
        }
        return counts;
    }

    fun part1(counts: ArrayList<Int>): Int? {
        return counts.maxOrNull();
    }

    fun part2(counts: ArrayList<Int>): Int? {
        counts.sortDescending();
        return counts[0] + counts[1] + counts[2]
    }

    var calorieCounts = calorieCounts("input_day01.txt")
    println(part1(calorieCounts))
    println(part2(calorieCounts))
}
