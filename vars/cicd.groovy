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
    bat '''curl -u hariadmin:hariadmin -T "C:/ProgramData/Jenkins/.jenkins/workspace/${jobname}/target/mywebapp.war" "%ip%/manager/text/deploy?path=/%{context%^&update=true"'''
}
