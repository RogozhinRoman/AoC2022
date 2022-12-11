fun main() {
    class Monkey(
        var items: MutableList<Long>,
        var operation: (old: Long) -> Long,
        var divisor: Long,
        var trueReceiver: Int,
        var falseReceiver: Int,
        var totalInspected: Long = 0
    )

    fun getTask2Monkeys(): List<Monkey> {
        return listOf(
            Monkey(mutableListOf(83, 62, 93), { old -> old * 17 }, 2, 1, 6),
            Monkey(mutableListOf(90, 55), { old -> old + 1 }, 17, 6, 3),
            Monkey(mutableListOf(91, 78, 80, 97, 79, 88), { old -> old + 3 }, 19, 7, 5),
            Monkey(mutableListOf(64, 80, 83, 89, 59), { old -> old + 5 }, 3, 7, 2),
            Monkey(mutableListOf(98, 92, 99, 51), { old -> old * old }, 5, 0, 1),
            Monkey(mutableListOf(68, 57, 95, 85, 98, 75, 98, 75), { old -> old + 2 }, 13, 4, 0),
            Monkey(mutableListOf(74), { old -> old + 4 }, 7, 3, 2),
            Monkey(mutableListOf(68, 64, 60, 68, 87, 80, 82), { old -> old * 19 }, 11, 4, 5)
        )
    }

    fun getTask1Monkeys(): List<Monkey> {
        return listOf(
            Monkey(mutableListOf(79, 98), { old -> old * 19 }, 23, 2, 3),
            Monkey(mutableListOf(54, 65, 75, 74), { old -> old + 6 }, 19, 2, 0),
            Monkey(mutableListOf(79, 60, 97), { old -> old * old }, 13, 1, 3),
            Monkey(mutableListOf(74), { old -> old + 3 }, 17, 0, 1)
        )
    }

    fun getMaxTotal(monkeys: List<Monkey>, reducer: (Long) -> Long): Long {
        for (i in 0 until 20) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    monkey.totalInspected++

                    val newWorryLevel = reducer(monkey.operation(monkey.items.first()))
                    monkey.items = monkey.items.drop(1).toMutableList()

                    val nextReceiver = if (newWorryLevel % monkey.divisor == 0L) {
                        monkey.trueReceiver
                    } else {
                        monkey.falseReceiver
                    }

                    monkeys[nextReceiver].items.add(newWorryLevel)
                }
            }
        }

        return monkeys
            .sortedByDescending { it.totalInspected }
            .take(2)
            .fold(1) { acc, monkey -> monkey.totalInspected * acc }
    }

    fun part1() = getMaxTotal(getTask2Monkeys()) { number -> number / 3 }
    fun part2() = getMaxTotal(
        getTask2Monkeys()
    ) { number -> number % listOf(2, 17, 19, 3, 5, 13, 7, 11).reduce { acc, i -> acc * i }.toLong() }

//    val testInput = readInput("DayN_test")
    // check(part1() == 10605)
    println(part2())
    check(part2() == 2713310158)
//
//    val input = readInput("DayN")
    // println(part1())
//    println(part2(input))
}
