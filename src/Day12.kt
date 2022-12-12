fun main() {
    fun getNeighbours(currentI: Int, currentJ: Int, limitI: Int, limitJ: Int): MutableSet<Pair<Int, Int>> {
        val cord = arrayOf(0, 1, 0, -1, 0)
        val neighbours = mutableSetOf<Pair<Int, Int>>()

        for (i in 0 until cord.lastIndex) {
            val newX = currentI + cord[i]
            val newY = currentJ + cord[i + 1]

            if (newX < 0 || newX >= limitI || newY < 0 || newY >= limitJ) continue

            neighbours.add(Pair(newX, newY))
        }

        return neighbours
    }

    fun getMinSteps(grid: Array<CharArray>, visited: Set<Pair<Int, Int>>, currentI: Int, currentJ: Int): Int {
        if (grid[currentI][currentJ] == 'E') {
            return visited.size
        }

        var min = Int.MAX_VALUE
        for (neighbour in getNeighbours(currentI, currentJ, grid.size, grid.first().size)) {
            if (visited.contains(neighbour)) continue
            if (grid[currentI][currentJ].code + 1 < grid[neighbour.first][neighbour.second].code ||
                (grid[neighbour.first][neighbour.second] == 'E' && grid[currentI][currentJ].code < 'y'.code)
            ) continue

            val newVisited = visited.toMutableSet()
            newVisited.add(neighbour)
            val minSteps = getMinSteps(grid, newVisited, neighbour.first, neighbour.second)
            min = kotlin.math.min(min, minSteps)
        }

        return min
    }

    fun part1(input: List<String>): Int {
        val grid = Array(input.size) { CharArray(input.first().length) }

        var startI = 0
        var startJ = 0
        for ((i, line) in input.withIndex()) {
            for ((j, symb) in line.withIndex()) {
                if (symb == 'S') {
                    startI = i
                    startJ = j
                    grid[i][j] = 'a'
                    continue
                }
                grid[i][j] = symb
            }
        }

        return getMinSteps(grid, setOf(Pair(startI, startJ)), startI, startJ) - 1
    }

    // fun part2(input: List<String>) =

    val testInput = readInput("Day12_test")
    val part1 = part1(testInput)
    println(part1)
    check(part1 == 31)
//    check(part2(testInput) == 70)
//
    val input = readInput("Day12")
    println(part1(input))
//    println(part2(input))
}
