def newGit(String repo, String branch = 'main') {
    if (!repo?.trim()) {
        error "Repository name must be provided."
    }

    echo "Checking out ${repo} on branch ${branch}"
    try {
        git url: "https://github.com/karpanitava/${repo}.git", branch: branch
    } catch (e) {
        error "Git checkout failed: ${e.message}"
    }
}

def build() {
    bat 'mvn clean package'
}

def deploy(jobname, ip, context) {
    def warPath = "C:/ProgramData/Jenkins/.jenkins/workspace/${jobname}/target/mywebapp.war"
    def deployUrl = "http://${ip}/manager/text/deploy?path=/${context}&update=true"

    bat """
        curl -u hariadmin:hariadmin -T "${warPath}" "${deployUrl}"
    """
}
def functiontest(job) {
    bat 'cmd java -jar C:/ProgramData/Jenkins/.jenkins/workspace/${job}/test.jar'
}
    
    



