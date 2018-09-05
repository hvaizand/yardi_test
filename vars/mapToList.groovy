@NonCPS
def call(depmap) {
    def dlist = []
    for (def entry2 in depmap) {
        debugMessage "mapToList", entry2.value
        dlist.add(entry2.value)
    }
    dlist
}