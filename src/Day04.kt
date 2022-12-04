fun main() {
    infix fun Pair<Int, Int>.isFullyContain(tested: Pair<Int, Int>) =
        this.first <= tested.first && this.second >= tested.second

    infix fun Pair<Int, Int>.includesStart(tested: Pair<Int, Int>) =
        this.first <= tested.first && this.second >= tested.first

    infix fun Pair<Int, Int>.hasOverlap(tested: Pair<Int, Int>) =
        this includesStart tested || tested includesStart this

    fun getInputPairs(input: List<String>) = input
        .map { it.split(',') }
        .map { it ->
            it.map { it.split('-') }
                .map { Pair(it.first().toInt(), it.last().toInt()) }
        }

    fun part1(input: List<String>) = getInputPairs(input).count {
        it.first() isFullyContain it.last() ||
            it.last() isFullyContain it.first()
    }

    fun part2(input: List<String>) = getInputPairs(input).count { it.first() hasOverlap it.last() }

    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
