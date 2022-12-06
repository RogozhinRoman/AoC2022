fun main() {
    fun getChunkStart(input: String, limit: Int): Int {
        val buffer = ArrayDeque<Char>()
        for (fast in input.indices) {
            if (fast == 0) {
                buffer.addLast(input[fast])
                continue
            }
            if (buffer.contains(input[fast])) {
                do {
                    val first = buffer.removeFirst()
                } while (first != input[fast])
                buffer.addLast(input[fast])
            } else {
                buffer.addLast(input[fast])
            }
            if (buffer.size == limit) {
                return fast + 1
            }
        }

        return 0
    }

    fun part1(input: String) = getChunkStart(input, 4)
    fun part2(input: String) = getChunkStart(input, 14)

    val testInput = readInput("Day06_test")
    check(part2(testInput.first()) == 19)

    val input = readInput("Day06")
    println(part2(input.first()))
}
