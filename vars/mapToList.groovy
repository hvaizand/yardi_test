@NonCPS
def call(depmap) {
    def dlist = []
    for (def entry2 in depmap) {
        dlist.add(entry2.value)
    }
    dlist
}