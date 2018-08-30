@NonCPS
def call(depmap, fileName) {
    def dlist = []
    fileOperations([fileCreateOperation(fileContent: '', fileName: fileName)])
    for (def entry2 in depmap) {
        dlist.add(new java.util.AbstractMap.SimpleImmutableEntry(entry2.key, entry2.value))
        writeFile encoding: 'UTF-8', file: fileName, text: entry2.value
    }
    dlist
}
//, fileCopyOperation(excludes: '', flattenFiles: false, includes: 'pldpkgload.pkglist', targetLocation: '')