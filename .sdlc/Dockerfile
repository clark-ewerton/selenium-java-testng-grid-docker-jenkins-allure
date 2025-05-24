FROM jenkins/jenkins:lts

USER root

# Instala Maven, Docker, Docker Compose
RUN apt-get update && \
    apt-get install -y sudo maven docker.io docker-compose && \
    apt-get clean && \
    usermod -aG docker jenkins

# Instala plugins essenciais
RUN jenkins-plugin-cli --plugins \
    git \
    workflow-aggregator \
    pipeline-stage-view \
    credentials \
    configuration-as-code \
	htmlpublisher
	
	# Remove qualquer job ou configuração anterior
RUN rm -rf /usr/share/jenkins/ref/jobs/* && \
    rm -rf /usr/share/jenkins/ref/init.groovy.d/*


# Script de inicialização para criar pipeline via SCM
COPY --chown=jenkins:jenkins init-job.groovy /usr/share/jenkins/ref/init.groovy.d/init-job.groovy

USER jenkins
