fun main() {
    fun getCrates(input: List<String>): List<ArrayDeque<String>> {
        val cratesTemp = input.takeWhile { !it.startsWith("move") }.dropLast(1)
        val stacks = List<ArrayDeque<String>>(cratesTemp.last().last().toString().toInt()) { ArrayDeque() }
        val crates = cratesTemp.dropLast(1)

        for (line in crates.reversed()) {
            for ((counter, crate) in line.chunked(4).withIndex()) {
                if (crate.startsWith("[")) {
                    stacks[counter].addLast(crate[1].toString())
                }
            }
        }

        return stacks
    }

    fun getCommands(input: List<String>): List<Triple<Int, Int, Int>> {
        val commands = input.dropWhile { !it.startsWith("move")}
        return commands.map {
            val splitted = it.split("move ", " from ", " to ")
            Triple(splitted[1].toInt(), splitted[2].toInt(), splitted[3].toInt())
        }
    }

    fun part1(input: List<String>): String {
        val crates = getCrates(input)
        val commands = getCommands(input)

        for (command in commands) {
            val from = crates[command.second - 1]
            val to = crates[command.third - 1]

            for (i in 0 until command.first) {
                val elem = from.removeLast()
                to.addLast(elem)
            }
        }

        return crates.joinToString("") { it.last() }
    }

    fun part2(input: List<String>): String {
        val crates = getCrates(input)
        val commands = getCommands(input)

        for (command in commands) {
            val from = crates[command.second - 1]
            val to = crates[command.third - 1]
            val toTransfer = ArrayDeque<String>()

            for (i in 0 until command.first) {
                toTransfer.addLast(from.removeLast())
            }
            for (i in 0 until command.first) {
                to.addLast(toTransfer.removeLast())
            }
        }

        return crates.joinToString("") { it.last() }
    }

    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
