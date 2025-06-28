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

