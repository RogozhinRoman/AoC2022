fun main() {
    fun getGroups(input: List<String>): List<Int> {
        val groups = mutableListOf(0)
        var currentIndex = 0

        for (line in input) {
            if (line.isBlank()) {
                currentIndex++
                groups.add(0)
            } else {
                groups[currentIndex] += line.toInt()
            }
        }

        return groups
    }

    fun part1(input: List<String>): Int = getGroups(input).max()
    fun part2(input: List<String>): Int = getGroups(input).sortedDescending().take(3).sum()

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
