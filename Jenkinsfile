node{
  stage('Checkout'){
    git 'https://github.com/msd026/LMS-API'
  }   

  stage('run Maven'){
  script: 'mvn clean package'
  }
}
