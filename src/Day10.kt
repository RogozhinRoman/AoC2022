fun main() {
    fun part1(input: List<String>): Int {
        var cycles = 1
        var x = 1
        var signalsSum = 0
        val frames = listOf(20, 60, 100, 140, 180, 220)

        for (command in input) {
            var dx = 0

            if (!command.startsWith("noop")) {
                cycles++

                if (frames.contains(cycles - 1)) {
                    signalsSum += x * (cycles - 1)
                }

                dx = command.split(' ').last().toInt()
            }

            cycles++

            if (frames.contains(cycles - 1)) {
                signalsSum += x * (cycles - 1)
            }
            x += dx
        }

        return signalsSum
    }

    fun printGrid(grid: Array<MutableList<String>>) {
        for (i in grid.indices) {
            for (j in grid[i].indices) {
                print(grid[i][j])
            }

            println()
        }
    }

    fun getSpritePositions(spritePosition: Int) = listOf(spritePosition - 1, spritePosition, spritePosition + 1)

    fun part2(input: List<String>) {
        val rowSize = 40
        val grid = Array(6) { MutableList(rowSize) { "." } }
        var cycles = 0
        var spriteCenter = 1

        for (command in input) {
            var dx = 0

            if (!command.startsWith("noop")) {
                if (getSpritePositions(spriteCenter).contains(cycles % rowSize)) {
                    grid[cycles / rowSize][cycles % rowSize] = "#"
                }
                cycles++

                dx = command.split(' ').last().toInt()
            }

            if (getSpritePositions(spriteCenter).contains(cycles % rowSize)) {
                grid[cycles / rowSize][cycles % rowSize] = "#"
            }
            cycles++

            spriteCenter += dx
        }

        printGrid(grid)
    }

    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)
//    check(part2(testInput) == 70)

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
