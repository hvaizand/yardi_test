def call(config){
    bat returnStdout: true, script: "NET USE * /del /y"
}