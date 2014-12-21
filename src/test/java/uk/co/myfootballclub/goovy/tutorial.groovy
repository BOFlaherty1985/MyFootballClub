def myList = [1, 2, 3, 4, 5]

println myList

assert myList.size == 5

def total = 0
for(i in myList) {
    println total += i
}

assert total == 15

def buildScript = new File("C:\\MyFootballClub\\build.gradle")
buildScript.eachLine {
    println it
}