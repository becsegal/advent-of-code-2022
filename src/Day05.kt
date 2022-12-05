import java.io.File

fun main() {

    var stacks: ArrayList<ArrayList<String?>> = ArrayList()
    var instructions: ArrayList<List<Int>> = ArrayList()

    fun initializeStackLine(row: List<String?>) {
        row.forEachIndexed { index, value ->
            if (stacks.size <= index) {
                stacks.add(ArrayList<String?>())
            }
            if (value != null) stacks[index].add(value);
        }
    }

    fun initalizeStacksAndInstructions(filename: String) {
        stacks = ArrayList()
        instructions = ArrayList()
        val elementRegex: Regex = "[A-Z]".toRegex()
        val movesRegex: Regex = """\d+""".toRegex()
        File(filename).forEachLine { 
            if (it.contains("[")) {
                val elements = it.chunked(4).map{ elementRegex.find(it)?.value } 
                initializeStackLine(elements)
            } else if (it.contains("move")) {
                instructions.add(movesRegex.findAll(it).map{ it.value.toInt() }.toList())
            }
        }

    }

    fun part1(filename: String): String  {
        initalizeStacksAndInstructions(filename)
        instructions.forEach {
            val nTimes = it[0]
            val fromStackNum = it[1] - 1
            val toStackNum = it[2] - 1
            repeat(nTimes) {
                val fromStack = stacks[fromStackNum]
                val moveVal = fromStack.removeAt(0)
                stacks[toStackNum].add(0, moveVal)
            }
        }
        return stacks.map{ if (it.size > 0) it[0] else "" }.joinToString(separator="")
    }

    fun part2(filename: String): String  {
        initalizeStacksAndInstructions(filename)
        instructions.forEach {
            val nTimes = it[0]
            val fromStackNum = it[1] - 1
            val toStackNum = it[2] - 1
            var removeFrom: Int = nTimes - 1;
            repeat(nTimes) {
                val fromStack = stacks[fromStackNum]
                val moveVal = fromStack.removeAt(removeFrom)
                stacks[toStackNum].add(0, moveVal)
                removeFrom -= 1
            }
        }
        return stacks.map{ if (it.size > 0) it[0] else "" }.joinToString(separator="")
    }

    println("part 1: " + part1("input_day05.txt"))
    println("part 2: " + part2("input_day05.txt"))
}
