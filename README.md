
# spring cloud microservice 

using theese enviorment you could walk through the code git pod is better than codespace .
to open the ui open the remote browser which is running on the port: 3000 once the browser is open type ui:3001 it will open the ui

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](http://gitpod.io/#https://github.com/sudeepcv/spring-cloud-microservice-docker)

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/codespaces/new?hide_repo_select=true&ref=main&repo=0000000&machine=premiumLinux&devcontainer_path=.devcontainer%2Fdevcontainer.json&location=WestUs2)


This is a simple spring cloud microservice sandbox .

you could run this project using docker:


docker-compose up -d  

to stop:

docker-compose down 

api:

http://localhost:9191/api/employees/1

Euraka server:

http://localhost:8761/

open remote browser 
http://localhost:3000/

inside remote firefox browser React ui:
http://ui:3001/



Zipkin server:

http://localhost:9411/zipkin