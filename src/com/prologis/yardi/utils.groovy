#!/usr/bin/groovy
package com.prologis.yardi

@NonCPS
def mapToList(depmap) {
    def dlist = []
    for (def entry2 in depmap) {
        dlist.add(new java.util.AbstractMap.SimpleImmutableEntry(entry2.key, entry2.value))
    }
    dlist
}

def mapDrive(config){
    withCredentials([usernamePassword(credentialsId: params.RdpUser, passwordVariable: 'RDPPASSWORD', usernameVariable: 'RDPUSERID')]) {
        echo "NET USE ${config.drive_letter} \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
        bat returnStdout: true, script: "NET USE ${config.drive_letter} \"${config.def_path}\" \"${RDPPASSWORD}\" /USER:${RDPUSERID} /y"
    }
}

def unmapDrive(){
    bat returnStdout: true, script: "NET USE * /del /y"
}