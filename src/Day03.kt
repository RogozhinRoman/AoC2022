fun main() {
    fun getPriority(c: Char): Int {
        return if (c.isLowerCase()) c.code - 'a'.code + 1
        else c.code - 'A'.code + 27
    }

    fun part1(input: List<String>): Int {
        val firstPartLetters = mutableSetOf<Char>()
        var priority = 0

        for (line in input) {
            val half = line.length / 2

            for (i in 0 until half) {
                firstPartLetters.add(line[i])
            }

            for (i in half until line.length) {
                if (firstPartLetters.contains(line[i])) {
                    priority += getPriority(line[i])
                    break
                }
            }

            firstPartLetters.clear()
        }

        return priority
    }

    fun part2(input: List<String>): Int {
        val firstLevelCommon = mutableSetOf<Char>()
        val secondLevelCommon = mutableSetOf<Char>()
        var resultPriority = 0
        var index = 0

        for (line in input) {
            if (index == 0) {
                firstLevelCommon.addAll(line.toList())
                index++
            } else if (index == 1) {
                for (c in line) {
                    if (firstLevelCommon.contains(c)) {
                        secondLevelCommon.add(c)
                    }
                }
                index++
            } else {
                for (c in line) {
                    if (secondLevelCommon.contains(c)) {
                        resultPriority += getPriority(c)
                        break
                    }
                }

                firstLevelCommon.clear()
                secondLevelCommon.clear()
                index = 0
            }
        }

        return resultPriority
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
