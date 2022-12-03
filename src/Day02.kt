import java.io.File

fun main() {
    val notationToShape = mapOf(
      "A" to "Rock",
      "B" to "Paper",
      "C" to "Scissors",
      "X" to "Rock",
      "Y" to "Paper",
      "Z" to "Scissors"
    )
    val shapePoints = mapOf(
      "Rock" to 1,
      "Paper" to 2,
      "Scissors" to 3
    )
    val winOver = mapOf(
      "A" to "B", // A-Rock loses to B-Paper
      "B" to "C", // B-Paper loses to C-Scissors
      "C" to "A" // C-Scissors loses to A-Rock
    )
    val loseTo = mapOf(
      "A" to "C", // A-Rock beats C-Scissors
      "B" to "A", // B-Paper beats A-Rock
      "C" to "B" // C-Scissors beats B-Paper
    )

    fun scoreRoundForPlayer(player: String?, opponent: String): Int {
        val playerShape = notationToShape[player];
        val opponentShape = notationToShape[opponent];
        var score: Int = shapePoints[playerShape] ?: 0;

        if (playerShape === opponentShape) {
            score += 3; 
        } else if (
            (playerShape == "Rock" && opponentShape == "Scissors")
            || (playerShape == "Paper" && opponentShape == "Rock")
            || (playerShape == "Scissors" && opponentShape == "Paper")
        ) {
            score += 6;
        } 
        return score;
    }

    fun chooseShapeForResult(opponentNotation: String, result: String): String? {
        if (result == "Y") { // Draw
            return opponentNotation;
        } else if (result == "X") {  // Lose
            return loseTo[opponentNotation];
        } else {
            return winOver[opponentNotation];
        }
    }

    fun part1(filename: String): Int? {
        var score: Int = 0;
        File(filename).forEachLine { 
            val shapes = it.split(" ");
            score += scoreRoundForPlayer(shapes[1], shapes[0])
        }
        return score;
    }

    fun part2(filename: String): Int? {
        var score: Int = 0;
        File(filename).forEachLine { 
            val notation = it.split(" ");
            val playerShapeNotation = chooseShapeForResult(notation[0], notation[1]);
            score += scoreRoundForPlayer(playerShapeNotation, notation[0])
        }
        return score;
    }

    println(part1("input_day02.txt"))
    println(part2("input_day02.txt"))
}
