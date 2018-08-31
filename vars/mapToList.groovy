@NonCPS
def call(depmap) {
    def dlist = []
    for (def entry2 in depmap) {
        echo entry2.value
        dlist.add(entry2.value)
    }
    dlist
}