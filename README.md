# Bonita Recipe App

## How to execute

Run the following commands
* docker run -d --name mysql -p 3306:3306 -v db_data://C/datadocker/mysql -e MYSQL_ROOT_HOST='%' -e MYSQL_ALLOW_EMPTY_PASSWORD='yes' mysql:8
* build Dockerfile
* Execute container