def instance = Jenkins.getInstance()
def jobName = "MeuPipelineSCM"

def existingJob = instance.getItem(jobName)
if (existingJob != null) {
    println "--> Job '${jobName}' já existe. Ignorando criação."
} else {
    println "--> Criando pipeline '${jobName}' via SCM..."

    def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(instance, jobName)
    def scm = new hudson.plugins.git.GitSCM("https://github.com/clark-ewerton/selenium-java-testng-grid-docker-jenkins-allure.git")

    scm.branches = [new hudson.plugins.git.BranchSpec("*/main")]
    scm.userRemoteConfigs = [
        new hudson.plugins.git.UserRemoteConfig("https://github.com/clark-ewerton/selenium-java-testng-grid-docker-jenkins-allure.git", null, null, null)
    ]

    def definition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, ".sdlc/Jenkinsfile")
    definition.setLightweight(true)
    job.setDefinition(definition)

    instance.add(job, jobName)
    instance.save()
}
