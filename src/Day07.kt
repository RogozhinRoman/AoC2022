import java.lang.Long.min

fun main() {
    fun getNewDirectory(target: String, currentNode: Node?, root: Node): Node {
        return when (target) {
            ".." -> currentNode!!.parent!!
            "/" -> root
            else -> currentNode!!.children.single { it.name == target }
        }
    }

    fun fillTree(node: Node): Long {
        if (node.children.isEmpty()) return node.fileSizes

        var sum = 0L
        for (child in node.children) {
            sum += fillTree(child)
        }
        node.fileSizes += sum

        return node.fileSizes
    }

    fun fillDirectory(
        currentLineIndex: Int,
        input: List<String>,
        currentNode: Node
    ): Int {
        var i = currentLineIndex
        while (i < input.size && !input[i].startsWith("$")) {
            if (input[i].startsWith("dir")) {
                val subDirectoryName = input[i].split(" ").last()
                val newNode = Node(currentNode, subDirectoryName, mutableListOf(), 0)

                currentNode.children.add(newNode)
            } else {
                currentNode.fileSizes += input[i].split(" ").first().toInt()
            }
            i++
        }

        return i
    }

    fun getDirectoriesSize(node: Node, limit: Long): Long {
        var sum = 0L

        if (node.children.isNotEmpty()) {
            for (child in node.children) {
                sum += getDirectoriesSize(child, limit)
            }
        }

        return if (node.fileSizes > limit) sum else node.fileSizes + sum
    }

    fun buildTree(input: List<String>): Node {
        val root = Node(null, "/", mutableListOf(), 0)
        var currentNode = root

        var i = 1
        while (i < input.size) {
            if (input[i].startsWith("$ cd")) {
                currentNode = getNewDirectory(input[i].split(" ").last(), currentNode, root)
                i++
            } else if (input[i].startsWith("$ ls")) {
                i++
                i = fillDirectory(i, input, currentNode)
            }
        }
        return root
    }

    fun part1(input: List<String>): Long {
        val root = buildTree(input)
        val rootSum = fillTree(root)
        root.fileSizes = rootSum

        return getDirectoriesSize(root, 100000L)
    }

    fun getSmallestDirectorySize(currentNode: Node, target: Long, candidate: Long): Long {
        if (currentNode.children.isEmpty()) {
            return if (currentNode.fileSizes >= target) min(currentNode.fileSizes, candidate) else candidate
        }

        var min = candidate
        for (child in currentNode.children) {
            val size = getSmallestDirectorySize(child, target, candidate)
            min = min(size, min)
        }

        return if (currentNode.fileSizes >= target) min(currentNode.fileSizes, min) else candidate
    }

    fun part2(input: List<String>): Long {
        val root = buildTree(input)
        val rootSum = fillTree(root)
        root.fileSizes = rootSum

        val target = 30000000L - (70000000L - root.fileSizes)
        return getSmallestDirectorySize(root, target, Long.MAX_VALUE)
    }

    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437L)
    check(part2(testInput) == 24933642L)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}

class Node(val parent: Node?, val name: String, var children: MutableList<Node>, var fileSizes: Long)
