fun main() {
    fun part1(input: List<String>): Int {
        var result = 0
        for (pair in input.map { it.split(' ') }) {
            when (pair.first()) {
                "A" -> when (pair.last()) {
                    "X" -> result += 1 + 3
                    "Y" -> result += 2 + 6
                    "Z" -> result += 3 + 0
                }

                "B" -> when (pair.last()) {
                    "X" -> result += 1 + 0
                    "Y" -> result += 2 + 3
                    "Z" -> result += 3 + 6
                }

                "C" -> when (pair.last()) {
                    "X" -> result += 1 + 6
                    "Y" -> result += 2 + 0
                    "Z" -> result += 3 + 3
                }
            }
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (pair in input.map { it.split(' ') }) {
            when (pair.first()) {
                "A" -> when (pair.last()) {
                    "X" -> result += 0 + 3
                    "Y" -> result += 3 + 1
                    "Z" -> result += 6 + 2
                }

                "B" -> when (pair.last()) {
                    "X" -> result += 0 + 1
                    "Y" -> result += 3 + 2
                    "Z" -> result += 6 + 3
                }

                "C" -> when (pair.last()) {
                    "X" -> result += 0 + 2
                    "Y" -> result += 3 + 3
                    "Z" -> result += 6 + 1
                }
            }
        }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    val testInput1 = readInput("Day02_test")
    check(part2(testInput1) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
    // println(part2(input))
}

// A -- rock 1
// B -- paper 2
// C -- scissors 3

// X -- rock 1
// Y -- paper 2
// Z -- scissors 3

// 0 -- lost
// 3 -- draw
// 6 -- won

// X -- lose 0
// Y -- draw 3
// Z -- win 6
