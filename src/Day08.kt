import kotlin.math.max

fun main() {
    fun isOpen(i: Int, j: Int, grid: Array<IntArray>): Boolean {
        var isOpen = false
        for (k in i - 1 downTo 0) {
            if (grid[k][j] >= grid[i][j]) {
                isOpen = false
                break
            } else {
                isOpen = true
            }
        }
        if (isOpen) return true

        for (k in i + 1 until grid.size) {
            if (grid[k][j] >= grid[i][j]) {
                isOpen = false
                break
            } else {
                isOpen = true
            }
        }
        if (isOpen) return true

        for (k in j - 1 downTo 0) {
            if (grid[i][k] >= grid[i][j]) {
                isOpen = false
                break
            } else {
                isOpen = true
            }
        }
        if (isOpen) return true

        for (k in j + 1 until grid[i].size) {
            if (grid[i][k] >= grid[i][j]) {
                isOpen = false
                break
            } else {
                isOpen = true
            }
        }

        return isOpen
    }

    fun getScore(i: Int, j: Int, grid: Array<IntArray>): Int {
        val scores = mutableListOf<Int>()

        scores.add(0)
        for (k in i - 1 downTo 0) {
            if (grid[k][j] >= grid[i][j]) {
                scores[scores.lastIndex]++
                break
            } else {
                scores[scores.lastIndex]++
            }
        }

        scores.add(0)
        for (k in i + 1 until grid.size) {
            if (grid[k][j] >= grid[i][j]) {
                scores[scores.lastIndex]++
                break
            } else {
                scores[scores.lastIndex]++
            }
        }

        scores.add(0)
        for (k in j - 1 downTo 0) {
            if (grid[i][k] >= grid[i][j]) {
                scores[scores.lastIndex]++
                break
            } else {
                scores[scores.lastIndex]++
            }
        }

        scores.add(0)
        for (k in j + 1 until grid[i].size) {
            if (grid[i][k] >= grid[i][j]) {
                scores[scores.lastIndex]++
                break
            } else {
                scores[scores.lastIndex]++
            }
        }

        return scores.fold(1) { acc, e -> acc * e }
    }

    fun getGrid(input: List<String>): Array<IntArray> {
        val grid = Array(input.size) { IntArray(input.first().length) }

        for (index in grid.indices) {
            grid[index] = input[index].map { it.toString().toInt() }.toIntArray()
        }

        return grid
    }

    fun part1(input: List<String>): Int {
        val grid = getGrid(input)

        var openCount = 0
        for (i in grid.indices) {
            if (i == 0 || i == grid.lastIndex) continue
            for (j in grid[i].indices) {
                if (j == 0 || j == grid[i].lastIndex) continue

                openCount += if (isOpen(i, j, grid)) 1 else 0
            }
        }

        return openCount + grid.size * 2 + (grid.first().size - 2) * 2
    }

    fun part2(input: List<String>): Int {
        val grid = getGrid(input)

        var max = Int.MIN_VALUE
        for (i in grid.indices) {
            if (i == 0 || i == grid.lastIndex) continue
            for (j in grid[i].indices) {
                if (j == 0 || j == grid[i].lastIndex) continue
                if (!isOpen(i, j, grid)) continue

                max = max(max, getScore(i, j, grid))
            }
        }

        return max
    }

    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    val part2 = part2(testInput)
    check(part2 == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
