fun main() {
    fun getNeighbours(currentNode: Pair<Int, Int>, grid: Array<CharArray>): MutableSet<Pair<Int, Int>> {
        val cord = arrayOf(0, 1, 0, -1, 0)
        val neighbours = mutableSetOf<Pair<Int, Int>>()

        for (i in 0 until cord.lastIndex) {
            val newX = currentNode.first + cord[i]
            val newY = currentNode.second + cord[i + 1]

            if (newX < 0 || newX >= grid.size || newY < 0 || newY >= grid.first().size) continue

            neighbours.add(Pair(newX, newY))
        }

        return neighbours
    }

    class Path(val currentCoordinate: Pair<Int, Int>, val steps: Int)

    fun getStepsNumber(startNode: Pair<Int, Int>, grid: Array<CharArray>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        val queue = ArrayDeque<Path>()
        queue.addLast(Path(startNode, 1))

        while (queue.isNotEmpty()) {
            val currentNode = queue.removeFirst()

            val currentCoordinates = currentNode.currentCoordinate
            for (neighbour in getNeighbours(currentCoordinates, grid)) {
                if (visited.contains(currentCoordinates)) continue

                val currentItem = grid[currentCoordinates.first][currentCoordinates.second]
                if (currentItem.code + 1 < grid[neighbour.first][neighbour.second].code ||
                    (grid[neighbour.first][neighbour.second] == 'E' && currentItem.code < 'y'.code)
                ) continue

                if (grid[neighbour.first][neighbour.second] == 'E') return currentNode.steps

                queue.addLast(Path(neighbour, currentNode.steps + 1))
            }

            visited.add(currentCoordinates)
        }

        return 0
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

        return getStepsNumber(Pair(startI, startJ), grid)
    }

    fun part2(input: List<String>): Int {
        val grid = Array(input.size) { CharArray(input.first().length) }

        val startNodes = mutableListOf<Pair<Int, Int>>()
        for ((i, line) in input.withIndex()) {
            for ((j, symb) in line.withIndex()) {
                if (symb == 'S') {
                    grid[i][j] = 'a'
                    startNodes.add(Pair(i, j))
                    continue
                }
                grid[i][j] = symb
                if (symb == 'a') startNodes.add(Pair(i, j))
            }
        }

        return startNodes.map { getStepsNumber(it, grid) }.filter { it != 0 }.min()
    }

    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 29)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))
}
