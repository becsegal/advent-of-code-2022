import java.io.File

fun main() {

    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

    fun mispackedCharacter(contents: String): String {
        val compartmentAContents: CharArray = contents.substring(0, contents.length/2).toCharArray()
        val compartmentBContents: CharArray = contents.substring(contents.length/2).toCharArray()
        return compartmentAContents.intersect(compartmentBContents.asIterable()).first().toString()
    }

    fun commonCharacter(str1: String, str2: String, str3: String): String {
        return str1.toCharArray().intersect(
                str2.toCharArray().asIterable()
            ).intersect(
                str3.toCharArray().asIterable()
            ).first().toString()
    }

    fun part1(filename: String): Int? {
        var priorityTotal: Int = 0;
        File(filename).forEachLine { 
            val char: String = mispackedCharacter(it)
            priorityTotal += letters.indexOf(char) + 1
        }
        return priorityTotal;
    }

    fun part2(filename: String): Int? {
        var priorityTotal: Int = 0;
        var threesie: ArrayList<String> = ArrayList();
        File(filename).forEachLine { 
            threesie.add(it)
            if (threesie.size == 3) {
                val char: String = commonCharacter(threesie[0], threesie[1], threesie[2])
                priorityTotal += letters.indexOf(char) + 1
                threesie = ArrayList()
            }
        }
        return priorityTotal;
    }

    println("part 1: " + part1("input_day03.txt"))
    println("part 2: " + part2("input_day03.txt"))
}
