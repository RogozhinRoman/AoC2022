import kotlin.math.abs

fun main() {
    fun printGrid(
        grid: Array<MutableList<String>>,
        H: Pair<Int, Int>,
        T: Pair<Int, Int>
    ) {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                if (H.first == i && H.second == j) {
                    print("H")
                    continue
                }
                if (T.first == i && T.second == j) {
                    print("T")
                    continue
                }
                print(grid[i][j])
            }
            println()
        }
    }

    fun isAttainable(newH: Pair<Int, Int>, T: Pair<Int, Int>): Boolean {
        if (newH.first == T.first) return abs(newH.second - T.second) <= 1
        if (newH.second == T.second) return abs(newH.first - T.first) <= 1

        return (abs(newH.first - T.first) + abs(newH.second - T.second)) <= 2
    }

    fun getOffset(x1: Int, x2: Int): Int {
        if (x1 == x2) {
            return 0
        }

        return if (x1 > x2) 1 else -1
    }

    fun performStep(
        x: Int,
        y: Int,
        H: Pair<Int, Int>,
        T: Pair<Int, Int>,
        grid: Array<MutableList<String>>,
        shouldDraw: Boolean
    ): Triple<Pair<Int, Int>, Pair<Int, Int>, Array<MutableList<String>>> {
        val newH = Pair(H.first + x, H.second + y)
        if (isAttainable(newH, T)) return Triple(newH, T, grid)

        if (shouldDraw) grid[T.first][T.second] = "#"

        return Triple(
            newH,
            Pair(
                T.first + getOffset(newH.first, T.first),
                T.second + getOffset(newH.second, T.second)
            ),
            grid
        )
    }

    fun performStep2(
        coord1: Int,
        coord2: Int,
        snake: Array<Pair<Int, Int>>,
        grid: Array<MutableList<String>>
    ): MutableList<Pair<Int, Int>> {
        val result = snake.toMutableList()

        val (first, second, _) = performStep(coord1, coord2, result[0], result[1], grid, false)
        result[0] = first
        result[1] = second

        for (i in 2 until result.size) {
            val (_, target, _) = performStep(0, 0, result[i - 1], result[i], grid, false)
            result[i] = target
        }
        grid[result.last().first][result.last().second] = "#"

        return result
    }

    fun performTravel(
        input: List<String>,
        grid: Array<MutableList<String>>
    ) {
        var H = Pair(grid.size / 2, grid.size / 2)
        var T = Pair(grid.size / 2, grid.size / 2)
        for (command in input) {
            val parts = command.split(' ')
            val direction = parts.first().toString()
            var steps = parts.last().toString().toInt()

            while (steps > 0) {
                val result = when (direction) {
                    "R" -> performStep(0, 1, H, T, grid, true)
                    "L" -> performStep(0, -1, H, T, grid, true)
                    "U" -> performStep(-1, 0, H, T, grid, true)
                    else -> performStep(1, 0, H, T, grid, true)
                }
                H = result.first
                T = result.second
                steps--
            }
        }

        grid[T.first][T.second] = "#"
    }

    fun performTravel2(
        input: List<String>,
        grid: Array<MutableList<String>>
    ) {
        var snake = Array(10) { Pair(grid.size / 2, grid.size / 2) }
        for (command in input) {
            val parts = command.split(' ')
            val direction = parts.first().toString()
            var steps = parts.last().toString().toInt()

            while (steps > 0) {
                val result = when (direction) {
                    "R" -> performStep2(0, 1, snake, grid)
                    "L" -> performStep2(0, -1, snake, grid)
                    "U" -> performStep2(-1, 0, snake, grid)
                    else -> performStep2(1, 0, snake, grid)
                }

                snake = result.toTypedArray()
                steps--
            }
        }

        grid[snake.last().first][snake.last().second] = "#"
    }

    fun part1(input: List<String>): Int {
        val size = 10000
        val grid = Array(size) { MutableList(size) { "." } }

        performTravel(input, grid)

        return grid.flatMap { it }.count { it == "#" }
    }

    fun part2(input: List<String>): Int {
        val size = 10000
        val grid = Array(size) { MutableList(size) { "." } }

        performTravel2(input, grid)

        return grid.flatMap { it }.count { it == "#" }
    }

    val testInput = readInput("Day09_test")
    val part1 = part1(testInput)
    check(part1 == 13)
//    check(part2(testInput) == 70)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
